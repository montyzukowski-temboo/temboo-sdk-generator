package com.temboo.sdkgen;

import java.io.InputStream;
import java.io.IOException;
import java.net.*;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.stringtemplate.v4.*;

public class SDKGenerator {

  public static String generateCode(URL templatePath, String templateName, InputStream jsonStream) 
      throws IOException, JSONException {
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
          st.add(key, value);
        }
      }
    }

    System.out.println(st.render());
    return st.toString();
  }

  public static void main(String[] args) {
    
  }

}