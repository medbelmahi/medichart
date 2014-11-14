<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- URL to the root -->
<s:url namespace="/" action="" var="rootUrl" />

<script>
	function myPopupAbout(){
		window.open('<s:property value="rootUrl"/>' + 'support/about', 'A propos de Medichart', 'toolbar=no, status=no, location=no, scrollbars=no, resizable=0, width=780, height=450').focus();
	}
</script>

<ul class="nav main">
	<li class="ic-gallery dd" ><a href="<s:property value="rootUrl"/>index"><span>Accueil</span></a></li>
	<li class="ic-dashboard"><a href="javascript:void(0);"><span>Trafics</span></a>
		<ul>
			<li><a href="<s:property value="rootUrl"/>traffics/inOutCompare">Entrée/Sortie (in/out)</a></li>
			<li><a href="<s:property value="rootUrl"/>traffics/perClassCompare">Classes</a></li>
			<li><a href="<s:property value="rootUrl"/>traffics/notificationError">Saturation</a></li>
		</ul>
	</li>
	<li class="ic-grid-tables" ><a href="<s:property value="rootUrl"/>streams/list"><span>Streams</span></a></li>
	<!-- <li class="ic-charts"><a href="<s:property value="rootUrl"/>support/aide"><span>Charge Cpus</span></a></li> -->
	<li class="ic-form-style" ><a href="#"><span>Support & Aide</span></a>
		<ul>
			<li><a href="<s:property value="rootUrl"/>support/aide">Guide d'utilisation</a></li>
			<li><a href="<s:property value="rootUrl"/>support/report">Signaler Bug/Amélioration</a></li>
		</ul>
	</li>
	<li class="ic-notifications"><a href="javascript:void(0);" onclick="myPopupAbout();" ><span>A propos</span></a></li>
</ul>