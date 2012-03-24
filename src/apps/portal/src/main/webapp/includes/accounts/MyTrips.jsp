<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<script language="JavaScript">
<!--
function tuichu()
{
   window.open("../tuichu.asp")
}
function change_user()
{
  window.open("../checklogin.asp","gg","toolbar=no,location=top,directories=no,status=no,menubar=no,scrollbars=no,width=350,height=200,top=20,left=120,resizable=no");
}

function ShowBooking (name_no)
{
    window.open("Booking/InfoBooking.asp?name_no="+name_no,"gg","toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,width=800,height=600,top=1,left=1,resizable=yes");
}
function ModifyBooking (name_no)
{
    window.open("Booking/ModifyBooking.asp?name_no="+name_no,"gg","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,width=600,height=500,top=20,left=120,resizable=yes");
}
function AddBooking (cpt_no)
{
    window.open("Booking/AddBooking.asp?cpt_no="+cpt_no,"gg","toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,width=800,height=600,top=1,left=1,resizable=yes");
}

function sub_date()
{
  var fm = document.AbroadSearchForm;
  fm.submit();
  
  var JZ = line_form.JZ_DATE.value;
  var CF = line_form.CF_DATE.value;
  var aJZ = JZ.split("-");
  var aCF = CF.split("-");
  
  var JZ_DATE = parseInt(aJZ[1],10)+"-"+parseInt(aJZ[2],10)+"-"+parseInt(aJZ[0],10);
  var CF_DATE = parseInt(aCF[1],10)+"-"+parseInt(aCF[2],10)+"-"+parseInt(aCF[0],10);
  
  if(Date.parse(JZ_DATE)<Date.parse(CF_DATE))
  
  {
    alert("第二个日期选择有错，只能大于或等于第一个日期，请重新选择！");
    line_form.JZ_DATE.focus();
    return (false);
  }
  if(line_form.CF_DATE.value=="")
  {
    alert("出发日期不能为空，请选择！");
    line_form.CF_DATE.focus();
    return (false);
  }
  if(line_form.JZ_DATE.value=="")
  {
    alert("截止日期不能为空，请选择！");
    line_form.JZ_DATE.focus();
    return (false);
  }
}
-->
</script>
<script language="javascript">
<!--
function Show (rec_no)
{
  window.open("SalesPInfo/InfoSalesP.asp?rec_no="+rec_no,"gg","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,width=600,height=400,top=120,left=230,resizable=no");
}

function openwin() { 
  window.open ("userpassword/modifyUserPassword.asp", "newwindow", "height=320, width=400, toolbar=no,menubar=no, left=200,scrollbars=no,resizable=no, location=no, status=no") 
} 

function sub_need(Flags)
{
  document.getElementById("MDD0").style.display="none";
  document.getElementById("MDD1").style.display="none";

  if(Flags==1)
    document.getElementById("MDD0").style.display="block";
  else if(Flags==2)
    document.getElementById("MDD1").style.display="block";
}

function setCheckInDate(d)
{
  document.getElementById("CF_DATE").value=d;
  if(document.getElementById("JZ_DATE").value.length==0)
  {
    showCalendar('dimg2',false,'JZ_DATE','CF_DATE');
  }
}

