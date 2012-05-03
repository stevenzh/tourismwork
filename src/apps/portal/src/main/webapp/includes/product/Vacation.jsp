<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线路搜索--Search</title>
<meta name="heading" content="List Vacations" />
<meta name="menu" content="VacationMenu" />
</head>

  <div class="w250 main_left left">
   <div class="left_box">
    <div class="left_box_top">
      <h2>旅游度假</h2>
    </div>
      <s:action name="lineSearch" namespace="/view" executeResult="true" />
   </div>
  </div>

  <div class="w660 main_middle left">

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
	      <s:action name="listLine" namespace="/view" executeResult="true">
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