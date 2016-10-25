<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="../../error/505.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="../../themes/redmond/jquery-ui-1.8.2.custom.css" />
<link rel="stylesheet" type="text/css" href="../../themes/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="../../css/jquery.contextmenu.css">
<link rel="stylesheet" type="text/css" href="../../css/jquery.alerts.css">
<style type="text/css">
	.bottom-navigator{
		position: absolute;
		float: left; 
		left: 0; 
		top: 0;
		padding-left:6px;
		width: 70px; 
		height: 100%; 
		padding-top: 3px; 
		overflow-y:hidden; 
		z-index:2000;
		border-left: 1px solid #C5DBEC;
		border-right: 1px solid #C5DBEC;
		border-top: 1px solid #C5DBEC;
		background: url("../../themes/redmond/images/ui-bg_glass_85_dfeffc_1x400.png") repeat-y scroll 50% 50% #DFEFFC
	}
</style>

<script type="text/javascript" src="../../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../../js/jquery.layout.js"></script>
<script type="text/javascript" src="../../js/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="../../js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="../../js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="../../js/jquery.ui.position.js"></script>
<script type="text/javascript" src="../../js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="../../js/jquery.ui.droppable.js"></script>
<script type="text/javascript" src="../../js/src/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="../../js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../../js/biz/jquery.contextmenu.js"></script>
<script type="text/javascript" src="../../js/jquery.alerts.js"></script>
<script type="text/javascript" src="../../js/biz/manage.customer.view.js"></script>
</head>
<body style="width: auto; height: 576px; margin: 0 auto;">
	<div style="width:auto; margin: 10px auto;">
		<div style="width:670px; float: left;">
			<table id="customerList"></table>
			<div id="customerListPager"></div>
		</div>
		<div id="searchCriteria" style="width:100px; float: left;">
			<fieldset>
				<legend>查询条件：</legend>
				<label for="mobile">手机</label>
				<input type="text" name="mobile" value="">
				<label for="name">名字</label>
				<input type="text" name="name" value="">
				<label for="buy">已买货</label>
				<input type="checkbox" name="buy" >
				<br />
				<br />
				<br />
				<input type="button" id="queryData" value="查询">
			</fieldset>
		</div>
		<div style="clear: both;"></div>
	</div>
	<div id="basicInfoModal"></div>
	<div id="healthModal" class="disappear"></div>
	<%-- 
	<div id="orderModal" class="disappear">
	div class="bottom-navigator">
	
	</div>
		<input id="inviteBtn" type="button" value="邀约">
		<input id="traceBtn" type="button" value="跟进">
	</div>
	 --%>
</body>
</html>