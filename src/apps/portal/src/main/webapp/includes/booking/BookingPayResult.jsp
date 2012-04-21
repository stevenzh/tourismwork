<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>填写旅游订单内容</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
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
        <td width="171" height="30" align="center"><a href="http://localhost/MetroshClub/MetroshClubPage.asp?rec_no=''" target="_blank">活动专区</a></td>
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
    <!-- 订单信息 -->
    <table style="width: 100%" align="center" border="0" cellspacing="1">
      <tr>
        <td colspan="4" align="center">预订表</td>
      </tr>
      <tr>
        <td colspan="4" align="center">状态：
	        <STRONG><s:if test='booking.confirmStatus == "1"'>已审核</s:if>
	        <s:else>未确认</s:else>
	        <s:if test='booking.delkey == "Y"'>已取消</s:if></STRONG></td>
      </tr>
      <tr>
        <td width="100">旅游线路:</td>
        <td colspan="3"><s:property value="booking.line.lineName" /></td>
      </tr>
      <tr>
        <td>团号:</td>
        <td colspan="3"><s:property value="booking.tourNo" /></td>
      </tr>
      <tr>
        <td>出发时间:</td>
        <td><s:date name="booking.outDate" format="yyyy-MM-dd" /></td>
        <td width="100">预订操作员</td>
        <td><s:property value="booking.reserve"/>&nbsp;</td>
      </tr>
      <tr>
        <td>订单人数:</td>
        <td><s:property value="booking.pax" />人</td>
        <td>应付团款</td>
        <td><s:property value="booking.dbamt" />&nbsp;</td>
      </tr>
      <tr>
        <td>订单备注</td>
        <td colspan="3"><s:property value="booking.remarks" />&nbsp;</td>
      </tr>
    </table>
    <br />
    
    <!-- 游客信息 -->
    <table style="width: 100%" align="center" border="0" cellspacing="1">
      <tr>
        <td class="lstidx">No.</td>
        <td class="lstidx">姓名</td>
        <td class="lstidx">性别</td>
        <td class="lstidx">证件号码/出生日期</td>
        <td class="lstidx">价格</td>
        <td class="lstidx">备注</td>
      </tr>
  
      <s:iterator value="booking.customerList" status="rowcounter">
        <tr>
          <!-- 一个客人的信息开始 序号 -->
          <td><s:property value="#rowcounter.count" /></td>
          <!-- 姓名 -->
          <td><s:property value="userName"/></td>
  
          <!-- 身份证号码 -->
          <td><s:property value="sex"/></td>
  
          <!-- 性别 -->
          <td><s:property value="idCard"/></td>
  
          <td></td>
          <td></td>
          <!-- 一个客人的信息结束 -->
        </tr>
      </s:iterator>

    </table>

    <br>

    <!-- 付款记录 -->
    <table style="width: 100%" align="center" border="0" cellspacing="1">
      <tr>
        <td class="lstidx">支付时间</td>
        <td class="lstidx">支付金额（元）</td>
        <td class="lstidx">支付结果</td>
        <td class="lstidx">支付订单号</td>
      </tr>
  
      <s:iterator value="payments">
        <tr>
          <td><s:textfield name="userName" size="8" maxlength="10" theme="simple">
          </s:textfield></td>
          <td><s:textfield name="idCard" size="18" maxlength="18" theme="simple">
          </s:textfield></td>
          <td>
          <s:select name="sex"
                    list="sexList"
                    listKey="label"
                    listValue="value"
                    theme="simple">
          </s:select>
          </td>
        </tr>
      </s:iterator>
    </table>

    </td>
  </tr>
</table>

</body>
</html>

