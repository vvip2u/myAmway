/**
 * 
 */


$(document).ready(function(){
	$("#list2").jqGrid(
		{
		url : '/myAmway/manage/customer/getCustomerList',
		datatype : "json",
		colNames : [ '顾客ID', '姓名', '年龄', '手机', '性别', '出生日期', '手机2' ],
		colModel : [ {
			name : 'customerId',
			index : 'customerId',
			width : 55
		}, {
			name : 'name',
			index : 'name',
			width : 90
		}, {
			name : 'age',
			index : 'age',
			width : 100
		}, {
			name : 'mobile',
			index : 'mobile',
			width : 90,
			align : "right",
			sortable : true
		}, {
			name : 'gender',
			index : 'gender',
			width : 80,
			align : "right"
		}, {
			name : 'birth',
			index : 'birth',
			width : 80,
			align : "right"
		}, {
			name : 'mobile2',
			index : 'mobile2',
			width : 150,
			sortable : false
		} ],
		hideCol : ["customerId"],
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#pager2',
		sortname : 'id',
		viewrecords : true,
		sortorder : "desc",
		caption : "顾客档案",
		ondblClickRow: function(id) {
			
		},
		onSortCol: function(name,index){
			alert("Column Name: "+name+" Column Index: "+index);
		},
		onRightClickRow: function(rowid,iRow,iCol,e) {
			
		}
	});
	$("#list2").jqGrid('navGrid', '#pager2', {
		search : true,
		edit : true,
		add : true,
		del : true
	});
});