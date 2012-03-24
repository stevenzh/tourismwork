<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品分类</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="产品分类">
<sx:head debug="true"/>
</head>

<body>
<script language="JavaScript" type="text/javascript">
    dojo.event.topic.subscribe("treeSelected", function treeNodeSelected(node) {
        dojo.io.bind({
            url: "<s:url value='/product/editCategory.jspa'/>?catId="+node.node.widgetId,
            load: function(type, data, evt) {
                var divDisplay = dojo.byId("displayId");
                divDisplay.innerHTML=data;
            },
            mimeType: "text/html"
        });
    });

function addCategory()
{
  var fm = document.saveCategory;
  fm.action = "<s:url action='addCategory' namespace='/product'/>";
  fm.submit();
}

function deleteCategory()
{
  var fm = document.saveCategory;
  if (confirm("是否真的删除?") == false)
  {
    return;
  }
  fm.action = "<s:url action='deleteCategory' namespace='/product'/>";
  fm.submit();
}

</script>

<div style="float:left; margin-right: 50px; font-size: 10pt">
<sx:tree 
    id="myTree"
    rootNode="%{treeRootNode}" 
    childCollectionProperty="children" 
    nodeIdProperty="destId"
    nodeTitleProperty="cnName"
	treeSelectedTopic="treeSelected"
    >
</sx:tree> 
</div>

<div id="displayId">请选择一个工作组...</div>

</body>
</html>