<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品预览</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="产品预览">
</head>

<body>

<s:form action="ShowRouteJourney" namespace="/product" method="post" theme="simple">
  <s:hidden name="day" />
  <s:hidden name="lineNo" />
  <table style="width: 100%">
    <tr>
      <td class="lstidx">天数</td>
      <td class="lstidx" colspan="6">日程描述</td>
      <!--  
		    <td class="lstidx">航班</td>
		    -->
      <td></td>
    </tr>

    <s:if test="scheduleList!=null">
      <s:iterator value="scheduleList" var="sch">
        <tr>
          <td valign="top" rowspan="2">
          <s:property value="%{day}"/></td>
          <td class="data" valign="top" colspan="6">
          <table>
            <tr>
              <td>城市：</td>
              <td><s:if test="lineTrafficList != null">
                <s:iterator value="lineTrafficList" var="tra">
                  <s:if test="#sch.day == #tra.day">
                    <table>
                      <tr>
                        <td>From -</td>
                        <td>
                        <s:property value="%{fromCity}"/></td>
                        <td>&nbsp;&nbsp;To -</td>
                        <td>
                        <s:property value="%{toCity}"/></td>
                        <td>&nbsp;&nbsp;交通：</td>
                        <td>
                        <s:if test="#tra.traffic == 0">
                              船
                        </s:if>
                        <s:elseif test="#tra.traffic == 1">
                              飞机
                        </s:elseif>
                        <s:elseif test="#tra.traffic == 2">
                              火车
                        </s:elseif>
                        <s:elseif test="#tra.traffic == 3">
                              巴士
                        </s:elseif>
                        <s:else>
                              其他
                        </s:else>
                        </td>
                        <s:if test="#tra.traffic == 3">                         
                        <td>&nbsp;&nbsp;行程时间：</td>
                        <td>
                        <s:property value="%{travelTime}"/>
                        </td>
                        </s:if>
                      </tr>
                    </table>
                  </s:if>
                </s:iterator>
              </s:if></td>
            </tr>
            <tr>
              <td colspan="3">
              <table>
                <td>早餐：</td>
                <td>
                <s:property value="%{breakfast}"/></td>
                <td>&nbsp;&nbsp;午餐：</td>
                <td>
                <s:property value="%{lunch}"/></td>
                <td>&nbsp;&nbsp;晚餐：</td>
                <td>
                <s:property value="%{supper}"/></td>
              </table>
              </td>
            </tr>
          </table>
          </td>
        </tr>

        <tr>
          <td colspan="8">
          <s:property value="%{program}"/></td>
        </tr>
        <tr>
          <td></td>
          <td colspan="7">
            <table>
              <tr>
                <td>住宿:</td>
                <td>
                <s:property value="%{quarter}"/>
                </td>
              </tr>
            </table>
          </td>
          
        </tr>
      </s:iterator>
    </s:if>
  </table>

</s:form>
</body>
</html>