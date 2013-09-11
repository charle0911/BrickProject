package com.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


import org.json.JSONArray;
import org.json.JSONObject;

public class GetYoutubeData {

	private String url;
	public GetYoutubeData()
	{}
	public GetYoutubeData(String SingerName ,String SongName)
	{
		this.url = "http://gdata.youtube.com/feeds/api/videos?q="+SingerName+"%20"+SongName+"&max-results=1&v=2&alt=jsonc";
	}
	@SuppressWarnings("finally")
	public String  GetData()
	{
		String YouTubeJson=" ";
		String youTubeID=null;
		URL u = null;
		InputStream in = null;
		InputStreamReader r = null;
		BufferedReader br = null;
		String message = null;

		try {
		   u = new URL(url);
		   in = u.openStream();
		   r = new InputStreamReader(in, "UTF-8");
		   br = new BufferedReader(r);
		   //String addMessage = null;
		   while ((message = br.readLine()) != null) {
			   YouTubeJson+=message;
			   //System.out.println(message);
		      
		   }
		   
		  JSONObject youTubeJsonObject= new JSONObject(YouTubeJson);
		  JSONObject jsonArrayData = youTubeJsonObject.getJSONObject("data");
		  JSONArray jsonArrayItems = jsonArrayData.getJSONArray("items");
		  JSONObject jsonArrayFirstItem = jsonArrayItems.getJSONObject(0);
		  youTubeID = jsonArrayFirstItem.getString("id");

		} catch (Exception e) {
		   e.getStackTrace();
		   System.out.println(e.getMessage());
		} finally {
		   try {
		      u = null;
		      in.close();
		      r.close();
		      br.close();
		   } catch (Exception e) {

		   }
		   return youTubeID;
		   //return YouTubeJson;
	}
	}	
}
