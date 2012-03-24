<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

  <div class="w250 main_left left">
   <div class="left_box">
    <div class="left_box_top">
      <h2>营业门店</h2>
    </div>
		<table align="center" border="0" cellpadding="0" cellspacing="1" width="100%">
		  <s:iterator value="branchs">
		  <tr>
		    <td width="12%"><img src="<s:url value='/images/icon6.gif' includeParams="none"/>" height="10"></td>
		    <td width="88%"><a href="<s:url action='BranchPage' namespace='/' includeParams="none" />?branchId=<s:property value='branchId'/>"><s:property value="name" /></a></td>
		  </tr>
		  </s:iterator>
		</table>
   </div>
   
   <div class="left_box">
    <div class="left_box_top2">
     <div class="more right"><a href="#">详细</a></div>
     <div class="left_box_top2left">
      <h2>旅游注意事项</h2>
     </div>
    </div>
    <div class="wds">
    <p>温馨提示：</p>
测试内容
    </div>
   </div>
   
   <div class="left_box">
    <div class="left_box_top2">
     <div class="more right"><a href="#">更多</a></div>
     <div class="left_box_top2left">
      <h2>出团线路</h2>
      </div>
    </div>
    <div class="wds">
    <p>出境游：</p>
日韩线 欧洲线 澳新线 美洲线 印度尼泊尔  中东非洲线 东南亚线 港澳线 台湾线
    <p>国内游：</p>
