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

<script
	src='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/jquery-2.1.1.min.js'></script>
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/legend.css'
	media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/style.css'>

<!--  Script popup window -->
<script type="text/javascript">
function myPopupGraph(link)
{
	window.open(link, "Graphe", "fullscreen=no, width=1070, height=550").focus();
}
</script>

<!--  fin Script popup window -->



</head>



<s:iterator value="trafficNoti" var="theNoti">

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

			<s:param name="grapheNotificationVu">
											Non
										</s:param>

			<s:param name="startDate">
				<s:property value="jourPrecedantDateStart()" />
			</s:param>
			<s:param name="endDate">
				<s:property value="jourPrecedantDateEnd()" />
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

			<s:param name="grapheNotificationVu">
											Non
										</s:param>

			<s:param name="startDate">
				<s:property value="jourPrecedantDateStart()" />
			</s:param>
			<s:param name="endDate">
				<s:property value="jourPrecedantDateEnd()" />
			</s:param>
		</s:url>
	</s:else>

</s:iterator>

<s:iterator value="trafficNoti" var="theNoti">
<s:if test="isClassType()">
	<s:url action="perClassGraphe" var="theUrl2" escapeAmp="false">
			<s:param name="interfacesId">
				<s:property value="getIdInterface()" />
			</s:param>

			<s:param name="classTraffic">
				<s:property value="typeOrClassTraffic" />
			</s:param>

			<s:param name="grapheType">
												line
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

			<s:param name="grapheNotificationVu">
											Non
										</s:param>

			<s:param name="startDate">
						<s:property value="jourSuivantDateStart()" />
					</s:param>
					<s:param name="endDate">
						<s:property value="jourSuivantDateEnd()" />
					</s:param>
		</s:url>
</s:if>
	<s:else>
		<s:url action="inOutGraphe" var="theUrl2" escapeAmp="false">
			<s:param name="interfacesId">
				<s:property value="getIdInterface()" />
			</s:param>

			<s:param name="typeTraffic">
				<s:property value="typeOrClassTraffic" />
			</s:param>

			<s:param name="grapheType">
												line
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

			<s:param name="grapheNotificationVu">
											Non
										</s:param>

			<s:param name="startDate">
						<s:property value="jourSuivantDateStart()" />
					</s:param>
					<s:param name="endDate">
						<s:property value="jourSuivantDateEnd()" />
					</s:param>
		</s:url>
	</s:else>

</s:iterator>


