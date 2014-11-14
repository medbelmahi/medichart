<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
 
<body>
<h1>Struts 2 download file example</h1>
 
<s:url id="fileDownload" namespace="/" action="traffics/downloadCsvFile" escapeAmp="false" >
	<s:param name="filename">
				<s:property value="filename" />
	</s:param>
</s:url>
 
<h4>Download file - <s:a href="%{fileDownload}"><s:property value="filename" /></s:a>
</h4>
 
</body>
</html>