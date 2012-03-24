<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FIT线路分类信息</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="FIT线路分类信息">
</head>

<body>
<script language="JavaScript">
<!--//
function back()
{
  var fm=document.FITTypeDetail;
  fm.action = "<s:url action='FITType' namespace='/product'/>"
  fm.submit();
}

function del()
{
  var fm=document.FITTypeDetail;
  fm.action = "<s:url action='FITTypeDelete' namespace='/product'/>"
  if (confirm("确定要删除此分类吗？") == false)
  {
  return;
  }
  fm.submit();
}
</script>

<s:form action="FITTypeDetail" namespace="/product" method="POST">
 <s:hidden name="code"></s:hidden>
  <table cellpadding="0" cellspacing="0">
    <tr>
      <td>
      <table>
        <tr>
          <td class="idx">代码：&nbsp;</td>
          <td class="cdata"><s:property value="fitType.code"/></td>
        </tr>
        <tr>
          <td class="idx">中文名称：&nbsp;</td>
          <td class="cdata"><s:property value="fitType.cnName"/></td>
        </tr>
        <tr>
          <td class="idx">图片英文名称：&nbsp;</td>
          <td class="cdata"><s:property value="fitType.mapFile"/></td>
        </tr>
      </table>
      </td>
    </tr>
  </table>
  <div align="center">
  <input type="button" value="删 除" onclick="javaScript:del()">
  <input type="button" value="返 回" onclick="javaScript:back()">
  </div>
</s:form>
</body>
</html>
