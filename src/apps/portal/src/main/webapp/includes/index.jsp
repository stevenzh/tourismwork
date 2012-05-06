<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<head>
<title><fmt:message key="mainMenu.title" /></title>
<meta name="heading" content="<fmt:message key='mainMenu.heading'/>" />
<meta name="menu" content="MainMenu" />
</head>

  <div class="w250 main_left left">
   <div class="left_box">
    <div class="left_box_top">
      <h2>旅游度假</h2>
    </div>
    <s:action name="lineSearch" namespace="/product" executeResult="true" />
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
      <div id="Tabs">
      <div class="menuDiv">
        <ul id="menu1">
        <li class="hover" onmouseover="setTab(1,0)"><a href="#">新品线路</a></li>
        <li onmouseover="setTab(1,1)"><a href="#">热门线路</a></li>
        <li onmouseover="setTab(1,2)"><a href="#">特色线路</a></li>
        <li onmouseover="setTab(1,3)"><a href="#">专家推荐线路</a></li>
      </ul>
      </div>

      <div class="mainDiv">
        <div id="main1">
        <s:action name="listLine" namespace="/product" executeResult="true">
          <s:param name="Region" value="" />
          <s:param name="Row" value="10" />
        </s:action>
        <ul>
          <li><a href="#">三亚亚龙湾5天4晚自由行</a> <span>5000元起</span></li>
          <li><a href="#">三亚亚龙湾5天4晚自由行</a> <span>5000元起</span></li>
        </ul>
        <ul>
          <li><a href="#">三亚亚龙湾5天4晚自由行</a> <span>5000元起</span></li>
          <li><a href="#">三亚亚龙湾5天4晚自由行</a> <span>5000元起</span></li>
          <li><a href="#">三亚亚龙湾5天4晚自由行</a> <span>5000元起</span></li>
        </ul>
        <ul>
          <li><a href="#">三亚亚龙湾5天4晚自由行</a> <span>5000元起</span></li>
          <li><a href="#">三亚亚龙湾5天4晚自由行</a> <span>5000元起</span></li>
          <li><a href="#">三亚亚龙湾5天4晚自由行</a> <span>5000元起</span></li>
          <li><a href="#">三亚亚龙湾5天4晚自由行</a> <span>5000元起</span></li>
        </ul>
          <p><a href="#">更多旅游线路</a></p>
      </div>
      </div>
    </div>
     </div>
   </div>

   <div class="middle_box3">
     <img src="<s:url value='/images/middle_ad2.gif'/>" />
   </div>

   <div class="middle_box">
     <div class="middle_box_top">
       <h2>您的旅游预算</h2>
     </div>
     <div class="wds">
       <ul>
       <li><a href="#">1000-2000元</a></li>
       <li><a href="#">2000-3000元</a></li>
       <li><a href="#">3000-5000元</a></li>
       <li><a href="#">5000-8000元</a></li>
       <li><a href="#">8000元以上</a></li>
      </ul>
     </div>
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
  <script type="text/javascript">
<!--
/*更换显示样式*/
function setTab(m,n)
{
    var tli=document.getElementById("menu"+m).getElementsByTagName("li");
    var mli=document.getElementById("main"+m).getElementsByTagName("ul");
    for(i=0;i<tli.length;i++)
    {
        tli[i].className=i==n?"hover":"";
        mli[i].style.display=i==n?"block":"none";
    }
}
//-->
</script>
