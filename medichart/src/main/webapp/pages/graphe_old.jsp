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
<!--[if IE 6]><link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/ie6.css' media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/ie.css' media="screen" /><![endif]-->
<!-- Lib css -->
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>lib/Chart.js.legend-master/legend.css'
	media="screen" />
<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>lib/datetimepicker-master/jquery.datetimepicker.css'
	media="screen" />
	<link rel="stylesheet" type="text/css"
	href='<s:property value="rootUrl"/>lib/Chart.js.legend-master/style.css'
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
	try {
		var data = JSON.parse('<s:property value="chartData"/>'.replace(
				/&quot;/g, '"'));
		var grapheType = "line";

	} catch (e) {
	}

	$(document).ready(function() {
		try {
			//Datetime picker
			jQueryDatatime('#startdatetimepicker').datetimepicker({
				format : 'Y-m-d H:i',
				lang : 'fr',
				beforeShowDay : function(date) {
					if (date > new Date()) {
						return [ false, "" ];
					}
					return [ true, "" ];
				}
			});
			jQueryDatatime('#enddatetimepicker').datetimepicker({
				format : 'Y-m-d H:i',
				lang : 'fr',
				beforeShowDay : function(date) {
					if (date > new Date()) {
						return [ false, "" ];
					}
					return [ true, "" ];
				}
			});
			//end Datetime picker

		} catch (e) {
			console.log(e);
		}

		//Chartjs
		try {
			var ctx = document.getElementById("mediChart").getContext("2d");
			if (grapheType == "line")
				new Chart(ctx).Line(data, {
					scaleIntegersOnly : false,
					responsive : true
				});
			else
				new Chart(ctx).Bar(data, {
					scaleIntegersOnly : false
				});
			legend(document.getElementById("chartLegend"), data);

		} catch (e) {
		}
		//end Chartjs
		
		
		//legend mousehover/////////////////////////////////////////////////////////////
		$("span.title").mouseover(function() {
		var title_ = $(this).text();
		
		for (var i = 0; i < data.datasets.length; i++) {
			if (data.datasets[i].title == title_) {
				objectElmentChart = data.datasets[i];

			}
		}
		//set data of item selected
		newDataSets = [objectElmentChart];

		LineCartDataCostume = {
			labels : data.labels,
			datasets : newDataSets
		};
		
		initailisationChart(LineCartDataCostume);
		
	});
		$("#chartLegend").mouseleave(function() {
			initailisationChart(data);
		
		});
		
		
		//end legend mouseover////////////////////////////////////////////////////////////////////////////////////////

	});
	
	
	//the hover chart function
	function initailisationChart(chartData) {
		document.getElementById("mediChartDiv").innerHTML = "<canvas id='mediChart' width='850' height='400'></canvas>";
		var ctx = document.getElementById("mediChart").getContext("2d");
		new Chart(ctx).Line(chartData, {
			animationSteps:10,
			responsive: true
		});
	}
</script>

</head>

<body>

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
		<div class='<s:property value="mediClass" />'>
			<div class="box round first" style="margin-top: 0;">
				<!-- <h2>Graphe</h2> -->
				<div class="block" style="margin-left: 10px; margin-right: 10px; padding-top: 0; padding-right: 0; ">

				<div id="mediChartDiv">
					<!-- Canvas graphes -->
					<canvas id="mediChart" width="850" height="400"></canvas>

				</div>
					<div id="chartLegend"></div>
					<!-- End canvas graphes -->
				</div>
			</div>
		</div>
	</s:if>
	<!-- End Box graphe interface  -->

	<div class="clear"></div>
	<div id="site_info">
		<p>© <a href="http://meditel.ma" target="_blank">Méditel</a> 2014.</p>
	</div>
	
	<!-- Chartjs import -->
	<script src='<s:property value="rootUrl"/>lib/chartjs/Chart.js'></script>
	<script src='<s:property value="rootUrl"/>lib/chartjs/Chart.Core.js'></script>
	<script src='<s:property value="rootUrl"/>lib/chartjs/Chart.Doughnut.js'></script>
	<script src='<s:property value="rootUrl"/>lib/chartjs/Chart.Line.js'></script>
	<script src='<s:property value="rootUrl"/>lib/chartjs/Chart.Bar.js'></script>
	<script src='<s:property value="rootUrl"/>lib/Chart.js.legend-master/legend.js'></script>
	<script type="text/javascript">
	
	</script>
	<!-- End Chartjs import -->
</body>