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
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>css/reset.css' media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>css/text.css' media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>css/grid.css' media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>css/layout.css' media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>css/nav.css' media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/meditel-style.css' media="screen" />
	
<!--[if IE 6]><link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/ie6.css' media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/ie.css' media="screen" /><![endif]-->
<!-- Lib css -->
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>lib/Chart.js.legend-master/legend.css'
	media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>lib/datetimepicker-master/jquery.datetimepicker.css'
	media="screen" />

<!-- BEGIN: load jquery -->
<script src='<s:property value="rootUrl"/>js/jquery-1.6.4.min.js'
	type="text/javascript"></script>

<script
	src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.core.min.js'
	type="text/javascript"></script>
<script
	src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.widget.min.js'
	type="text/javascript"></script>
<script
	src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.accordion.min.js'
	type="text/javascript"></script>
<script
	src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.core.min.js'
	type="text/javascript"></script>
<script
	src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.slide.min.js'
	type="text/javascript"></script>
<!-- END: load jquery -->

<!-- Add for table -->
<script
	src='<s:property value="rootUrl"/>js/table/jquery.dataTables.min.js'
	type="text/javascript"></script>
<script type="text/javascript">
        $(document).ready(function () {
            setupLeftMenu();
            $('.datatable').dataTable();
			setSidebarHeight();
        });
    </script>

<!-- fin Add for table -->

<!--  Script popup window -->
<script type="text/javascript">
function myPopupGraph(link)
{
	window.open(link, "Graphe", "fullscreen=no, width=1070, height=550").focus();
}
</script>

<!--  fin Script popup window -->

<style type="text/css">
a:hover {
	color: blue;
}

.theRedValue {
	color: red;
}
</style>

<script src='<s:property value="rootUrl"/>js/setup.js'
	type="text/javascript"></script>

<script
	src='<s:property value="rootUrl"/>lib/datetimepicker-master/jquery.js'></script>
<script type="text/javascript">
	var jQueryDatatime = $.noConflict(true);
