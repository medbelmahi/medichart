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
				format:'Y-m-d H:i', 
				lang:'fr',
				minDate:'2014/07/01',
				maxDate:'0'
			});
			jQueryDatatime('#enddatetimepicker').datetimepicker({
				format:'Y-m-d H:i', 
				lang:'fr',
				minDate:'2014/07/01',
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
				<h2>Générer le graphe de streams</h2>
				<div class="block">

					<!-- Message Error & Info & warning -->
					<ul class="message warning">
						<li>NB: Les valeurs en questions (qui déspassent les seuils)
							sont représentées en <strong style="color:red;">ROUGE</strong>.
						</li>
					</ul>
					<s:if test="hasActionErrors()">
						<s:actionerror cssClass="message error" />
					</s:if>
					<s:if test="hasActionMessages()">
						<s:actionmessage cssClass="message info" />
					</s:if>
					<!-- End message -->

					<form action='<s:property value="rootUrl"/>streams/list'
						method="post">
						<table class="form">

							<tr>
								<td>Date de début:</td>
								<td><input id="startdatetimepicker" type="text"
									name="startDate" class="medium" placeholder="yyyy-mm-dd hh:mm"
									readonly="readonly" required="required" value='<s:property value="startDate"/>' ></td>
								<%-- <td><s:textfield name="startDate" id="startdatetimepicker" cssClass="medium" placeholder="yyyy-mm-dd hh:mm" readonly="readonly" required="required" /></td> --%>
							</tr>

							<tr>
								<td>Date de fin:</td>
								<td><input id="enddatetimepicker" type="text"
									name="endDate" class="medium" placeholder="yyyy-mm-dd hh:mm"
									readonly="readonly" required="required" value='<s:property value="endDate"/>' ></td>
								<%-- <td><s:textfield name="endDate" id="enddatetimepicker" cssClass="medium" placeholder="yyyy-mm-dd hh:mm" readonly="readonly" required="required" /></td> --%>
							</tr>


							<!-- <tr>
								<td>Stream Type :</td>
								<td>
									<input type="checkbox" name="streamType" value="Voice" checked="checked" />Voice
                             	   	<input type="checkbox" name="streamType" value="RTP" checked="checked" />RTP
                             	   	<input type="checkbox" name="streamType" value="STP" checked="checked" />STP
                             	   	<input type="checkbox" name="streamType" value="SS7" checked="checked" />SS7
                             	</td>
							</tr> -->


							<tr>
								<td></td>
								<td><input type="submit" class="medium"
									value="Récupérer le résultat"></td>
							</tr>
						</table>
					</form>

					<table class="data display datatable" id="example">
						<thead>
							<tr>
								<th>Date</th>
								<th>Emetteur</th>
								<th>Recepteur</th>
								<th>DPHI MAX</th>
								<th>JPHI MAX</th>
								<th>Loss percent MAX</th>
								<th>Stream Type</th>
								<th>Voix</th>
							</tr>
						</thead>
						<tbody>
							<!-- champ variables -->
							<s:iterator value="streams" var="stream">

								<td><s:property value="dateToString()" /></td>
								<td><s:property value="probTransmitter" /></td>
								<td><s:property value="probReceiver" /></td>
								<s:if test="isOverDphi()">
									<s:url action="graphe" var="theUrl" escapeAmp="false">
										<s:param name="recepteur">
											<s:property value="probReceiver" />
										</s:param>
										<s:param name="emetteur">
											<s:property value="probTransmitter" />
										</s:param>
										<s:param name="startDate">
											<s:property value="startDate" />
										</s:param>
										<s:param name="endDate">
											<s:property value="endDate" />
										</s:param>
										<s:param name="typeStream">
											<s:property value="typeStream" />
										</s:param>
										<s:param name="voix">
											<s:property value="voice" />
										</s:param>
										<s:param name="typeGraphe">DPHI</s:param>
									</s:url>
									<td><a class="theRedValue"
										onclick='myPopupGraph("<s:property value="#theUrl"/>");'
										href='javascript:void(0);'> <s:property value="dpHiMax" /></a>
								</s:if>
								<s:else>
									<td><s:property value="dpHiMax" />
								</s:else>

								</td>

								<s:if test="isOverJphi()">
									<s:url action="graphe" var="theUrl" escapeAmp="false">
										<s:param name="recepteur">
											<s:property value="probReceiver" />
										</s:param>
										<s:param name="emetteur">
											<s:property value="probTransmitter" />
										</s:param>
										<s:param name="startDate">
											<s:property value="startDate" />
										</s:param>
										<s:param name="endDate">
											<s:property value="endDate" />
										</s:param>
										<s:param name="typeStream">
											<s:property value="typeStream" />
										</s:param>
										<s:param name="voix">
											<s:property value="voice" />
										</s:param>
										<s:param name="typeGraphe">JPHI</s:param>
									</s:url>
									<td><a class="theRedValue"
										onclick='myPopupGraph("<s:property value="#theUrl"/>");'
										href='javascript:void(0);'> <s:property value="jpHiMax" /></a>
								</s:if>
								<s:else>
									<td><s:property value="jpHiMax" />
								</s:else>


								</td>

								<s:if test="isOverLoss()">
									<s:url action="graphe" var="theUrl" escapeAmp="false">
										<s:param name="recepteur">
											<s:property value="probReceiver" />
										</s:param>
										<s:param name="emetteur">
											<s:property value="probTransmitter" />
										</s:param>
										<s:param name="startDate">
											<s:property value="startDate" />
										</s:param>
										<s:param name="endDate">
											<s:property value="endDate" />
										</s:param>
										<s:param name="typeStream">
											<s:property value="typeStream" />
										</s:param>
										<s:param name="voix">
											<s:property value="voice" />
										</s:param>
										<s:param name="typeGraphe">LOSS</s:param>
									</s:url>
									<td><a class="theRedValue"
										onclick='myPopupGraph("<s:property value="#theUrl"/>");'
										href='javascript:void(0);'> <s:property
												value="lossPrecentMax" /></a>
								</s:if>
								<s:else>
									<td><s:property value="lossPrecentMax" />
								</s:else>

								</td>
								<td><s:property value="typeStream" /></td>
								<td><s:property value="voice" /></td>

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
	
	</script>
	<!-- End Chartjs import -->

</body>
</html>