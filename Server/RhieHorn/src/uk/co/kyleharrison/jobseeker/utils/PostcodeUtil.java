package uk.co.kyleharrison.jobseeker.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;


public class PostcodeUtil {

	public static void main(String [] argruments){

		String requestURL ="http://api.indeed.com/ads/apisearch?publisher=8188725749639977&q=java&l=dundee&sort=&format=json&radius=&st=&jt=&start=&limit=&fromage=&filter=&latlong=1&co=u&chnl=&userip=1.2.3.4&useragent=Mozilla/%2F4.0%28Firefox%29&v=2";
		
		String targetURL = "http://maps.googleapis.com/maps/api/geocode/json";
		String urlParameters = "";
		String jsonString ="";
		
		try {
		    URL url = new URL(requestURL);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line.toString());
		        jsonString += line;
		    }
		    reader.close();

		} catch (MalformedURLException e) {
		    // ...
		} catch (IOException e) {
		    // ...
		}

		JsonParser jsp = JsonParser.getInstance();
		
		//jsp.parseResults(jsonString);
		
		/*
		 JSONObject json = (JSONObject) JsonSerializer.toJSON( jsonString );        
	        JSONArray pilot = json.getJSONArray("results");
	        
	        
	        JSONObject obj = (JSONObject) pilot.getJSONObject(0);
	        System.out.println("Results "+ obj.get("jobtitle"));
*/
		

		
	 
	 /*
	 JSONObject res = new JSONObject(city);
						JSONArray mapsData = res.getJSONArray("results");
						
					
						JSONObject test1 = mapsData.getJSONObject(0);
						JSONArray jArr = test1.getJSONArray("address_components");
						for(int i=0;i<jArr.length();i++){
							if (jArr.getJSONObject(i).getJSONArray("types").getString(0).equals("locality")){
								city = jArr.getJSONObject(i).getString("long_name");
							}
						}
						*/
	
	}
	
	  public static String excutePost(String targetURL, String urlParameters)
	  {
	    URL url;
	    HttpURLConnection connection = null;  
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/x-www-form-urlencoded");
				
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	      connection.setRequestProperty("Content-Language", "en-US");  
				
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.writeBytes (urlParameters);
	      wr.flush ();
	      wr.close ();

	      //Get Response	
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();
	      return response.toString();

	    } catch (Exception e) {

	      e.printStackTrace();
	      return null;

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	  }
	
}
