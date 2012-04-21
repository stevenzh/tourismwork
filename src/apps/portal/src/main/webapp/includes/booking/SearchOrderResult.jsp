<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head/>
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

      <!-- 旅游工具箱 -->
      <tr>
        <td height="3"></td>
      </tr>
      <tr>
        <td align="center" width="171" height="30">旅游工具箱</td>
      </tr>
      <tr>
        <td>
        <table style="width: 100%" align="center" border="0">
          <!-- 签证办理办法 -->
          <tr>
            <td valign="baseline" bgcolor="#ffffff" height="20">&nbsp;&nbsp;<a href="<s:url action='showVisaHelp' namespace='/'/>" target="_blank">签证办理办法</a></td>
          </tr>
          <!-- 旅游须知 -->
          <tr>
            <td valign="baseline" bgcolor="#ffffff" height="20">&nbsp;&nbsp;<a href="/html/freetravel_info_link.htm" target="_blank">旅游须知</a></td>
          </tr>
          <!-- 订单查询 -->
          <tr>
            <td valign="baseline" bgcolor="#ffffff" height="20">&nbsp;&nbsp;<a href="<s:url action='searchOrder' namespace='/'/>" target="_blank">订单查询</a></td>
          </tr>
        </table>
        </td>
      </tr>

      <!-- 友情链接 -->
      <tr>
        <td height="3"></td>
      </tr>
      <tr>
        <td align="center" width="171" height="30">友情链接</td>
      </tr>
      <tr>
        <td>
        <table style="width: 100%" align="center" border="0">
          <tr>
            <td height="8"></td>
          </tr>
          <!-- 网银在线 -->
          <tr>
            <td align="center"><a href="http://www.chinabank.com.cn" target="_blank"><img src="/images/chinabank.gif" width="120" height="60" border="0" /></a></td>
          </tr>
          <!-- 中南百草原 -->
          <tr>
            <td align="center"><a href="http://www.znc.cn" target="_blank"><img src="http://www.znc.cn/images/znlogo.gif" border="0" /></a></td>
          </tr>
        </table>
        </td>
      </tr>
    </table>

    <!-- 左侧结束 -->
    </td>
    <td valign="top" width="584">
      <table style="width: 100%" align="center" border="1" cellspacing="1">
        <tr>
          <td colspan="8">查询结果</td>
        </tr>
        <tr align="center">
          <td>订单号</td>
          <td>旅游线路</td>
          <td>出发日期</td>
          <td>人数</td>
          <td>应付金额</td>
          <td>预订时间</td>
          <td>订单状态</td>
        </tr>
        <s:iterator value="bookings">
        <tr>
          <td><s:property value="bookingNo"/>&nbsp;</td>
          <td><s:property value="lineName"/>&nbsp;</td>
          <td><s:date name="outDate" format="yyyy-MM-dd"/>&nbsp;</td>
          <td><s:property value="pax"/>&nbsp;</td>
          <td><s:property value="dbamt"/>&nbsp;</td>
          <td><s:date name="reserveDate" format="MM-dd HH:ss"/>&nbsp;</td>
          <td>
          <STRONG>
          <s:if test='book.confirmStatus == "1"'>以审核</s:if>
          <s:else>未确认</s:else>
          <s:if test='book.delkey == "Y"'>已取消</s:if></STRONG>
          <a href="<s:url action='showBookingDetail' namespace='/accounts' includeParams='none'/>?reserveNo=<s:property value="reserveNo"/>">详细</a><br>
          </td>
        </tr>
        </s:iterator>
    
      </table>
      </td>
   </tr>
</table>

</body>
</html>

