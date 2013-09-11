package com.model;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetLyrics {

	private String  LyricsSting;
	private String path;
	private String SongName;
	public GetLyrics()
	{}
	public GetLyrics(String path,String SongName)
	{
		this.path = path;
		this.SongName = SongName;
	}
	public String GetData()
	{
		
		try
		{
			
			URL url = new URL(path);
	   		Document doc = Jsoup.parse(url, 3000);//選出主體並設定timeout
	   		Elements listTag = doc.select("tr[itemprop=track]");
	   		Elements eachSong = listTag.select("td[class=song-name]");
	   		//System.out.println(eachSong.size());
	   		//System.out.println(eachSong.html());
	   		Elements eachSongName = eachSong.select("a");
	   		//System.out.println(eachSongName.html());
	   		String lyricsPath=null;
	   		for(int s=0;s<eachSongName.size();s++)
	   		{
	   			if(eachSongName.get(s).ownText().equals(SongName))
	   			{
	   				//System.out.println(eachSongName.get(s).ownText());
	   				Element songParent= eachSongName.get(s).parent();
	   				System.out.println(songParent.html());
	   				//Elements parentSibling = songParent.select("i[class=icon icon-b-lyrics]");
	   				//System.out.println(parentSibling.size());
	   				String findUrl = songParent.select("a").attr("href");
	   				System.out.println(findUrl);
	   				lyricsPath = "http://www.kkbox.com"+findUrl;
	   				System.out.println(lyricsPath);
	   				break;
	   			}
	   			
	   		}
			
	   		URL lyricsUrl = new URL(lyricsPath);
	   		Document LyricsDoc = Jsoup.parse(lyricsUrl, 3000);//選出主體並設定timeout
	   		String content = LyricsDoc.select("div[class=content]").get(1).html();
	   		//System.out.println(lyricsUrl);
	   		
	   		
	   		LyricsSting = content;//傳送 字串
			
		}
		catch (Exception e) 
		{
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		finally{
		
			return LyricsSting ;//傳送json 字串
			}
	
	}
}
