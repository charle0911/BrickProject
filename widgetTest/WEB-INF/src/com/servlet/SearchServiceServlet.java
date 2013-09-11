package com.servlet;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*; 

import com.model.SearchService;

public class SearchServiceServlet extends HttpServlet {

/*calledFor:輸入來源; searchData:搜尋的關鍵字; user_id:登入後查詢application; jsonString: 回應給main的jsonArray*/
	public void doPost (HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException,NullPointerException
	{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String calledFor 	 = request.getParameter("calledFor");
		String searchKey = request.getParameter("searchKey");
		
		PrintWriter out = response.getWriter();		

		SearchService service = new SearchService();
		String Data=null;
		
/*---------- SearchKey = {Brick_type ,Brick_name ,application_type ,application_name} ----------*/		
		if(calledFor.equals("brickSearch")|| calledFor.equals("applicationSearch") )
		{
			searchKey = searchKey.toLowerCase();
			Data = service.getSearchData(calledFor, searchKey);
			out.print(Data);
		}
		
/*------------------------------ SearchKey = resourceType ------------------------------*/
		else if (calledFor.equals("resourceProvider"))
		{
			Data = service.getKernalResourceProvider(searchKey);
			out.print(Data);
		}
/*------------------------------ Application for main page -------------------------------*/
/*------------------------------ data contains Id and name -------------------------------*/		
		else if(calledFor.equals("mainApplication"))
		{
			Data = service.getApplicationIdAndName(searchKey);
			out.print(Data);
		}
/*------------------------------ SearchKey = application_id ------------------------------*/
		else if(calledFor.equals("designerGetApplication")||calledFor.equals("preview"))
		{
			Data = service.getApplicationData(searchKey);
			out.println(Data);
		}
/*------------------------------ SearchKey = applicationJsonString ------------------------------*/
		else if(calledFor.equals("updateApplication"))
		{
			service.updateApplicationData(searchKey);
		}
/*------------------------------ SearchKey = applicationId ------------------------------*/
		else if(calledFor.equals("deleteApplication"))
		{
			service.deleteApplicationToDatabase(searchKey);
		}
/*------------------------------ SearchKey = brick_idJSONArray ------------------------------*/
		else if(calledFor.equals("createCompositeBrick"))
		{
			Data = service.getBrickData(searchKey);
			out.println(Data);
		}
		else if(calledFor.equals("saveCompositeBrick"))
		{
			HttpSession session =request.getSession();
			String userId = (String) session.getAttribute("user_id");
			service.insertApplicationToDatabase(searchKey, userId);
		}
	}
}
