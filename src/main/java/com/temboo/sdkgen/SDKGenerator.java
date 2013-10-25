package com.temboo.sdkgen;

import java.io.InputStream;
import java.io.IOException;
import java.net.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.stringtemplate.v4.*;

public class SDKGenerator {

  public static String generateCode(URL templatePath, InputStream jsonStream) 
      throws IOException, JSONException {
    JSONObject json = new JSONObject(new JSONTokener(jsonStream));
    STGroup test = new STGroupFile(templatePath, "UTF-8", '<', '>');
    System.out.println(json.toString());
    System.out.println(test.toString());
    return json.toString();
  }

  public static void main(String[] args) {
    
  }

}