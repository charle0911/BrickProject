package com.servlet;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*; 
 
import com.model.AccountService;


public class AccountServiceServlet extends HttpServlet{
	public void doGet (HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	{
		doPost(request,response);
	}
	public void doPost (HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
/* for login and register*/
		String calledFor = request.getParameter("calledFor");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		AccountService service = new AccountService();
		String Data=null;
/*----------------------------- for register ----------------------------*/
		if(calledFor.equals("register"))
		{
			Data = service.setRegisterData(account, password);
		}
/*----------------------------- for login ----------------------------*/
		else if (calledFor.equals("login"))
		{
			Data = service.accountAuthentication(account, password);
			/*----------------------------- �n�J���\���main���� ----------------------------*/			
			if(Data.equals("true"))
			{
				HttpSession session =request.getSession();
				session.setAttribute("user_id", account);
			}
			
		}
		out.write(Data);
	}
}
