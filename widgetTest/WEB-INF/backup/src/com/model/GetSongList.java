package com.model;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetSongList {

	private String SongName;
	private String SingerName;
	private String JsonDataSting;
	public GetSongList()
	{}
	public GetSongList(String SongName,String SingerName)
	{
		this.SongName = SongName;
		this.SingerName = SingerName;
	}
	public String GetData()
	{
		try
		{
			String path = "http://tw.kkbox.com/search.php?word="+java.net.URLEncoder.encode(SongName,"UTF-8")+"%20"+
					java.net.URLEncoder.encode(SingerName,"UTF-8")+"&search=album&search_lang=";
			URL url = new URL(path);
	   		Document doc = Jsoup.parse(url, 3000);//選出主體並設定timeout
	   		Elements listTag = doc.select("a[class=url fn]");
	   		String nextPath = "http://tw.kkbox.com"+listTag.attr("href");
			System.out.println(nextPath);
			
			
			JSONArray SongsName = new JSONArray();
			
			URL nextUrl = new URL(nextPath);
			Document songPage= Jsoup.parse(nextUrl, 3000);//選出主體並設定timeout
	   		Elements songList= songPage.select("table[class=tablesorter]");//找出歌table
	   		Elements eachSong = songList.select("td[class=song-name]");
	   		System.out.println(eachSong.size());
	   		Elements eachSongName = eachSong.select("a");
	   		for(int s=0;s<eachSongName.size();s++)
	   			SongsName.put(eachSongName.get(s).ownText());
	   		
	   		Map<String, Object> map = new HashMap<String, Object>();
			map.put("songList",SongsName );
			map.put("albumUrl", nextPath);
			JSONObject jsonObjectJacky = new JSONObject(map);//將map物件包成json物件
	   		
	   		//System.out.println(jsonObjectJacky.toString());
	   		JsonDataSting = jsonObjectJacky.toString();//傳送json 字串
			
		}
		catch (Exception e) 
		{
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		finally{
		
			return JsonDataSting;//傳送json 字串
			}
	}
}
