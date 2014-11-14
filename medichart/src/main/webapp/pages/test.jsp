<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
Test Page! 
<s:if test="hasActionErrors()">
   <div class="errors">
      <s:actionerror/>
   </div>
</s:if>

<s:iterator value="map">
  <h3><s:property value="key" /></h3>
  <table>
  <s:iterator value="value">
    <tr><td><s:property /></td></tr>
  </s:iterator>
  </table>
</s:iterator>
</body>
</html>