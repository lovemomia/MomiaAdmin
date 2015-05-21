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
		
</head>

<body>
	<!-- topbar ends -->
		<div class="container-fluid">
		<div class="row-fluid">
			<div id="content" class="span10">
			<!-- content starts -->
			<div class="box span12">
					<div class="row-fluid sortable">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i>商品链接</h2>
							<div class="box-icon">
								<a href="${ctx}/goodslink/operation.do?uid=${user.id}&flag=add&id=0&gid=${gid}" class="btn btn-add btn-round"><i class=" icon-plus"></i></a>
							</div>
					</div>
					<div class="box-content">
					<form id="linkform" action="${ctx}/goods/info.do?uid=${user.id}" method="post">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>编号</th>
								  <th>摘要</th>
								  <th>价格</th>
								  <th>商品链接</th>
								  <th>排序值</th>
								  <th>操作</th>
							  </tr>
						  </thead>   
						  <tbody>
							<c:forEach items="${links}" var="node">  
							<tr>
								<td><c:out value="${node.id}"></c:out></td>
								<td><c:out value="${fn:substring(node.abstracts,0,20)}"></c:out></td>
								<td><c:out value="${node.price}"></c:out></td>
								<td><c:out value="${fn:substring(node.goodUrl,0,20)}"></c:out></td>
								<td><c:out value="${node.origin}"></c:out></td>
								<td class="center">
								<a class="btn btn-info" href="${ctx}/goodslink/operation.do?uid=${user.id}&id=${node.id}&flag=update&gid=${node.gid}">
									<i class="icon-edit icon-white"></i>修改</a>
								<a class="btn btn-danger" href="${ctx}/goodslink/delete.do?uid=${user.id}&id=${node.id}&gid=${gid}">
									<i class="icon-trash icon-white"></i>删除</a>
								</td>
							</tr>
							</c:forEach>
						  </tbody>
					  </table>  
					  	<div class="form-actions">
							<button class="btn" type="submit" id="calbtn" name="calbtn" >返   回</button>
						</div> 
					  </form>  
					  	       
					</div>
					</div>
				</div><!--/span-->
			<!-- content ends -->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
		
	</div><!--/.fluid-container-->

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
		
</body>
</html>