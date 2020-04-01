<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品行程</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="产品行程">
</head>

<body>
<script type="text/javascript">
<!--//

function SubmitForm(param, target)
{
  var fm = document.RouteScheduleAdd;
  fm.day.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='RouteScheduleDelete' namespace='/product'/>"
  }
  fm.submit();
}

function addCity(obj)
{
	var fm=document.RouteScheduleAdd;
	fm.day.value=obj;
	fm.action ="<s:url action='RouteScheduleAddCity' namespace='/product'/>";
	fm.submit();
}

//-->
</script>

<s:form action="RouteScheduleAdd" namespace="/product" method="post" theme="simple">
  <s:hidden name="day" />
  <s:hidden name="lineNo" />
  <table style="width: 100%">
    <tr>
      <td class="lstidx">天数</td>
      <td class="lstidx" colspan="6">日程描述</td>
      <td class="lstidx">操作</td>
    </tr>

    <s:if test="scheduleList!=null">
    <s:iterator value="scheduleList" var="sch">
      <tr>
        <td valign="top" rowspan="2">
          <s:textfield value="%{day}" size="5" maxlength="2" readonly="true"></s:textfield>
        </td>
        <td class="data" valign="top" colspan="6">
        <table>
          <tr>
            <td>城市：</td>
            <td>
            <s:if test="lineTrafficList != null">
            <s:iterator value="lineTrafficList" var="tra">
              <s:if test="#sch.day == #tra.day">
                <s:hidden name="lineTrafficList(%{step}).day"></s:hidden>
                <s:hidden name="lineTrafficList(%{step}).step"></s:hidden>
                <s:hidden name="lineTrafficList(%{step}).id"></s:hidden>
                <table>
                  <tr>
                    <td>From -</td>
                    <td>
	                    <s:textfield name="lineTrafficList(%{step}).fromCity"
	                                 value="%{fromCity}">
	                    </s:textfield>
                    </td>
                    <td>To -</td>
                    <td>
                      <s:textfield name="lineTrafficList(%{step}).toCity"
                                   value="%{toCity}">
                      </s:textfield>
                    </td>
                    <td>交通：</td>
                    <td>
                      <s:select name="lineTrafficList(%{step}).traffic"
                                list="traffic1List" 
                                listKey="value"
                                listValue="label">
                      </s:select>
                    </td>
                    <td>行程时间：</td>
                    <td>
                      <s:textfield name="lineTrafficList(%{step}).travelTime"
                                   value="%{travelTime}" size="5">
                      </s:textfield>
                    </td>
                  </tr>
                </table>
              </s:if>
            </s:iterator>
            </s:if>
            </td>
            <td><input type="button" onclick="javascript:addCity('<s:property value='%{day}'/>');" value="Add" /></td>
          </tr>
          <tr>
            <td colspan="3">
            <table>
              <tr>
              <td>早餐：</td>
              <td>          
                <sj:autocompleter list="%{eatList}"
			                    name="scheduleList(%{day}).breakfast"
			                    listKey="label"
			                    listValue="label">
                </sj:autocompleter>&nbsp;&nbsp;
              </td>
              <td>午餐：</td>
              <td>
                <sj:autocompleter list="%{eatList}"
			                    name="scheduleList(%{day}).lunch"
			                    listKey="label"
			                    listValue="label">
                </sj:autocompleter>&nbsp;&nbsp;
              </td>
              <td>晚餐：</td>
              <td>
                <sj:autocompleter list="%{eatList}"
			                    name="scheduleList(%{day}).supper"
			                    listKey="label"
			                    listValue="label">
                </sj:autocompleter>
              </td>
              </tr>
            </table>
            </td>
          </tr>
        </table>
        </td>
        <td rowspan="2"><a href="javascript:SubmitForm('delete','<s:property value="day" />')">删除</a></td>
      </tr>

      <tr>
        <td colspan="8">
          <s:textarea name="scheduleList(%{day}).program"
                      value="%{program}"
                      cols="100" rows="5">
          </s:textarea>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td colspan="7">
          <table>
            <tr>
              <td>住宿:</td>
			        <td><s:textfield name="scheduleList(%{day}).quarter" value="%{quarter}" size="95"></s:textfield></td>
            </tr>
          </table>
        </td>
        
      </tr>
    </s:iterator>
    </s:if>

    <tr>
      <td colspan="8">
        <s:submit action="RouteScheduleAdd" value="增加一行" />&nbsp;&nbsp;
        <s:submit action="RouteScheduleSave" value="保存" /></td>
    </tr>
  </table>

</s:form>
</body>
</html>