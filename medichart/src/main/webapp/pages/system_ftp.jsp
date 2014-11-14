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
                 	
					<form action='<s:property value="rootUrl"/>system/ftpmerge' method="post" >
						<table class="form">
							<s:hidden id="idSystemFTP" name="idSystemFTP" value="1" />
							<tr>
								<td>Adresse IP des serveurs FTP :</td>
								<td><s:textfield id="hostAddress" cssClass="medium" name="hostAddress" required="required" value="100.09.299" /></td>
							</tr>
							<tr>
								<td>Username:</td>
								<td><s:textfield id="username" cssClass="medium" name="username" value="user" required="required" /></td>
							</tr>
							<tr>
								<td>Mot de passe:</td>
								<td><s:password id="password" cssClass="medium" name="password" value="passe" required="required" /></td>
							</tr>
							<tr>
								<td>Path:</td>
								<td><s:textfield id="path" cssClass="medium" name="path" value="path" required="required" /></td>
							</tr>
							<tr>
								<td>PATH Type:</td>
								<td>
									
									 <select id="type" name="type" onchange="myOnchangeSelectFunction()">
									 
									 </select>
								</td>
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
	
	<script type="text/javascript">
	
	var ftpServers = new Array();
	
	<s:iterator value="ftpServers">
	ftpServers[ftpServers.length] = {
									idSystemFTP : '<s:property value="idSystemFTP" />' ,
									hostAddress : '<s:property value="hostAddress" />' ,
									username : '<s:property value="username" />' ,
									password : '<s:property value="password" />' ,
									path : '<s:property value="path" />' ,
									type : '<s:property value="type" />'
									};
		
	</s:iterator>

	function remplir_select()
	{
		//var typeSelect = $('#type');
		
		for(var i = 0; i < ftpServers.length ; i++)
		{
			var valeur = ftpServers[i].type;
			var input_option = document.createElement("option");
			input_option.setAttribute('value',valeur);
			input_option.innerHTML = valeur ;
			document.getElementById("type").add(input_option);
		}
		
		myOnchangeSelectFunction();
	}
	
	remplir_select();
	
	function myOnchangeSelectFunction(){
		var selectedVar = document.getElementById("type");
		var valeur;
		for (var i = 0; i < selectedVar.length; i++ )
			{
				if(selectedVar[i].selected)
					{
					valeur = selectedVar[i].value;
					}
			}
		
		for (var i = 0; i < ftpServers.length ; i++)
			{
				if(ftpServers[i].type == valeur)
					{
						document.getElementById("idSystemFTP").setAttribute("value", ftpServers[i].idSystemFTP);
						document.getElementById("hostAddress").setAttribute("value", ftpServers[i].hostAddress);
						document.getElementById("username").setAttribute("value", ftpServers[i].username);
						document.getElementById("password").setAttribute("value", ftpServers[i].password);
						document.getElementById("path").setAttribute("value", ftpServers[i].path);
					}
			}
	}
	
	</script>
	
</body>
</html>