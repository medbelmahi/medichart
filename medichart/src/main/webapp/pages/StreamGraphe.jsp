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
					<!-- End canvas graphes -->
					
	</div>
	
	<%-- <div id="interfaceInformation" class="CSSTableGenerator">
	<table>
			<tr>
			<td>Alias</td>
			<td>Interface</td>
			<td>Node</td>
			</tr>
		
		<tbody>
		<s:iterator value="listInterfaces" var="interfaceee">
			<tr>
				<td><s:property value="getIfAlias()" /></td>
				<td>Index : <s:property value="getIfIndex()" /> Speed : <s:property value="getIfSpeed()" /></td>
				<td>Node name : <s:property value="getNode().getNodeName()" /> Device vendor : <s:property value="getNode().getDeviceVendor()" /> Management address : <s:property value="getNode().getManagementAddress()" /></td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
	</div> --%>
	</s:if>
	
	
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
			try {
				var lineChartData = JSON.parse('<s:property value="chartData"/>'.replace(
						/&quot;/g, '"'));
				var grapheType = "line";

			} catch (e) {}
			
				varTypeChart = true;
				varTypeGraphe = true;
				var boolBreak = false;

			var LineCartDataCostume = new Object();
			
			LineCartDataCostume = lineChartData;
			var ctx;
			window.onload = function() {
				ctx = document.getElementById("canvas").getContext("2d");
				new Chart(ctx).Line(LineCartDataCostume, {
					responsive : true

				});
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