<%@page import="com.momia.entity.User"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>麻麻蜜丫平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
	<meta name="author" content="Muhammad Usman">

	<!-- The styles -->
	<link  href="${ctx}/pages/css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="${ctx}/pages/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${ctx}/pages/css/charisma-app.css" rel="stylesheet">
	<link href="${ctx}/pages/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='${ctx}/pages/css/fullcalendar.css' rel='stylesheet'>
	<link href='${ctx}/pages/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='${ctx}/pages/css/chosen.css' rel='stylesheet'>
	<link href='${ctx}/pages/css/uniform.default.css' rel='stylesheet'>
	<link href='${ctx}/pages/css/colorbox.css' rel='stylesheet'>
	<link href='${ctx}/pages/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='${ctx}/pages/css/jquery.noty.css' rel='stylesheet'>
	<link href='${ctx}/pages/css/noty_theme_default.css' rel='stylesheet'>
	<link href='${ctx}/pages/css/elfinder.min.css' rel='stylesheet'>
	<link href='${ctx}/pages/css/elfinder.theme.css' rel='stylesheet'>
	<link href='${ctx}/pages/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='${ctx}/pages/css/opa-icons.css' rel='stylesheet'>
	<link href='${ctx}/pages/css/uploadify.css' rel='stylesheet'>

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="${ctx}/pages/img/logo20.png">
	
	<script type="text/javascript" charset="utf-8" src="${ctx}/pages/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/pages/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/pages/ueditor/lang/zh-cn/zh-cn.js"></script>
	
	
</head>
<body>
				<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> 添加商品</h2>
						<div class="box-icon">
							<a href="${ctx}/event/info.do?uid=${user.id}" class="btn btn-back btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" id="eventform" action="${ctx}/event/insert.do?uid=${user.id}" method="post">
							<fieldset>
							<div class="control-group">
								<label class="control-label" for="focusedInput">标题</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="title" name="title" type="text" value="">
								  <span class="help-inline">必填项</span>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="focusedInput">副标题</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="subtitle" name="subtitle" type="text" value="">
								  <span class="help-inline">必填项</span>
								</div>
							  </div>
							   <div class="control-group">
								<label class="control-label" for="focusedInput">封面</label>
								<div class="controls">
								  <script type="text/plain" id="upload_ue"></script>
								  <input class="input-xlarge focused" id="picUrl" name="picUrl" type="text" value="" readonly>
								  <span class="help-inline">必填项</span>
								  <a class="btn btn-success" href="javascript:void(0);" onclick="upImage();"><i class="icon-zoom-in icon-white"></i>选择图片</a>
								</div>
							  </div>
							<div class="control-group">
							  <label class="control-label" >地址</label>
							  <div class="controls">
								<textarea  id="eventUrl" name="eventUrl" rows="3"></textarea>
								<span class="help-inline">必填项</span>
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="date01">活动时间</label>
							  <div class="controls">
								<input type="text" class="input-xlarge datepicker" id="beginTime"  name="beginTime" value="${nowdate}" readonly>至
								<input type="text" class="input-xlarge datepicker" id="endTime"  name="endTime"  value="${nowdate}" readonly>
								<span class="help-inline">必填项</span>
							  </div>
							</div>
							</fieldset>
						  </form>
							<div class="form-actions">
								<button class="btn btn-primary" id="confirmbtn" name="confirmbtn" >确   定</button>
								<button class="btn" id="calbtn" name="calbtn" onClick="javascript:history.go(-1)">返   回</button>
							</div>
					</div>
				</div><!--/span-->
			
			</div><!--/row-->

		<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="${ctx}/pages/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="${ctx}/pages/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="${ctx}/pages/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="${ctx}/pages/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="${ctx}/pages/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="${ctx}/pages/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="${ctx}/pages/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="${ctx}/pages/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="${ctx}/pages/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="${ctx}/pages/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="${ctx}/pages/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="${ctx}/pages/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="${ctx}/pages/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="${ctx}/pages/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="${ctx}/pages/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="${ctx}/pages/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='${ctx}/pages/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='${ctx}/pages/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="${ctx}/pages/js/excanvas.js"></script>
	<script src="${ctx}/pages/js/jquery.flot.min.js"></script>
	<script src="${ctx}/pages/js/jquery.flot.pie.min.js"></script>
	<script src="${ctx}/pages/js/jquery.flot.stack.js"></script>
	<script src="${ctx}/pages/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="${ctx}/pages/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="${ctx}/pages/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="${ctx}/pages/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="${ctx}/pages/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="${ctx}/pages/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="${ctx}/pages/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="${ctx}/pages/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="${ctx}/pages/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="${ctx}/pages/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="${ctx}/pages/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="${ctx}/pages/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="${ctx}/pages/js/charisma.js"></script>

<script type="text/javascript">
	
	var _editor;
	//重新实例化一个编辑器
	_editor = UE.getEditor('upload_ue');
	_editor.ready(function () {
    //设置编辑器不可用
   // _editor.setDisabled();
    //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
    _editor.hide();
    //侦听图片上传
    _editor.addListener('beforeInsertImage', function (t, arg) {
        //将地址赋值给相应的input,只取第一张图片的路径
        $("#picUrl").attr("value", arg[0].src);
        //图片预览
        $("#preview").attr("src", arg[0].src);
   		 })
    //侦听文件上传，取上传文件列表中第一个上传的文件的路径
    _editor.addListener('afterUpfile', function (t, arg) {
        $("#file").attr("value", _editor.options.filePath + arg[0].url);
   		 })
	});   
	//弹出图片上传的对话框
	function upImage() {
		var myImage = _editor.getDialog("insertimage");
		myImage.open();
	}
	//弹出文件上传的对话框
	function upFiles() {
		var myFiles = _editor.getDialog("attachment");
		myFiles.open();
	}
	
	$(function () {
		
        $("#confirmbtn").click(function () {
            var title = $("#title").val();
            var subtitle = $("#subtitle").val();
            var picUrl = $("#picUrl").val();
            var eventUrl = $("#eventUrl").val();
            var beginTime = $("#beginTime").val();
            var endTime = $("#endTime").val();
            if (title == "" || subtitle == "" || picUrl == "" || eventUrl == "" || beginTime == "" || endTime == "" ) {
                alert("对不起! 您的[标题]、[副标题]、[封面]、[地址]和[活动时间]为必填项！");
                return false;
            }else{
            	$("#eventform").submit();
            }
        });
    });
    </script>
</body>
</html>