</script>
<script
	src='<s:property value="rootUrl"/>lib/datetimepicker-master/jquery.datetimepicker.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
				
		try{
			//Datetime picker
			jQueryDatatime('#startdatetimepicker').datetimepicker({
// 				format:'Y-m-d H:i', 
				format:'Y-m-d', 
				lang:'fr',
				minDate:'2014/07/01',
				maxDate:'0',
				timepicker : false
			});
			jQueryDatatime('#enddatetimepicker').datetimepicker({
				format:'Y-m-d', 
				lang:'fr',
				minDate:'2014/07/01',
				maxDate:'0',
				timepicker : false
			});
			//end Datetime picker
			
		}catch(e){
			console.log(e);
		}
		
		
		//side Menu
		setupLeftMenu();
		setSidebarHeight();
		//end side Menu
		
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

		<!-- Box graphe interface infos -->
		<div class='<s:property value="gridClass" />'>
			<div class="box round first">
				<h2>Liste des saturations detectées </h2>
				<div class="block">

					<!-- Message Error & Info & warning -->
					<ul class="message warning">
						<li>NB: <strong>le rang</strong> = La marge de detection des saturation entre plusieurs périodes.</li>
						<li>NB: <strong>nombre repétition</strong> = Nombre des valeurs répétitives qui ne dépassent pas un rang.</li>
						<li>NB: <strong>traffics intervale values</strong> = La valeur Min et Max en Mo des trafics visés par cette recherche.</li>
					</ul>
					<s:if test="hasActionErrors()">
						<s:actionerror cssClass="message error" />
					</s:if>
					<s:if test="hasActionMessages()">
						<s:actionmessage cssClass="message info" />
					</s:if>
					<!-- End message -->

					
					<!-- Formulaire -->
					
					<form action='<s:property value="rootUrl"/>saturation/detection'
						method="post" onsubmit="popupMedi('Cette opération peut prendre plusieurs minutes, Veuillez patienter...');" >
						<table class="form">

							<tr>
								<td>Date de le journée:</td>
								<td><input id="startdatetimepicker" type="text"
									name="startDate" class="medium" placeholder="yyyy-mm-dd"
									readonly="readonly" required="required" value='<s:property value="startDate"/>' ></td>
								<%-- <td><s:textfield name="startDate" id="startdatetimepicker" cssClass="medium" placeholder="yyyy-mm-dd hh:mm" readonly="readonly" required="required" /></td> --%>
							</tr>
							
							<tr>
								<td>Node :</td>
								<td><select name="idNode"
													id="selectionOptionNode"
									>
									
									<s:iterator value="lesNodes">
										<option value='<s:property value="idNode" />' ><s:property value="nodeName" /></option>
									</s:iterator>
	
								</select></td>
							</tr>
							
							<tr>
								<td>Traffic Type :</td>
								<td>
									<input type="radio" id="TrafficType_inOut" name="TrafficType" value="InOut" checked="checked" onchange="myChangeFunction();" />InOut
                             	   	<input type="radio" name="TrafficType" value="Class" onchange="myChangeFunction();" />Par Classe
                             	</td>
							</tr>
							<tr>
								<td>Paramétrage :</td>
								<td>
									Rang : <input type="text" name="rang" value="1" />
                             	   	nombre repétition : <input type="text" name="varScore" value="4" />
                             	</td>
							</tr>
							<tr>
								<td>traffics intervale values :</td>
								<td>
                             	   	MinValue : <input type="text" name="TrafficMin" value="20" />
                             	   	MaxValue : <input type="text" name="TrafficMax" value="2000000" />
                             	</td>
							</tr>
							<td>Marge d'agrégation:</td>
								<td>
									<input type="radio" name="margeAgregation" value="5" />5 minutes
                             	   	<input type="radio" name="margeAgregation" value="15" checked="checked" />15 minutes
                             	   	<input type="radio" name="margeAgregation" value="30" />30 minutes
                             	   	<input type="radio" name="margeAgregation" value="60" />1 heure
                             	</td>
							<tr>
								<td></td>
								<td>
								<button class="btn btn-green" style="width: 50%;">Récupérer le résultat</button>
								</td>
							</tr>
							
							</table>
					</form>
					<table class="form">
					<tr>
								<td><button onclick='javascript:document.location="<s:property value="rootUrl"/>traffics/notificationError";' class="btn-icon btn-refresh btn-grey">
										<span></span> Actualiser
									</button></td>
								<td>
									
									
								</td>
							</tr>
						</table>
						
						
					<table class="data display datatable" id="example">
						<thead>
							<tr>
								<th>Jour</th>
								<th>maxMetriceValue</th>
								<th>TrafficType</th>
								<th>Interface</th>
								<th>marge d'Agregation</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<!-- champ variables -->
							
							<s:iterator value="trafficNotis" var="trafficNoti">
							<s:if test="testNotificationVu()">
								<tr>
							</s:if>
							<s:else>
								<tr style="background-color: #efc2c2;">
							</s:else>
								
								<td><s:property value="dateJour" /></td>
								<td><s:property value="roundMaxMetriceValue()" /></td>
								<td><s:property value="typeOrClassTraffic" /></td>
								<td>Name : <s:property value="getIinterface().getIfAlias()" />
									&nbsp;&nbsp;&nbsp;Node : <s:property value="getIinterface().getNode().getNodeName()" />
								</td>
								<td>
									<s:property value="marge" /> min
								</td>
								<s:if test="isClassType()">
									<s:url action="perClassGraphe" var="theUrl" escapeAmp="false">
											<s:param name="interfacesId">
												<s:property value="getIdInterface()" />
											</s:param>
											
											<s:param name="classTraffic">
												<s:property value="typeOrClassTraffic" />
											</s:param>
											
											<s:param name="grapheType">
												line
											</s:param>
											<s:param name="margeAgregation">
												<s:property value="marge" />
											</s:param>
											<s:param name="niveauAgregation">
												avg
											</s:param>
											
											<s:param name="grapheNotificationType">
												isValue
											</s:param>
											<s:param name="grapheTypeChart_">
												type1
											</s:param>
											
											<s:param name="grapheType">
												line
											</s:param>
											
											<s:param name="startDate">
												<s:property value="getTheStartDate()" />
											</s:param>
											<s:param name="endDate">
												<s:property value="getTheEndDate()" />
											</s:param>
										</s:url>
									</s:if>
									<s:else>
										<s:url action="inOutGraphe" var="theUrl" escapeAmp="false">
											<s:param name="interfacesId">
												<s:property value="getIdInterface()" />
											</s:param>
											
											<s:param name="typeTraffic">
												<s:property value="typeOrClassTraffic" />
											</s:param>
											
											<s:param name="grapheType">
												line
											</s:param>
											<s:param name="margeAgregation">
												<s:property value="marge" />
											</s:param>
											<s:param name="niveauAgregation">
												avg
											</s:param>
											
											<s:param name="grapheNotificationType">
												isValue
											</s:param>
											
											<s:param name="grapheTypeChart_">
												type1
											</s:param>
											
											<s:param name="grapheType">
												line
											</s:param>
											
											<s:param name="startDate">
												<s:property value="getTheStartDate()" />
											</s:param>
											<s:param name="endDate">
												<s:property value="getTheEndDate()" />
											</s:param>
										</s:url>
									</s:else>
									<td>
									
										<button class="btn btn-small btn-blue" onclick='myPopupGraph("<s:property value="#theUrl"/>");'>
										View
										</button>
										
										<button class="btn-small btn-icon btn-red btn-cross " >
										<span></span>
										<a style="color:#FFF;" href="<s:property value="rootUrl"/>traffics/notificationError?idTrafficNoti=<s:property value="idTrafficNotification"/>" >Supprimer</a>
										</button>
										
								</td>

								</tr>
							</s:iterator>
						</tbody>
					</table>

				</div>
			</div>
		</div>
		<!-- End Box graphe interface infos  -->

		<div class="clear"></div>
	</div>
	<!-- footer -->
	<s:include value="include/footer.jsp"></s:include>

	<!-- Chartjs import -->
	<script src='<s:property value="rootUrl"/>lib/chartjs/Chart.js'></script>
	<script src='<s:property value="rootUrl"/>lib/chartjs/Chart.Core.js'></script>
	<script
		src='<s:property value="rootUrl"/>lib/chartjs/Chart.Doughnut.js'></script>
	<script src='<s:property value="rootUrl"/>lib/chartjs/Chart.Line.js'></script>
	<script src='<s:property value="rootUrl"/>lib/chartjs/Chart.Bar.js'></script>
	<script
		src='<s:property value="rootUrl"/>lib/Chart.js.legend-master/legend.js'></script>
	<script type="text/javascript">
	
	var listNode_InOut = new Array();
	var listNode_Class = new Array();
	
	<s:iterator value="lesNodes">
		
	listNode_InOut[listNode_InOut.length] = {
			idNode : '<s:property value="idNode" />',
			nodeName : '<s:property value="nodeName" />'
		};
	
	</s:iterator>;
	
	<s:iterator value="lesNodes_forClass">
	
	listNode_Class[listNode_Class.length] = {
			idNode : '<s:property value="idNode" />',
			nodeName : '<s:property value="nodeName" />'
		};
	
	</s:iterator>
	
	function myChangeFunction(){
		
		var radioButton = document.getElementsByName("TrafficType");
		var selectedVar = document.getElementById("selectionOptionNode");
		var lavaleur = null;
		//radioButton (radio)
		for(var i=0; i<radioButton.length; i++){
			if(radioButton[i].checked){
				lavaleur = radioButton[i].value;
				break;
			}
		}
		
		if(lavaleur == "InOut")
			{
				selectedVar.innerHTML = "";
				
				for(var c = 0; c < listNode_InOut.length; c++)
				{
					
						var valeur_ = listNode_InOut[c].idNode;
						var input_option = document.createElement("option");
						input_option.setAttribute('value',valeur_);
						input_option.innerHTML = listNode_InOut[c].nodeName ;
						selectedVar.add(input_option);	
					
				}
				
			}else 
				{
					selectedVar.innerHTML = "";
				
					for(var c = 0; c < listNode_Class.length; c++)
					{
						
							var valeur_ = listNode_Class[c].idNode;
							var input_option = document.createElement("option");
							input_option.setAttribute('value',valeur_);
							input_option.innerHTML = listNode_Class[c].nodeName ;
							selectedVar.add(input_option);	
						
					}
				}
		
		
	};
	
	</script>
	<!-- End Chartjs import -->

</body>
</html>