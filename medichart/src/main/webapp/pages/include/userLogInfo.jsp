<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- URL to the root -->
<s:url namespace="/" action="" var="rootUrl" />

<script type="text/javascript">
function isIE(){
	var nav = navigator.userAgent.toLocaleLowerCase();
	return (nav.indexOf('msie') != -1) ? parseInt(nav.split('msie')[1]) : false;
}
if(isIE()){
	window.location = '<s:property value="rootUrl"/>support/isIE';
}
</script>

<link type="text/css" rel="stylesheet" href='<s:property value="rootUrl"/>css/bootstrap.css' />

<div class="grid_12 header-repeat">
			<div id="branding">
				<div class="floatleft">
					<img src="<s:property value="rootUrl"/>img/logo.png" alt="Logo" />
				</div>
				<div class="floatright">
					<div class="floatleft">
						<img src="<s:property value="rootUrl"/>img/img-profile.jpg" alt="Profile Pic" />
					</div>
					<div class="floatleft marginleft10">
						<ul class="inline-ul floatleft">
							<li><s:property value="%{#session.user.fullName}" /></li>
							<li><a href="<s:property value="rootUrl"/>users/editprofil">Profil</a></li>
							<li><a href="<s:property value="rootUrl"/>logout">Logout</a></li>
<%-- 							<li><a href="#"><span class="badge">42</span></a></li> --%>
						</ul>
						<br />
						<span class="small grey"><s:property value="%{#session.user.roleDescription}"/></span>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>