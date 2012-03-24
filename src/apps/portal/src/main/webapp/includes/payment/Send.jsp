<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<jsp:useBean id="MD5" scope="request" class="com.opentravelsoft.util.MD5" />
<%
	//***********************************************
	//初始化定义参数
	String v_mid, key, v_url, v_oid, v_amount, v_moneytype, v_md5info; //定义必须传递的参数变量

	v_mid = "1001"; // 商户号，这里为测试商户号1001，替换为自己的商户号(老版商户号为4位或5位,新版为8位)即可
	v_url = "http://localhost/chinabank/Receive.jsp"; // 商户自定义返回接收支付结果的页面
	key = "test"; // 如果您还没有设置MD5密钥请登陆我们为您提供商户后台，地址：https://merchant3.chinabank.com.cn/
	// 登陆后在上面的导航栏里可能找到“B2C”，在二级导航栏里有“MD5密钥设置”
	// 建议您设置一个16位以上的密钥或更高，密钥最多64位，但设置16位已经足够了
	//**********************************************

	//以上三项必须修改

	if (request.getParameter("v_oid") != null
			&& !request.getParameter("v_oid").equals("")) //判断是否有传递订单号
	{
		v_oid = request.getParameter("v_oid");
	} else {
		Date currTime = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd-" + v_mid
				+ "-hhmmss", Locale.US);
		v_oid = sf.format(currTime); // 推荐订单号构成格式为 年月日-商户号-小时分钟秒
	}
	v_amount = request.getParameter("v_amount"); // 订单金额                   
	v_moneytype = "CNY"; // 币种
	v_md5info = ""; // 对拼凑串MD5私钥加密后的值

	String text = v_amount + v_moneytype + v_oid + v_mid + v_url + key; // 拼凑加密串
	v_md5info = MD5.getMD5ofStr(text); // 网银支付平台对MD5值只认大写字符串，所以小写的MD5值得转换为大写

	//************以下几项为非必填项**************
	String v_rcvname, v_rcvaddr, v_rcvtel, v_rcvpost, v_rcvemail, v_rcvmobile; //定义非必需参数

	v_rcvname = request.getParameter("v_rcvname"); // 收货人
	v_rcvaddr = request.getParameter("v_rcvaddr"); // 收货地址
	v_rcvtel = request.getParameter("v_rcvtel"); // 收货人电话
	v_rcvpost = request.getParameter("v_rcvpost"); // 收货人邮编
	v_rcvemail = request.getParameter("v_rcvemail"); // 收货人邮件
	v_rcvmobile = request.getParameter("v_rcvmobile"); // 收货人手机号

	String v_ordername, v_orderaddr, v_ordertel, v_orderpost, v_orderemail, v_ordermobile;

	v_ordername = request.getParameter("v_ordername"); // 订货人姓名
	v_orderaddr = request.getParameter("v_orderaddr"); // 订货人地址
	v_ordertel = request.getParameter("v_ordertel"); // 订货人电话
	v_orderpost = request.getParameter("v_orderpost"); // 订货人邮编
	v_orderemail = request.getParameter("v_orderemail"); // 订货人邮件
	v_ordermobile = request.getParameter("v_ordermobile"); // 订货人手机号

	String remark1, remark2;

	remark1 = request.getParameter("remark1"); //备注字段1
	remark2 = request.getParameter("remark2"); //备注字段2
%>

<!--以下信息为标准的 HTML 格式 + ASP 语言 拼凑而成的 网银在线 支付接口标准演示页面 无需修改-->

<html>

<body onLoad="javascript:document.E_FORM.submit()">
  <form action="https://pay3.chinabank.com.cn/PayGate" method="POST" name="E_FORM">
    <input type="hidden" name="v_md5info" value="<%=v_md5info%>" size="100">
    <input type="hidden" name="v_mid" value="<%=v_mid%>">
    <input type="hidden" name="v_oid" value="<%=v_oid%>">
    <input type="hidden" name="v_amount" value="<%=v_amount%>">
    <input type="hidden" name="v_moneytype" value="<%=v_moneytype%>">
    <input type="hidden" name="v_url" value="<%=v_url%>">
    
    <!--以下几项项为网上支付完成后，随支付反馈信息一同传给信息接收页 -->
    <input type="hidden" name="remark1" value="<%=remark1%>">
    <input type="hidden" name="remark2" value="<%=remark2%>">
    
    <!--以下几项只是用来记录客户信息，可以不用，不影响支付 -->
    <input type="hidden" name="v_rcvname" value="<%=v_rcvname%>">
    <input type="hidden" name="v_rcvaddr" value="<%=v_rcvaddr%>">
    <input type="hidden" name="v_rcvtel" value="<%=v_rcvtel%>">
    <input type="hidden" name="v_rcvpost" value="<%=v_rcvpost%>">
    <input type="hidden" name="v_rcvemail" value="<%=v_rcvemail%>">
    <input type="hidden" name="v_rcvmobile" value="<%=v_rcvmobile%>">
    
    <input type="hidden" name="v_ordername" value="<%=v_ordername%>">
    <input type="hidden" name="v_orderaddr" value="<%=v_orderaddr%>">
    <input type="hidden" name="v_ordertel" value="<%=v_ordertel%>">
    <input type="hidden" name="v_orderpost" value="<%=v_orderpost%>">
    <input type="hidden" name="v_orderemail" value="<%=v_orderemail%>">
    <input type="hidden" name="v_ordermobile" value="<%=v_ordermobile%>">
  </form>
</body>
</html>
