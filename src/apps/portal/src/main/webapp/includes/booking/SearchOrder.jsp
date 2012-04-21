<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<table width="760" border="0" align="center" cellspacing="1">
  <tr>
    <td valign="top" width="172" height="25">

    <!-- 左侧栏目 -->
    <table width="167" border="0" align="center">

      <!-- 活动专区 -->
      <tr>
        <td height="3"></td>
      </tr>
      <tr>
        <td width="171" height="30" align="center"><a href="http://localhost:8080/MetroshClub/MetroshClubPage.asp?rec_no=''" target="_blank">活动专区</a></td>
      </tr>
      <tr>
        <td>
        <table width="167" align="center" border="0">
        <s:if test="activityAds.isEmpty()">
          <tr>
            <td height="20" valign="baseline" bgcolor="#FFFFFF" align="center">最近未举行活动</td>
          </tr>
        </s:if>
        
        <s:iterator value="activityAds">
        <s:if test="fileExtend == 'swf'">
        <tr>
          <td width="166" height="20" align="center" valign="baseline" bgcolor="#FFFFFF">
            <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
                    codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0"
                    width="160" height="100">
              <param name="movie"
                     value="<%=request.getContextPath() %>/<s:property value="imageURL" />">
              <param name="quality" value="high">
              <embed src="<%=request.getContextPath() %>/<s:property value="imageURL" />"
                     quality="high"
                     pluginspage="http://www.macromedia.com/go/getflashplayer"
                     type="application/x-shockwave-flash" width="160" height="100"></embed>
            </object>
          </td>
        </tr>
        </s:if>
        <s:else>
          <tr>
            <td valign="baseline" align="center" bgcolor="#ffffff" height="20">
            <a target="_bank" href="<s:property value="linkURL" />">
            <img src="<%=request.getContextPath() %>/<s:property value="imageURL" />"
                 width="160" border="0" >
            </a>
            </td>
          </tr>
        </s:else>
        </s:iterator>

        </table>
        </td>
      </tr>
    </table>

    <!-- 左侧结束 -->
    </td>
    <td valign="top" width="584">
      <s:form namespace="/" action="searchOrderResult" method="post" theme="simple">
      <table style="width: 100%" border="0" align="center" cellpadding="3">
        <tr>
          <td width="30%"><STRONG>订单搜索</STRONG></td>
          <td width="70%" align="right">&nbsp;</td>
        </tr>
        <tr>
          <td align="right">订单号：&nbsp;</td>
          <td><s:textfield name="kenOrderNo"></s:textfield> </td>
        </tr>
        <tr>
          <td align="right">游客姓名：&nbsp;</td>
          <td><s:textfield name="kenTourist"></s:textfield> </td>
        </tr>
        <tr>
          <td align="right">订单金额：&nbsp;</td>
          <td><s:textfield name="kenAccount"></s:textfield> </td>
        </tr>
        <tr>
          <td align="center" colspan="2"><s:submit value="快速查找"></s:submit>&nbsp;</td>
        </tr>
      </table>
      </s:form>
    </td>
  </tr>
</table>
</body>
</html>

