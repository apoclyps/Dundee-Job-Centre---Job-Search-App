package uk.co.kyleharrison.jobseeker.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import uk.co.kyleharrison.jobseeker.model.indeedJob;

public class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
    JSONObject json = readJsonFromUrl("http://api.indeed.com/ads/apisearch?publisher=8188725749639977&q=java&l=dundee&sort=&format=json&radius=&st=&jt=&start=&limit=&fromage=&filter=&latlong=1&co=uk&chnl=&userip=1.2.3.4&useragent=Mozilla/%2F4.0%28Firefox%29&v=2");
   // System.out.println(json.toString());
    org.json.JSONArray jsa = json.getJSONArray("results");
    
    System.out.println(jsa.get(0).toString());
      
    indeedJob bean = new ObjectMapper().readValue(jsa.get(1).toString(), indeedJob.class);
    System.out.println(bean.getJobTitle());
  }
}