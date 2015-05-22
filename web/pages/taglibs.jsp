<%@page import="com.momia.until.PropertyParameter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String url = PropertyParameter.loadProperties().getProperty("picUrl");
%>

<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/${ctx}"></c:set>
<c:set var="imgPath" value="<%=url%>"></c:set>
