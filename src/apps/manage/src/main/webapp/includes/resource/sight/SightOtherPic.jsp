<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>景点图片列表</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="景点图片列表">
</head>

<body>
<script type="text/javascript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.RouteTraitAdd;
  fm.refNo.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='RouteTraitDelete' namespace='/product'/>"
  }
  fm.submit();
}

function checkArea(vid, len)
{
  var val = document.getElementById(vid);
  var str = val.value;
  if (textMaxLengthCheck(val, len) == false)
  {
    alert("第" + str.substring(str.length -1,str.length) + "行 描述文字不超过2000.");
    val.focus();
  }
}

//-->
</script>

<s:form action="showSights" namespace="/resource" method="POST" theme="simple">
<s:hidden name="refNo" />
<table width="100%">
    <tr>
      <td class="lstidx" width="20%">显示序列</td>
      <td class="lstidx" width="60%">内容</td>
      <td class="lstidx" width="20%"" colspan="2">操作</td>
    </tr>

	  <s:iterator value="sightPicList" status="rowCounter">
	  <tr>
	    <td valign="top" >
	      <s:textfield name="sightPicList(%{id}).id" 
	      			 value="%{#rowCounter.count}"
	                 size="3"
	                 maxlength="2"
                     readonly="true">
        </s:textfield>
	    </td>
	    <td class="data" valign="top" >
	    <s:textfield name="sightPicList(%{id}).mapAddress"
	                 value="%{mapAddress}"
	                 size="80"
	                 maxLength="200" />
	    </td>
	    <td valign="top" ><a href="javascript:SubmitForm('delete','<s:property value="recNo" />')">删除</a></td>	    
	  </tr>
	  <tr>
	  <td></td>
	  	<td valign="top" >
	    	<s:textarea name="sightPicList(%{id}).note"
	                value="%{note}"
	                cols="100"
	                rows="5">
	    	</s:textarea>
	    </td>
	    <td></td>
	  </tr>
	  </s:iterator>
    <tr>
      <td>
      <s:submit action="SightPicAdd" value="增加一行" />&nbsp;
      <s:submit action="SightPicSave" value="保存" />
      </td>
    </tr>

</table>

</s:form>
</body>
</html>