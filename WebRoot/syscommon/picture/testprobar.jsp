<%@ page language="java" import="java.util.*" pageEncoding="GBK" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String delToUrl=(String)session.getAttribute("delToUrl");
System.out.println("delToUrl"+delToUrl);
   %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <!--����JS -->
     <script type="text/javascript" src="<%=path%>/js/picture.js"></script>
  </head>
  <!--����ɾ��JS,IE�İ�ȫ���� -->
  <body onload="javascript:del('<%=delToUrl %>');">
  </body>
</html>

