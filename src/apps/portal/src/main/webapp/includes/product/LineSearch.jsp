<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form action="processLineSearch" namespace="/product" method="post" theme="simple">
  <div class="search">
   <span>
     <div class="search_1">出发地:</div>
     <input id="kenOutCity" type="text" class="txt"/>
   </span>
   <span>
     <div class="search_1">目的地:</div>
     <input id="kenDestination" type="text" class="txt">
   </span>
   <span>
     <div class="search_1">游轮 探险  自驾游  </div>
   </span>
   <div class="search_btn">
     <img src="<s:url value='/images/index_search.gif'/>" onclick="" />
   </div>
  </div>
</s:form>
<!-- 
加载静态数据   旅游目的地  出发地
<script type="text/javascript" src="" />
 -->
<script type="text/javascript">
<!--
$(function () {
  $("#kenDestination").val("武汉")
  $("#kenOutCity").val("上海")
});
//-->
</script>