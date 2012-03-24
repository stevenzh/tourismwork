<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:hidden name="countInPage"></s:hidden>
<s:hidden name="currentPage"></s:hidden>
<s:hidden name="sortId"></s:hidden>
<s:hidden name="movePage"></s:hidden>

<div class="PagerTable">
    <ul>
    <li class="doc" style="WIDTH: 600px">第<s:property value="currentPage"/>页 共<s:property value="totalPage"/>页 共<s:property value="totalRecord"/>条记录</li>
    <li class="button"><img class="PBMouseOut" title="首页" onClick="javascript:_getlist('first')" onMouseOver="javascript:PBMOver(this);" onMouseOut="javascript:PBMOut(this);" src="<s:url value="/images/manage/other/bbFirst.gif" />"></li>
    <li class="button"><img class="PBMouseOut" title="上一页" onClick="javascript:_getlist('prev')" onMouseOver="javascript:PBMOver(this);" onMouseOut="javascript:PBMOut(this);" src="<s:url value="/images/manage/other/bbPrev.gif" />"></li>
    <li class="button"><img class="PBMouseOut" title="下一页" onClick="javascript:_getlist('next')" onMouseOver="javascript:PBMOver(this);" onMouseOut="javascript:PBMOut(this);" src="<s:url value="/images/manage/other/bbNext.gif" />"></li>
    <li class="button"><img class="PBMouseOut" title="尾页" onClick="javascript:_getlist('last')" onMouseOver="javascript:PBMOver(this);" onMouseOut="javascript:PBMOut(this);" src="<s:url value="/images/manage/other/bbLast.gif" />"></li>
    <li class="button"><img class="PBMouseOut" title="跳转到" onClick="javascript:_getlist('page')" onMouseOver="javascript:PBMOver(this);" onMouseOut="javascript:PBMOut(this);" src="<s:url value="/images/manage/other/bbGotopage.gif" />"></li>
    <li class="txt"><input id="movePg" class="SeachTextEdit" size="3" maxlength="3" type="text"></li>
    </ul>
  </div>