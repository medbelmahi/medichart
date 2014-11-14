<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Meditel Reporting</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/text.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/grid.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/layout.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/nav.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/meditel-style.css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" type="text/css" href="css/ie6.css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" type="text/css" href="css/ie.css" media="screen" /><![endif]-->
    <link href="css/fancy-button/fancy-button.css" rel="stylesheet" type="text/css" />
    <!--Jquery UI CSS-->
    <link href="css/themes/base/jquery.ui.all.css" rel="stylesheet" type="text/css" />    
	<script type="text/javascript">
	function isIE(){
		var nav = navigator.userAgent.toLocaleLowerCase();
		return (nav.indexOf('msie') != -1) ? parseInt(nav.split('msie')[1]) : false;
	}
	if(isIE()){
		window.location = '<s:property value="rootUrl"/>support/isIE';
	}
	</script>

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
                    <img src="img/logo.png" alt="Logo" />
				</div>
                <div class="clear">
                </div>
            </div>
        </div>
        <div class="clear">
        </div>
		<!-- login box -->
        <div class="grid_5 medi_center" >
            <div class="box round first fullpage" style="border-radius: 5px 5px;-webkit-box-shadow: 3px 3px 6px 0px rgba(50, 50, 50, 0.61);  -moz-box-shadow:3px 3px 6px 0px rgba(50, 50, 50, 0.61);  box-shadow:3px 3px 6px 0px rgba(50, 50, 50, 0.61);" >
                <div class="block " style="padding-top: 0;" >
                    <form action="login" method="post">
                    <table class="form">
						<tr>
						<s:if test="hasActionErrors()">
                        	<s:actionerror cssClass="message error" />
                        </s:if>
						</tr>
                        <tr>
                            <td class="col1">
                                <label>E-mail:</label>
                            </td>
                            <td class="col2">
                            	<s:textfield name="email" cssClass="large" placeholder="Email@mail.com" required="required" />
                            </td>
                        </tr>
						<tr>
                            <td class="col1">
                                <label>Mot de passe:</label>
                            </td>
                            <td class="col2">
                            	<s:password name="password" cssClass="large" placeholder="Mot de passe" required="required" />
                            </td>
                        </tr>
						<!-- vide -->
						<tr>
                            <td></td>
                            <td></td>
                        </tr>
						<tr>
                            <td></td>
                            <td></td>
                        </tr>
						<!-- end vide -->
						<tr>
                            <td></td>
                            <td>
								<input type="submit" class="large" value="Se connecter" >
                            </td>
                        </tr>
                    </table>
                    </form>
                </div>
            </div>
        </div>
        <div class="clear">
        </div>
    </div>
    <!-- footer -->
	<s:include value="include/footer.jsp"></s:include>
</body>
</html>
