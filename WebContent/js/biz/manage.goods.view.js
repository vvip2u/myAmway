var com;
if(com == undefined) com = {};
if(com.myAmway == undefined) com.myAmway = {};

com.myAmway.Goods = {
	
	viewSelectedRow : -1,	
	
	winPopup : null,
	
	goodsListGrid : null,
	
	init : function() {
		this.goodsListGrid = $("#goodsList").jqGrid(
			{
			url : '/myAmway/manage/goods/getGoodsList',
			datatype : "json",
			colNames : [ '商品ID', '商品名', '品名', '价格', '数量', '日期', '保质期' ],
			colModel : [ {
				name : 'goodsId',
				index : 'goodsId',
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
				name : 'price',
				index : 'price',
				width : 80,
				align : "right",
				sorttype:"int"
			}, {
				name : 'quantity',
				index : 'quantity',
				width : 120,
				align : "right"
			}, {
				name : 'produceTime',
				index : 'produceTime',
				width : 150,
				align : "right",
				sortable : false
			}, {
				name : 'guaranteePeriod',
				index : 'guaranteePeriod',
				width : 120,
				align : "right"
			} ],
			rowNum : 10,
			rowList : [ 10, 20, 30, 50, 100, 200, 500 ],
			height : 250 ,
			pager : '#goodsListPager',
			sortname : 'id',
			viewrecords : true,
			sortorder : "desc",
			multiselect: true, 
			multikey: "ctrlKey", 
			caption : "商品档案",
			hideCol : ["goodsId"],
			closeOnEscape:true,
			onCellSelect : function(rowid,iRow,iCol,e) {
				$("#goodsList").jqGrid('setSelection', rowid, true);
			},
			ondblClickRow: function(rowid,iRow,iCol,e) {
//				console.log("dblclick");
				$("#goodsList").jqGrid('setSelection', rowid, true);
//				$("#goodsList").jqGrid('setSelection', rowid,true );//选中点击的行
			},
			onSortCol: function(name,index){
				alert("Column Name: "+name+" Column Index: "+index);
			},
			onRightClickRow: function(rowid,iRow,iCol,e) {
//				console.log("rtclick");
				$("#goodsList").jqGrid('setSelection', rowid, true);
				com.myAmway.Goods.setViewSelectedRow(rowid);
			}
		});
		$("#goodsList").jqGrid('navGrid', '#goodsListPager', {
			search : true,
			edit : true,
			add : true,
			del : true
		}).jqGrid('navButtonAdd', '#goodsListPager', {
			position:'last',
			title:'导出为Excel文件',
			caption:'',
			buttonicon:"ui-icon-add",
			onClickButton:com.myAmway.Goods.exportGoodsListXsl
		});
		this.rightClickMenu();
	},
	
	setViewSelectedRow : function(rowid) {
		this.viewSelectedRow = rowid;
	},
	
	exportGoodsListXsl : function() {
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
    	   var goods = com.myAmway.Goods;
    	   var basicInfoModal = $("#basicInfoModal");
    	   var rowData = $("#goodsList").getRowData(com.myAmway.Goods.viewSelectedRow);
    	   $.ajax({
    		   url : "view/"+rowData['goodsId'],
    		   success: function(html,textStatus, XMLHttpRequest) {
    			   basicInfoModal.show();
    			   winPopup = basicInfoModal
    			   .dialog({ title: '基本信息', 
    				   buttons: {"提交": function(){
    					   console.log($("#goodsList").getRowData(com.myAmway.Goods.viewSelectedRow));
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
		$("#goodsList").contextmenu(option);
	}
};

$(document).ready(function(){
	com.myAmway.Goods.init();
});
