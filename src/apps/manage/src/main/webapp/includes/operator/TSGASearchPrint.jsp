<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta name="heading" content="[港澳游名单打印] 第一步：查找团">
</head>

<body>
<script language="JavaScript">
<!--//

function changedp()
{
  var fm = document.ToursSearchOutBand;
  var departments = document.getElementById("kenDepartmentId");
  var combo = document.getElementById("kenEmployeeId")
  var sdpt_no = departments.value;

  //更换目的地的内容
  //将原表中的内容清空
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;

  $.ajax({
      type: "post",
      url: '<s:url value="/json/listEmployee.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {groupId: sdpt_no},
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (typeof(n)=='object')
          {
              var kOption = document.createElement('OPTION');
              kOption.text = "全部";
              kOption.value = "";
              combo.options.add(kOption);
              
              $.each(n,function(j,m){
                  var oOption = document.createElement('OPTION');
                  oOption.text = m ;
                  oOption.value = j ;
                  combo.options.add(oOption);
              });
          }
      });
      }
  });
}

function _getlist(type)
{
  var frm = document.ToursSearchOutBand;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

//-->
</script>

<s:form action="ToursSearchOutBand" namespace="/operator" method="post" theme="simple">
<s:hidden name="tourNos"></s:hidden>
<s:hidden name="tourNum"></s:hidden>

<table border="0" cellpadding="2" cellspacing="2" width="100%">
  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx">选择</td>
    <td class="lstidx" nowrap="nowrap">团号</td>
    <td class="lstidx">行程</td>
    <td class="lstidx">团类型</td>
    <td class="lstidx">人数</td>
    <td class="lstidx">出发日期</td>
  </tr>

 <s:iterator value="tours" status="rowcounter">
  <tr>
    <td class="cdata"><s:property value="#rowcounter.count"/></td>
    <td class="cdata"><s:checkbox id="tourNos" name="tourNos" fieldValue="%{tourNo}"/></td>
    <td class="data"><a href="#" title="点击修改客户信息" onclick="javascript:modifyCustomer('<s:property value='tourNo'/>')"><s:property value="tourNo" /></a></td>
    <td class="data"><s:property value="line.lineName"/></td>
    <td class="data"><s:property value="tourKey"/></td>
    <td class="rdata"><s:property value="pax"/>&nbsp;</td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
  </tr>
  </s:iterator>
  <td colspan="14" ><a href="<s:url action='ToursSearchGAPrint' namespace='/operator' includeParams='none' />">更多</a></td>
</table>

<s:submit action="ToursSearchGANext" value="下一步"></s:submit>

</s:form>

</body>
</html>
