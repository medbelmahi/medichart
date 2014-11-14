<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- URL to the root -->
<s:url namespace="/" action="" var="rootUrl" />

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Méditel Reporting</title>
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/reset.css' media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/text.css' media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/grid.css' media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/layout.css' media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/nav.css' media="screen" />
<!--[if IE 6]><link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/ie6.css' media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/ie.css' media="screen" /><![endif]-->
<!-- BEGIN: load jquery -->
<script src='<s:property value="rootUrl"/>js/jquery-1.6.4.min.js' type="text/javascript"></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.core.min.js' type="text/javascript" ></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.widget.min.js' type="text/javascript"></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.accordion.min.js' type="text/javascript"></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.core.min.js' type="text/javascript"></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.slide.min.js' type="text/javascript"></script>
<!-- END: load jquery -->

<script src='<s:property value="rootUrl"/>js/setup.js' type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		setupLeftMenu();
		setSidebarHeight();
		initialize_accordion();
	});
</script>