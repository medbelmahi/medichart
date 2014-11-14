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
<link href='<s:property value="rootUrl"/>css/table/demo_page.css' rel="stylesheet" type="text/css" />


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
		
		<!-- Box alias Form -->
		<div class="grid_10">
			<div class="box round first">
				<h2>Modifier la liste des alias autorisées</h2>
				<div class="block">
					<!-- Message Error & Info & warning -->
					<s:if test="hasActionErrors()">
                        <s:actionerror cssClass="message error" />
                 	</s:if>
                 	<s:if test="hasActionMessages()">
      					<s:actionmessage cssClass="message info" />
					</s:if>
                 	<!-- End message -->
                 	
					<form action='<s:property value="rootUrl"/>interfaces/autorizedAliasAdd' method="post" >
						<table class="form">
							<tr>
								<td>Alias préfixe/suffixe:</td>
								<td><s:textfield cssClass="medium" name="preAlias" placeholder="Alias..." required="required" /></td>
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
		<!-- End box alias form -->
		
		<!-- Box Liste des alias -->
		<div class="grid_10">
            <div class="box round first grid">
                <h2>La liste des alias autorisés</h2>
                <div class="block">
                	<!-- Message Error & Info & warning -->
					<ul class="message warning">
						<li>NB: Notez-vous que l'Alias sera <strong>supprimé automatiquement</strong> sans confirmation</li>
					</ul>
                 	<!-- End message -->
                 	
                    <table class="data display datatable" id="example">
					<thead>
						<tr>
							<th>Alias</th>
							<th class="center">Supprimer</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listAlias" var="alias">
						<tr class="odd gradeX">
							<td><s:property value="preAlias" /></td>
							
							<s:url action="autorizedAliasRemove" var="removeUrl">
								<s:param name="IdAlias"><s:property value="IdInterfaceAutorizedAlias" /></s:param>
							</s:url>
							<td class="center"><a href='<s:property value="#removeUrl" />'>Supprimer</a></td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
                </div>
            </div>
        </div>
		<!-- End Liste des alias -->

		<div class="clear"></div>
	</div>
	<!-- footer -->
	<s:include value="include/footer.jsp"></s:include>
	
	<!-- BEGIN: load jquery -->
<!-- END: load jquery -->

    <script src='<s:property value="rootUrl"/>js/jquery-1.6.4.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.core.min.js'  type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.widget.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.accordion.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.core.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.slide.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.mouse.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.sortable.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/table/jquery.dataTables.min.js' type="text/javascript"></script>
    <!-- END: load jquery -->
    <script src='<s:property value="rootUrl"/>js/table/table.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/setup.js' type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            setupLeftMenu();
            $('.datatable').dataTable();
			setSidebarHeight();
			initialize_accordion();
        });
    </script>
</body>
</html>
