<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- URL to the root -->
<s:url namespace="/" action="" var="rootUrl" />

<!DOCTYPE html>
<html>
<head>
	
	<!-- user head -->
	<s:include value="include/userPageHead.jsp"></s:include>

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
				<s:set var="gridClass" value="%{'grid_10'}" />
			</div>
		</s:if>
		<s:else>
			<div class="grid_2">
				<!-- boxSideMenuElse -->
				<s:include value="include/boxSideMenuElse.jsp"></s:include>
				<s:set var="gridClass" value="%{'grid_12'}" />
			</div>
		</s:else>
		
		<!-- Box add users -->
		<div class='<s:property value="gridClass" />' >
			<div class="box round first">
				<h2>Signaler bug et amélioration</h2>
				<div class="block">
				
					<!-- Message Error & Info & warning -->
					<ul class="message warning">
						<li>Nous vous saurions gré de bien vouloir consacrer quelques minutes à remplir ce formulaire, afin de nous aider à améliorer cette plateforme. :)</li>
					</ul>
					
					<s:if test="hasActionErrors()">
                        <s:actionerror cssClass="message error" />
                 	</s:if>
                 	<s:if test="hasActionMessages()">
      					<s:actionmessage cssClass="message info" />
					</s:if>
                 	<!-- End message -->
                 	
					<form action='<s:property value="rootUrl"/>support/report' method="post" >
						<table class="form">
							<tr>
								<td>Nom et prénom:</td>
								<td><s:textfield cssClass="medium" name="nom" value="%{#session.user.fullName}" disabled="true"  /></td>
							</tr>
							<tr>
								<td>Objet:</td>
								<td><s:textfield cssClass="medium" name="objet" placeholder="Objet..." required="required" /></td>
							</tr>
							<tr>
								<td>Sujet:</td>
								<td><s:textarea cssClass="large" name="sujet" placeholder="Sujet..." required="required" cols="63" rows="10" style="vertical-align: middle;" /></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" class="medium" value="Envoyer"></td>
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