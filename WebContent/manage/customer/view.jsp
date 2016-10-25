<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.myAmway.util.DateUtils"%>
<%@taglib prefix="c" uri="/WEB-INF/lib/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../../themes/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css"
	href="../../themes/redmond/jquery-ui-1.8.2.custom.css" />
<style type="text/css">
.customer-view {
	font-family: "微软雅黑", "宋体", Verdana;
}

.myAmway-view-page {
	width: 760px;
}

.myAmway-view-userinfo,.myAmway-view-remark {
	float: left;
	width: 350px;
}

.myAmway-view-staticsinfo {
	display: none;
}

.customer-info-sub {
	height: 214px;
}

.myAmway-view-remark {
	float: left;
	overflow: hidden;
	width: 400px;
	height: 244px;
	border-left: 0px;
	white-space: nowrap;
}

.myAmway-view-remark-container {
	padding: 5px 10px;
}

.myAmway-view-businessCard {
	-moz-user-select: none;
	-webkit-user-select: none;
}

.clearBoth {
	clear: both;
	margin-bottom: 8px;
}

.myAmway-view-page td {
	margin: 2px 0;
}

.myAmway-view-page div.myAmway-view-detail {
	border-bottom: 1px solid #bfcddb;
	width: 300px;
}

.myAmway-view-remark label {
	background: #bfcddb;
	padding: 2px 4px;
}

.myAmway-view-page span.myAmway-view-title {
	background: #bfcddb;
	padding: 2px 5px 2px 15px;
	margin-right: 10px;
	width: 200px;
	display: -moz-inline-box;
	display: inline-block;
	width: 40px;
}

#customer-info .ui-widget-header,#customer-detail .ui-widget-header {
	background: #FFFFFF;
	border: 0;
	padding: 0;
}

#customer-info,#customer-detail {
	border: 0;
	padding: 0;
}

.customer-detail-sub {
	height: 246px;
}

#trace-header th, #trace-body td {
	border: 1px solid #A6C9E2;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../../js/jquery.layout.js"></script>
<script type="text/javascript" src="../../js/jquery.raty.js"></script>
<script type="text/javascript"
	src="../../js/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="../../ js/biz/test.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#rating').raty({
			path : "../../images/",
			cancel : true,
			cancelOff : 'cancel-off-big.png',
			cancelOn : 'cancel-on-big.png',
			half : true,
			size : 24,
			starHalf : 'star-half-big.png',
			starOff : 'star-off-big.png',
			starOn : 'star-on-big.png',
			start : 3,
			scoreName : "rating"
		});

		$.each($(".tracerating"), function(k, v) {
			var $this = $(this);
			$this.raty({
				path : "../../images/",
				half : true,
				size : 24,
				starHalf : 'star-half-big.png',
				starOff : 'star-off-big.png',
				starOn : 'star-on-big.png',
				scoreName : "display-rating",
				start : $this.attr("rating"),
				readOnly: true
			});
		});

		$("#traceTime").datepicker({
			dateFormat : 'yy-mm-dd'
		});

		$(".customer-detail-sub").hide();
		$("#tabs-1").show();

		$(".customer-info-sub").hide();
		$("#tabs-name").show();

		$("#customer-info").tabs({
			event : "mouseover",
			select : function(event, ui) {
				$(".customer-info-sub").hide();
				$("#" + ui.panel.id).show();
			}
		});

		$("#customer-detail").tabs({
			event : "mouseover",
			select : function(event, ui) {
				$(".customer-detail-sub").hide();
				$("#" + ui.panel.id).show();
			}
		});
		
		$("#detailSubmitBtn").button();
		$("#detailSubmitBtn").bind("click", function() {
			var url = "addDetail";
			var params = {
				"customerId" : $("#customerId").val(),
				"mobile" : $("#mobile").val(),
				"email" : $("#email").val()
			};

			$.get(url, params, function(data) {
				if (data['status'] == "ok") {
					jAlert("保存成功");
					$("#remark").empty();
				}
				if (data['status'] == "fail") {
					var a = jAlert("保存失败");
				}
			}, "json");

		});
		
		$("#submitBtn").button();
		$("#submitBtn").bind("click", function() {
			var url = "addTrace";
			var params = {
				"customerId" : $("#customerId").val(),
				"traceTime" : $("#traceTime").val(),
				"rating" : $("#rating input:last").val(),
				"content" : encodeURI($("#remark").val())
			};

			$.get(url, params, function(data) {
				if (data['status'] == "ok") {
					jAlert("保存成功");
					$("#remark").empty();
				}
				if (data['status'] == "fail") {
					var a = jAlert("保存失败");
				}
			}, "json");

		});
		
		$("select").selectmenu();

	});
</script>

