<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="/WEB-INF/lib/spring.tld" %>
<%@taglib prefix="form" uri="/WEB-INF/lib/spring-form.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../../js/jquery.layout.js"></script>
<script type="text/javascript" src="../../js/jquery-ui-1.8.2.custom.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='birth']").datepicker();
	});
</script>


<link rel="stylesheet" type="text/css" href="../../themes/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="../../themes/redmond/jquery-ui-1.8.2.custom.css" />

</head>
<body>
	<div style="width: 1000px; margin: 0 auto;">
		<div style="width: 988px; margin: 10px auto;">
			<form:form commandName="customer">
				<div>
					<div>
						<form:label path="name">姓名：</form:label>
						<form:input path="name"/>
					</div>
					<div>
						<form:label path="birth">生日：</form:label>
						<form:input path="birth"/>
					</div>
					<div>
						<form:label path="age">年龄：</form:label>
						<form:input path="age"/>
					</div>
					<div>
						<form:label path="mobile">手机：</form:label>
						<form:input path="mobile"/>
					</div>
					<div>
						<form:label path="mobile2">手机2：</form:label>
						<form:input path="mobile2"/>
					</div>
					<div>
						<form:label path="address">地址：</form:label>
						<form:input path="address"/>
					</div>
					<div>
						<form:label path="gender">性别：</form:label>
						<form:select path="gender">
							<form:option value="1">男</form:option>
							<form:option value="2">女</form:option>
						</form:select>
					</div>
					<div>
						<input type="submit" value="Save Customer" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>