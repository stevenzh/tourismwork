<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
     
  </head>  
    
  <body>  
        <s:form action="PictureAction.action" enctype="multipart/form-data">  
            <s:file name="file"></s:file>  
            <s:submit></s:submit>  
        </s:form>  
  </body>  
</html>  