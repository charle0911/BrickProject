package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.GetYoutubeData;
import com.model.SearchData;

public class YouTubeServlet extends HttpServlet{
	public void doPost (HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String singerName = request.getParameter("singerName");
		String songName = request.getParameter("songName");
		String newSingerName =singerName.replace(" ","%20");
		String newSongName =songName.replace(" ","%20");
		PrintWriter out = response.getWriter();
		GetYoutubeData YouTube = new GetYoutubeData(newSingerName,newSongName);
		out.write(YouTube.GetData());
		out.close(); 
	}

}
