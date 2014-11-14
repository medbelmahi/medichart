<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- URL to the root -->
<s:url namespace="/" action="" var="rootUrl" />

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Meditel Reporting</title>
    <link rel="stylesheet" type="text/css" href="<s:property value="rootUrl"/>css/reset.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="<s:property value="rootUrl"/>css/text.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="<s:property value="rootUrl"/>css/grid.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="<s:property value="rootUrl"/>css/layout.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="<s:property value="rootUrl"/>css/nav.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<s:property value="rootUrl"/>css/meditel-style.css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" type="text/css" href="<s:property value="rootUrl"/>css/ie6.css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" type="text/css" href="<s:property value="rootUrl"/>css/ie.css" media="screen" /><![endif]-->
    <link href="<s:property value="rootUrl"/>css/fancy-button/fancy-button.css" rel="stylesheet" type="text/css" />
    <!--Jquery UI CSS-->
    <link href="<s:property value="rootUrl"/>css/themes/base/jquery.ui.all.css" rel="stylesheet" type="text/css" />    
    
	<style>
		html, body {
			height: 100%;
		}
	</style>
</head>
<body>
    <div class="container_12" style="height:95%;">
		<!-- l'entete -->
        <div class="grid_12 header-repeat">
            <div id="branding">
                <div class="floatleft">
                    <img src="<s:property value="rootUrl"/>img/logo.png" alt="Logo" />
				</div>
                <div class="clear">
                </div>
            </div>
        </div>
        <div class="clear">
        </div>
		<!-- login box -->
		<center>
        <div class="grid_5 medi_center" style="margin: 5px auto;" >
            <div class="box round first fullpage" style="border-radius: 5px 5px;-webkit-box-shadow: 3px 3px 6px 0px rgba(50, 50, 50, 0.61);  -moz-box-shadow:3px 3px 6px 0px rgba(50, 50, 50, 0.61);  box-shadow:3px 3px 6px 0px rgba(50, 50, 50, 0.61);" >
                <div class="block " style="text-align: center;" >
                   <h6>Nous sommes désolés, le site Mappy n'est pas compatible avec la version du navigateur que vous utilisez. </h6>
                   <h6>Pour profiter d'une expérience de navigation optimale, nous vous invitons de mettre à jour votre navigateur.</h6>
                   
                   <p>Ou utiliser des navigateurs recommandés comme: </p>
					<ul>
						<li><a target="_blank" href="https://www.google.fr/chrome/browser/">Chrome</a></li>
						<li><a target="_blank" href="https://www.mozilla.org/fr/">FireFox</a></li>
					</ul>
                   
                   <h6 style="margin-top: 10px;margin-bottom: 10px;"><img src="<s:property value="rootUrl"/>img/tools-159692_150.png"></h6>
                   <h6></h6>
                   <a href="http://windows.microsoft.com/fr-fr/internet-explorer/download-ie"><button style="width:95%;" class="btn-icon btn-grey btn-refresh"><span></span>Mettre à jour</button></a>
                </div>
            </div>
        </div>
        </center>
        <div class="clear">
        </div>
    </div>
    <div class="clear">
    </div>
    <div id="site_info">
        <p>© <a href="http://meditel.ma" target="_blank">Méditel</a> 2014.</p>
    </div>
</body>
</html>
