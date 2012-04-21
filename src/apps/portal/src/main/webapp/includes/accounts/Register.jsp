<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>会员注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<script type="text/javascript">
<!--//

function change()
{
  var fm = document.submitRegister;
  var departments = document.getElementById("Nation");
  var combo = document.getElementById("City")
  var sdpt_no = departments.value;

  //更换目的地的内容
  //将原表中的内容清空
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;
  
  var call = new WS.Call('<s:url value="/services/CityService" encode="false" includeParams="none"/>'); 
  var nsuri = 'http://www.opentravelsoft.com';
  var qn_op = new WS.QName('getCitysByNation',nsuri);
  var qn_op_resp = new WS.QName('getCitysByNationResponse',nsuri);  
  call.invoke_rpc(
    qn_op,
    new Array(
      {name:'name',value:sdpt_no}
    ),null,
    function(call,envelope) {
      var ret = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
      var empls = ret.split(',');

      if(empls.length > 0)
      {
        var kOption = document.createElement('OPTION');
        kOption.text = "";
        kOption.value = "";
        combo.options.add(kOption);

        for(var i=0;i<empls.length -1;i=i+2)
        {
          var oOption = document.createElement('OPTION');
          oOption.text = empls[i+1];
          oOption.value = empls[i];
          combo.options.add(oOption);
        }
      }
    }
  );
}

function preCheck()
{
  var uid = document.getElementById("customer_uid").value;
  if (uid == '') return;
  
  var call = new WS.Call('<s:url value="/services/MemberService" encode="false" includeParams="none"/>'); 
  var nsuri = 'http://www.opentravelsoft.com';
  var qn_op = new WS.QName('validUid',nsuri);
  var qn_op_resp = new WS.QName('validUidResponse',nsuri);  
  call.invoke_rpc(
    qn_op,
    new Array(
      {name:'name',value:uid}
    ),null,
    function(call,envelope) {
      var ret = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
      if (ret == "false")
      {
        document.getElementById("customer_uid").value = "";
        var ou = document.getElementById("valid");
        alert('该用户名已经存在！');
      } else if (ret == "true") {
        alert('该用户名可以使用！');
      }
    }
  );
  
}
      
//-->
</script>
<table align="center" border="0" width="60%">
  <tr>
    <td align="center" height="30">
    <strong>会员注册</strong>
    </td>
  </tr>
</table>

<table align="center" border="0" width="60%">
  <tr>
    <td height="45">
    <table align="center" bgcolor="#f8c18b" border="0" style="width: 100%">
      <tr>
        <td bgcolor="#fdf7ee" height="25">
        <img src="<s:url value='/images/warning.gif'/>" height="14" hspace="6" width="14">带<font color="red">*</font>的栏目必须填写，否则不能够注册。</td>
      </tr>
    </table>
  </td>
  </tr>
</table>

