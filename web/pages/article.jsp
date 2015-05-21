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
		<!-- topbar starts -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="${ctx}/user/spage.do?id=${user.id}"> <img alt="Charisma Logo" src="${ctx}/pages/img/logo20.png" /> <span>麻麻蜜丫</span></a>
				
				<!-- user dropdown starts -->
				<div class="btn-group pull-right" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i><span class="hidden-phone">  ${user.username}</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<!-- <li><a href="#">Profile</a></li> -->
						<li class="divider"></li>
						<li><a href="${ctx}/user/login.do">注销</a></li>
					</ul>
				</div>
				<!-- user dropdown ends -->
			</div>
		</div>
	</div>
	<!-- topbar ends -->
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="nav-header hidden-tablet">应用菜单</li>
						<li><a class="ajax-link" href="${ctx}/user/spage.do?id=${user.id}"><i class="icon-home"></i><span class="hidden-tablet">首页</span></a></li>
						<li><a class="ajax-link" href="${ctx}/user/info.do?id=${user.id}"><i class="icon-user"></i><span class="hidden-tablet">用户管理</span></a></li>
						<li><a class="ajax-link" href="${ctx}/assortarticle/info.do?uid=${user.id}"><i class="icon-th"></i><span class="hidden-tablet"> 文章分类管理</span></a></li>
						<li><a class="ajax-link" href="${ctx}/assortgoods/info.do?uid=${user.id}"><i class="icon-th-large"></i><span class="hidden-tablet"> 商品分类管理</span></a></li>
						<li><a class="ajax-link" href="${ctx}/topicarticle/info.do?uid=${user.id}"><i class="icon-th-list"></i><span class="hidden-tablet"> 文章专题管理</span></a></li>
						<li><a class="ajax-link" href="${ctx}/topicgoods/info.do?uid=${user.id}"><i class="icon-camera"></i><span class="hidden-tablet"> 商品专题管理</span></a></li>
						<li><a class="ajax-link" href="${ctx}/article/info.do?uid=${user.id}"><i class="icon-list-alt"></i><span class="hidden-tablet"> 文章管理</span></a></li>
						<li><a class="ajax-link" href="${ctx}/goods/info.do?uid=${user.id}"><i class="icon-list"></i><span class="hidden-tablet"> 商品管理</span></a></li>
						<li><a class="ajax-link" href="${ctx}/event/info.do?uid=${user.id}"><i class="icon-flag"></i><span class="hidden-tablet"> 活动管理</span></a></li>
						<li><a class="ajax-link" href="${ctx}/feedback/info.do?uid=${user.id}"><i class="icon-pencil"></i><span class="hidden-tablet">意见反馈</span></a></li>
					</ul>
					 <!-- <label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>-->
				</div><!--/.well -->
			</div><!--/span-->
			<!-- left menu ends -->
			
			<div id="content" class="span10">
			<!-- content starts -->
		<div class="box span12">
					<div class="row-fluid sortable">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i> 文章管理</h2>
						<div class="box-icon">
							<a href="${ctx}/article/operation.do?uid=${user.id}&id=0&flag=add" class="btn btn-add btn-round"><i class=" icon-plus"></i></a>
						</div>
					</div>
					<div class="box-content">
					<form >
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>编号</th>
								  <th>标题</th>
								  <th>摘要</th>
								  <th>封面</th>
								  <th>内容</th>
								  <th>作者</th>
								  <th>创建时间</th>
								  <th>操作</th>
							  </tr>
						  </thead>   
						  <tbody>
							<c:forEach items="${articles}" var="node">  
							<tr>
								<td><c:out value="${node.id}"></c:out></td>
								<td><c:out value="${node.title}"></c:out></td>
								<td><c:out value="${node.abstracts}"></c:out></td>
								<td><img src="${imgPath}${node.picUrl}"  style="margin:0 auto;display:block;height:100ps" width="100ps"></td>
								<td><c:out value="${fn:substring(node.content,0,20)}"></c:out></td>
								<td><c:out value="${node.authorId}"></c:out></td>
								<td><c:out value="${fn:substring(node.addTime,0,19)}"></c:out></td>
								<td class="center">
									<a class="btn btn-info" href="${ctx}/article/operation.do?uid=${user.id}&id=${node.id}&flag=update">
										<i class="icon-edit icon-white"></i>  
										修改                                            
									</a>
									<a class="btn btn-danger" href="${ctx}/article/delete.do?uid=${user.id}&id=${node.id}">
										<i class="icon-trash icon-white"></i> 
										删除
									</a>
								</td>
							</tr>
							</c:forEach>
						  </tbody>
					  </table>   
					  </form>         
					</div>
					</div>
				</div><!--/span-->
					<!-- content ends -->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
				
		<hr>
		<footer>
			<p class="pull-left">&copy; <a href="#" target="_blank">Development Time</a> 2015</p>
			<p class="pull-right">Powered by: <a href="#">momia</a></p>
		</footer>
		
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