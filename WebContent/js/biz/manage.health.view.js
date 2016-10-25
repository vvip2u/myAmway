var com;
if(com == undefined) com = {};
if(com.myAmway == undefined) com.myAmway = {};

com.myAmway.Customer = {
	
	init : function() {
		$("#healthList").jqGrid(
			{
			url : '/myAmway/manage/health/getHealthList',
			datatype : "json",
			colNames : [ '顾客ID', '姓名', '年龄', '手机', '性别', '出生日期', '手机2' ],
			colModel : [ {
				name : 'customerId',
				index : 'customerId',
				hidden : true,
				width : 55
			}, {
				name : 'name',
				index : 'name',
				align : 'center',
				width : 90
			}, {
				name : 'age',
				index : 'age',
				align : "right",
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
				align : "right",
				sorttype:"int",
				editable:true,
				edittype:"select",
				editoptions: {value:"1:男;2:女"}
			}, {
				name : 'birth',
				index : 'birth',
				width : 80,
				align : "right"
			}, {
				name : 'mobile2',
				index : 'mobile2',
				width : 150,
				align : "right",
				sortable : false
			} ],
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			pager : '#healthListPager',
			sortname : 'id',
			viewrecords : true,
			sortorder : "desc",
			multiselect: true, 
			multikey: "ctrlKey", 
			caption : "顾客档案",
			hideCol : ["customerId"],
			onCellSelect : function(rowid,iRow,iCol,e) {
				$("#healthList").jqGrid('setSelection', rowid, true);
			},
			ondblClickRow: function(rowid,iRow,iCol,e) {
				console.log("dblclick");
				$("#healthList").jqGrid('setSelection', rowid, true);
//				$("#healthList").jqGrid('setSelection', rowid,true );//选中点击的行
			},
			onSortCol: function(name,index){
				alert("Column Name: "+name+" Column Index: "+index);
			},
			onRightClickRow: function(rowid,iRow,iCol,e) {
				console.log("rtclick");
				$("#healthList").jqGrid('setSelection', rowid, true);
			}
		});
		$("#healthList").jqGrid('navGrid', '#healthListPager', {
			search : true,
			edit : true,
			add : true,
			del : true
		});
		this.rightClickMenu();
	},
	
	rightClickMenu : function() {
        var option = { width: 160, items: [
           { text: "基本信息", icon: "../../images/icons/ico2.gif", alias: "basic info", width: 160, action: menuAction},
           { text: "健康信息", icon: "../../images/icons/ico3.gif", alias: "health info", action: menuAction },
           { text: "购货信息", icon: "../../images/icons/ico3.gif", alias: "buy record", action: menuAction },
               { type: "splitLine" },
           { text: "保存位置", icon: "../../images/icons/ico4.gif", alias: "1-4", action: menuAction },
           { text: "重新排列", icon: "../../images/icons/ico4.gif", alias: "1-4", action: menuAction },
               { type: "splitLine" },
           { text: "视图", icon: "../../images/icons/ico6.gif", alias: "1-6", type: "group", width: 180, items: [
               { text: "展开所有节点", icon: "../../images/icons/ico6-1.gif", alias: "4-1", action: menuAction },
               { text: "收起所有节点", icon: "../../images/icons/ico6-2.gif", alias: "4-2", action: menuAction }]
           },
               { type: "splitLine" },
           { text: "属性", icon: "../../images/icons/ico5.gif", alias: "1-5", action: menuAction }], 
           onShow: applyrule,
           onContextMenu: BeforeContextMenu
       };
       function menuAction() {
           alert(this.data.alias);
       }
       function applyrule(menu) {               

       }
       function BeforeContextMenu() {
//           return this.id != "target3";
           return true;
       }
		$("#healthList").contextmenu(option);
	}
};

$(document).ready(function(){
	com.myAmway.Customer.init();
});
