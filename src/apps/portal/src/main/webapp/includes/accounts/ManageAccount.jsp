<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>我的帐户</title>
</head>

<body dir="ltr">

<table border="0" style="width: 100%">
  <tr>
    <td colspan="2"><img alt="" height="2" width="1"></td>
  </tr>
  <tr>
    <td valign="top" width="1%">
      <a href="/accounts/">
      <img src="ManageAccount_files/skyaccountslogo.gif" align="left" border="0">
      </a>
    </td>
    <td valign="top">
      <table border="0" style="width: 100%">
        <tr>
          <td colspan="2"><img alt="" height="15" width="1"></td>
        </tr>
        <tr bgcolor="#3366cc">
          <td><img alt="" height="1" width="1"></td>
        </tr>
        <tr bgcolor="#e5ecf9">
          <td style="padding-left: 4px; padding-bottom: 3px; padding-top: 2px; font-family: arial,sans-serif;"><b><s:property value="account.name"/> 的 帐户</b> </td>
        </tr>
        <tr>
          <td colspan="2"><img alt="" height="5" width="1"></td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<div class="body">
  <table cellspacing="10" style="width: 100%">
    <tr>
      <td valign="top" width="49%">
      <table style="width: 100%">
          <tr>
            <td nowrap="nowrap" width="1%"><b>帐户信息</b>
            <!--  - <a href="/accounts/EditUserInfo"><font size="-1">修改</font></a> -->
            </td>
            <td width="99%"><hr color="#dddddd" noshade="noshade" size="1">
            </td>
          </tr>
        </table>
        <table class="pi">
          <tr>
            <td nowrap="nowrap"><s:property value="account.name"/></td>
          </tr>
          <tr>
            <td>地址：<s:property value="account.address"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap">邮编: <s:property value="account.zipCode"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap">省份/城市: <s:property value="account.provinceId"/> - <s:property value="account.cityId"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap">信用额度: <s:property value="account.creditAmt1"/></td>
          </tr>
        </table>

        <table class="pi">
          <tr>
            <td nowrap="nowrap">联系人信息</td>
          </tr>
          <tr>
            <td>联系人姓名：<s:property value="account.contact"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap">联系人电话: <s:property value="account.contactTel"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap">联系人传真: <s:property value="account.contactFax"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap">联系人移动电话: <s:property value="account.contactMobile"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap">联系人移动电话: <s:property value="account.contactEmail"/></td>
          </tr>
        </table>
      </td>
      <td width="15">&nbsp;</td>
      <td valign="top" width="49%">
        <table style="width: 100%">
          <tr>
            <td nowrap="nowrap" width="1%"><b>我的工具箱</b>
            <!--  - <a href="/accounts/EditServices" id="MyServices_Edit"><font size="-1">修改</font></a> -->
            </td>
            <td width="99%"><hr color="#dddddd" noshade="noshade" size="1">
            </td>
          </tr>
        </table>

        <table class="svcs">
          <tr>
            <td valign="top" width="32">&nbsp;</td>
            <td><font size="-1"><b><s:url var="url" namespace="/accounts" action="BookSearch" /><s:a href="%{url}">订单查询</s:a></font></td>
          </tr>
          <tr>
            <td valign="top" width="32">&nbsp;</td>
            <td><font size="-1"><b> <s:url var="url" namespace="/accounts" action="PaymentSearch" /><s:a href="%{url}">付款查询</s:a></b></font></td>
          </tr>
        </table>
        
      <table class="svcs">
        <tr>
          <td colspan="10">
          <table style="width: 100%">
            <tr>
              <td nowrap="nowrap" width="1%"><b>联系人列表</b>
              <!--  - <a href="/accounts/EditServices" id="MyServices_Edit"><font size="-1">修改</font></a> -->
              </td>
              <td width="99%"><hr color="#dddddd" noshade="noshade" size="1">
              </td>
            </tr>
          </table>
          </td>          
        </tr>
        <tr>
          <td>No.</td>
          <td>姓名</td>
          <td>职位</td>
          <td>电话</td>
          <td>传真</td>
          <td>移动电话</td>
          <td>E-Mail</td>
          <td>MSN</td>
          <td>腾讯QQ</td>
        </tr>
      
        <s:iterator value="account.contacts" status="rowCounter">
        <tr> 
          <td><s:property value="#rowCounter.count" /></td>
          <td valign="top"><s:property value="name" /></td>
         <td><s:property value="rank" /></td>
         <td valign="top"><s:property value="phone" /></td>
         <td valign="top"><s:property value="fax" /></td>
         <td valign="top"><s:property value="mobile" /></td>
         <td valign="top"><s:property value="email" /></td>
         <td valign="top"><s:property value="msn" /></td>
         <td valign="top"><s:property value="qq" /></td>
        </tr> 
        </s:iterator>
      </table>
      </td>
    </tr>
  </table>
</div>
</body>
</html>
