package com.servlet;

import com.model.GetLyrics;
import com.model.GetSongList;
import com.model.SearchData;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
public class WidgetServlet extends HttpServlet
{
	public void doPost (HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		//String SingerName= new String(request.getParameter("SearchData").getBytes("ISO-8859-1"), "UTF-8");
		PrintWriter out = response.getWriter();
		if(type.equals("singer"))
		{
			//out.write("fsdfsdf");
		String SingerName=request.getParameter("SearchName");
		SearchData SD = new SearchData();
		out.write(SD.GetData(SingerName));
		}
		else if(type.equals("album"))
		{
			
			String SongName=request.getParameter("songName");
			String SingerName=request.getParameter("singerName");
			GetSongList SL = new GetSongList(SongName,SingerName);
			out.write(SL.GetData());
		}
		else if(type.equals("lyrics"))
		{
			String url=request.getParameter("url");
			String SongName=request.getParameter("SongName");
			GetLyrics SD = new GetLyrics(url,SongName);
			out.write(SD.GetData());
		}
		out.close();  
	}
}