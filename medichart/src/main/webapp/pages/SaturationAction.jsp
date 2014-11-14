<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- URL to the root -->
<s:url namespace="/" action="" var="rootUrl" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Méditel Reporting</title>

<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>css/reset.css' media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>css/text.css' media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>css/grid.css' media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>css/layout.css' media="screen" />
	<script src='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/jquery-2.1.1.min.js'></script>
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/legend.css'
	media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/style.css'>


</head>
<body>
	<div id="container">
	
	<!-- Message Error & Info & warning -->
		<s:if test="hasActionErrors()">
		<s:actionerror cssClass="message error" />
	</s:if>
	<s:if test="hasActionMessages()">
		<s:actionmessage cssClass="message info" />
	</s:if>
	<!-- End message -->
	<!-- Box graphe interface -->

	<h1>Detection des problèmes de saturation est terminées</h1>
	
		<!-- footer -->
	<s:include value="include/footer.jsp"></s:include>
<style type="text/css" >
html, body{
background-color: #FFF;
}
</style>

	</div>
	
</body>
</html>