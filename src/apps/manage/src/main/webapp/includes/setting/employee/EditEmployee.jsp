<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户修改</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="用户修改">
</head>
<body>
<script type="text/javascript">
  
  function cancel(){
    var fm = document.submitUser;
    fm.action = "<s:url action='listUser' namespace='/setting'/>"
    fm.submit();
  }

  function changePasswd()
  {
      var fm = document.submitUser;
      fm.action = "<s:url action='changeUserPwd' namespace='/setting'/>"
      fm.submit();
  }
  
</script>
  <table border="0" style="width: 100%">
    <tr>
      <td class="header"></td>
    </tr>
</table>

<s:form action="submitUser" namespace="/setting" method="POST" theme="simple">
  <s:hidden name="countInPage"></s:hidden>
  <s:hidden name="currentPage"></s:hidden>

  <s:hidden name="kenUserName"></s:hidden>
  <s:hidden name="groupId"></s:hidden>
  <s:hidden name="agentId"></s:hidden>
  <s:hidden name="agentKey"></s:hidden>
  <s:hidden name="employee.userId"></s:hidden>
  <s:hidden name="userId"></s:hidden>

  <sj:tabbedpanel id="mytabs" collapsible="true" useSelectedTabCookie="true" cssStyle="height: 400px">
    <sj:tab id="tab1" target="tone" label="帐户信息"/>
    <sj:tab id="tab2"  target="ttwo" label="所属团队"/>
    <sj:tab id="tab3"  target="tthree" label="权限设置"/>

    <div id="tone">
      <table style="width: 100%" border="0">
        <tr>
          <td class="idx" style="width: 200px">用户代码：</td>
          <td class="data">
          <s:if test="userId == 0">
          <s:textfield name="employee.uid"/>
          </s:if><s:else>
          <s:label name="employee.uid"/></s:else>
          </td>
        </tr>
        <tr>
          <td class="idx">用户姓名：</td>
          <td class="data"><s:textfield name="employee.userName" /></td>
        </tr>
        <tr>
          <td class="idx">是否管理员 </td>
          <td class="data">
            <s:checkbox name="employee.isAdmin"></s:checkbox>
          </td>
        </tr>
        <tr>
          <td class="idx">是否激活 </td>
          <td class="data">
            <s:checkbox name="employee.isActive"></s:checkbox>
          </td>
        </tr>
        <tr>
          <td class="idx" style="width: 200px">CTI号码:</td>
          <td class="data"><s:textfield name="employee.ctiNo" /></td>
        </tr>
        <tr>
          <td class="idx">密码：</td>
          <td class="data">
            <s:password name="employee.passwd"
                        size="10"
                        value=""
                        showPassword="true"
                        maxlength="10"/>
          </td>
        </tr>
        <tr>
          <td class="idx">电话：</td>
          <td class="data"><s:textfield name="employee.phone" /></td>
        </tr>
        <tr>
          <td class="idx">传真：</td>
          <td class="data"><s:textfield name="employee.fax" /></td>
        </tr>
        <tr>
          <td class="idx">部门：</td>
          <td class="data">
          <s:select name="employee.group.groupId" 
                    list="departmentList"
                    listKey="groupId" 
                    listValue="name" 
                    emptyOption="true">
          </s:select>
          </td>
        </tr>
        <tr>
          <td class="idx">岗位：</td>
          <td class="data">
          <s:select name="employee.workKey" list="#{0:'普通',2:'销售'}"
                    listKey="key"
                    listValue="value">
          </s:select>
          </td>
        </tr>
      </table>
    </div>

    <div id="ttwo">
      <table style="width: 100%" border="0">
        <tr>
          <td class="idx" style="width: 200px" valign="top">选择团队：</td>
          <td class="data">
            <s:optiontransferselect
                   tooltip="Select Your Need Destination"
                   headerKey="-1"
                   headerValue="--- Please Select ---"
                   doubleHeaderKey="-1"
                   doubleHeaderValue="--- Please Select ---"
                   leftTitle="已选定团队"
                   rightTitle="可选团队"
                   name="employee.teams" 
                   list="employee.teamMemberships"
                   listKey="teamId"
                   listValue="name"
                   doubleList="teamList"
                   doubleName="oteam"
                   doubleListKey="teamId"
                   doubleListValue="name">
            </s:optiontransferselect>
          </td>
        </tr>
      </table>
    </div>

    <div id="tthree">
        <table style="width: 100%" border="0">
        <tr>
          <td class="idx" style="width: 200px" valign="top">权限设置：</td>
          <td class="data">
            <s:optiontransferselect
                   tooltip="Select Your Need Destination"
                   headerKey="-1"
                   headerValue="--- Please Select ---"
                   doubleHeaderKey="-1"
                   doubleHeaderValue="--- Please Select ---"
                   leftTitle="已选定权限"
                   rightTitle="可用权限"
                   name="employee.roleids" 
                   list="employee.memberships"
                   listKey="roleId"
                   listValue="roleName"
                   doubleList="roles"
                   doubleName="orule"
                   doubleListKey="roleId"
                   doubleListValue="roleName">
            </s:optiontransferselect>
            <br>
          </td>
        </tr>
      </table>
    </div>
  </div>
  </sj:tabbedpanel>

  <div align="center">
    <table>
      <tr>
        <td align="center">
          <s:submit value="确定"></s:submit>&nbsp;
          <input type="button" value="返回" onclick="javascript:cancel()">
        </td>
      </tr>
    </table>
  </div>
</s:form>

</body>
</html>
