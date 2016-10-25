<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c"  uri="/WEB-INF/lib/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="../../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../../js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../../../js/jquery.layout.js"></script>
<script type="text/javascript" src="../../../js/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="../../../js/biz/test.js"></script>

<link rel="stylesheet" type="text/css" href="../../../themes/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="../../../themes/redmond/jquery-ui-1.8.2.custom.css" />

<style type="text/css">
.myAmway-view-page{
	width: 900px
}
	.myAmway-view-userinfo{
		float: left;
		width: 250px;
	}
	.myAmway-view-businessCard{
		float: right;
		width: 630px;
	}
	.clearBoth{
		clear: both;
	}
</style>
</head>
<body>
	<div>
		<div class="myAmway-view-page">
			<div>
				<input type="hidden" name="customer.customerId">
				<label>跟进时间</label> <input type="text" name="customer.traceList.traceTime">
				<label>跟进内容</label><textarea rows="8" cols="300" name="customer.tractList.remark"></textarea>
				<label>本次打分</label><input type="text" name="customer.traceList.rating">
			</div>
			<div class="myAmway-view-userinfo">
				<table cellpadding="2" cellspacing="1" border="1" style="font-size: 16px; padding: 2px;">
					<tbody>
						<tr>
							<td>姓名</td>
							<td>${customer.name }</td>
						</tr>
						<tr>
							<td>年龄</td>
							<td>${customer.age }</td>
						</tr>
						<tr>
							<td>生日</td>
							<td>${customer.birth }</td>
						</tr>
						<tr>
							<td>婚否</td>
							<td>${customer.marriage }</td>
						</tr>
						<tr>
							<td>职业</td>
							<td>${customer.profession }</td>
						</tr>
						<tr>
							<td>性别</td>
							<td>${customer.gender }</td>
						</tr>
						<tr>
							<td>手机</td>
							<td>${customer.mobile }</td>
						</tr>
						<tr>
							<td>住址</td>
							<td>${customer.address }</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="myAmway-view-businessCard">
			</div>
			<div class="clearBoth"></div>
		</div>
		<div>
			<table cellpadding="2" cellspacing="1" border="1" style="font-size: 16px; padding: 2px;" width="800">
				<thead>
					<tr>
						<th>跟进时间</th>
						<th>跟进内容</th>
						<th>打分</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="trace" items="${customer.traceList}">
					<tr>
						<td>${trace.traceTime }</td>
						<td>${trace.remark }</td>
						<td>${trace.rating }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>