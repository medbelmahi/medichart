<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- URL to the root -->
<s:url namespace="/" action="" var="rootUrl" />

<!DOCTYPE html>

<html>
	<head>
		<title>A propos de midéChart</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<!--<link rel="stylesheet" type="text/css" href="bootstrap.css" />-->
		<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>pages/about/persoStyle.css' />
	</head>

	<body>
		<div id="container">
			<div id="container-logoDescription">
				<div id="col-md-4">
					<img id="logoImage" src='<s:property value="rootUrl"/>pages/about/loho.png' />
				</div>
				<div id="col-md-8">
					<div id="imageDiv">
						<img src='<s:property value="rootUrl"/>pages/about/mediLogo_About.png' />
					</div>
					<div id="desctiption-text">
						<p>
							Version 1.0 bêta (Juillet/Août) 2014 ©
						</p>
						<p>
							Ceci est une version bêta de Médichart, Cette version est instable.
						</p>
						
						<p>
							<strong>Médichart</strong> est une application d'analyse et de traitement des statistiques et de surveillance des performances et des trafics des équipements (Routeurs, Switchs,..) du réseau Backbone IP de Méditel. <a target="_blank" href='<s:property value="rootUrl"/>support/aide'>lire plus...</a>
						</p>
						<p>
							Merci de nous aider à  améliorer la plateforme, par vos suggestions <a target="_blank" href='<s:property value="rootUrl"/>support/report'>signaler une bug/amélioration.</a>
						</p>
						<p>
							Amusez-vous bien ;)
						</p>
					</div>
				</div>
			</div>
			<div id="footer">
				<table width="500">
					<tr>
						<td>Auteur : </td>
					</tr>
					<tr>
						<td><strong>Younes OUARRAK</strong> (MEDITEL)</td>
						<td>younes.ouarrak@meditel.ma</td>
					</tr>
					<tr>
						<td><strong>Mouad EL FAKIR</strong> (ENSAK)</td>
						<td>mouad.fkr@gmail.com</td>
					</tr>
					<tr>
						<td><strong>Mohamed BELMAHI</strong> (ENSAK)</td>
						<td>medbelmahi@outlook.com</td>
					</tr>
					
				</table>
			</div>
		</div>
	</body>
</html>