<%@ taglib prefix="s" uri="/struts-tags"%>

<style type="text/css">
html, body{
background-color: #c90032;
}
</style>

<s:if test="%{#session.user instanceof ma.meditel.reporting.entities.Admin}">

</s:if>
<s:else>
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
</s:else>

<div class="clear"></div>

	<div id="footer">
	<div id="site_info">
		<p>© <a href="http://meditel.ma" target="_blank">Méditel</a> 2014.</p>
	</div>
	</div>