//-->
</script>
<table cellspacing="0" cellpadding="1" width="760"align="center" border="0">
  <tr>
    <!-- 左侧栏目 -->
    <td valign="top" width="172" bgcolor="#f7f7f7">
      <table width="100%" border="0">
        <tr>
          <td align="middle" bgcolor="#d9dbff" height="20">
            <font color="#000000">最新线路</font>
          </td>
        </tr>
        <tr>
          <td>
            <table width="100%" border="0">
              <s:iterator value="newLines">
                <tr>
                  <td align="left" width="10%" height="20">
                    <img src="<s:url value='/images/arrow_o.gif' encode='false' includeParams='none'/>" width="11" height="11">
                  </td>
                  <td align="left" width="90%">
                    <a target="_bank" href="<s:url action='RouteDetail' namespace="/" />?routeNo=<s:property value="lineNo" />"><s:property value="lineName" /></a>
                  </td>
                </tr>
              </s:iterator>
            </table>
          </td>
        </tr>
        <tr>
          <td align="right" height="20">
            <a href="/OutBand/OB_LineList.asp" target=_blank><font color=#ff0000>更多...</font> </a>
            <br>
          </td>
        </tr>
      </table>
    </td>

    <!-- 中央区-开始 -->
    <td valign="top" width="416">
      <table cellspacing="1" cellpadding="0" width="100%" bgcolor="#6699cc" border="0">
        <tr>
          <td height="104">
            <s:form id="line_form" action="AbroadSearch" namespace="/" method="post" theme="simple">
            <table cellspacing=3 cellpadding=0 width="100%" bgcolor=#ffffff border=0>
              <tr>
                <td colspan="2" height="25">
                  <s:radio name="classType"
                            list="classTypes"
                            listKey="value"
                            listValue="label"
                            onclick="sub_need();">
                  </s:radio>
                </td>
              </tr>
              <tr>
                <td width="17%" align="left">目的地：</td>
                <td width="83%">
                <s:select value="destCity"
                           id="MDD0"
                           list="abroadDestinations"
                           listKey="code"
                           listValue="%{code +' '+ cnName}">
                </s:select>
                <s:select value="destCity"
                           id="MDD1"
                           list="hongkongDestinations"
                           listKey="code"
                           listValue="%{code +' '+ cnName}"
                           cssStyle="display: none">
                </s:select>
                </td>
              </tr>
              <tr>
                <td align="left">出发日期：</td>
                <td valign="center">
                <sj:datepicker size="10" displayFormat="yy-mm-dd"  name="startDatePeriod" /> 至
                <sj:datepicker size="10" displayFormat="yy-mm-dd"  name="endDatePeriod" /></td>
              </tr>
              <tr>
                <td align="right" bgcolor="#f7f7f7" colspan="2">
                  <s:submit type="image" src="/images/search.gif" value="%{getText('common.forms.search')}"></s:submit>
                </td>
              </tr>
            </table>
            </s:form>
          </td>
        </tr>
      </table>
      <table height=14 cellspacing=0 cellpadding=0 width="100%" border=0>
        <tr>
          <td height=10></td>
        </tr>
      </table>
      <table cellspacing="0" cellpadding="0" width="100%" border="0">
        <s:iterator value="plans">
          <tr>
            <td class="fond" bgcolor="#f7f7f7" colspan="7" height="22">
              <font color="#6699cc"><strong><s:property value="key"/></strong></font>
            </td>
          </tr>

          <tr>
            <td colspan="7">&nbsp;</td>
          </tr>
          <s:iterator value="value">
            <tr>
              <td valign="top" width="15"><img src="<s:url value='/images/maru_g.gif' encode='false' includeParams='none'/>" width="12" height="12"></td>
              <td valign="top" width="167" height="20">
                <a target="_bank" href="<s:url action='RouteDetail' namespace='/'/>?routeNo=<s:property value="line.lineNo"/>"><s:property value="line.lineName" /></a>
              </td>
              <td valign="top" align="middle" width="44"><s:date name="outDate" format="yyyy-MM-dd"/></td>
              <td valign="top" align="right" width="60"><s:property value="price"/>元</td>
              <td valign="top" align="right" width="35"><s:property value="pax3"/>人</td>
              <td valign="top" align="middle" width="52"><s:property value="outCity"/></td>
            </tr>
          </s:iterator>
          <tr>
            <td align=right colspan=7 height=10>
              <a href="/OutBand/OB_LineQuite.ASP?dpt_no=C2" target=_blank><font color=#ff0000>更多... </font> </a>
            </td>
          <tr>
            <td colspan="7" height="5">&nbsp;</td>
        </s:iterator>
      </table>
      <hr width="100%" size="1">
    </td>

    <!-- 右侧开始 -->
    <td valign="top" width="172">
      <table cellspacing="0" cellpadding="0" width="100%" border="0">
        <tr>
          <td bgcolor="#6493d9" height="20">
            <div class="fond" align="center">
              <font color="#ffffff"><strong>推广信息</strong></font>
            </div>
          </td>
        </tr>
        <tr>
          <td valign="center">
            <marquee onMouseOver="this.stop()" onMouseOut="this.start()" scrollamount=1 scrolldelay=60 direction=up height=200>
              <table cellspacing="2" cellpadding="0" width="100%" border="0">
                <tr>
                  <td align="center" valign="top" colspan="2">
                      <br>
                      <font color="#ff0000">暂无推广信息</font>
                  </td>
                </tr>
              </table>
            </marquee>
            <div></div>
          </td>
        </tr>
      </table>

      <table cellspacing=2 cellpadding=0 width="100%" border="0">
        <!-- 旅游工具箱 -->
        <tr>
          <td height="3"></td>
        </tr>
        <tr>
          <td align="center" width="100%" height="30">旅游工具箱</td>
        </tr>
        <tr>
          <td>
          <table cellspacing="1" cellpadding="0" width="100%" align="center" border="0">
            <!-- 签证办理办法 -->
            <tr>
              <td valign="baseline" bgcolor="#ffffff" height="20">&nbsp;&nbsp;<a href="/visa_temp.htm" target="_blank">签证办理办法(下载)</a></td>
            </tr>
            <!-- 各国旅游须知 -->
            <tr>
              <td valign="baseline" bgcolor="#ffffff" height="20">&nbsp;&nbsp;<a href="/freetravel_info_link.htm" target="_blank">各国旅游须知</a></td>
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