京津华北线 海南线 四川线 浪漫海岛线 西藏线 晋蒙线 云南线 广西，越南线
    </div>
   </div>
   
   <div class="left_box">
    <div class="left_box_top2">
     <div class="more right"><a href="#">更多</a></div>
     <div class="left_box_top2left">
      <h2>旅游促销活动</h2>
      </div>
    </div>
    <img src="<s:url value='/images/left_ad1.gif'/>" />
   </div>
  </div> <!--main_left-->
  
  <div class="w435 main_middle left">
   <div class="middle_box">
     <div class="middle_box1">
     <img src="<s:url value='/images/middle_ad1.gif'/>" />
     </div>
   </div>

   <div class="middle_box_js">
     <div class="middle_box2">
     <table width="586" align="center" bgcolor="#FFFFFF">
       <!-- 第一行：店面图、名称、地址、电话、传真、营业时间。-->
       <tr>
         <td>
         <table align="center" border="0" cellpadding="0" cellspacing="3" width="100%">
           <tr>
             <td align="center" height="200" valign="middle" width="258">
               <input name="imageField" src="<s:url value='/images/' includeParams="none"/><s:property value='branch.shopkeeperPhoneFile'/>" border="0" height="160" type="image" width="200">
             </td>
             <td valign="top" width="314">
             <table align="center" border="0" cellpadding="0" cellspacing="3" width="100%">
               <tr>
                 <td colspan="2" height="40"><s:property value="branch.name"/></td>
               </tr>
               <tr>
                 <td valign="top" width="22%"><strong>地址：</strong></td>
                 <td valign="top" width="78%"><s:property value="branch.address"/>&nbsp;</td>
               </tr>
               <tr>
                 <td valign="top"><strong>电话：</strong></td>
                 <td valign="top"><s:property value="branch.phone"/>&nbsp;</td>
               </tr>
               <tr>
                 <td valign="top"><strong>传真：</strong></td>
                 <td valign="top"><s:property value="branch.fax"/>&nbsp;</td>
               </tr>
               <tr>
                 <td nowrap="nowrap" valign="top"><strong>营业时间：</strong></td>
                 <td valign="top"><s:property value="branch.officeHours" escape="false"/></td>
               </tr>
             </table>
           </td>
           </tr>
         </table>
       </td>
       </tr>

       <!-- 第二行：交通、店长、地图。-->
       <tr>
         <td>
         <table align="center" border="0" cellpadding="0" cellspacing="3" width="100%">
           <tr>
             <td valign="top" width="314">
             <table align="center" border="0" cellpadding="0" cellspacing="3" width="100%">
               <tr>
                 <td colspan="2" valign="top"><strong>交通：</strong></td>
               </tr>
               <tr>
                 <td colspan="2" valign="top"><s:property value="branch.comeAndGo"/>&nbsp;</td>
               </tr>
               <tr>
                 <td align="left" valign="middle" width="47%">
                 <input name="imageField2" src="<s:url value='/images/' includeParams="none"/><s:property value='branch.branchPhoneFile'/>" border="0" height="123" type="image" width="129">
                 </td>
                 <td valign="top" width="53%">
                 <table width="100%">
                   <tr>
                     <td width="48%">店长：</td>
                     <td width="52%"><s:property value="branch.shopkeeper"/></td>
                   </tr>
                   <tr>
                     <td nowrap="nowrap">联系电话：</td>
                     <td><s:property value="branch.shopkeeperPhone"/></td>
                   </tr>
                 </table>
                 </td>
               </tr>
             </table>
             </td>
             <td align="center" height="200" valign="middle" width="258">
             <input name="imageField" src="<s:url value='/images/' includeParams="none"/><s:property value='branch.mapFile'/>" border="0" height="160" type="image" width="200">
             </td>
           </tr>
         </table>
         </td>
       </tr>
       <!-- 第三行：店长寄语。-->
       <tr>
         <td><strong>店长寄语： </strong></td>
       </tr>
       <tr>
         <td align="center" height="40"><s:property value="branch.shopkeeperSaying"/></td>
       </tr>
     </table>
     </div>
   </div>

   <div class="middle_box3">
     <img src="<s:url value='/images/middle_ad2.gif'/>" />
   </div>

   <div class="middle_box">
     <div class="middle_box_top">
       <h2>友情链接</h2>
     </div>
     <div class="wds">
       <ul>
       <li><a href="#">国际旅行社</a></li>
       <li><a href="#">国际旅行社</a></li>
       <li><a href="#">国际旅行社</a></li>
       <li><a href="#">国际旅行社</a></li>
       <li><a href="#">国际旅行社</a></li>
       <li><a href="#">国际旅行社</a></li>
      </ul>
     </div>
   </div>
   
  </div> <!--main_middle-->
  
  <div class="w215 main_right left">
   <div class="right_box">
     <div class="right_box_top2">
     <div class="more right"></div>
     <div class="right_box_top2left">
      <h2>策划园地</h2>
     </div>
     </div>
     <img src="<s:url value='/images/right_ad1.gif'/>" />
    </div> <!--right_box-->
    
    <div class="right_box">
     <div class="right_box_top2">
      <div class="more right"></div>
      <div class="right_box_top2left">
       <h2>旅游工具箱</h2>
      </div>
     </div>

     <div class="right_tourtool">
      <div class="list">
       <ul>
        <li><a href="#">旅行须知</a></li>
        <li><a href="#">百变讲堂</a></li>
        <li><a href="#">签证知识</a></li>
        <li><a href="#">列车时刻</a></li>
        <li><a href="#">天气预报</a></li>
        <li><a href="#">电子地图</a></li>
        <li><a href="#">外汇牌价</a></li>
        <li><a href="#">城市时差</a></li>
       </ul>
      </div>
     </div>
    </div> <!--right_box-->
    
    <div class="right_box">
     <div class="right_box_top2">
     <div class="more right"><a href="#">更多</a></div>
     <div class="right_box_top2left">
      <h2>主题网站</h2>
     </div>
     </div>
     <img class="pic1" src="<s:url value='/images/right_ad2.gif'/>" />
     <img class="pic1" src="<s:url value='/images/right_ad3.gif'/>" />
     <img class="pic1" src="<s:url value='/images/right_ad4.gif'/>" />
    </div> <!--right_box-->
    
        
    <div class="right_box">
     <div class="right_box_top2">
     <div class="more right"><a href="#">详细</a></div>
     <div class="right_box_top2left">
      <h2>企业声明</h2>
     </div>
     </div>
     <div class="wds">
     <p>测试内容</p>
    </div>
  </div> <!--right_box-->
  </div>
  
</body>
</html>

