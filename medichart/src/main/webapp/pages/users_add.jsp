<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		
		<div class="grid_2">
			
			<!-- boxSideMenu -->
			<s:include value="include/boxSideMenu.jsp"></s:include>
			
		</div>
		
		<!-- Box add users -->
		<div class="grid_10">
			<div class="box round first">
				<h2>Ajouter un nouveau utilisateur</h2>
				<div class="block">
				
					<!-- Message Error & Info & warning -->
					<ul class="message warning">
						<li>NB: Notez-vous qu'un utilisateur avec le <strong>Role Admin</strong> a tous les droits sur le systeme</li>
					</ul>
					
					<s:if test="hasActionErrors()">
                        <s:actionerror cssClass="message error" />
                 	</s:if>
                 	<s:if test="hasActionMessages()">
      					<s:actionmessage cssClass="message info" />
					</s:if>
                 	<!-- End message -->
                 	
					<form action='<s:property value="rootUrl"/>users/add' method="post" >
						<table class="form">
							<tr>
								<td>Nom:</td>
								<!--<td><input type="text" class="medium" name="nom" placeholder="Nom" required="required"></td>-->
								<td><s:textfield cssClass="medium" name="nom" placeholder="Nom" required="required" /></td>
							</tr>
							<tr>
								<td>Prenom:</td>
								<!-- <td><input type="text" class="medium" name="prenom"  placeholder="Prenom" required="required"></td>-->
								<td><s:textfield cssClass="medium" name="prenom" placeholder="Prenom" required="required" /></td>
							</tr>
							<tr>
								<td>Email:</td>
								<!-- <td><input type="email" class="medium" name="email"  placeholder="Email" required="required"></td>-->
								<td><s:textfield cssClass="medium" name="email" placeholder="Email" type="email" required="required" /></td>
							</tr>
							<tr>
								<td>Mot de passe:</td>
								<!-- <td><input type="text" class="medium" name="password"  placeholder="Mot de passe" required="required"></td> -->
								<td><s:textfield cssClass="medium" name="password" placeholder="Mot de passe" required="required" /></td>
							</tr>
							<tr>
								<td>Role:</td>
								<td><select name="role" class="medium">
									<option value="guest">Guest</option>
									<option value="admin">Admin</option>
								</select></td>
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
