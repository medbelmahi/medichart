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
<!--[if IE 6]><link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/ie6.css' media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/ie.css' media="screen" /><![endif]-->
<!-- Lib css -->
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>lib/Chart.js.legend-master/legend.css' media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>lib/datetimepicker-master/jquery.datetimepicker.css' media="screen" />

<!-- BEGIN: load jquery -->
<script src='<s:property value="rootUrl"/>js/jquery-1.6.4.min.js' type="text/javascript"></script>

<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.core.min.js' type="text/javascript" ></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.widget.min.js' type="text/javascript"></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.ui.accordion.min.js' type="text/javascript"></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.core.min.js' type="text/javascript"></script>
<script src='<s:property value="rootUrl"/>js/jquery-ui/jquery.effects.slide.min.js' type="text/javascript"></script>
<!-- END: load jquery -->

<script src='<s:property value="rootUrl"/>js/setup.js' type="text/javascript"></script>

<script src='<s:property value="rootUrl"/>lib/datetimepicker-master/jquery.js'></script>
<script type="text/javascript">
	var jQueryDatatime = $.noConflict(true);
</script>
<script src='<s:property value="rootUrl"/>lib/datetimepicker-master/jquery.datetimepicker.js'></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		try{
			//Datetime picker
			jQueryDatatime('#startdatetimepicker').datetimepicker({
				format:'Y-m-d H:i', 
				lang:'fr',
				minDate:'2014/08/01',
				maxDate:'0'
			});
			jQueryDatatime('#enddatetimepicker').datetimepicker({
				format:'Y-m-d H:i', 
				lang:'fr',
				minDate:'2014/08/01',
				maxDate:'0'
			});
			//end Datetime picker
			
		}catch(e){
			console.log(e);
		}
		
		//side Menu
		setupLeftMenu();
		setSidebarHeight();
		//end side Menu
		
	});
	
	
	function onSubmiForm(){
		var interfacesId = "";
		var typeTraffic = "";
		var grapheType = "";
		var grapheTypeChart = "";
		var margeAgregation = "";
		var niveauAgregation = "";
		var startDate = "", endDate = "";
		
		var einterfacesId = document.getElementsByName("interfacesId")[0];
		var etypeTraffic = document.getElementsByName("typeTraffic");
		var egrapheType = document.getElementsByName("grapheType");
		var egrapheTypeChart = document.getElementsByName("grapheTypeChart_");
		var emargeAgregation = document.getElementsByName("margeAgregation");
		var eniveauAgregation = document.getElementsByName("niveauAgregation");
		var estartDate = document.getElementsByName("startDate")[0];
		var eendDate = document.getElementsByName("endDate")[0];
		
		//start making link
		var link = "inOutGraphe?";
		
		//interfacesId (select)
		for(var i=0; i<einterfacesId.length; i++){
			if(einterfacesId[i].selected){
				interfacesId += "interfacesId=" + einterfacesId[i].value + "&";
			}
		}
		
		//typeTraffic(checkbox)
		for(var i=0; i<etypeTraffic.length; i++){
			if(etypeTraffic[i].checked){
				typeTraffic += "typeTraffic=" + etypeTraffic[i].value + "&";
			}
		}
		
		//grapheType (radio)
		for(var i=0; i<egrapheType.length; i++){
			if(egrapheType[i].checked){
				grapheType += "grapheType=" + egrapheType[i].value + "&";
				break;
			}
		}
		
		//grapheTypeChart (radio)
		for(var i=0; i<egrapheTypeChart.length; i++){
			if(egrapheTypeChart[i].checked){
				grapheTypeChart += "grapheTypeChart_=" + egrapheTypeChart[i].value + "&";
				break;
			}
		}
		
		//margeAgregation (radio)
		for(var i=0; i<emargeAgregation.length; i++){
			if(emargeAgregation[i].checked){
				margeAgregation += "margeAgregation=" + emargeAgregation[i].value + "&";
				break;
			}
		}
		
		//niveauAgregation (radio)
		for(var i=0; i<eniveauAgregation.length; i++){
			if(eniveauAgregation[i].checked){
				niveauAgregation += "niveauAgregation=" + eniveauAgregation[i].value + "&";
				break;
			}
		}
		
		//startDate + endDate (input)
		startDate += "startDate=" + estartDate.value + "&";
		endDate += "endDate=" + eendDate.value;
		
// alert(grapheType);
		if(interfacesId=="" || typeTraffic=="" || margeAgregation=="" || niveauAgregation=="" || startDate=="" || endDate==""){
			alert("Alert: Vueillez remplir tous les champs");
			
		}else{
			link += interfacesId + typeTraffic + grapheType + grapheTypeChart + margeAgregation + niveauAgregation + startDate + endDate;
			myPopupGraph(link);
		}
		
		return false;
	}
	
	function myPopupGraph(link)
	{
		window.open(link, "", "fullscreen=no, width=1070, height=550").focus();
	}
