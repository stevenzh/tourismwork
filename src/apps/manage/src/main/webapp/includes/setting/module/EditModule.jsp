<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模块修改</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="模块修改">
</head>

<body>
<script type="text/javascript">
  function cancel(){
    var fm = document.submitModule;
    fm.action = "<s:url action='listModule' namespace='/setting'/>"
    fm.submit();
  }
</script>
<s:form action="submitModule" namespace="/setting" method="POST" theme="simple">
  <s:hidden name="moduleId"></s:hidden>
   <s:hidden name="module.moduleId"></s:hidden>
   <s:hidden name="module.type"></s:hidden>
   <s:hidden name="module.isActive"></s:hidden>

  <table border="0" cellpadding="2" cellspacing="0" width="100%">
   <tr>
      <td class="idx" width="80">序号：</td>
      <td class="data"><s:property value="module.moduleId"/></td>
    </tr>
    <tr>
      <td class="idx">模块名称：</td>
      <td class="data"><s:textfield name="module.moduleName" /></td>
    </tr>
    <tr>
      <td class="idx">模块标题：</td>
      <td class="data"><s:textfield name="module.moduleTitle" /></td>
    </tr>
    <tr>
      <td class="idx">模块说明:</td>
      <td class="data">
        <s:textarea name="module.description"
                    cols="80"
                    rows="4" />
      </td>
    </tr>
    <tr>
      <td class="idx">版本：</td>
      <td class="data"><s:textfield name="module.version"/></td>
    </tr>
    <tr>
      <td class="idx">链接：</td>
      <td class="data"><s:textfield name="module.action"/></td>
    </tr>
    <tr>
      <td class="idx">权重 ：</td>
      <td class="data"><s:textfield name="module.sortOrder"/></td>
    </tr>
    <tr>
      <td>
      <div align="center"><s:submit value="确定"></s:submit>&nbsp;
      <input type="button" value="返回" onclick="javascript:cancel()">
      </div>
      </td>
    </tr>
  </table>
</s:form>
</body>
</html>
