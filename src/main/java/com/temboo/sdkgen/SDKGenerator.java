package com.temboo.sdkgen;

import java.io.InputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;
import org.json.*;
import org.stringtemplate.v4.*;

public class SDKGenerator {

  public static String generateCode(URL templatePath, String templateName, InputStream jsonStream)
      throws Exception {
    JSONObject json = new JSONObject(new JSONTokener(jsonStream));
    STGroup stg = new STGroupFile(templatePath, "UTF-8", '$', '$');
    ST st = stg.getInstanceOf(templateName);
    Map<String, Object> attrs = st.getAttributes();
    for (String key:attrs.keySet()) {
      if (json.has(key)) {
        Object value = json.get(key);
        if (value instanceof String) {
          if (((String) value).length() == 0) {
            value = null;
          }
        }
        if (value != null) {
          //System.out.println(key + " " + value.getClass().toString() + " " + value.toString());
          st.add(key, gimmeValue(value));
        }
      }
    }

    System.out.println(st.render());
    return st.toString();
  }

  public static Object gimmeValue(Object value) throws Exception {
    if (value instanceof JSONArray) {
      JSONArray jv = (JSONArray) value;
      int len = jv.length();
      String[] ar = new String[len];
      for (int i=0; i< len; i++) {
        ar[i] = jv.getString(i);
      }
      return ar;
    }
    if (value instanceof JSONObject) {
      HashMap<String, Object> map = new HashMap<String,Object>();
      JSONObject jo = (JSONObject) value;
      Iterator keys = jo.keys();
      while (keys.hasNext()) {
        String key = (String) keys.next();
        map.put(key, gimmeValue(jo.get(key)));
      }
      return map;
    }
    if (value instanceof String) {
      String str = (String) value;
      if (str.length()==0) {
        return null;
      }
      return value;
    }
    return null;
  }

  public static void main(String[] args) {
    
  }

}