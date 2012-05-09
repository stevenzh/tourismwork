<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=basePath%>css/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/swfupload.js"></script>
<script type="text/javascript" src="<%=basePath%>js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=basePath%>js/fileprogress.js"></script>
<script type="text/javascript" src="<%=basePath%>js/handlers.js"></script>
<!-- 初始化swfupload 对象-->
<script type="text/javascript">
	var upload1, upload2;

	window.onload = function() {
		upload1 = new SWFUpload({
			// Backend Settings  
			upload_url : "PictureAction.action",
			post_params : {
				"picSESSID" : "songhao"
			},
			file_post_name : "file",
			// File Upload Settings  
			file_size_limit : "102400", // 100MB  
			file_types : "*.*",
			file_types_description : "All Files",
			file_upload_limit : "10",
			file_queue_limit : "0",

			// Event Handler Settings (all my handlers are in the Handler.js file)  
			file_dialog_start_handler : fileDialogStart,
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_start_handler : uploadStart,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,

			// Button Settings  
			button_image_url : "images/XPButtonUploadText_61x22.png",
			button_placeholder_id : "spanButtonPlaceholder1",
			button_width : 61,
			button_height : 22,

			// Flash Settings  
			flash_url : "js/swfupload.swf",

			custom_settings : {
				progressTarget : "fsUploadProgress1",
				cancelButtonId : "btnCancel1"
			},

			// Debug Settings  
			debug : false
		});

		upload2 = new SWFUpload({
			// Backend Settings  
			upload_url : "PictureAction.action",
			post_params : {
				"SESSID" : "file"
			},

			// File Upload Settings  
			file_size_limit : "200", // 200 kb  
			file_types : "*.jpg;*.gif;*.png",
			file_types_description : "Image Files",
			file_upload_limit : "10",
			file_queue_limit : "5",

			// Event Handler Settings (all my handlers are in the Handler.js file)  
			file_dialog_start_handler : fileDialogStart,
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_start_handler : uploadStart,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,

			// Button Settings  
			button_image_url : "images/XPButtonUploadText_61x22.png",
			button_placeholder_id : "spanButtonPlaceholder2",
			button_width : 61,
			button_height : 22,

			// Flash Settings  
			flash_url : "js/swfupload.swf",

			swfupload_element_id : "flashUI2", // Setting from graceful degradation plugin  
			degraded_element_id : "degradedUI2", // Setting from graceful degradation plugin  

			custom_settings : {
				progressTarget : "fsUploadProgress2",
				cancelButtonId : "btnCancel2"
			},

			// Debug Settings  
			debug : false
		});

	}
</script>
</head>

<body>
	<div id="header">
		<h1 id="logo">
			<a href="../">SWFUpload</a>
		</h1>
		<div id="version">v2.2.0</div>
	</div>
	<div id="content">
		<h2>Multi-Instance Demo</h2>
		<form action="pictureAction" method="post" name="thisform"
			enctype="multipart/form-data">
			<p>This page demonstrates how multiple instances of SWFUpload can
				be loaded on the same page. It also demonstrates the use of the
				graceful degradation plugin and the queue plugin.</p>
			<table>
				<tr valign="top">
					<td>
						<div>
							<div class="fieldset flash" id="fsUploadProgress1">
								<span class="legend">Large File Upload Site</span>
							</div>
							<div style="padding-left: 5px;">
								<span id="spanButtonPlaceholder1"></span> <input id="btnCancel1"
									type="button" value="Cancel Uploads"
									onclick="cancelQueue(upload1);" disabled="disabled"
									style="margin-left: 2px; height: 22px; font-size: 8pt;" /> <br />
							</div>
						</div>
					</td>
					<td>
						<div>
							<div class="fieldset flash" id="fsUploadProgress2">
								<span class="legend">Small File Upload Site</span>
							</div>
							<div style="padding-left: 5px;">
								<span id="spanButtonPlaceholder2"></span> <input id="btnCancel2"
									type="button" value="Cancel Uploads"
									onclick="cancelQueue(upload2);" disabled="disabled"
									style="margin-left: 2px; height: 22px; font-size: 8pt;" /> <br />
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
