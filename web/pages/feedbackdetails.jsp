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
						<h2><i class="icon-edit"></i>意见反馈详情</h2>
						<div class="box-icon">
							<a href="${ctx}/feedback/info.do?uid=${user.id}" class="btn btn-back btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form >
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="focusedInput">反馈内容:</label>
								<div class="controls">
								  ${model.content}
								</div>
							  </div>
							  <div class="control-group">
							  <label class="control-label" >反馈人:</label>
							  <div class="controls">
								${model.uid}
							  </div>
							</div>
							 <div class="control-group">
							  <label class="control-label" >Email:</label>
							  <div class="controls">
								${model.email}
							  </div>
							</div>
							  <div class="control-group">
								<label class="control-label" for="focusedInput">反馈时间:</label>
								<div class="controls">
								  ${fn:substring(model.addTime,0,19)}
								</div>
							  </div>
							</fieldset>
						  </form>
					
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

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    	//var ue = UE.getEditor('content');
    var option = { 
		initialContent: '编写文章内容...',//初始化编辑器的内容 
		textarea: 'editorValue',//设置提交时编辑器内容的名字 
		toolbars: [[
		            'simpleupload'
		        ]],
		minFrameHeight:500, //设置高度 
		initialFrameWidth:600 //设置宽度 
	} 
	var editor = new baidu.editor.ui.Editor(option);//new一个对象，通过对象创建编辑器 
	editor.render("content"); 
	
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
	//alert(asdhakjsd);
	var myImage = _editor.getDialog("insertimage");
	myImage.open();
	}
	//弹出文件上传的对话框
	function upFiles() {
	var myFiles = _editor.getDialog("attachment");
	myFiles.open();
	}
    </script>
</body>
</html>