<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>各国签证服务</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="各国签证服务">
</head>
<body>

<script type="text/javascript">

function Shows(target)
{
  var fm = document.listVisa;
  fm.recordNo.value = target;
  fm.action= "<s:url action='detailVisa' namespace='/product'/>"
  fm.submit();
}

function SubmitForm(param, target)
{
  var fm = document.listVisa;
  fm.recordNo.value = target;
  if (param == 'add')
  {
	  fm.recordNo.value = '';
	  fm.action = "<s:url action='editVisa' namespace='/product' />"
  }
  if (param =='delete') 
  {
    if(confirm('确定删除吗？') == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteVisa' namespace='/product'/>"
  } 
  else if (param == 'modify') 
  {
    fm.action = "<s:url action='editVisa' namespace='/product' />"
  }
  fm.submit();
}

</script>

<s:form method="post" action="listVisa" namespace="/product" theme="simple">
<s:hidden name="recordNo"></s:hidden>
  <table align="left" border="0" style="width: 100%">
    <tr>
      <td>
      <table align="center" border="0" style="width: 100%">
        <tr>
          <td class="idx" height="25" nowrap="nowrap" width="82">国家/地区：</td>
          <td class="idx">
          <s:select name="country"
                    list="countrys"
                    listKey="countryId"
                    listValue="name"
                    headerKey=""
                    headerValue="所有">
          </s:select></td>
          <td class="idx">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <s:submit value="%{getText('common.forms.search')}" /></td>
        </tr>
        <tr>
          <td colspan="2">&nbsp;</td>
        </tr>
      </table>
      <div align="center"><br>
      <input name="add" value="添加" onclick="javascript:SubmitForm('add','')" type="button"/>
      </div>
      </td>
    </tr>
    <tr>
      <td>
      <table border="0" style="width: 100%">
        <tr bgcolor="#f3f3f3">
          <td class="lstidx" width="30">No.</td>
          <td class="lstidx">国家/地区</td>
          <td class="lstidx">项目</td>
          <td class="lstidx">直客价</td>
          <td class="lstidx">同业价</td>
          <td class="lstidx">成本价</td>
          <td class="lstidx">单位</td>
          <td class="lstidx">办理时间</td>
          <td class="lstidx">有效期</td>
          <td class="lstidx">操 作</td>
        </tr>
        <s:iterator value="visaList" status="rowcounter">
          <tr>
            <td class="cdata"><s:property value="#rowcounter.count" /></td>
            <td class="data"><s:property value="cnName"/></td>
            <td class="data">
              <a href="javascript:Shows('<s:property value="recordNo"/>')"><s:property value="subject" /></a>
            </td>
            <td class="data" nowrap="nowrap">RMB <s:property value="marketPrice" /></td>
            <td class="data" nowrap="nowrap">RMB <s:property value="quotedPrice" /></td>
            <td class="data" nowrap="nowrap">RMB <s:property value="costPrice" /></td>
            <td class="data"><s:property value="unit" /></td>
            <td class="data"><s:property value="transactDays" /></td>
            <td class="cdata">
              <s:date name="startDate" format="yyyy/MM/dd" />-<s:date name="endDate" format="yyyy/MM/dd" />
            </td>
            <td align="center">
              <a href="javascript:SubmitForm('modify','<s:property value="recordNo"/>')">修改</a>
              <!-- 
              <a href="javascript:SubmitForm('delete','<s:property value="recordNo"/>')">删除</a>
               -->
            </td>
          </tr>
        </s:iterator>
      </table>
      </td>
    </tr>
  </table>
</s:form>
</body>
</html>
