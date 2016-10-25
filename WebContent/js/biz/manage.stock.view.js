var com;
if(com == undefined) com = {};
if(com.myAmway == undefined) com.myAmway = {};

com.myAmway.Stock = {
	
	viewSelectedRow : -1,	
	
	winPopup : null,
	
	stockListGrid : null,
	
	init : function() {
		this.stockListGrid = $("#stockList").jqGrid(
			{
			url : '/myAmway/manage/stock/getStockList',
			datatype : "json",
			colNames : [ '商品ID', '商品名', '品名', '数量', '总价'],
			colModel : [ {
				name : 'stockId',
				index : 'stockId',
				hidden : true,
				width : 55
			}, {
				name : 'name',
				index : 'name',
				align : 'center',
				width : 90
			}, {
				name : 'shortName',
				index : 'shortName',
				align : "center",
				width : 80
			}, {
				name : 'quantity',
				index : 'quantity',
				width : 80,
				align : "right",
				sorttype:"int"
			}, {
				name : 'total',
				index : 'total',
				width : 120,
				align : "right"
			} ],
			rowNum : 10,
			rowList : [ 10, 20, 30, 50, 100, 200, 500 ],
			height : 250 ,
			pager : '#stockListPager',
			sortname : 'id',
			viewrecords : true,
			sortorder : "desc",
			multiselect: true, 
			multikey: "ctrlKey", 
			caption : "商品档案",
			hideCol : ["stockId"],
			closeOnEscape:true,
			onCellSelect : function(rowid,iRow,iCol,e) {
				$("#stockList").jqGrid('setSelection', rowid, true);
			},
			ondblClickRow: function(rowid,iRow,iCol,e) {
//				console.log("dblclick");
				$("#stockList").jqGrid('setSelection', rowid, true);
//				$("#stockList").jqGrid('setSelection', rowid,true );//选中点击的行
			},
			onSortCol: function(name,index){
				alert("Column Name: "+name+" Column Index: "+index);
			},
			onRightClickRow: function(rowid,iRow,iCol,e) {
//				console.log("rtclick");
				$("#stockList").jqGrid('setSelection', rowid, true);
				com.myAmway.Stock.setViewSelectedRow(rowid);
			}
		});
		$("#stockList").jqGrid('navGrid', '#stockListPager', {
			search : true,
			edit : true,
			add : true,
			del : true
		}).jqGrid('navButtonAdd', '#stockListPager', {
			position:'last',
			title:'导出为Excel文件',
			caption:'',
			buttonicon:"ui-icon-add",
			onClickButton:com.myAmway.Stock.exportStockListXsl
		});
		this.rightClickMenu();
	},
	
	setViewSelectedRow : function(rowid) {
		this.viewSelectedRow = rowid;
	},
	
	exportStockListXsl : function() {
 	   $.ajax({
		   url : "import",
		   success: function(html,textStatus, XMLHttpRequest) {
			   jAlert('Import Successful!', 'Confirm!');
		   },error: function () {
			   jAlert('Import Fail!', 'Return!');
		   }
	   });
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
       function basicInfoPopup() {
    	   var stock = com.myAmway.Stock;
    	   var basicInfoModal = $("#basicInfoModal");
    	   var rowData = $("#stockList").getRowData(com.myAmway.Stock.viewSelectedRow);
    	   $.ajax({
    		   url : "view/"+rowData['stockId'],
    		   success: function(html,textStatus, XMLHttpRequest) {
    			   basicInfoModal.show();
    			   winPopup = basicInfoModal
    			   .dialog({ title: '基本信息', 
    				   buttons: {"提交": function(){
    					   console.log($("#stockList").getRowData(com.myAmway.Stock.viewSelectedRow));
    				   }, "关闭": function() { winPopup.dialog("close"); }} 
    			   }).html(html);
    			   winPopup.dialog("open");
    		   }
    	   });
       }
       function menuAction() {
           alert(this.data.alias);
       }
       function applyrule(menu) {               

       }
       function BeforeContextMenu() {
//           return this.id != "target3";
           return true;
       }
		$("#stockList").contextmenu(option);
	}
};

$(document).ready(function(){
	com.myAmway.Stock.init();
});
