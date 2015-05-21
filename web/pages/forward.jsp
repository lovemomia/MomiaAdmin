<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp" %><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>forward</title>
<script type="text/javascript">
	$(document).ready(function(){
		document.transfer.submit();
	});
</script>
</head>
<body>
	<form name="transfer" action="${ctx}/user/login.do" method="post"></form>
</body>
</html>