<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改签证信息</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="修改签证信息">
</head>
<body>

<script type="text/javascript">

function SubmitDeleteItem(param, target)
{
  var fm = document.submitVisa;
  fm.idx.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='editVisaDelItem' namespace='/product'/>"
  }
  fm.submit();
}

function SubmitDeleteAttached(param, target)
{
  var fm = document.submitVisa;
  fm.attachedId.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='delVisaAttached' namespace='/product'/>"
  }
  fm.submit();
}
</script>
<s:form action="submitVisa" method="post" namespace="/product" enctype="multipart/form-data" theme="simple">
  <s:hidden name="recordNo"></s:hidden>
  <s:hidden name="visaHelp.recordNo"></s:hidden>
  <s:hidden name="attachedId"></s:hidden>
  <s:hidden name="country"></s:hidden>
  <s:hidden name="idx"></s:hidden>
  <table style="width: 100%" border="0" align="center">
    <tr>
      <td class="idx">国家/地区：</td>
      <td class="data" colspan="5">
      <s:if test="recordNo == null">
      <s:hidden name="visaHelp.country" value="%{country}"></s:hidden>
      <s:select name="visaHelp.country"
                list="countrys"
                listKey="countryId"
                listValue="name"
                disabled="true">
      </s:select>
      </s:if>
      <s:else>
      <s:select name="visaHelp.country"
                list="countrys"
                listKey="countryId"
                listValue="name">
      </s:select>
      <font color="ff0000">**必选</font>
      </s:else>
      </td>
    </tr>
    <tr>
      <td class="idx">签证种类：</td>
      <td class="data" colspan="5">
      <s:radio name="visaHelp.visaKind"
               list="visaKinds"
               listKey="value"
               listValue="label">
      </s:radio>
      </td>
    </tr>
    <tr>
      <td class="idx">网站是否显示：</td>
      <td class="data" colspan="5">
      <s:radio name="visaHelp.isOpen"
               list="visaOpens"
               listKey="value"
               listValue="label">
      </s:radio>
      </td>
    </tr>
    <tr>
      <td class="idx">加急办理：</td>
      <td class="data" colspan="5">
      <s:radio name="visaHelp.canQuick"
               list="quickList"
               listKey="value"
               listValue="label">
      </s:radio>
      </td>
    </tr>
    <tr>
      <td class="idx">项目（中文）：</td>
      <td class="data" colspan="5">
        <s:textarea name="visaHelp.subject" cols="80" rows="2" />&nbsp;<font color="ff0000">**必填</font>
      </td>
    </tr>
    <tr>
      <td class="idx">停留天数：</td>
      <td class="data" colspan="5">
      <s:textfield name="visaHelp.stayDays" size="20" maxLength="20"/>&nbsp;
      </td>
    </tr>
    <tr>
      <td class="idx">办理时间：</td>
      <td class="data" colspan="5">
      <s:textfield name="visaHelp.transactDays" size="20" maxLength="20"/>&nbsp;<font color="ff0000">**必填</font>
      </td>
    </tr>
    <tr>
      <td class="idx">办签要求：</td>
      <td class="data" colspan="5">
      <sjr:ckeditor name="visaHelp.note" cols="60" rows="10">
      </sjr:ckeditor>
      </td>
    </tr>
    <tr>
      <td class="idx">成本价：</td>
      <td class="data"><s:textfield name="visaHelp.costPrice" size="11" maxlength="11" />
          &nbsp;<font color="ff0000">**必填</font></td>
      <td class="idx">同行价：</td>
      <td class="data"><s:textfield name="visaHelp.quotedPrice" size="11" maxlength="11" />
          &nbsp;<font color="ff0000">**必填</font></td>
      <td class="idx">直客报价：</td>
      <td class="data"><s:textfield name="visaHelp.marketPrice" size="11" maxlength="11" />
          &nbsp;</td>
    </tr>
    <tr>
      <td class="idx">计量单位：</td>
      <td class="data" colspan="5">
        <s:textfield name="visaHelp.unit" />
      </td>
    </tr>
    <tr>
      <td class="idx">有效日期：</td>
      <td class="data" colspan="5">
      <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="visaHelp.startDate"
          />至<s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="visaHelp.endDate" 
          /><font color="ff0000">**必填 [格式 YYYY-MM-DD]</font></td>
    </tr>
    <tr>
      <td colspan="8">
      <table style="width: 100%">
        <tr>
          <td colspan="5" class="header">签证所须材料</td>
        </tr>
        <tr>
          <td class="lstidx">No.</td>
          <td class="lstidx">项目</td>
          <td class="lstidx">数量</td>
          <td class="lstidx">辅助说明</td>
          <td class="lstidx">操作</td>
        </tr>
        <s:if test="items!=null">
          <s:iterator value="items" status="rowCounter">
            <tr>
              <td><s:property value="#rowCounter.count" />
                <s:hidden name="items(%{idx}).visaitemId" value="%{visaitemId}"></s:hidden>
              </td>
              <td>
                <s:select name="items(%{idx}).itemId"
                          list="visaDatas"
                          listKey="label"
                          listValue="value"
                          value="%{itemId}">
                </s:select>
              </td>
              <td class="data" valign="top">
                <s:textfield name="items(%{idx}).num" value="%{num}" size="10" maxLength="2"></s:textfield>
              </td>
              <td class="data" valign="top">
                <s:textfield name="items(%{idx}).outline" value="%{outline}"
                  size="80" maxLength="80"></s:textfield>
              </td>
              <td class="data" valign="top">
                <a href="javascript:SubmitDeleteItem('delete','<s:property value="idx" />')">删除</a>
              </td> 
            </tr>
          </s:iterator>
        </s:if>
        <tr>
          <td colspan="4"><s:submit action="editVisaAdditem" value="%{getText('common.forms.add')}" /></td>
        </tr>
      </table>
      </td>
    </tr>
    <tr>
      <td colspan="8">
      <table style="width: 100%">
        <tr>
          <td class="header" colspan="2">附件</td>
        </tr>
        <tr>
          <td class="idx" colspan="2">附件列表</td>
        </tr>
          <s:iterator value="fileItems" status="rowCounter">
          <tr>
            <td>
            <s:property value="#rowCounter.count" />
            <s:property value="note" />&nbsp;&nbsp;&nbsp;
            <a href="javascript:SubmitDeleteAttached('delete','<s:property value="attachedId" />')">删除</a>
            </td>
          </tr>
          </s:iterator>
        <tr>
          <td class="data" colspan="2">附件 一:<s:file name="upload" size="20" />&nbsp;说明:<s:textfield name="note" /></td>
        </tr>
        <tr>
          <td class="data" colspan="2">附件 二:<s:file name="upload" size="20" />&nbsp;说明:<s:textfield name="note" /></td>
        </tr>
        <tr>
          <td class="data" colspan="2">附件 三:<s:file name="upload" size="20" />&nbsp;说明:<s:textfield name="note" /></td>
        </tr>
      </table>
      </td>
    </tr>
    <tr>
      <td colspan="6" align="center">
      <s:submit action="submitVisa" value="提交" />
      <s:submit action="listVisa" value="返回" />
      </td>
    </tr>
  </table>

</s:form>
</body>
</html>