</head>
<body>
	<div class="customer-view" style="height: 540px;">
		<div class="myAmway-view-page">
			<div class="myAmway-view-userinfo">
				<div id="customer-info">
					<ul>
						<li><a href="#tabs-name"><span>信息</span>
						</a>
						<li><a href="#tabs-communication"><span>联系</span>
						</a>
						</li>
						<li><a href="#tabs-favorite"><span>爱好</span>
						</a>
						</li>
						<li><a href="#tabs-statics"><span>统计</span>
						</a>
						</li>
					</ul>
				</div>
				<div id="tabs-name" class="customer-info-sub">
					<table class="myAmway-view-detailinfo" cellpadding="2"
						cellspacing="0" border="0" style="font-size: 16px; padding: 2px;"
						width="250">
						<tbody>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">姓名</span>${customer.name }
										<span class="myAmway-view-title">生日</span>${customer.birth }
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">邮箱</span>${customer.email }
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">性别</span>${customer.gender }
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">婚否</span>${customer.marriage}
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">职业</span>${customer.profession}
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">手机</span>${customer.mobile }
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">住址</span>${customer.address }
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="tabs-communication" class="customer-info-sub">
					<div class="myAmway-view-detail">
						<span class="mobile">phone</span><input id="mobile" name="mobile" />
					</div>
					<div class="myAmway-view-detail">
						<span class="email">email</span><input id="email" name="email" />
					</div>
					<div>
						<input id="detailSubmitBtn" type="button" value="提交">
					</div>
				</div>
				<div id="tabs-favorite" class="customer-info-sub">
					<p>暂无资料</p>
					<div><select>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select></div>
				</div>
				<div id="tabs-statics" class="customer-info-sub">
					<table class="myAmway-view-staticsinfo" cellpadding="2"
						cellspacing="0" border="0" style="font-size: 16px; padding: 2px;"
						width="250">
						<tbody>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">邀约次数</span>1
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">邀约日期</span>2
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">跟进次数</span>3
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">购买次数</span>4
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">转介绍数</span>5
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">星级</span>5
									</div>
								</td>
							</tr>
							<tr>
								<td><div class="myAmway-view-detail">
										<span class="myAmway-view-title">住址</span>${customer.address }
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="myAmway-view-remark">
				<div class="myAmway-view-remark-container">
					<div>
						<div>
							<label>本次打分</label><span id="rating"></span> <span><input
								id="submitBtn" type="button" value="提交">
							</span>
						</div>
						<div></div>
					</div>
					<div>
						<div>
							<label>跟进时间</label> <input id="traceTime" type="text"
								name="customer.traceList.traceTime"
								value="<%=DateUtils.getCurrentDate()%>">
						</div>
						<div>
							<label>跟进内容</label>
						</div>
						<div>
							<textarea style="border: 1px solid #bfcddb;" id="remark" rows="8"
								cols="50" name="customer.tractList.remark"></textarea>
						</div>
					</div>
				</div>
			</div>
			<div class="clearBoth"></div>
		</div>
		<div id="customer-detail">
			<ul>
				<li><a href="#tabs-1"><span>名片</span>
				</a>
				</li>
				<li><a href="#tabs-2"><span>跟进信息</span>
				</a>
				</li>
				<li><a href="#tabs-3"><span>健康信息</span>
				</a>
				</li>
				<li><a href="#tabs-4"><span>购物信息</span>
				</a>
				</li>
			</ul>
		</div>
		<div id="tabs-4" class="customer-detail-sub"></div>
		<div id="tabs-3" class="customer-detail-sub"></div>
		<div id="tabs-2" class="customer-detail-sub">
			<div>
				<table id="trace-header" cellpadding="0" cellspacing="0" border="0"
					style="font-size: 16px;" width="750">
					<thead>
						<tr>
							<th width="20%">跟进时间</th>
							<th width="60%">跟进内容</th>
							<th width="20%">打分</th>
						</tr>
					</thead>
				</table>
			</div>
			<div style="height: 214px; overflow-y: scroll; overflow-x: hidden;">
				<table id="trace-body" cellpadding="0" cellspacing="0" border="0" bordercolor="#A6C9E2"
					style="font-size: 16px;" width="750">
					<tbody>
						<c:forEach var="trace" items="${customer.traceList}">
							<tr>
								<td width="20%"><span>${trace.traceTime }</span>
								</td>
								<td width="60%"><span>${trace.remark }</span>
								</td>
								<td width="20%"><div id="${trace.traceId }"
										class="tracerating" rating="${trace.rating }"></div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div id="tabs-1" class="customer-detail-sub">
			<div class="myAmway-view-businessCard">
				<c:if test="${customer.bizCardPath==null }">
					暂无名片
				</c:if>
				<c:if test="${customer.bizCardPath!=null }">
					<img height="245" width="400" alt="${customer.name }的名片"
						src="/myAmway/bizCardDisplay?bcPath=${customer.bizCardPath }">
				</c:if>
			</div>
		</div>
	</div>
	<input type="hidden" id="customerId" name="customer.customerId"
		value="${customer.customerId }">
</body>
</html>