</script>

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
		
		<!-- Box graphe interface infos -->
		<div class='<s:property value="gridClass" />'>
			<div class="box round first">
				<h2>Générer le graphe de trafics entrant/sortant</h2>
				<div class="block">
				
					<!-- Message Error & Info & warning -->
					<ul class="message warning">
						<li>
							NB: Notez-vous que vous pouvez choisir plusieurs interfaces, maximum <strong>6</strong> interfaces. (<strong>CTRL + Clique</strong> pour selectionner plusieurs interfaces).
						</li>
					</ul>
					<s:if test="hasActionErrors()">
                        <s:actionerror cssClass="message error" />
                 	</s:if>
                 	<s:if test="hasActionMessages()">
      					<s:actionmessage cssClass="message info" />
					</s:if>
                 	<!-- End message -->
                 		<!-- <div style="width:55%; height:310px;">
												
												<div id="nodeee">
													
												</div>
												
												<div id="interfacceee">
													
												</div>
												
												
												<div id="buttonDiv" style="display: inline-block; margin-top: 15px;">
												
													
													
												
												
												</div>
												
												
												<div id="removeSelect">
												</div>
												
												<div id="removeAll">
												</div>
											
											</div> -->
											<table class="form">
											
											<tr>
												<td>Node</td>
												<td><select name="theSelectNode"
													id="selectionOptionNode"
													onchange="myOnchangeSelectFunction()">
	
													</select></td>
												
											</tr>
											
											<tr>
												<td>interface</td>
												<td><select name="theSelectInterface"
												id="selectionOptionInterface"></select></td>
											</tr>
											
											
											<tr>
												<td>Action</td>
												<td>
												<div id="buttonDiv" style="margin-top: 15px;">
													<button class="btn btn-blue" onclick='addInterfac();'>
														Ajouter
													</button>
													
													<button class="btn btn-purpel" onclick='removeSelected();'>
														remove selected
													</button>
													<button class="btn btn-red" onclick='removeAll();'>
														remove All
													</button>
												</div>
												</td>
											</tr>
											
					<form action='<s:property value="rootUrl"/>traffics/inOutGraphe' method="get" onsubmit="return onSubmiForm();" >
						
							<tr>
								<td style="vertical-align: middle;">Node / Interfaces:</td>
								<td>
								<%-- <select multiple="multiple" name="interfacesId" style="width:55%; height:200px;" >
								<s:iterator value="mapInterfaces">
								
									<optgroup label="<s:property value="key" />">
  									<s:iterator value="value">
	  									<s:if test="%{idInterface in interfacesId}">
	            							<option selected="selected" value="<s:property value="idInterface" />"><s:property value="ifAlias" /></option>
	            						</s:if>
	    								<s:else>
	    									<option value="<s:property value="idInterface" />"><s:property value="ifAlias" /></option>
	    								</s:else>
  									</s:iterator>
  									</optgroup>
  									
								</s:iterator>
								</select> --%>
											
								<div id="laselectInterface" style="margin-top: 15px;">
													<select multiple="multiple"
													id="ListInterface" name="interfacesId"
													style="width: 400px; height: 150px;">
		
												</select>
												</div>

								</td>
							</tr>
						
							<tr>
								<td>Date de début:</td>
								<td><input id="startdatetimepicker" type="text" name="startDate" class="medium" placeholder="yyyy-mm-dd hh:mm" required="required" readonly="readonly" ></td>
								<%-- <td><s:textfield name="startDate" id="startdatetimepicker" cssClass="medium" placeholder="yyyy-mm-dd hh:mm" required="required" /></td> --%>
							</tr>
							
							<tr>
								<td>Date de fin:</td>
								<td><input id="enddatetimepicker" type="text" name="endDate" class="medium" placeholder="yyyy-mm-dd hh:mm" required="required" readonly="readonly" ></td>
								<%-- <td><s:textfield name="endDate" id="enddatetimepicker" cssClass="medium" placeholder="yyyy-mm-dd hh:mm" required="required" /></td> --%>
							</tr>
							<tr>
								<td>IN/OUT:</td>
								<td>
									<s:checkbox name="typeTraffic" fieldValue="in" checked="checked" />IN
									<s:checkbox name="typeTraffic" fieldValue="out" />OUT
								</td>
								<!-- <td><input type="radio" name="typeTraffic" value="in" checked="checked"/>IN
                                <input type="radio" name="typeTraffic" value="out" />OUT</td> -->
							</tr>
							<input type="hidden" name="grapheType" value="line"/>
							<tr>
								<td>Graphe chart type:</td>
								<td>
									<input type="radio" name="grapheType" value="line" checked="checked"/>Line
                             	   	<input type="radio" name="grapheType" value="bar" />Bar
                             	</td>
								<%-- <td>
									<select name="grapheType">
										<option value="line">Line</option>
										<option value="bar">Bar</option>
									</select>
								</td> --%>
							</tr>
							
							<tr>
								<td>Chart type:</td>
									<td>
										<input type="radio" name="grapheTypeChart_" value="type1" checked="checked"/>type Curve
	                             	   	<input type="radio" name="grapheTypeChart_" value="type2" />type contunue
	                             	</td>
							</tr>
							
							<tr>
								<td>Marge d'agrégation:</td>
								<td>
									<input type="radio" name="margeAgregation" value="5" />5 minutes
                             	   	<input type="radio" name="margeAgregation" value="15" checked="checked" />15 minutes
                             	   	<input type="radio" name="margeAgregation" value="30" />30 minutes
                             	   	<input type="radio" name="margeAgregation" value="60" />1 heure
                             	   	<input type="radio" name="margeAgregation" value="1440" />1 jour
                             	</td>
								<%-- <td>
									<select name="margeAgregation">
										<option value="5">5 minutes</option>
										<option value="15" selected="selected">15 minutes</option>
										<option value="30">30 minutes</option>
										<option value="60">1 heure</option>
										<option value="1440">1 jour</option>
									</select>
								</td> --%>
							</tr>
							
							<tr>
								<td>Niveau d'agrégation:</td>
								<td>
									<input type="radio" name="niveauAgregation" value="avg" checked="checked" />AVG
                             	   	<input type="radio" name="niveauAgregation" value="min"  />MIN
                             	   	<input type="radio" name="niveauAgregation" value="max" />MAX
