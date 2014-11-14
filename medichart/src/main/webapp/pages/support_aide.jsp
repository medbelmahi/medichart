<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- URL to the root -->
<s:url namespace="/" action="" var="rootUrl" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Méditel Reporting : Aide & Support</title>
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/reset.css' media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/text.css' media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/grid.css' media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/layout.css' media="screen" />
<link rel="stylesheet" type="text/css" href='<s:property value="rootUrl"/>css/nav.css' media="screen" />
<link href='<s:property value="rootUrl"/>css/prettyPhoto.css' rel="stylesheet" type="text/css" />
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
<script src='<s:property value="rootUrl"/>js/pretty-photo/jquery.prettyPhoto.js' type="text/javascript"></script>
<!-- END: load jquery -->

<script src='<s:property value="rootUrl"/>js/setup.js' type="text/javascript"></script>

<script src='<s:property value="rootUrl"/>lib/datetimepicker-master/jquery.js'></script>
<script type="text/javascript">
	var jQueryDatatime = $.noConflict(true);
</script>
<script src='<s:property value="rootUrl"/>lib/datetimepicker-master/jquery.datetimepicker.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
				
		//side Menu
		setupPrettyPhoto();
		setupLeftMenu();
		setSidebarHeight();
		//end side Menu
		
	});
	
	
	
	function myPopupGraph(link)
	{
		window.open(link, "", "fullscreen=no, width=1070, height=550").focus();
	}
