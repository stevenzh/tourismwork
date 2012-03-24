<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>同业报价上传</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="同业报价上传">
</head>

<body>
<script type="text/javascript">

function del(param)
{
  var fm = document.priceUpload;
  fm.fileId.value = param;
  fm.action="<s:url action='delFileItem' namespace='/operator'/>";
  fm.submit();
}

</script>
<s:iterator value="items">
<div><s:property value="fileName"/> 上传时间 <s:date name="created" format="yyyy-MM-dd HH:mm:ss[E]"/>
 <a onclick="javascript:del('<s:property value='fileId'/>')" href="#">删除</a></div>
</s:iterator>

<br>
<s:form action="priceUpload" namespace="/operator" method="POST" enctype="multipart/form-data">
  <s:hidden name="fileId"></s:hidden>
	<s:file name="upload" label="上传文件" />
	<s:textfield name="caption" label="说明" />
	<s:submit value="上传文件" />
</s:form>

</body>
</html>

