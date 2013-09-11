<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
   request.setCharacterEncoding("UTF-8");
   String actionNow = request.getParameter("action");
   String inputType = request.getParameter("input");
   session.setAttribute("action",actionNow);
   if(inputType.equals("applicationID"))
	{
		String setSessionBuffer = request.getParameter("applicationID_data");
		
		session.setAttribute(inputType,setSessionBuffer);
		out.println("true");
	}else if(inputType.equals("selectBrick")){
		String setSessionBuffer = request.getParameter("selectBrick_data");

		session.setAttribute(inputType,setSessionBuffer);
		out.println("true");
	}
%>