<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head/>
</head>

<body>

<table align="center" border="0" cellpadding="0" cellspacing="2" width="99%">

   <tr>
  <td bgcolor="#99ccff">
   <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td align="center" width="100">
            <a href="<s:url action='showMyPage' namespace='/accounts' includeParams='none' />">我的信息</a>&nbsp;&nbsp;
            </td>
            <td align="center">&nbsp;</td>
          </tr>
        </table>
  </td>
 </tr>

  <!-- 广告位 -->
  <tr>
    <td align="center" height="100"><img src="<s:url value='/images/member/ad_title.jpg'/>" height="91" width="602"></td>
  </tr>
  <tr>
    <td>

    <!-- 功能类表 -->
    <table border="0" cellpadding="0" cellspacing="2" width="100%" >
      <tr>
        <td height="60" valign="middle">
        <table border="0" cellpadding="0" cellspacing="0" width="197" height="80" bgcolor="#92BEE4">
          <tr>
            <td rowspan="2" width="35%" align="center" valign="middle"><img src="<s:url value='/images/member/mem_info.gif'/>"></td>
            <td width="65%"><a href="#"><b>我的信息</b></a></td>
          </tr>
          <tr>
            <td><a href="<s:url action='showMyInfo' namespace='/accounts' includeParams='none' />">基本信息管理</a><br>
            <a href="<s:url action='ModifyPasswordMyInfo' namespace='/accounts' includeParams='none' />">修改密码</a><br><br>
            </td>
          </tr>
        </table>
        </td>
        
        <td>
        <table border="0" cellpadding="0" cellspacing="0" width="198" height="80" bgcolor="#92BEE4">
          <tr>
            <td rowspan="2" width="35%" align="center" valign="middle"><img src="<s:url value='/images/member/mem_booking.gif'/>"></td>
            <td width="65%"><a href="<s:url action='showMyBooking' namespace='/accounts' includeParams='none' />"><b>我的订单</b></a></td>
          </tr>
          <tr>
            <td><a href="<s:url action='showMyBooking' namespace='/accounts' includeParams='none' />">旅游订单</a><br>
            <a href="<s:url action='showMyBooking' namespace='/accounts' includeParams='none' />">酒店订单</a><br>
            <a href="<s:url action='showMyBooking' namespace='/accounts' includeParams='none' />">机票订单</a>
            </td>
          </tr>
        </table>
        </td>

       <td>
        <table border="0" cellpadding="0" cellspacing="0" width="197" height="80" bgcolor="#92BEE4">
          <tr>
            <td rowspan="2" width="35%" align="center" valign="middle"><img src="<s:url value='/images/member/mem_booking.gif'/>"></td>
            <td width="65%"><a href="<s:url action='CommunityHomeAction' namespace='/accounts' includeParams='none' />"><b>我的社区</b></a></td>
          </tr>
          <tr>
            <td><a href="<s:url action='CommunityHomeAction' namespace='/accounts' includeParams='none' />">进入社区</a><br>
            </td>
          </tr>
        </table>
        </td>
       
       
      </tr>
      <tr>
      
       <td>
        <table border="0" cellpadding="0" cellspacing="0" width="197" height="80" bgcolor="#92BEE4">
          <tr>
            <td rowspan="2" width="35%" align="center" valign="middle"><img src="<s:url value='/images/member/mem_booking.gif'/>"></td>
            <td width="65%"><a href="<s:url action='showMyBooking' namespace='/accounts' includeParams='none' />"><b>我的积分</b></a></td>
          </tr>
          <tr>
            <td><a href="#">积分查询</a><br>
            
            </td>
          </tr>
        </table>
        </td>
        
         <td>
        <table border="0" cellpadding="0" cellspacing="0" width="198" height="80" bgcolor="#92BEE4">
          <tr>
            <td rowspan="2" width="35%" align="center" valign="middle"><img src="<s:url value='/images/member/mem_netpayment.gif'/>"></td>
            <td width="65%"><a href="<s:url action='searchOrder' namespace='/' includeParams='none' />"><b>网上支付</b></a></td>
          </tr>
          <tr>
            <td><br><br><br>
            </td>
          </tr>
        </table>
        </td>
        
         <td>
        <table border="0" cellpadding="0" cellspacing="0" width="197" height="80" bgcolor="#92BEE4">
          <tr>
            <td rowspan="2" width="35%" align="center" valign="middle"><img src="<s:url value='/images/member/mem_service.gif'/>"></td>
            <td width="65%"><a href="<s:url action='CustomerServiceAction' namespace='/accounts' includeParams='none' />"><b>客户服务</b></a></td>
          </tr>
          <tr>
            <td><a href="<s:url action='FeqCustomerServiceAction' namespace='/accounts' includeParams='none' />">常见问题</a><br>
            <a href="<s:url action='NewPeopleCustomerServiceAction' namespace='/accounts' includeParams='none' />">新手上路</a><br>
            <a href="<s:url action='AnswerCustomerServiceAction' namespace='/accounts' includeParams='none' />">在线咨询</a>
            </td>
          </tr>
        </table>
        </td>
      
      </tr>
    </table>
    </td>
  </tr>
</table>
</body>
</html>

