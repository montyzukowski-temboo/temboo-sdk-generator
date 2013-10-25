package com.temboo.sdkgen;

import java.io.InputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SDKGenerator {

  public static String generateCode(String templatePath, InputStream jsonStream) 
      throws IOException, JSONException {
    JSONObject json = new JSONObject(new JSONTokener(jsonStream));
    System.out.println(templatePath);
    System.out.println(json.toString());
    return json.toString();
  }

  public static void main(String[] args) {
    String templatePath=args[1];
    String jsonPath=args[2];
    System.out.println(templatePath);
    System.out.println(jsonPath);
  }

}