</script>
<style type="text/css">
        		tr{
        			border-bottom: 1px solid grey;
        		}
                 		</style>
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
				<h2>Médichart : Support & aide </h2>
				<div class="block">
				
					<!-- Message Error & Info & warning -->
					<ul class="message warning">
					<li>
					Cette Section à pour objectif de vous aidez à réussir votre premier pas avec MediChart, elle inclut une simple explication des différentes fonctionalités de la plateforme.
					</li>
						<li>
							
							NB: Ce support peut étre non complet . (merci de nous envoyer vos suggestions en cas d'ambiguité sur le formulaire <a href="<s:property value="rootUrl"/>support/report"><strong>Signaler bug et amélioration</strong></a>).
						</li>
					</ul>
                 	<!-- End message -->
                 		
						<table class="form">
							<tr>
								<td>
								
								<ul class="prettygallery clearfix">
									<li><a href='<s:property value="rootUrl"/>img/aideSupport/cap1.PNG' rel="prettyPhoto[gallery2]">
									<img width="240px" height="120px" alt="" src='<s:property value="rootUrl"/>img/aideSupport/cap1.PNG' >
									</a></li>
								</ul>
								
								</td>
								<td style="vertical-align: middle;" >
									
									NB: A fin de profiter d'une expérience de navigation optimale , nous vous invitons de utiliser les navigateus web suivants: Google Chrome, Mozilla Firefox (la platforme n'est pas compatible avec la version d'Internet explorer inférieur à 9.0)
									<br /><br />
									<p>Exemples des navigateurs recommandés : </p>
									<ul>
										<li><a target="_blank" href="https://www.google.fr/chrome/browser/">Chrome</a></li>
										<li><a target="_blank" href="https://www.mozilla.org/fr/">FireFox</a></li>
									</ul>
							</td>
							</tr>
							<hr />
							<tr >
								<td>
										 <ul class="prettygallery clearfix">
                        <li><a href='<s:property value="rootUrl"/>img/aideSupport/cap2.PNG' rel="prettyPhoto[gallery2]"
                            title="">
                            <img width="240px" height="120px" src='<s:property value="rootUrl"/>img/aideSupport/cap2.PNG' alt="Authentification" /></a></li>
										</ul>
										
								</td>
								<td style="vertical-align: middle;">
									
									<h3>Authentification</h3>
									A fin d'accider au fonctionnalités de la plateforme vous serez invité à entrer votre Email et Mot de passe.
									<a target="_blank" href='<s:property value="rootUrl"/>login' >login</a>
									
								</td>
								
							</tr>
							
							<tr>
								<td>
										 <ul class="prettygallery clearfix">
                        <li><a href='<s:property value="rootUrl"/>img/aideSupport/cap3.PNG' rel="prettyPhoto[gallery2]"
                            title="">
                            <img width="240px" height="120px" src='<s:property value="rootUrl"/>img/aideSupport/cap3.PNG' alt="Mettre à jour votre profil" /></a></li>
										</ul>
										
								</td>
								<td style="vertical-align: middle;">
									
									<h3>Mettre à jour votre profil:</h3>
									Il est important de modifier votre mot de passe aprés la premiere authentification.
									Pour mettre à jour votre profil, cliquez sur "Profil" en haut à droit de la page, effectuer votre changement puis validez.
									<a target="_blank" href='<s:property value="rootUrl"/>users/editprofil'>Profil</a>
								</td>
							</tr>
							
							<tr>
								<td>
								<ul class="prettygallery clearfix">
									<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/cap4.PNG'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/cap4.PNG'
											alt="Tracer Les graphes des trafics entrants/sortants, Capture 4" /></a></li>
											
											<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/formulaireTrafficInOut.png'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/formulaireTrafficInOut.png'
											alt="Tracer Les graphes des trafics entrants/sortants, formulaireTrafficInOut" /></a></li>
											
											<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/cap5.PNG'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/cap5.PNG'
											alt="Tracer Les graphes des trafics entrants/sortants, Capture 5" /></a></li>
											
											<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/TrafficInOutGraphe.png'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/TrafficInOutGraphe.png'
											alt="Tracer Les graphes des trafics entrants/sortants, TrafficInOutGraphe" /></a></li>
								</ul>

							</td>
								<td style="vertical-align: middle;">
									
									<h3>Tracer Les graphes des trafics entrants/sortants:</h3>
									Pour tracer un ou plusieurs graphes des trafics entrants/sortants d'une période quelconque, aller sur "Trafics->Entrée/Sortie" dans le menu en haut de la page.
									puis choisisser dans la fenêtre du paramètres une ou plusieurs, une période quelconque (Date de début et Date de fin), ensuite vous pouvez parametrer encore votre requéte ou lissez les parametres par défauts.
									et ensuite cliquez sur "traces la courbe".
									<a target="_blank" href='<s:property value="rootUrl"/>traffics/inOutCompare'>traffics_inOut</a>
									
								</td>
							</tr>
							
							
							<tr>
								<td>
										 <ul class="prettygallery clearfix">
                        <li><a href='<s:property value="rootUrl"/>img/aideSupport/trafficParClass_Min.png' rel="prettyPhoto[gallery2]"
                            title="">
                            <img width="240px" height="120px" src='<s:property value="rootUrl"/>img/aideSupport/trafficParClass_Min.png' alt="trafficParClass_Min " /></a></li>
										</ul>
										
								</td>
								<td style="vertical-align: middle;">
									
									<h3>Tracer Les graphes du trafics par classes:</h3>
									Pour tracer les graphes des trafics par classes choisissez "Trafics->Classes" dans le menu, puis suiver les mémes étapes précendantes.
									<a target="_blank" href='<s:property value="rootUrl"/>traffics/perClassCompare'>traffics_parClass</a>
									
								</td>
							</tr>
							
							<tr>
								<td>
										 <ul class="prettygallery clearfix">
									<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/cap7.PNG'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/cap7.PNG'
											alt="Lister et tracer les streams Capture 7" /></a></li>
											<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/cap8.PNG'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/cap8.PNG'
											alt="Lister et tracer les streams Capture 8" /></a></li>
											<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/streamList.png'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/streamList.png'
											alt="Lister et tracer les streams Capture 9" /></a></li>
											<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/StreamGraphe.png'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/StreamGraphe.png'
											alt="Lister et tracer les streams Capture 10" /></a></li>
											
								</ul>

							</td>
								<td style="vertical-align: middle;">
									
									<h3>Lister et tracer les streams:</h3>
									Pour lister les streams entre les différentes probs du backbone IP, rendez vous sur "Stream" dans le menu en haut, puis choisissez 2 différentes date (Date de début et fin) et cliquez sur récupérer les resultats, une liste des streams sera afficher en bat de la formulaire, ensuite vous pouvez cliquez sur une des valeurs présentées en rouge pour avoir le graphe d'évolution en temps.
									<a target="_blank" href='<s:property value="rootUrl"/>streams/list' >streams_list</a>
									
								</td>
							</tr>
							<tr>
								<td>
										 <ul class="prettygallery clearfix">
									<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/signaler.png'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/signaler.png'
											alt="Signaler Bug/Amélioration" /></a></li>
											
											
								</ul>

							</td>
								<td style="vertical-align: middle;">
									
									<h3>Signaler Bug/Amélioration:</h3>
									A fin de nous aider à améliorer cette plateforme, Nous vous saurions gré de bien vouloir consacrer quelques minutes à remplir ce 
									<a target="_blank" href='<s:property value="rootUrl"/>support/report' >formulaire</a>.

								</td>
							</tr>
							
							
							<tr>
								<td>
										 <ul class="prettygallery clearfix">
									<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/listeDesSaturation.png'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/listeDesSaturation.png'
											alt="liste Des Saturations" /></a></li>
											
											<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/saturationGraphe.png'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/saturationGraphe.png'
											alt="liste Des Saturations (Graphe)" /></a></li>
											
											<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/exemple1_saturation.png'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/exemple1_saturation.png'
											alt="liste Des Saturations exemple 1" /></a></li>
											
											<li><a
										href='<s:property value="rootUrl"/>img/aideSupport/exemple2_saturation.png'
										rel="prettyPhoto[gallery2]" title=""> <img width="240px"
											height="120px"
											src='<s:property value="rootUrl"/>img/aideSupport/exemple2_saturation.png'
											alt="liste Des Saturations exemple 2" /></a></li>
											
								</ul>

							</td>
								<td style="vertical-align: middle;">
									
									<h3>Saturation :</h3>
									La saturation est un phénomène fréquent qui influence sur la qualité des services. Il apparait pendant les périodes de surcharge sur le trafic passé. Et tant que la vitesse de transmission des trafics est limitée et les politiques de limitation son appliquées notamment la division des quantités de trafic passées , alors les valeurs des trafics ne peuvent pas dépassées ces limitations.
La détection automatique du phénomène de saturation vise à trouver une suite des données semblable (presque égaux), qui sont représentés sur les graphes par un trait horizontal presque droit, dont le trafic prend des valeurs semblables (avec une différence entre 1 et 0) pendant une longue durée (4 à 5 heurs).

									<br /> 
									<a target="_blank" href='<s:property value="rootUrl"/>traffics/notificationError' >Saturations</a>.

								</td>
							</tr>
							
						</table>
				</div>
			</div>
		</div>
		<!-- End Box support & aide  -->
		
		<div class="clear"></div>
	</div>
	<!-- footer -->
	<s:include value="include/footer.jsp"></s:include>
</body>
</html>