<s:form action="submitRegister" namespace="/accounts" method="POST" theme="simple">
<table align="center" border="0" cellpadding="1" width="60%">
  <tr>
    <td align="right" width="120">用户名：</td>
    <td>
    <s:textfield id="customer_uid"
                 name="customer.uid"
                
                 required="true"
                 maxLength="100">
    </s:textfield>
    <span class="required">*</span>
    <input name="button" type="button" onClick="preCheck();" value=" 预检查 ">
    <div id="valid"></div>
    </td>
  </tr>

  <tr>
    <td align="right">用户密码：</td>
    <td>
    <s:password name="customer.passwd"
                
                 maxLength="20"
                 required="true">
    </s:password>
    <span class="required">*</span>（6位以上数字、英文字母、特殊符号组成）
    </td>
  </tr>
  <tr>
    <td align="right">确认密码：</td>
    <td>
    <s:password name="customer.confirmPassword"
                
                 maxLength="20"
                 required="true">
    </s:password>
    </td>
  </tr>
  <!-- 
  <tr>
    <td align="right">真实名称：</td>
    <td>
      <s:textfield name="customer.userName"
                   
                    maxLength="40">
      </s:textfield>
      <span class="required">*</span>
    </td>
  </tr>
   -->

  <tr>
    <td align="right">手机号：</td>
    <td>
      <s:textfield name="customer.mobile"
                   
                    maxLength="11"
                    required="true">
      </s:textfield>
      <span class="required">*</span></td>
  </tr>
  
  <!-- 
  <tr>
    <td align="right" valign="top">地址：</td>
    <td>
      <s:textarea name="customer.address"
                 
                  rows="3"
                  cols="50">
      </s:textarea>
    </td>
  </tr>

  <tr>
    <td valign="top" align="right">身份证号：</td>
    <td>
      <s:textfield name="customer.idCard"
                   
                    maxLength="18"
                    required="true">
      </s:textfield>
      <span class="required">*</span><br>请填写真实有效的身份证号码</td>
    </td>
  </tr>
   -->

  <tr>
    <td valign="top" align="right">称呼：</td>
    <td>
      <s:radio name="customer.sex" 
               list="sexs"
               listKey="value"
               listValue="label">
      </s:radio>
      </td>
  </tr>
  <tr>
    <td valign="top" align="right">E-MAIL：</td>
    <td>
      <s:textfield name="customer.email"
                   
                    maxLength="25"
                    required="true">
      </s:textfield>
    </td>
  </tr>
  <!-- 
  <tr>
    <td align="right">验证码：</td>
    <td>
      <input name=":TextBox_Validate" id="_TextBox_Validate" size="26" type="text">
      <img src="MemberRegister.aspx_files/5d750f89-58bf-4fef-b219-e48f0afd994b.jpg" border="0">
    </td>
  </tr>
   -->

  <!-- 
  <tr>
    <td colspan="2"><strong>客户基本资料</strong></td>
  </tr>
  <tr>
    <td align="right" width="120">电话：</td>
    <td>
      <s:textfield name="customer.phone"
                  >
      </s:textfield>
    </td>
  </tr>
  <tr>
    <td align="right" width="120">传真：</td>
    <td>
      <s:textfield name="customer.fax"
                  >
      </s:textfield>
    </td>
  </tr>

  <tr>
    <td align="right" width="120">所在国家：</td>
    <td>
      <s:select id="Nation"
                name="customer.nation"
               
                list="nations"
                listKey="code"
                listValue="cnName">
      </s:select>
    </td>
  </tr>

  <tr>
    <td align="right">所在省份：</td>
    <td>
      <s:select name="customer.province"
               
                list="provinces"
                listKey="code"
                listValue="cnName">
      </s:select>
    </td>
  </tr>
  <tr>
    <td align="right">所在城市：</td>
    <td>
      <s:select id="City"
                name="customer.city"
               
                list="citys"
                listKey="citycd"
                listValue="citynm">
      </s:select>
    </td>
  </tr>
  <tr>
    <td align="right">邮政编码：</td>
    <td>
      <s:textfield name="customer.postcode"
                   >
      </s:textfield>
    </td>
  </tr>
   -->
  <tr>
    <td align="right">是否接收E-mail：</td>
    <td>
      <s:radio name="customer.receiveMail"
                list="yesOrNo"
                listKey="value"
                listValue="label"
               >
      </s:radio>
    </td>
  </tr>

  <!-- 
  <tr>
    <td align="right">职业：</td>
    <td>
      <s:select name="customer.vocation"
                list="vocations"
                listKey="value"
                listValue="label"
               
                emptyOption="true">
      </s:select>
    </td>
  </tr>

  <tr>
    <td align="right">教育程度：</td>
    <td>
      <s:select name="customer.educate"
                list="educates"
                listKey="value"
                listValue="label"
               
                emptyOption="true">
      </s:select>
    </td>
  </tr>

  <tr>
    <td align="right">每月家庭收入：</td>
    <td>
      <s:select name="customer.householdIncome"
                list="hiList"
                listKey="value"
                listValue="label"
                emptyOption="true">
      </s:select>
    </td>
  </tr>

  <tr>
    <td align="right">每月个人收入：</td>
    <td>
      <s:select name="customer.personalIncome"
                list="piList"
                listKey="value"
                listValue="label"
                emptyOption="true">
      </s:select>
    </td>
  </tr>

  <tr>
    <td align="right">平均每年外游次数：</td>
    <td>
      <s:select name="customer.tripTimes"
                list="ttList"
                listKey="value"
                listValue="label"
                emptyOption="true">
      </s:select>
    </td>
  </tr>

  <tr>
    <td align="right">每次旅行的平均花费：</td>
    <td>
      <s:select name="customer.expendOneTrip"
                list="etList"
                listKey="value"
                listValue="label"
                emptyOption="true">
      </s:select>
    </td>
  </tr>

  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;

      <input onclick="aaa();" type="radio" checked value="0" name="cardtype"> 已有会员卡,请直接输入
      <input onclick="bbb();" type="radio" value="1" name="cardtype"> 尚无卡号,直接申请
    </td>
  </tr>
  <tr>
    <td valign="top" align="right">会员卡号：</td>
    <td>
    <script type="text/javascript">
    function aaa()
    {
      var cardno = document.getElementById('cardno');
      var cardinfo = document.getElementById('cardinfo');
      cardno.style.visibility='visible';
      cardinfo.innerText = '';
    }
    function bbb()
    {
      var cardno = document.getElementById('cardno');
      var cardinfo = document.getElementById('cardinfo');
      cardno.style.visibility='hidden';
      cardinfo.innerText = '保存后自动生成会员卡号';
    }
    </script>
    <s:textfield id="cardno"
                  name="customer.cardNo"
                 
                  size="11"
                  maxLength="11">
    </s:textfield>
    <div id="cardinfo"></div>
    <br>
    <span class="fontcheng">(凡有我社会员卡的手机注册用户，在这里成功登记会员卡号，将给予首次登陆积分奖励100分。)</span> 
    </td>
  </tr>
   -->
  <tr align="right">
    <td colspan="2">
      <s:submit value="确  定"></s:submit>
      <input name="cancel" type="button" onclick="history.go(-1);" value="取&nbsp;&nbsp;消" >
    </td>
  </tr>
</table>
</s:form>

</body>
</html>

