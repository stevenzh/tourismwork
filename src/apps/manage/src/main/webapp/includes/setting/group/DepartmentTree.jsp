<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>行政部门结构</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="行政部门结构">
<sx:head debug="true"/>
</head>
<body>

<!-- START SNIPPET: treeExampleDynamicJsp -->

<script language="JavaScript" type="text/javascript">
dojo.event.topic.subscribe("treeSelected", function treeNodeSelected(node) {
    dojo.io.bind({
        url: "<s:url value='/setting/detailDepartment.jspa'/>?dptNo="+node.node.widgetId,
        load: function(type, data, evt) {
            var divDisplay = dojo.byId("displayId");
            divDisplay.innerHTML=data;
        },
        mimeType: "text/html"
    });
});

function addGroup()
{
  var fm = document.submitDepartment;
  fm.action = "<s:url action='addCategory' namespace='/setting'/>";
  fm.submit();
}

function deleteGroup()
{
  var fm = document.submitDepartment;
  if (confirm("是否真的删除?") == false)
  {
    return;
  }
  fm.action = "<s:url action='deleteCategory' namespace='/setting'/>";
  fm.submit();
}
</script>

<div style="float:left; margin-right: 50px;">
<sx:tree 
    id="myTree"
    rootNode="%{treeRootNode}" 
    childCollectionProperty="children" 
    nodeIdProperty="groupId"
    nodeTitleProperty="name"
	treeSelectedTopic="treeSelected"
    >
</sx:tree> 
</div>

<div id="displayId">请选择一个部门...</div>

<!-- END SNIPPET: treeExampleDynamicJsp -->

</body>
</html>