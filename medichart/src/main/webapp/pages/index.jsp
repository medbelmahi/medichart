<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<!-- javaScript includes -->
	<s:include value="include/indexHead.jsp"></s:include>
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
		<s:if
			test="%{#session.user instanceof ma.meditel.reporting.entities.Admin}">
			<div class="grid_2">
				<!-- boxSideMenu -->
				<s:include value="include/boxSideMenu.jsp"></s:include>
				<s:set var="gridClass" value="%{'grid_5'}" />
			</div>
		</s:if>
		<s:else>
			<div class="grid_2">
				<!-- boxSideMenuElse -->
				<s:include value="include/boxSideMenuElse.jsp"></s:include>
				<s:set var="gridClass" value="%{'grid_12'}" />
			</div>
		</s:else>
		
		<!-- Informations personnelles -->
		<div class='<s:property value="gridClass" />'>
			<div class="box round first">
				<h2>Informations personnelles</h2>
				<div class="block">
					<table class="form">
					<tr><td><h5>Nom: </h5></td><td><h6 style="color:gray;"><s:property value="%{#session.user.nom}" /></h6></td></tr>
					<tr><td><h5>Prenom: </h5></td><td><h6 style="color:gray;"><s:property value="%{#session.user.prenom}" /></h6></td></tr>
					<tr><td><h5>Email: </h5></td><td><h6 style="color:gray;"><s:property value="%{#session.user.email}" /></h6></td></tr>
					<tr><td><h5>Role: </h5></td><td><h6 style="color:gray;"><s:property value="%{#session.user.roleDescription}" /></h6></td></tr>
					<tr><td><h5>Password: </h5></td><td><h6 style="color:gray;">******</h6></td></tr>
					</table>
				</div>
			</div>
		</div>
		<!-- End Informations personnelles -->
		
		<!-- Informations systeme -->
		<div class='<s:property value="gridClass" />'>
			<div class="box round first">
				<h2>Informations systeme</h2>
				<div class="block">
				<table class="form">
					<tr><td><h5>État du système: </h5></td><td><h6 style="color:gray;"><s:property value="%{#session.statistics.systemStatusString}"/></h6></td></tr>
					<tr><td><h5>Nombre total des utilisateurs: </h5></td><td><h6 style="color:gray;"><s:property value="%{#session.statistics.usersNombre}"/></h6></td></tr>
					<tr><td><h5>Nombre total des nodes: </h5></td><td><h6 style="color:gray;"><s:property value="%{#session.statistics.nodesNombre}"/></h6></td></tr>
					<tr><td><h5>Nombre total des interfaces logiques: </h5></td><td><h6 style="color:gray;"><s:property value="%{#session.statistics.interfacesLogicNombre}"/></h6></td></tr>
					<tr><td><h5>Nombre total des interfaces physiques: </h5></td><td><h6 style="color:gray;"><s:property value="%{#session.statistics.interfacesPhysicNombre}"/></h6></td></tr>
				</table>
				</div>
			</div>
		</div>
		<!-- End Informations systeme -->
		
		<div class="clear"></div>
	</div>
	<!-- footer -->
	<s:include value="include/footer.jsp"></s:include>
</body>
</html>
