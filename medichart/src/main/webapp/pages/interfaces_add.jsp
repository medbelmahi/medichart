<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- URL to the root -->
<s:url namespace="/" action="" var="rootUrl" />

<!DOCTYPE html>
<html>
<head>

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
		
		<div class="grid_2">
			
			<!-- boxSideMenu -->
			<s:include value="include/boxSideMenu.jsp"></s:include>
			

		</div>
		
		<!-- Box add users -->
		<div class="grid_10">
			<div class="box round first">
				<h2>Ajouter une nouvelle interface logique</h2>
				<div class="block">
				
					<!-- Message Error & Info & warning -->
					<s:if test="hasActionErrors()">
                        <s:actionerror cssClass="message error" />
                 	</s:if>
                 	<s:if test="hasActionMessages()">
      					<s:actionmessage cssClass="message info" />
					</s:if>
                 	<!-- End message -->
                 	
					<form action='<s:property value="rootUrl"/>interfaces/add' method="post" >
						<table class="form">
							<tr>
								<td>Index:</td>
								<td><s:textfield cssClass="medium" name="ifIndex" placeholder="Index de l'interface" required="required" /></td>
							</tr>
							<tr>
								<td>Nom:</td>
								<td><s:textfield cssClass="medium" name="ifName" placeholder="Nom de l'interface" required="required" /></td>
							</tr>
							<tr>
								<td>Alias:</td>
								<td><s:textfield cssClass="medium" name="ifAlias" placeholder="Alias de l'interface" required="required" /></td>
							</tr>
							<tr>
								<td>Description:</td>
								<td><s:textfield cssClass="medium" name="ifDescr" placeholder="Description de l'interface" required="required" /></td>
							</tr>
							<tr>
								<td>Speed:</td>
								<td><s:textfield cssClass="medium" name="ifSpeed" placeholder="Vitesse de l'interface (x Mbps ,x Gbps, ...)" required="required" /></td>
							</tr>
							<tr>
								<td>Type:</td>
								<td><s:textfield cssClass="medium" name="ifType" placeholder="Type de l'interface" required="required" /></td>
							</tr>
							<tr>
								<td>Node:</td>
								<td>	<s:select name="nameNode" label="Ref Node" list="nodesList"  /></td>
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