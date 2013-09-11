package com.model;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.*;
import java.io.*;
public class SearchData 
{
	private String SearchName=null;
	private String JsonDataSting="搜尋不到結果";
	String path;
	@SuppressWarnings("finally")
	public String GetData(String name)
	{

	try {
			
			SearchName= name;
			path= "http://tw.kkbox.com/search.php?word="+java.net.URLEncoder.encode(SearchName,"UTF-8")+"&search=artist&search_lang=";
			//path= "http://tw.kkbox.com/search.php?word="+singerName+"&search=artist&search_lang=";
			//中文要記得先編碼後才能給URL Class
			System.out.println(path);
			URL url = new URL(path);
	   		Document doc = Jsoup.parse(url, 3000);//選出主體並設定timeout
	   		Elements result = doc.select("li[class=artists-column]");//找出搜尋結果的nodes
	   		Element firstResult = result.get(0);//選出第一個node
	   		Elements SerchUrl = firstResult.select("h4");//找出它的url html
	   		String URLStings = SerchUrl.select("a").attr("href");//分段並找出網址
	   		String SingerName = SerchUrl.select("a").attr("title");
	   		String GoString = "http://tw.kkbox.com"+URLStings;//搜尋歌手的網址
	   		
	   		URL singerUrl =new URL(GoString);
	   		System.out.println(GoString); //歌手網頁
	   		Document singerPage= Jsoup.parse(singerUrl, 3000);//選出主體並設定timeout
	   		//Elements songs= singerPage.select("ol[class=songs]");//找出歌手清單
	   	  //Elements  hot1to10 = songs.get(0).select("a[itemprop=url]");//選出前10
	   	  //Elements  hot11to20 = songs.get(1).select("a[itemprop=url]");//選出11到20
	   		Elements albumsTag = singerPage.select("[class=pagination pagination-centered]");//找出專輯
	   		//System.out.println(albumsTag.html());
	   		Elements eachAlbum =null;
	   		if(albumsTag.size()!=0)
	   			eachAlbum = albumsTag.get(0).select("[data-to]");//選取有title資訊的a Tag
	   		
	   		//System.out.println(eachAlbum.html());
	   		//Element singerIdTag = singerPage.select("a[href=#photos]").get(0);
	   		//String [] singerIdUrl = singerIdTag.attr("data-src").split("artist_id=");
	   		String [] singerIdUrl = GoString.split("artist/");
	   		String singerID = singerIdUrl[1].split("-index")[0];
	   		JSONArray albumNames = new JSONArray();//放入專輯的Json Array
	   		
	   		//System.out.println(eachAlbum.size());
	   		
	   		int albumNumber = 0;
	   		int findAlbumNum = 1;
	   		if(eachAlbum!=null)
	   			findAlbumNum = eachAlbum.size()+1;		
	   		for(int i=0;i<findAlbumNum;i++)
	   		{
	   			
	   			String eachSongListUrl ="http://tw.kkbox.com/ajax/get_artist_extend_data.php?type=album&artist_id="+
	   					singerID+"&page="+(i+1);
	   			
	   			URL eachSongListPage =new URL(eachSongListUrl);
	   			Document songListDoc = Jsoup.parse(eachSongListPage, 3000);
	   			Elements songListLink = songListDoc.select("h3").select("a");
	   			//System.out.println(songListLink.size());
	   			for(int j=0;j<songListLink.size();j++)
	   			{
	   				albumNumber++;
	   				//System.out.println(songListLink.get(j).attr("title"));
	   				albumNames.put(songListLink.get(j).attr("title"));
	   			}
	   		}
	   		
	   		
	   		
	   		/*JSONArray albums = new JSONArray();//json array
	   		//json array
	   		for(int i=0;i<eachAlbum.size();i++)//將所有專輯名稱加入array
	   		{
	   			Map<String, Object> albumsMap = new HashMap<String, Object>();
	   			JSONArray SongsName = new JSONArray();
	   			String SongString = "http://tw.kkbox.com"+eachAlbum.get(i).attr("href");//搜尋歌手的網址
	   			System.out.println(SongString);
	   			Document songPage= Jsoup.parse(new URL(SongString), 3000);//選出主體並設定timeout
		   		Elements songList= songPage.select("table[class=tablesorter]");//找出歌table
		   		Elements eachSong = songList.select("td[class=song-name]");
		   		System.out.println(eachSong.size());
		   		Elements eachSongName = eachSong.select("a");
		   		for(int s=0;s<eachSongName.size();s++)
		   			SongsName.put(eachSongName.get(s).ownText());
		   		System.out.println(eachSongName.get(0).ownText());
	   			albumsMap.put("songList",SongsName);
	   			albumsMap.put("albumName",eachAlbum.get(i).attr("title"));
	   			albums.put(albumsMap);
	   		}
	   		*/
	   		
	   		
	   		
	   			
	   		Map<String, Object> map = new HashMap<String, Object>();//令用map物件加入要包成json的資料
	   		map.put("name", SingerName);
	   		map.put("albumSize",albumNumber);
	   		map.put("albums", albumNames);
	  
	   		JSONObject jsonObjectJacky = new JSONObject(map);//將map物件包成json物件
	   		
	   		System.out.println(jsonObjectJacky.toString());
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
