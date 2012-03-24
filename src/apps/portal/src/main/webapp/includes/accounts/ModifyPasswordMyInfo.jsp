<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>会员信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
</head>

<body>
<script type="text/javascript">
<!--//
function checkOut()
{
  var fm=document.submitForm;
  if(password1=="")
  {
    alert('密码不能为空!');
    return false;
  }
  
  if(password1!=password2)
  {
    alert('你两次输入的密码不相同!');
    return false;
  }

}

//-->
</script>

<table width="100%">
  <tr>
    <td bgcolor="#99ccff">
    <table align="center" border="0" cellpadding="0"
      cellspacing="1" width="100%">
      <tr>
         <td align="center" width="100">
            <a href="<s:url action='showMyBasicInfo' namespace='/accounts' includeParams='none' />">基本信息查询</a>&nbsp;&nbsp;|
            </td>
            <td align="center" width="100">
            <a href="<s:url action='showMyInfo' namespace='/accounts' includeParams='none' />">基本信息修改</a>&nbsp;&nbsp;|
            </td>
        <td align="center" width="80"><a
          href="<s:url action='ModifyPasswordMyInfo' namespace='/accounts' includeParams='none' />"
         >修改密码</a>&nbsp;&nbsp;|</td>
        <td align="center">&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>

  <tr>
    <td bgcolor="#99ccff">
    <s:form action="ModifyPasswordSubmitMyInfo" namespace="/accounts"
      method="POST" theme="simple">
      <s:hidden></s:hidden>
      <table align="center" bgcolor="#ffffff" border="0" cellpadding="5" cellspacing="1" width="100%">
        <tr>
          <td colspan="2" align="center" bgcolor="#bbbbff"><strong>修改密码 </strong></td>
        </tr>
        <tr>
        <tr>
          <td align="right">新密码：</td>
          <td><s:password name="customer.passwd"
            maxLength="20" required="true">
          </s:password> <span class="required">*</span>（6位以上数字、英文字母、特殊符号组成）</td>
        </tr>
        <tr>
          <td align="right">确认密码：</td>
          <td><s:password name="customer.confirmPassword"
            maxLength="20" required="true">
          </s:password></td>
        </tr>

        <tr align="right">
          <td colspan="2"><s:submit value="确  定"></s:submit>
          <input name="cancel" type="button"
            onclick="history.go(-1);" value="取&nbsp;&nbsp;消"></td>
        </tr>
      </table>
    </s:form></td>
  </tr>
</table>
</body>
</html>

