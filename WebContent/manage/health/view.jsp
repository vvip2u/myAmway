<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/lib/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../../js/jquery.layout.js"></script>
<script type="text/javascript" src="../../js/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="../../js/biz/test.js"></script>

<link rel="stylesheet" type="text/css" href="../../themes/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="../../themes/redmond/jquery-ui-1.8.2.custom.css" />

</head>
<body>
	<table cellpadding="2" cellspacing="1" border="1" style="font-size: 16px; padding: 2px;">
		<tbody>
			<c:forEach var="health" items="${healthList}" >
			<tr>
				<td>日期</td>
				<td>${health.examDatetime }</td>
				<td>日期</td>
				<td>${health.reportPath }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>