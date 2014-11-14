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
	<script>
		function showHidePassword(){
			var checkPass = document.getElementById("checkpassword");
			var inputPass = document.getElementsByName("password")[0];
			
			if(checkPass.checked){
				inputPass.type = "text";
			}else {
				inputPass.type = "password";
			}
		}
	</script>
	
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
		<div class='<s:property value="gridClass" />'>
			<div class="box round first">
				<h2>Mettre à jour votre profil</h2>
				<div class="block">
				
					<!-- Message Error & Info & warning -->
					<ul class="message warning">
						<li>NB: Notez-vous que l'<strong>Email</strong> et le <strong>Role</strong> sont des champs non modifiable.</li>
					</ul>
					
					<s:if test="hasActionErrors()">
                        <s:actionerror cssClass="message error" />
                 	</s:if>
                 	<s:if test="hasActionMessages()">
      					<s:actionmessage cssClass="message info" />
					</s:if>
                 	<!-- End message -->
                 	
					<form action='<s:property value="rootUrl"/>users/editprofil' method="post" >
						<table class="form">
							<tr>
								<td>Nom:</td>
								<td><s:textfield cssClass="medium" name="nom" placeholder="Nom" required="required" /></td>
							</tr>
							<tr>
								<td>Prenom:</td>
								<td><s:textfield cssClass="medium" name="prenom" placeholder="Prenom" required="required" /></td>
							</tr>
							<tr>
								<td>Email:</td>
								<td><s:textfield cssClass="medium" name="email" placeholder="Email" type="email" required="required" disabled="true" /></td>
							</tr>
							<tr>
								<td>Mot de passe:</td>
								<td><s:textfield cssClass="medium" type="password" name="password" placeholder="Mot de passe" required="required" /><input type="checkbox" id="checkpassword" onclick="showHidePassword();" >show/hide</td>
							</tr>
							<tr>
								<td>Role:</td>
								<td><s:textfield cssClass="medium" name="role" placeholder="Role" required="required" disabled="true" /></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" class="medium" value="Valider"></td>
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