<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>港澳游客人名单表打印</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="港澳游客人名单表打印">
</head>

<body>
<script type="text/javascript">
function printGACustomerList(param)
{
  var fm = document.fopReport;
  document.getElementById('paramid').value = param;
  
  document.getElementById('pla1').value = document.getElementById('pla1x').value;
  document.getElementById('pass1').value = document.getElementById('pass1x').value;
  document.getElementById('end1').value = document.getElementById('end1x').value;
  document.getElementById('pla2').value = document.getElementById('pla2x').value;
  document.getElementById('pass2').value = document.getElementById('pass2x').value;
  document.getElementById('end2').value = document.getElementById('end2x').value;
  document.getElementById('pla3').value = document.getElementById('pla3x').value;
  document.getElementById('pass3').value = document.getElementById('pass3x').value;
  document.getElementById('end3').value = document.getElementById('end3x').value;
  
  document.getElementById('arrHKtime').value = document.getElementById('arrHKtime1').value;
  document.getElementById('arrHKvehicle').value = document.getElementById('arrHKvehicle1').value;
  document.getElementById('leaveHKtime').value = document.getElementById('leaveHKtime1').value;
  document.getElementById('leaveHKvehicle').value = document.getElementById('leaveHKvehicle1').value;
  
  document.getElementById('arrMCtime').value = document.getElementById('arrMCtime1').value;
  document.getElementById('arrMCvehicle').value = document.getElementById('arrMCvehicle1').value;
  document.getElementById('leaveMCtime').value = document.getElementById('leaveMCtime1').value;
  document.getElementById('leaveMCvehicle').value = document.getElementById('leaveMCvehicle1').value;
  
  document.getElementById('malePax').value = document.getElementById('malePax1').value;
  document.getElementById('femalePax').value = document.getElementById('femalePax1').value;
  document.getElementById('childPax').value = document.getElementById('childPax1').value;
  document.getElementById('pax').value = document.getElementById('pax1').value;
  
  document.getElementById('localtname').value = document.getElementById('localtname1').value;
  document.getElementById('hktname').value = document.getElementById('hktname1').value;
  document.getElementById('mctname').value = document.getElementById('mctname1').value;
  document.getElementById('localecontact').value = document.getElementById('localecontact1').value;
  document.getElementById('hkecontact').value = document.getElementById('hkecontact1').value;
  document.getElementById('mcecontact').value = document.getElementById('mcecontact1').value;
  
  document.getElementById('tnum').value = document.getElementById('tnum1').value;
  document.getElementById('leader').value = document.getElementById('leader1').value;
  document.getElementById('leadnum').value = document.getElementById('leadnum1').value;
  fm.submit();
}
</script>

