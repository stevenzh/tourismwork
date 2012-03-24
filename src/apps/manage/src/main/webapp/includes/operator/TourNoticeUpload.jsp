<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传出团通知书</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="上传出团通知书">
</head>

<body>
<script type="text/javascript">

 function del(param)
 {
     var fm=document.TourNoticeUploadActionDelete;
     document.getElementById("fileId1").value=param;
     fm.submit();
 }

</script>

<s:form action="TourNoticeUploadActionDelete" namespace="/operator" method="POST" enctype="multipart/form-data">
   <s:if test="null!=tourNoticeFile">
      <s:hidden id="fileId1" name="fileId"></s:hidden>
      你所上传的文件为：<br>
      &nbsp;&nbsp;&nbsp;&nbsp;文件名：<s:property value="tourNoticeFile.fileName"/> 
      <br>
      &nbsp;&nbsp;&nbsp;&nbsp;上传时间： <s:date name="tourNoticeFile.created" format="yyyy-MM-dd HH:mm:ss[E]"/>
      &nbsp;&nbsp;<a onclick="javascript:del('<s:property value='tourNoticeFile.fileId'/>')" href="#">删除</a>
   </s:if>
</s:form>

<br>
<s:form action="TourNoticeUploadAction" namespace="/operator" method="POST" enctype="multipart/form-data">
   <s:if test="null!=tourNoticeFile">
      
   </s:if>
    
    <s:textfield name="title" label="文件标题" />
	<s:file name="upload" label="上传文件" />
	<s:submit value="确　定" />
</s:form>

</body>
</html>

