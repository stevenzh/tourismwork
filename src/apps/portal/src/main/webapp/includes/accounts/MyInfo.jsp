<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>会员信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
</head>

<body>
<script type="text/javascript">
<!--//

function change()
{
  var fm = document.Register;
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
//-->
</script>

<table style="width: 100%">
  <tr>
    <td bgcolor="#99ccff">
      <table align="center" border="0" style="width: 100%">
          <tr>
          <td align="center" width="100">
            <a href="<s:url action='showMyBasicInfo' namespace='/accounts' includeParams='none' />">基本信息查询</a>&nbsp;&nbsp;|
            </td>
            <td align="center" width="100">
            <a href="<s:url action='showMyInfo' namespace='/accounts' includeParams='none' />">基本信息修改</a>&nbsp;&nbsp;|
            </td>
            <td align="center" width="80">
            <a href="<s:url action='ModifyPasswordMyInfo' namespace='/accounts' includeParams='none' />">修改密码</a>&nbsp;&nbsp;|
            </td>
            <td align="center">&nbsp;</td>
          </tr>
        </table>
    </td>
  </tr>
   
  <tr>
    <td bgcolor="#99ccff">
    <s:form action="submitMyInfo" namespace="/accounts" method="POST"
      theme="simple">
      <table align="center" bgcolor="#ffffff" border="0" cellspacing="5" style="width: 100%">
        <tr bgcolor="#bbbbff">
          <td align="center" colspan="2"><strong>基本信息修改</strong></td>
        </tr>
        <tr>
        <tr>
          <td align="right" width="120">用户名：</td>
          <td><s:hidden name="customer.uid"></s:hidden> <s:property
            value="customer.uid" /></td>
        </tr>

        <tr>
          <td align="right">新密码：</td>
          <td><s:password name="customer.passwd"
            maxLength="20" required="true">
          </s:password> <span class="required">*</span>（6位以上数字、英文字母、特殊符号组成）</td>
        </tr>
        <tr>
          <td align="right">确认密码：</td>
          <td><s:password name="customer.confirmPassword"
            maxLength="20" required="true">
          </s:password></td>
        </tr>
        <tr>
          <td align="right">真实名称：</td>
          <td><s:textfield name="customer.userName"
            maxLength="40">
          </s:textfield> <span class="required">*</span></td>
        </tr>
        <tr>
          <td align="right">手机号：</td>
          <td><s:textfield name="customer.mobile"
            maxLength="11" required="true">
          </s:textfield> <span class="required">*</span></td>
        </tr>
        <tr>
          <td align="right">地址：</td>
          <td><s:textarea name="customer.address"
            rows="3" cols="50">
          </s:textarea></td>
        </tr>
        <tr>
          <td valign="top" align="right">身份证号：</td>
          <td><s:textfield name="customer.idCard"
            maxLength="18" required="true">
          </s:textfield> <span class="required">*</span><br>
          请填写真实有效的身份证号码</td>
          </td>
        </tr>
        <tr>
          <td valign="top" align="right">称呼：</td>
          <td><s:radio name="customer.sex" list="sexs"
            listKey="value" listValue="label">
          </s:radio></td>
        </tr>
        <tr>
          <td valign="top" align="right">E-MAIL：</td>
          <td><s:textfield name="customer.email"
            maxLength="25" required="true">
          </s:textfield></td>
        </tr>

        <tr>
          <td align="right" width="120">生日：</td>
          <td><s:textfield name="customer.birthday"
           >
          </s:textfield></td>
        </tr>
        <tr>
          <td align="right" width="120">电话：</td>
          <td><s:textfield name="customer.phone"
           >
          </s:textfield></td>
        </tr>
        <tr>
          <td align="right" width="120">传真：</td>
          <td><s:textfield name="customer.fax"
           >
          </s:textfield></td>
        </tr>
        <!-- 
  <tr>
    <td align="right" width="120">所在国家：</td>
    <td>
      <s:select id="Nation"
                name="customer.nation"
               
                list="nations"
                listKey="code"
                listValue="cnName"
                onchange="javascript:change();">
      </s:select>
    </td>
  </tr>
   -->
        <tr>
          <td align="right">所在省份：</td>
          <td><s:select name="customer.province"
            list="provinces" listKey="code"
            listValue="cnName">
          </s:select></td>
        </tr>
        <tr>
          <td align="right">所在城市：</td>
          <td><s:select id="City" name="customer.city"
            list="citys" listKey="citycd"
            listValue="citynm">
          </s:select></td>
        </tr>
        <tr>
          <td align="right">邮政编码：</td>
          <td><s:textfield name="customer.postcode"
           >
          </s:textfield></td>
        </tr>

        <tr>
          <td align="right">是否接收E-mail：</td>
          <td><s:radio name="customer.receiveMail" list="yesOrNo"
            listKey="value" listValue="label">
          </s:radio></td>
        </tr>

        <tr>
          <td align="right">职业：</td>
          <td><s:select name="customer.vocation" list="vocations"
            listKey="value" listValue="label"
            emptyOption="true">
          </s:select></td>
        </tr>

        <tr>
          <td align="right">教育程度：</td>
          <td><s:select name="customer.educate" list="educates"
            listKey="value" listValue="label"
            emptyOption="true">
          </s:select></td>
        </tr>

        <tr>
          <td align="right">每月家庭收入：</td>
          <td><s:select name="customer.householdIncome"
            list="hiList" listKey="value" listValue="label"
            emptyOption="true">
          </s:select></td>
        </tr>

        <tr>
          <td align="right">每月个人收入：</td>
          <td><s:select name="customer.personalIncome"
            list="piList" listKey="value" listValue="label"
            emptyOption="true">
          </s:select></td>
        </tr>

        <tr>
          <td align="right">平均每年外游次数：</td>
          <td><s:select name="customer.tripTimes" list="ttList"
            listKey="value" listValue="label" emptyOption="true">
          </s:select></td>
        </tr>

        <tr>
          <td align="right">每次旅行的平均花费：</td>
          <td><s:select name="customer.expendOneTrip" list="etList"
            listKey="value" listValue="label" emptyOption="true">
          </s:select></td>
        </tr>

        <tr>
          <td></td>
          <td><input onclick="aaa();" type="radio" checked
            value="0" name="cardtype"> 已有会员卡,请直接输入 <input
            onclick="bbb();" type="radio" value="1" name="cardtype">
          尚无卡号,直接申请</td>
        </tr>

        <!-- 
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
    <span class="fontcheng">(凡有新我社会员卡的手机注册用户，在这里成功登记会员卡号，将给予首次登陆积分奖励100分。)</span> 
    </td>
  </tr>
   -->

        <tr align="right">
          <td colspan="2"><s:submit value="确  定"></s:submit>
          <input name="cancel" type="button"
            onclick="history.go(-1);" value="取&nbsp;&nbsp;消"></td>
        </tr>
      </table>
    </s:form>

    </td>
  </tr>
</table>
</body>
</html>