<!--                              	   	<input type="radio" name="niveauAgregation" value="reel" />REEL -->
                             	</td>
                             </tr>
						
							<tr>
								<td></td>
								<td>
								<button class="btn btn-green" style="width: 50%;">Tracer la courbe</button>
<!-- 								<input type="submit" class="medium" value="Tracer la courbe"> -->
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<!-- End Box graphe interface infos  -->
		
		<div class="clear"></div>
	</div>
	<script src='<s:property value="rootUrl"/>js/selectInterface.js' type="text/javascript"></script>
	<script type="text/javascript">
	
		var iterfaceOption = new Array();
		var listNode = new Array();
		
		<s:iterator value="mapInterfaces">
		
		listNode[listNode.length] = '<s:property value="key" />';
		
		</s:iterator>
		var keyNode;
		<s:iterator value="mapInterfaces">

		keyNode = '<s:property value="key" />';

			<s:iterator value="value">
	
			iterfaceOption[iterfaceOption.length] = {
				node : keyNode,
				aliasInterface : '<s:property value="ifAlias" />',
				idInterface : '<s:property value="idInterface" />'
			};
	
			</s:iterator>

		</s:iterator>
		
		
		var selectedVarNode = document.getElementById("selectionOptionNode");
		
		for(var j = 0; j < listNode.length ; j++)
			{
				var input_option = document.createElement("option");
				input_option.setAttribute('value',listNode[j]);
				input_option.innerHTML = listNode[j] ;
				selectedVarNode.add(input_option);
			}
		
		
		myOnchangeSelectFunction();
		
	</script>
	
	
	
	
	<!-- footer -->
	<s:include value="include/footer.jsp"></s:include>
</body>
</html>