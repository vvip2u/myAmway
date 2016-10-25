<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="/WEB-INF/lib/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>购货信息</title>
<style type="text/css">
.order-view {
	font-family: "微软雅黑", "宋体", Verdana;
}

.left-align{
	text-align: left;
}

.right-align{
	text-align: right;
}

.center-align{
	text-align: center;
}

.width-50{
	width: 50px;
}

.width-100{
	width: 100px;
}
</style>
</head>
<body>
	<div class="order-view" style="width: 800px; height: 540px;">
		<div style="padding: 0 50px; ">
			<c:forEach var="order" items="${customer.orderList }">
			<div>
				<table width="660" cellspacing="0"  cellpadding="1" border="1">
					<thead>
					<tr>
						<th colspan="7" class="left-align">订货编号：${order.orderCode }</th>
					</tr>
					<tr>
						<th colspan="3" class="left-align width-100">预定时间：${order.orderTime }</th>
						<th colspan="3" class="left-align width-100">送货时间：${order.serviceTime }</th>
						<th colspan="1" class="width-100">
							<c:if test="${order.finish }">
								<span style="background: green;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</c:if>
							<c:if test="${!order.finish }">
								<span style="background: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</c:if>
						</th>
					</tr>
					<tr>
						<th colspan="7" class="left-align">送货地址：${order.serviceAddress }</th>
					</tr>
					</thead>
				</table>
				<table width="660" cellspacing="0"  cellpadding="1" border="1">
					<thead>
					<tr>
						<th>商品编号</th>
						<th colspan="2">商品名称</th>
						<th>单价</th>
						<th>数量</th>
						<th>总价</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="orderDetail" items="${order.orderDetails }">
					<tr>
						<td class="center-align">200129</td>
						<td class="center-align" colspan="2">${orderDetail.product.name }</td>
						<td class="right-align">${orderDetail.product.price }</td>
						<td class="center-align">${orderDetail.amount }</td>
						<td class="right-align">${orderDetail.product.price * orderDetail.amount }</td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>