<body>
	<div id="container">

		<!-- Message Error & Info & warning -->
		<s:if test="hasActionErrors()">
			<s:actionerror cssClass="message error" />
		</s:if>
		<s:if test="hasActionMessages()">
			<s:actionmessage cssClass="message info" />
		</s:if>
		<!-- End message -->
		<!-- Box graphe interface -->
		<s:if test='%{chartData != null}'>

			<div class="block">

				<!-- Canvas graphes -->
				<div id="chartDiv">
					<canvas id="canvas" width="810" height="380"
						style="margin: auto auto; display: inline-block;"></canvas>

					
				</div>
				
				<div id="chartLegend">
				
				</div>
				
				<div id="infooIterface"
						>
						
						<div id="interfaceinformation" >
						<table width="200" style="float:right;">
							<tbody>
							<tr>
							    <td colspan="2" style="text-align: center; color:red;" >Interface</td>
							  </tr>
							<tr>
								<td>Index:</td>
								<td><s:property value="trafficNoti.getIinterface().getIfIndex()" /> </td>
							</tr>
							<tr>
								<td>Nom:</td>
								<td><s:property value="trafficNoti.getIinterface().getIfName()" /></td>
							</tr>
							<tr>
								<td>Alias:</td>
								<td><s:property value="trafficNoti.getIinterface().getIfAlias()" /></td>
							</tr>
							<tr>
								<td>Description:</td>
								<td><s:property value="trafficNoti.getIinterface().getIfDescr()" /></td>
							</tr>
							<tr>
								<td>Speed:</td>
								<td><s:property value="trafficNoti.getIinterface().getIfSpeed()" /></td>
							</tr>
							<tr>
								<td>Type:</td>
								<td><s:property value="trafficNoti.getIinterface().getIfType()" /></td>
							</tr>
							<tr>
							    <td colspan="2" style="text-align: center; color:red;" >Node</td>
							  
							</tr>
							
							<tr>
								<td>Node name:</td>
								<td><s:property value="trafficNoti.getIinterface().getNode().getNodeName()" /></td>
							</tr>
							<tr>
								<td>Device category:</td>
								<td><s:property value="trafficNoti.getIinterface().getNode().getDeviceCategory()" /></td>
							</tr>
							<tr>
								<td>Device family:</td>
								<td><s:property value="trafficNoti.getIinterface().getNode().getDeviceFamily()" /></td>
							</tr>
							<tr>
								<td>Device profile:</td>
								<td><s:property value="trafficNoti.getIinterface().getNode().getDeviceProfile()" /></td>
							</tr>
							<tr>
								<td>Device vendor:</td>
								<td><s:property value="trafficNoti.getIinterface().getNode().getDeviceVendor()" /></td>
							</tr>
							<tr>
								<td>Management address:</td>
								<td>
									<s:property value="trafficNoti.getIinterface().getNode().getManagementAddress()" />
								</td>
							</tr>
							<tr>
								<td>Node managed:</td>
								<td><s:property value="trafficNoti.getIinterface().getNode().getNodeManaged()" /></td>
							</tr>
						
							<tr>
								<td>System object id:</td>
								<td><s:property value="trafficNoti.getIinterface().getNode().getSystemObjectId()" /></td>
							</tr>
							
						</tbody></table>
						</div>
						
						<div id="suivantPrecedanr" style="float:right;">
						
						<button class="btn-icon btn-grey btn-arrow-left"
							onclick="myPopupGraph('<s:property value="#theUrl"/>&margeAgregation=<s:property value="margeAgregation" />');">
							<span></span> Jour précedant
						</button>



						<button class="btn-icon btn-grey btn-arrow-right"
							onclick="myPopupGraph('<s:property value="#theUrl2"/>&margeAgregation=<s:property value="margeAgregation" />');">
							<span></span> jour suivant
						</button>
						</div>
						
						<button class="btn btn-green"
								onclick='OpenInNewTab("<s:property value="rootUrl"/>traffics/generateTheCsvFile<s:property value="urlForGenerateCSV"/>");'>
								<span></span> Générer un fichier CSV
							</button>
							<br />
							
					</div>
				
				<!-- End canvas graphes -->

			</div>
		</s:if>


	</div>

		<!-- footer -->
		<s:include value="include/footer.jsp"></s:include>
		<style type="text/css">
html,body {
	background-color: #FFF;
}
</style>

	</div>
	<script
		src='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/Chart.js'></script>
	<script
		src='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/jquery-2.1.1.min.js'></script>
	<script
		src='<s:property value="rootUrl"/>lib/Chart.js.legend-master/newFiles/legend.js'></script>

	<script>
	function OpenInNewTab(url) {
		  var win = window.open(url, '_self');
		  win.focus();
		};
	
			try {
				var lineChartData = JSON.parse('<s:property value="chartData"/>'.replace(
						/&quot;/g, '"'));
				var grapheType = "line";

			} catch (e) {}
			
			var LineCartDataCostume = new Object();
			var boolBreak = false;
			LineCartDataCostume = lineChartData;
			var ctx;
			window.onload = function() {
				ctx = document.getElementById("canvas").getContext("2d");
				new Chart(ctx).Line(LineCartDataCostume, {
					responsive : true

				});
				//ctx.clear();
			};

// 			legend(document.getElementById("chartLegend"), lineChartData);
			$("#chartLegend").mouseleave(null);
			
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