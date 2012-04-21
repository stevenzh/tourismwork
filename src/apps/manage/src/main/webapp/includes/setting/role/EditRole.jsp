<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色设置</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="角色设置">
</head>
<body>
<script type="text/javascript">
  
  function cancel(){
    var fm = document.submitRole;
    fm.action = "<s:url action='listRole' namespace='/setting'/>"
    fm.submit();
  }

  function mo(param)
  {
    var role = $("#roleId").val();
    
    var t = $(param).find("input").attr("id").split("_");
    var moduleName = t[0];
    var moduleAction = t[1];
    var moduleActionPerm = $(param).find("input").attr("value");
    
    $.ajax({
        type: "post",
        url: '<s:url value="/json/setRoleModelPerm.jspa" encode="false" includeParams="none"/>',
        timeout: 20000,
        error: function(){
            alert('服务器错误');
        },
        data: {roleId:role, moName: moduleName, moduleAction: moduleAction, permValue: moduleActionPerm},
        async: false,
        success: function(data){
            $.each(data, function(i, n){

            if (i =='result')
            {
              if (n == 'false')
                $(param).text("×");
              else if (n == 'true')
                $(param).text("√");
            }
        });
        }
    });
  }

</script>
<s:form action="submitRole" namespace="/setting" method="POST" theme="simple">
  <s:hidden id="roleId" name="roleId"></s:hidden>
  <s:hidden name="role.roleCode"></s:hidden>

  <s:hidden name="role.roleId"></s:hidden>
  <table border="0" style="width: 100%">
    <tr>
      <td class="idx" width="100">角色ID：</td>
      <td class="data"><s:label name="role.roleId"/></td>
    </tr>
    <tr>
      <td class="idx">角色名称：</td>
      <td class="data"><s:textfield name="role.roleName" /></td>
    </tr>
    <tr>
      <td class="idx">描述：</td>
      <td class="data"><s:textfield name="role.roleDesc" /></td>
    </tr>
    <tr>
      <td class="idx">激活状态：</td>
      <td class="data"><s:textfield name="role.isActive" /></td>
    </tr>
    <tr>
      <td colspan="2">
      <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;<s:submit value="确定"></s:submit>&nbsp; <input
        type="button" value="返回" onclick="javascript:cancel()">
      </div>
      </td>
    </tr>

    <tr>
      <td colspan="2">
    <table border="1" bordercolor="#b9c0ff" cellpadding="1" style="width: 100%">
       <tr bgcolor="#b9c0ff">
          <td class="lstidx">模块</td>
          <s:iterator value="permission">
            <td class="lstidx"><s:property value="value"/></td>
          </s:iterator>
       </tr>
       
         <s:iterator value="modulePerm" id="pe">
         <s:hidden name="modulePerm(%{number}).moduleId"/>
         <tr>
           <td class="cdata" nowrap="nowrap"><s:property value="%{moduleName}"/></td>
           <s:iterator value="permission" id="st">
              <s:if test="rolePermissionMap.containsKey(#st.key)">
                <s:if test="rolePermissionMap.get(#st.key) == 1 ">
                  <td class="cdata"><span ondblclick="javascript:mo(this)">√<s:hidden id="%{#pe.moduleName +'_'+ #st.value}" value="%{rolePermissionMap.get(#st.key)}"></s:hidden>
                  </span></td>
                </s:if>
                <s:else>
                  <td class="cdata"><span ondblclick="javascript:mo(this)">×<s:hidden id="%{#pe.moduleName +'_'+ #st.value}" value="%{rolePermissionMap.get(#st.key)}"></s:hidden></span></td>
                </s:else>
              </s:if>
              <s:else>
                <td class="cdata"><span ondblclick="javascript:mo(this)">×<s:hidden id="%{#pe.moduleName +'_'+ #st.value}" value="%{rolePermissionMap.get(#st.key)}"></s:hidden></span></td>
              </s:else>
           </s:iterator>
         </tr>
         </s:iterator>
     </table>
      </td>
    </tr>
  </table>
</s:form>

</body>
</html>
