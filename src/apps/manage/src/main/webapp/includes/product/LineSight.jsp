<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品途径景点</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="产品途径景点">
</head>
<body>

<script type="text/javascript">
<!--//

function modify(param)
{
  var fm = document.inputRouteSight;
  document.getElementById("input_districtNo").value = param;
  fm.submit();
}

function add()
{
  var fm = document.inputRouteSight;
  document.getElementById("input_districtNo").value = '';
  fm.submit();
}

function selectAll()
{
	var cb = document.all.check;
		for(i=0;i<cb.length;i++)
		{
			cb[i].checked = true;
		}
}

//-->
</script>

<s:form action="submitRouteSights" namespace="/product" method="post" theme="simple">
  <table>
    <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">选定</td>
      <td class="lstidx" nowrap="nowrap">目的地</td>
      <td class="lstidx" nowrap="nowrap">景点</td>
      <td class="lstidx">景点描述</td>
      <td class="lstidx" style="width: 80px">操作</td>
    </tr>

    <s:iterator value="searchList" status="rowcounter">
      <tr>
        <td class="cdata" valign="top"><s:property value="#rowcounter.count" /></td>
        <td class="data" valign="top">
        <s:checkbox name="check"
                    value="%{checked}"
                    fieldValue="%{sightNo}">
        </s:checkbox>
        </td>
        <td class="data" valign="top"><s:property value="district.cnName" /></td>
        <td class="data" valign="top"><strong><s:property value="name" /></strong></td>
        <td class="data"><s:property value="cnNote" /></td>
        <td class="data"><a href="#" onclick="javascript:modify('<s:property value='sightNo'/>')">修改景点描述</a></td>
      </tr>
    </s:iterator>

    <tr>
      <td colspan="5">注意：下面列出的景点需要补充 点击“添加景点”地按钮
      <input type="button" value="添加景点" onclick="javascript:add();">
      </td>
    </tr>
    <tr>
     <td ><input type="button" onclick="javascript:selectAll()" value="全选"></td>
      <td colspan="5"><s:submit value="保存" /></td>
    </tr>
  </table>
</s:form>

<s:form action="inputRouteSight" namespace="/product" method="POST" theme="simple">
<s:hidden id="input_districtNo" name="sightNo"></s:hidden>
</s:form>
</body>
</html>