<s:form action="fopReport" namespace="/" method="POST" theme="simple">
	<table border="0" style="width: 100%">
		<tr>
			<td>团号：</td>
			<td><s:textfield id="tnum1"/></td>
		</tr>
		<tr>
			<td>领队：</td>
			<td><s:select id="leader1"
                    name="tour.leader"
                    list="leaders"
                    listKey="label"
                    listValue="label"
                    emptyOption="true">
        </s:select>&nbsp;&nbsp;&nbsp;领队证号：<s:textfield id="leadnum1"/></td>
			<td></td>
		</tr>
		<tr>
			<td>
				<hr>
			</td>
		</tr>
		<tr>
			<td>行程：</td>
			<td><s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" id="date1" name="date1">
			</s:textfield>由<s:textfield id="pla1x"/>经<s:textfield id="pass1x"/>至<s:textfield id="end1x"/></td>
		</tr>
		<tr>
			<td></td>
			<td><s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" id="date2" name="date2">
			</s:textfield>由<s:textfield id="pla2x"/>经<s:textfield id="pass2x"/>至<s:textfield id="end2x"/></td>
		</tr>
		<tr>
			<td></td>
			<td><s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" id="date3" name="date3">
			</s:textfield>由<s:textfield id="pla3x"/>经<s:textfield id="pass3x"/>至<s:textfield id="end3x"/></td>
		</tr>
		<tr>
			<td></td>
			<td><s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" id="arrHKdate" name="arrHKdate">
			</s:textfield><s:textfield id="arrHKtime1"/>时&nbsp;抵港&nbsp;&nbsp;交通<s:textfield id="arrHKvehicle1"/></td>
		</tr>
		<tr>
			<td></td>
			<td><s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" id="leaveHKdate" name="leaveHKdate">
			</s:textfield><s:textfield id="leaveHKtime1"/>时&nbsp;离港&nbsp;&nbsp;交通<s:textfield id="leaveHKvehicle1"/></td>
		</tr>
		<tr>
			<td></td>
			<td><s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" id="arrMCdate" name="arrMCdate">
			</s:textfield><s:textfield id="arrMCtime1"/>时&nbsp;抵澳&nbsp;&nbsp;交通<s:textfield id="arrMCvehicle1"/></td>
		</tr>
		<tr>
			<td></td>
			<td><s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" id="leaveMCdate" name="leaveMCdate">
			</s:textfield><s:textfield id="leaveMCtime1"/>时&nbsp;离澳&nbsp;&nbsp;交通<s:textfield id="leaveMCvehicle1"/></td>
		</tr>
		<tr>
			<td>人数：</td>
      <td>男：<s:textfield id="malePax1" name="tour.malePax" size="10"/>
                           女：<s:textfield  id="femalePax1" name="tour.femalePax" size="10" />
			  <br>
                          小童：<s:textfield id="childPax1" name="tour.childPax" size="10" />
                          合计：<s:textfield id="pax1" 	name="tour.pax" size="10" />
      </td>
		</tr>
		<tr>
			<td>
				<hr>
			</td>
		</tr>
		<tr>
			<td>内地组团社名称：</td>
			<td align="left"><s:textfield id="localtname1" name="localtname1" value="旅行社" size="40"/>联络人员姓名及电话：<s:textfield id="localecontact1" name="tour.localEcontact"/></td>
		</tr>
		<tr>
			<td>香港接待社名称：</td>
			<td align="left"><s:textfield id="hktname1" name="hktname1" size="40"/>联络人员姓名及电话：<s:textfield id="hkecontact1" name="hkecontact1"/></td>
		</tr>
		<tr>
			<td>澳门接待社名称：</td>
			<td align="left"><s:textfield id="mctname1" name="mctname1" size="40"/>联络人员姓名及电话：<s:textfield id="mcecontact1" name="mcecontact1"/></td>
		</tr>
	</table>
	<br>

	<table border="0" style="width: 100%">
	<tr>
	<td>
		<input type="button" value="打印港澳游名单"
			onclick="javascript:printGACustomerList('<s:property value='tour.tourNo' />')" />
    </td>
    </tr>
	</table>

	<s:hidden name="tourNum"></s:hidden>
	<s:hidden name="parameters(0).name" value="TOUR_NO"></s:hidden>
	<s:hidden id="paramid" name="parameters(0).data"></s:hidden>
	<s:hidden name="parameters(2).name" value="PLA1"></s:hidden>
	<s:hidden id="pla1" name="parameters(2).data"></s:hidden>
	<s:hidden name="parameters(3).name" value="PASS1"></s:hidden>
	<s:hidden id="pass1" name="parameters(3).data"></s:hidden>
	<s:hidden name="parameters(4).name" value="END1"></s:hidden>
	<s:hidden id="end1" name="parameters(4).data"></s:hidden>
	
	<s:hidden name="parameters(6).name" value="PLA2"></s:hidden>
	<s:hidden id="pla2" name="parameters(6).data"></s:hidden>
	<s:hidden name="parameters(7).name" value="PASS2"></s:hidden>
	<s:hidden id="pass2" name="parameters(7).data"></s:hidden>
	<s:hidden name="parameters(8).name" value="END2"></s:hidden>
	<s:hidden id="end2" name="parameters(8).data"></s:hidden>
	
	<s:hidden name="parameters(10).name" value="PLA3"></s:hidden>
	<s:hidden id="pla3" name="parameters(10).data"></s:hidden>
	<s:hidden name="parameters(11).name" value="PASS3"></s:hidden>
	<s:hidden id="pass3" name="parameters(11).data"></s:hidden>
	<s:hidden name="parameters(12).name" value="END3"></s:hidden>
	<s:hidden id="end3" name="parameters(12).data"></s:hidden>
	
	<s:hidden name="parameters(14).name" value="ARRHKTIME"></s:hidden>
	<s:hidden id="arrHKtime" name="parameters(14).data"></s:hidden>
	<s:hidden name="parameters(15).name" value="ARRHKVEHICLE"></s:hidden>
	<s:hidden id="arrHKvehicle" name="parameters(15).data"></s:hidden>
	<s:hidden name="parameters(17).name" value="LEAVEHKTIME"></s:hidden>
	<s:hidden id="leaveHKtime" name="parameters(17).data"></s:hidden>
	<s:hidden name="parameters(18).name" value="LEAVEHKVEHICLE"></s:hidden>
	<s:hidden id="leaveHKvehicle" name="parameters(18).data"></s:hidden>
	
	<s:hidden name="parameters(20).name" value="ARRMCTIME"></s:hidden>
	<s:hidden id="arrMCtime" name="parameters(20).data"></s:hidden>
	<s:hidden name="parameters(21).name" value="ARRMCVEHICLE"></s:hidden>
	<s:hidden id="arrMCvehicle" name="parameters(21).data"></s:hidden>
	<s:hidden name="parameters(23).name" value="LEAVEMCTIME"></s:hidden>
	<s:hidden id="leaveMCtime" name="parameters(23).data"></s:hidden>
	<s:hidden name="parameters(24).name" value="LEAVEMCVEHICLE"></s:hidden>
	<s:hidden id="leaveMCvehicle" name="parameters(24).data"></s:hidden>
	
	<s:hidden name="parameters(25).name" value="MALEPAX"></s:hidden>
	<s:hidden id="malePax" name="parameters(25).data"></s:hidden>
	<s:hidden name="parameters(26).name" value="FEMALEPAX"></s:hidden>
	<s:hidden id="femalePax" name="parameters(26).data"></s:hidden>
	<s:hidden name="parameters(27).name" value="CHILDPAX"></s:hidden>
	<s:hidden id="childPax" name="parameters(27).data"></s:hidden>
	<s:hidden name="parameters(28).name" value="PAX"></s:hidden>
	<s:hidden id="pax" name="parameters(28).data"></s:hidden>
	
	<s:hidden name="parameters(29).name" value="LOCALTNAME"></s:hidden>
	<s:hidden id="localtname" name="parameters(29).data"></s:hidden>
	<s:hidden name="parameters(30).name" value="HKTNAME"></s:hidden>
	<s:hidden id="hktname" name="parameters(30).data"></s:hidden>
	<s:hidden name="parameters(31).name" value="MCTNAME"></s:hidden>
	<s:hidden id="mctname" name="parameters(31).data"></s:hidden>
	<s:hidden name="parameters(32).name" value="LOCALECON"></s:hidden>
	<s:hidden id="localecontact" name="parameters(32).data"></s:hidden>
	<s:hidden name="parameters(33).name" value="HKECON"></s:hidden>
	<s:hidden id="hkecontact" name="parameters(33).data"></s:hidden>
	<s:hidden name="parameters(34).name" value="MCECON"></s:hidden>
	<s:hidden id="mcecontact" name="parameters(34).data"></s:hidden>
	
	<s:hidden name="parameters(35).name" value="TNUM"></s:hidden>
	<s:hidden id="tnum" name="parameters(35).data"></s:hidden>
	<s:hidden name="parameters(36).name" value="LEADER"></s:hidden>
	<s:hidden id="leader" name="parameters(36).data"></s:hidden>
	<s:hidden name="parameters(37).name" value="LEADNUM"></s:hidden>
	<s:hidden id="leadnum" name="parameters(37).data"></s:hidden>

	<s:hidden name="reportId" value="8"></s:hidden>
</s:form>
</body>
</html>
