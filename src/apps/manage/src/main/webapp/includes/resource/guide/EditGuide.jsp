<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导游领队资料</title>
<meta name="menu" content="SettingMenu"/>
</head>
<body>
<script language="JavaScript">
<!--//

function cancel(){
  var fm = document.submitGuide;
  fm.action = "<s:url action='listGuide' namespace='/resource'/>"
  fm.submit();
}

function pinyin()
{
  $.ajax({
      type: "post",
      url: '<s:url value="/json/getPinyin.jspa" encode="false" includeParams="none"/>' ,
      timeout: 20000,
      data: {name: $("#guideName").val()},
      error: function(){
          // alert('服务器错误');
      },
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (i == 'pinyin')
          {
            $("#guidePinyin").val(n);
          }
      });
      }
  });
}

//-->
</script>
<table border="0" cellpadding="2" cellspacing="0" width="100%">
  <tr>
    <td class="header"><s:if test='guide.accCd eq ""'>添加导陪员</s:if> <s:else>修改导陪员</s:else></td>
  </tr>
</table>

<s:form action="submitGuide" method="POST" namespace="/resource" theme="simple">
  <s:hidden name="accCd"></s:hidden>
  <table cellpadding="2">
    <tr>
      <td class="idx">陪同代码:</td>
      <td class="data" colspan="5">
      <s:textfield name="guide.accCd" size="4" maxlength="4"></s:textfield>&nbsp;
      <font color="red">*</font></td>
    </tr>
    <tr>
      <td class="idx">部 &nbsp;&nbsp; 门:</td>
      <td class="data" colspan="5">
      <s:select name="guide.dptNo"
        list="teamList"
        listKey="teamId"
        listValue="name"
        emptyOption="true">
      </s:select></td>
    </tr>
    <tr>
      <td class="idx">姓 &nbsp;&nbsp; 名:</td>
      <td class="data">
      <s:textfield id="guideName" name="guide.accNm"
       size="20" maxlength="10"
       onchange="javascript:pinyin();">
      </s:textfield> &nbsp;
      <font color="red">* </font></td>
      <td class="idx">拼  &nbsp;&nbsp; 音:</td>
      <td class="data">
      <s:textfield id="guidePinyin" name="guide.pinyin"
       size="20" maxlength="10" ondblclick="javascript:pinyin();">
      </s:textfield> &nbsp;
      </td>
      <td class="idx">性 &nbsp;&nbsp; 别:</td>
      <td class="data">
      <s:select name="guide.accSex"
                list="sexList" listKey="label" listValue="value">
      </s:select>&nbsp;<font color="red">*</font>
      </td>
    </tr>
    <tr>
      <td class="idx">出生日期:</td>
      <td class="data"><sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="guide.birthday" /></td>
      <td class="idx">出 生 地:</td>
      <td class="data"><s:select name="guide.bthplc" list="birthPlaceList" listKey="label" listValue="value"></s:select></td>
    </tr>
    <tr>
      <td class="idx">身份证号:</td>
      <td class="data" colspan="3"><s:textfield name="guide.card" size="20" maxlength="18"></s:textfield></td>
    </tr>
    <!--  
        <tr>
            <td class="idx">身&nbsp;&nbsp;&nbsp;&nbsp;高:</td>
            <td class="data"><s:textfield name="guide.high" size="20"
                maxlength="18"></s:textfield>cm</td>
            <td class="idx">体&nbsp;&nbsp;&nbsp;&nbsp;重:</td>
            <td class="data"><s:textfield name="guide.weight" size="20"
                maxlength="18"></s:textfield>kg</td>
            <td class="idx">血&nbsp;&nbsp;&nbsp;&nbsp;型:</td>
            <td class="data"><s:select name="guide.bloodType" list="bloodTypeList"
                listKey="value" listValue="label"></s:select></td>
        </tr>
        -->
    <tr>
      <td class="idx">联系电话:</td>
      <td class="data"><s:textfield name="guide.tel" size="20" maxlength="20"></s:textfield></td>
      <td class="idx">手 &nbsp;&nbsp; 机:</td>
      <td class="data"><s:textfield name="guide.mobile" size="20" maxlength="20"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">E_mail:</td>
      <td class="data" colspan="3"><s:textfield name="guide.EMail" size="50" maxlength="50"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">地 &nbsp;&nbsp; 址:</td>
      <td class="data" colspan="3"><s:textfield name="guide.address" size="50" maxlength="50"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">是否有导游证:</td>
      <td class="data"><s:select name="guide.tourKey" list="holdKeyList" listKey="value" listValue="label"></s:select></td>
      <td class="idx">导游证号:</td>
      <td class="data"><s:textfield name="guide.tourCard" size="20" maxlength="18"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">年检时间:</td>
      <td class="data"><sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="guide.checkDate" /></td>
      <td class="idx">IC卡登记号:</td>
      <td class="data"><s:textfield name="guide.icCard" size="20" maxlength="18"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">是否有领队证:</td>
      <td class="data"><s:select name="guide.leaderKey" list="holdKeyList" listKey="value" listValue="label"></s:select></td>
      <td class="idx">领队证号:</td>
      <td class="data"><s:textfield name="guide.leadCard" size="20" maxlength="18"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">领队证有效期:</td>
      <td class="data" colspan="3"><sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="guide.dateStart"
        />至 <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="guide.dateEnd" /></td>
    </tr>
    <tr>
      <td class="idx">护照种类:</td>
      <td class="data"><s:select name="guide.hzzl" list="hzzlList" listKey="value" listValue="label"
        emptyOption="true"></s:select></td>
      <td class="idx">护 照 号:</td>
      <td class="data"><s:textfield name="guide.hzno" size="20" maxlength="20"></s:textfield></td>
      <td class="idx">护照签发地:</td>
      <td class="data"><s:select name="guide.hzadd" list="hzaddList" listKey="label" listValue="value"
        emptyOption="true">
      </s:select></td>
    </tr>
    <tr>
      <td class="idx">护照有效期:</td>
      <td class="data" colspan="3"><sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="guide.hzdate1" />至
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="guide.hzrang" /></td>
    </tr>
    <!--  
        <tr>
            <td class="idx">职&nbsp;&nbsp;&nbsp;&nbsp;业:</td>
            <td class="data"><s:select name="guide.business" list="businessList"
                listKey="code" listValue="name"></s:select></td>
            <td class="idx">职&nbsp;&nbsp;&nbsp;&nbsp;称:</td>
            <td class="data"><s:textfield name="guide.duty" size="20"
                maxlength="18"></s:textfield></td>
            <td class="idx">是否签约:</td>
            <td class="data"><s:select name="guide.signKey" list="signKeyList"
                listKey="value" listValue="label"></s:select></td>
        </tr>
        -->
    <tr>
      <td class="idx">分类(一):</td>
      <td class="data"><s:select name="guide.workType1" list="workType1List" listKey="value" listValue="label"></s:select></td>
      <td class="idx">分类(二):</td>
      <td class="data"><s:select name="guide.workType2" list="workType2List" listKey="value" listValue="label"></s:select></td>
      <td class="idx">可带团类型:</td>
      <td class="data"><s:textfield name="guide.remarks1" size="20" maxlength="18"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">性 &nbsp;&nbsp; 格:</td>
      <td class="data"><s:textfield name="guide.character" size="20" maxlength="20"></s:textfield></td>
      <td class="idx">爱 &nbsp;&nbsp; 好:</td>
      <td class="data"><s:textfield name="guide.taste" size="20" maxlength="20"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">特 &nbsp;&nbsp; 长:</td>
      <td class="data" colspan="3"><s:textfield name="guide.speciality" size="50" maxlength="50"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">毕业学校:</td>
      <td class="data" colspan="3"><s:textfield name="guide.finishSchool" size="50" maxlength="50"></s:textfield></td>
      <td class="idx">毕业时间:</td>
      <td class="data"><sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="guide.finishDate" /></td>
    </tr>
    <tr>
      <td class="idx">工作经历(250字以内):</td>
      <td class="data" colspan="3"><s:textarea name="guide.workRemark" cols="80" rows="5" /></td>
    </tr>
    <tr>
      <td colspan="6">&nbsp;<font color="red">*</font>&nbsp;为必填项目！</td>
    </tr>
  </table>
  <table border="0" cellpadding="2" cellspacing="0" width="0%">
    <tr>
      <td align="center"><s:if test='guide.accCd eq ""'>
        <s:submit value="添加" />
      </s:if><s:else>
        <s:submit value="修改" />
      </s:else> <input type="button" value="返 回" onclick="javascript:cancel()"></td>
    </tr>
  </table>
</s:form>
</body>
</html>
