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
		
		<!-- Box Liste des users -->
		<div class="grid_10">
            <div class="box round first grid">
                <h2>La liste des utilisateurs</h2>
                <div class="block">
                	<!-- Message Error & Info & warning -->
					<ul class="message warning">
						<li>NB: Notez-vous que le compte sera <strong>supprimé automatiquement</strong> sans confirmation</li>
					</ul>
					<s:if test="hasActionErrors()">
                        <s:actionerror cssClass="message error" />
                 	</s:if>
                 	<s:if test="hasActionMessages()">
      					<s:actionmessage cssClass="message info" />
					</s:if>
                 	<!-- End message -->
                
                    <table class="data display datatable" id="example">
					<thead>
						<tr>
							<th>Nom et Prénom</th>
							<th>Email</th>
							<th>Role</th>
							<th>Password</th>
							<th>Modifier</th>
							<th>Supprimer</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="users" var="user">
						<tr class="odd gradeX">
							<td><s:property value="fullName" /></td>
							<td><s:property value="email" /></td>
							<td><s:property value="role" /></td>
							<td><s:property value="password" /></td>
							<s:url action="info" var="editUrl">
								<s:param name="idUser"><s:property value="idUser" /></s:param>
							</s:url>
							<s:url action="delete" var="deleteUrl">
								<s:param name="idUser"><s:property value="idUser" /></s:param>
							</s:url>
							<td class="center"><a href='<s:property value="#editUrl" />'>Modifier</a></td>
							<td class="center"><a href='<s:property value="#deleteUrl" />'>Supprimer</a></td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
                </div>
            </div>
        </div>
		<!-- End Liste des users -->

		<div class="clear"></div>
	</div>
	
	<!-- footer -->
	<s:include value="include/footer.jsp"></s:include>
	
</body>
</html>
