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
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/meditel-style.css' media="screen" />
<!--[if IE 6]><link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/ie6.css' media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/ie.css' media="screen" /><![endif]-->

<!-- BEGIN: load jquery -->
<script src='<s:property value="rootUrl"/>js/jquery-1.6.4.min.js' type="text/javascript"></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.core.min.js' type="text/javascript" ></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.widget.min.js' type="text/javascript"></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.accordion.min.js' type="text/javascript"></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.core.min.js' type="text/javascript"></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.slide.min.js' type="text/javascript"></script>
<!-- END: load jquery -->

<!-- jQuery dialog related-->
    <script src='<s:property value="rootUrl"/>js/jquery-ui/external/jquery.bgiframe-2.1.2.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.mouse.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.draggable.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.position.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.resizable.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.dialog.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.core.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.blind.min.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.explode.min.js' type="text/javascript"></script>
    <!-- jQuery dialog end here-->
    <script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.accordion.min.js' type="text/javascript"></script>
    <!--Fancy Button-->
    <script src='<s:property value="rootUrl"/>js/fancy-button/fancy-button.js' type="text/javascript"></script>
    <script src='<s:property value="rootUrl"/>js/setup.js' type="text/javascript"></script>
    <!-- Load TinyMCE -->
    <script src='<s:property value="rootUrl"/>js/tiny-mce/jquery.tinymce.js' type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
		setupLeftMenu();
		setSidebarHeight();
		
		// if user clicked on button, the overlay layer or the dialogbox, close the dialog    
	    $('a.btn-ok, #dialog-overlay, #dialog-box').click(function () {        
	        //$('#dialog-overlay, #dialog-box').hide();        
	        return false;
	    });
	    
	    // if user resize the window, call the same function again
	    // to make sure the overlay fills the screen and dialogbox aligned to center    
	    $(window).resize(function () {
	        //only do it if the dialog box is not hidden
	        if (!$('#dialog-box').is(':hidden')) popupMedi();        
	    });    
		
	});
	
	//Popup dialog
	function popupMedi(message) {
	    // get the screen height and width  
	    var maskHeight = $(document).height();  
	    var maskWidth = $(window).width();
	    
	    // calculate the values for center alignment
	    var dialogTop =  (maskHeight/3) - ($('#dialog-box').height());  
	    var dialogLeft = (maskWidth/2) - ($('#dialog-box').width()/2); 
	    
	    // assign values to the overlay and dialog box
	    $('#dialog-overlay').css({height:maskHeight, width:maskWidth}).show();
	    $('#dialog-box').css({top:dialogTop, left:dialogLeft}).show();
	    
	    // display the message
	    $('#dialog-message').html(message);            
	}
	
</script>

</head>
<body>
	<div class="container_12">
	<!-- popup window -->
		<div id="dialog-overlay"></div>
			<div id="dialog-box">
		    <div class="dialog-content">
		        <div id="dialog-message"></div>
		        <a class="buttonloader"><img src='<s:property value="rootUrl"/>img/loading.GIF' /></a>
		    </div>
		</div>
		<!-- end popup window -->
		
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
				<h2>Supprimer tous les interfaces physiques</h2>
				<div class="block">
					<!-- Message Error & Info & warning -->
					<ul class="message warning">
						<li>
							NB: Notez-vous que les interfaces seront <strong>supprimées automatiquement</strong> sans confirmation. 
							Tous les données relativent au interfaces physiques (charges CPU,...) seront supprimées egalement.
						</li>
					</ul>
					<s:if test="hasActionErrors()">
                        <s:actionerror cssClass="message error" />
                 	</s:if>
                 	<s:if test="hasActionMessages()">
      					<s:actionmessage cssClass="message info" />
					</s:if>
                 	<!-- End message -->
                 	
					<form action='<s:property value="rootUrl"/>interfacesPhysics/deleteAll' method="post" onsubmit="popupMedi('Cette opération peut prendre plusieurs minutes, Veuillez patienter...');" >
						<table class="form">
							<tr>
								<td><input name="confirme" type="checkbox"  required="required" />Veuillez confirmer la suppression</td>
							</tr>
							<tr>
								<td><input type="submit" value="Supprimer tous les interfaces"></td>
								<td></td>
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