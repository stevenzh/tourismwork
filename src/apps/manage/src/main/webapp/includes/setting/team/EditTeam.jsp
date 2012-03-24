<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改团队信息</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="修改团队信息">
</head>

<body>
<script type="text/javascript">
<!--//

function back()
{
  var fm=document.updateTeam;
  fm.action = "<s:url action='listTeam' namespace='/setting'/>"
  fm.submit();
}

function SubmitDeleteFlight(param, target)
{
  var fm = document.updateTeam;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='delTeam' namespace='/setting'/>"
  }
  fm.submit();
}
//-->
</script>

<s:form action="updateTeam" namespace="/setting" method="POST" theme="simple">
  <s:hidden name="team.teamId" />
  <table>
    <tr>
      <td class="idx">ID:</td>
      <td class="data"><s:textfield name="team.id" /></td>
    </tr>
    <tr>
      <td class="idx">名称:</td>
      <td class="data"><s:textfield name="team.name" /></td>
    </tr>
    <tr>
      <td class="idx">类型:</td>
      <td class="data">
        <s:select name="team.type"
                  list="#{0:'普通',1:'产品',2:'销售',3:'计调',4:'财务'}"
                  listKey="key"
                  listValue="value">
        </s:select>
      </td>
    </tr>
        <tr>
          <td class="idx">是否激活 </td>
          <td class="data">
            <s:checkbox name="team.active"></s:checkbox>
          </td>
        </tr>
    <tr>
      <td class="idx">说明:</td>
      <td class="data"><s:textarea name="team.descriotion" cols="50" rows="6" /></td>
    </tr>
    <tr>
    <td colspan="2">
      <s:submit value="修改"></s:submit>&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="返回" onclick="javascript:back()">
    </td>
    </tr>
  </table>

</s:form>

</body>
</html>
