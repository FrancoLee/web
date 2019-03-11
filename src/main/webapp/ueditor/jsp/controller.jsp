<%@ page language="java" contentType="text/html; charset=UTF-8"
		 import="com.baidu.ueditor.ActionEnter"
		 pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%

	request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	//String saveRootPath="你的实际地址";//(String)session.getAttribute("infoPath");//
	String rootPath = System.getProperty("webRoot");
//	String rootPath = application.getRealPath( "/" );
	out.write( new ActionEnter( request, rootPath ).exec() );

%>