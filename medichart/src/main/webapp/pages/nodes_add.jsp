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
	});
</script>

</head>
<body>
	<div class="container_12">
		
		<!-- user ( image + fullname + userType + TheLougoutTag -->
		<s:include value="include/userLogInfo.jsp"></s:include>
		
		<div class="clear"></div>
		<div class="grid_12">
			
			<!-- Nav main -->
			<s:include value="include/navMain.jsp"></s:include>
			
		</div>
		<div class="clear"></div>
		
		<div class="grid_2">
			
			<!-- boxSideMenu -->
			<s:include value="include/boxSideMenu.jsp"></s:include>
			
		</div>
		
		<!-- Box add users -->
		<div class="grid_10">
			<div class="box round first">
				<h2>Ajouter un nouveau node</h2>
				<div class="block">
				
					<!-- Message Error & Info & warning -->
					<s:if test="hasActionErrors()">
                        <s:actionerror cssClass="message error" />
                 	</s:if>
                 	<s:if test="hasActionMessages()">
      					<s:actionmessage cssClass="message info" />
					</s:if>
                 	<!-- End message -->
                 	
					<form action='<s:property value="rootUrl"/>nodes/add' method="post" >
						<table class="form">
							<tr>
								<td>Node name:</td>
								<td><s:textfield cssClass="medium" name="nameNode" placeholder="Node name" required="required" /></td>
							</tr>
							<tr>
								<td>Device category:</td>
								<td><s:textfield cssClass="medium" name="deviceCat" placeholder="Device category" required="required" /></td>
							</tr>
							<tr>
								<td>Device family:</td>
								<td><s:textfield cssClass="medium" name="deviceFamily" placeholder="Device family" required="required" /></td>
							</tr>
							<tr>
								<td>Device profile:</td>
								<td><s:textfield cssClass="medium" name="deviceProfile" placeholder="Device profile" required="required" /></td>
							</tr>
							<tr>
								<td>Device vendor:</td>
								<td><s:textfield cssClass="medium" name="deviceVendor" placeholder="Device vendor" required="required" /></td>
							</tr>
							<tr>
								<td>Management address:</td>
								<td><s:textfield cssClass="medium" name="managementAddress" placeholder="Management address" required="required" /></td>
							</tr>
							<tr>
								<td>Node managed:</td>
								<td><s:textfield cssClass="medium" name="nodeManaged" placeholder="Node managed" required="required" /></td>
							</tr>
							<tr>
								<td>System description:</td>
								<td><s:textfield cssClass="medium" name="systemDescription" placeholder="System description" required="required" /></td>
							</tr>
							<tr>
								<td>System object id:</td>
								<td><s:textfield cssClass="medium" name="systemObjectId" placeholder="System object id" required="required" /></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" class="medium" value="Enregistrer"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<!-- End box add users -->

		<div class="clear"></div>
	</div>
	
		<!-- footer -->
		<s:include value="include/footer.jsp"></s:include>
		
</body>
</html>