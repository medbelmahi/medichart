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
	href='<s:property value="rootUrl"/>css/table/TableCSSCode.css' media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>css/grid.css' media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>css/layout.css' media="screen" />
	<script src='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/jquery-2.1.1.min.js'></script>
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/legend.css'
	media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/style.css'>


</head>
<body>
	<div id="container">
	
	<!-- Message Error & Info & warning -->
		<s:if test="hasActionErrors()">
		<s:actionerror cssClass="message error" />
	</s:if>
	<s:if test="hasActionMessages()">
		<s:actionmessage cssClass="message warning" />
	</s:if>
	<!-- End message -->
	<!-- Box graphe interface -->
	<s:if test='%{chartData != null}'>
	
			<div class="block">
			
<%-- 			<h5><s:property value="infoGraphe"/></h5> --%>
					<!-- Canvas graphes -->
					<div id="chartDiv">
					
					
					<canvas id="canvas" width="810" height="380"
						style="margin: auto auto; display: inline-block;"></canvas>
					</div>
					<div id="chartLegend">

					</div>
					<!-- End canvas graphes -->
					
					<div id="messageManqueDonnee" style="color:red; font-size:13px; text-align:justify; display:inline-block; float: right; width: 230px; margin-top: 20px; margin-right: 10px;" >
						
						<div id="interfaceInformation_" class="CSSTableGenerator">
							<table>
								<tr>
									<td>Propriété</td>
									<td>Valeur</td>
								</tr>
								<tr>
									<td>Graphe</td>
									<td><s:property value="infoGraphe"/></td>
								</tr>
								<tr>
									<td>Marge d'agrégation</td>
									<td><s:property value="mesgMarge"/></td>
								</tr>
								<tr>
									<td>Niveau d'agrégation</td>
									<td><s:property value="mesgNiveau"/></td>
								</tr>
								<tr>
									<td>Date debut</td>
									<td><s:property value="sdate.toLocaleString()"/></td>
								</tr>
								<tr>
									<td>Date fin</td>
									<td><s:property value="edate.toLocaleString()"/></td>
								</tr>
								
							</table>
							
						</div>
						<div style="margin-top: 20px;">
						
							<button class="btn btn-green"
								onclick='OpenInNewTab("<s:property value="rootUrl"/>traffics/generateTheCsvFile<s:property value="urlForGenerateCSV"/>");'>
								<span></span> Générer un fichier CSV
							</button>
							<br />
							<strong><s:property value="MessageManqueDesdonnee" /></strong>
						</div>
						
					</div>
					
	</div>
	
	<div id="interfaceInformation" class="CSSTableGenerator">
	<table>
			<tr>
			<td>Alias</td>
			<td>Interface</td>
			<td>Node</td>
			</tr>
		
		
		<s:iterator value="listInterfaces" var="interfaceee">
			<tr>
				<td><s:property value="getIfAlias()" /></td>
				<td>Index : <s:property value="getIfIndex()" /> Speed : <s:property value="getIfSpeed()" /></td>
				<td>Node name : <s:property value="getNode().getNodeName()" /> Device vendor : <s:property value="getNode().getDeviceVendor()" /> Management address : <s:property value="getNode().getManagementAddress()" /></td>
			</tr>
			</s:iterator>
	</table>
	</div>
	</s:if>
	<s:else>
	
	</s:else>
	
		<!-- footer -->
	<s:include value="include/footer.jsp"></s:include>
<style type="text/css" >
html, body{
background-color: #FFF;
}
</style>

	</div>
	<script src='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/Chart.js'></script>
	<script src='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/jquery-2.1.1.min.js'></script>
	<script src='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/legend.js'></script>
	
			<script>
			function OpenInNewTab(url) {
				  var win = window.open(url, '_self');
				  win.focus();
				}
			
			try {
				var lineChartData = JSON.parse('<s:property value="chartData"/>'.replace(
						/&quot;/g, '"'));

			} catch (e) {
				
				
			}
			
			var boolBreak = false;
			
			<s:if test='booleRender'>
			boolBreak = true;
			</s:if>
			
			var LineCartDataCostume = new Object();
			
// 			var varTypeGraphe = true;
// 			var varTypeChart = true;
			
			LineCartDataCostume = lineChartData;
			var ctx;
			window.onload = function() {
				ctx = document.getElementById("canvas").getContext("2d");
		
				
				<s:if test='typeDeGraphe'>
				<s:if test='theTypeGraphe'>
						new Chart(ctx).Line(LineCartDataCostume, {
							responsive : true,
							bezierCurve : false,
							pointHitDetectionRadius:1
							<s:if test='booleRender'>
							,scaleFontSize: 16
							</s:if>
	
						});
						
						varTypeChart = true;
					
					</s:if>
					<s:else>
						new Chart(ctx).Line(LineCartDataCostume, {
							responsive : true,
							bezierCurve : true,
							pointHitDetectionRadius:1
							<s:if test='booleRender'>
							,scaleFontSize: 16
							</s:if>
// 							pointDotRadius:1
	
						});
						
						varTypeChart = false;
					
					</s:else>
					
					varTypeGraphe = true;
				</s:if>
				<s:else>
				
					varTypeGraphe = false;
					new Chart(ctx).Bar(LineCartDataCostume, {
						responsive : true,
						barShowStroke : false,
						pointHitDetectionRadius:1
						<s:if test='booleRender'>
							,scaleFontSize: 16
						</s:if>
					});
				</s:else>

					//ctx.clear();
				};

				legend(document.getElementById("chartLegend"), lineChartData);
			</script>
		<script type="text/javascript">
function footer_replace()
{
	var docHeight = $(window).height();
	var footerHeight = $('#footer').height();
	var footerTop = $('#footer').position().top + footerHeight;  
	
	if (footerTop < docHeight) {
		$('#footer').css('margin-top', 0 + (docHeight - footerTop) + 'px');
	}
}
window.onresize = function name() {
	footer_replace();
};
$(document).ready(function() {
	footer_replace();
}
);
</script>

	

</body>
</html>