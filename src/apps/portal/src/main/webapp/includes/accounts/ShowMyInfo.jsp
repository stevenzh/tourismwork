<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>会员基本信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
</head>

<body>
<table width="100%">
  <tr>
    <td bgcolor="#99ccff">
    <table align="center" border="0" cellpadding="0"
      cellspacing="1" width="100%">
      <tr>
        <td align="center" width="100"><a
          href="<s:url action='showMyBasicInfo' namespace='/accounts' includeParams='none' />"
         >基本信息查询</a>&nbsp;&nbsp;|</td>
        <td align="center" width="100"><a
          href="<s:url action='showMyInfo' namespace='/accounts' includeParams='none' />"
         >基本信息修改</a>&nbsp;&nbsp;|</td>
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
    <table align="center" bgcolor="#ffffff" border="0" cellpadding="2"
      cellspacing="5" width="100%">
      <tr bgcolor="#bbbbff">
        <td align="center" colspan="2"><strong>基本信息查询</strong></td>
      </tr>
      <tr>
      <tr>
        <td align="right" width="180">用户名：</td>
        <td><s:hidden name="customer.uid"></s:hidden> <s:property
          value="customer.uid" /></td>
      </tr>
      <tr>
        <td align="right">真实名称：</td>
        <td><s:property value="customer.userName" /></td>
      </tr>
      <tr>
        <td align="right">手机号：</td>
        <td><s:property value="customer.mobile" /></td>
      </tr>
      <tr>
        <td align="right">地址：</td>
        <td><s:property value="customer.address" /></td>
      </tr>
      <tr>
        <td valign="top" align="right">身份证号：</td>
        <td><s:property value="customer.idCard" /></td>
      </tr>
      <tr>
        <td valign="top" align="right">称呼：</td>
        <td><s:property value="customer.sex" /></td>
      </tr>
      <tr>
        <td valign="top" align="right">E-MAIL：</td>
        <td><s:property value="customer.email" /></td>
      </tr>

      <tr>
        <td align="right" width="120">生日：</td>
        <td><s:property value="customer.birthday" /></td>
      </tr>
      <tr>
        <td align="right" width="120">电话：</td>
        <td><s:property value="customer.phone" /></td>
      </tr>
      <tr>
        <td align="right" width="120">传真：</td>
        <td><s:property value="customer.fax" /></td>
      </tr>
      <!-- 
  <tr>
    <td align="right" width="120">所在国家：</td>
    <td>
      <s:select id="Nation"
                name="customer.nation"
               
                list="nations"
                listKey="code"
                listValue="cnName"
                onchange="javascript:change();">
      </s:select>
    </td>
  </tr>
   -->
      <tr>
        <td align="right">所在省份：</td>
        <td><s:property value="customer.province" /></td>
      </tr>
      <tr>
        <td align="right">所在城市：</td>
        <td><s:property value="customer.city" /></td>
      </tr>
      <tr>
        <td align="right">邮政编码：</td>
        <td><s:property value="customer.postcode" /></td>
      </tr>

      <tr>
        <td align="right">是否接收E-mail：</td>
        <td><s:property value="customer.receiveMail" /></td>
      </tr>

      <tr>
        <td align="right">职业：</td>
        <td><s:property value="customer.vocation" /></td>
      </tr>

      <tr>
        <td align="right">教育程度：</td>
        <td><s:property value="customer.educate" /></td>
      </tr>

      <tr>
        <td align="right">每月家庭收入：</td>
        <td><s:property value="customer.householdIncome" /></td>
      </tr>

      <tr>
        <td align="right">每月个人收入：</td>
        <td><s:property value="customer.personalIncome" /></td>
      </tr>

      <tr>
        <td align="right">平均每年外游次数：</td>
        <td><s:property value="customer.tripTimes" /></td>
      </tr>

      <tr>
        <td align="right">每次旅行的平均花费：</td>
        <td><s:property value="customer.expendOneTrip" /></td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</body>
</html>

