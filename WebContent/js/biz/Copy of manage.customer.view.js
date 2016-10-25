var com;
if(com == undefined) com = {};
if(com.myAmway == undefined) com.myAmway = {};

com.myAmway.Customer = {
	
	viewSelectedRow : -1,	
	
	winPopup : null,
	
	ableHoverOrClick: true,
	
	customerListGrid : null,
	
	initBtns : function() {
		$("#inviteBtn").button({
			
		});
		
		$("#traceBtn").button({
			
		});
	},
	
	init : function() {
		this.initBtns();
		var self = this;
		this.customerListGrid = $("#customerList").jqGrid(
			{
			url : '/myAmway/manage/customer/getCustomerList',
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
				align : "center",
				width : 80
			}, {
				name : 'mobile',
				index : 'mobile',
				width : 140,
				align : "right",
				sortable : true
			}, {
				name : 'gender',
				index : 'gender',
				width : 80,
				align : "right",
				formatter:'select',
				sorttype:"int",
				editable:true,
				edittype:"select",
				editoptions: {value:"1:男;2:女"}
			}, {
				name : 'birth',
				index : 'birth',
				width : 120,
				
				align : "right"
			}, {
				name : 'mobile2',
				index : 'mobile2',
				width : 150,
				align : "right",
				sortable : false
			} ],
			rowNum : 500,
			rowList : [ /*10, 20, 30, 50, 100, 200,*/ 500 ],
			height : 460 ,
			pager : '#customerListPager',
			sortname : 'id',
			viewrecords : true,
			sortorder : "desc",
//			multiselect: true, 
//			multikey: "ctrlKey", 
			caption : "顾客档案",
			hideCol : ["customerId"],
			closeOnEscape:true,
			onCellSelect : function(rowid,iRow,iCol,e) {
//				e.preventDefault();
				console.log("onCell   " + rowid);
				if(!self.ableHoverOrClick) return;
				$("#customerList").jqGrid('setSelection', rowid, true);
			},
			ondblClickRow: function(rowid,iRow,iCol,e) {
				if(!self.ableHoverOrClick) return;
//				console.log("dblclick");
				$("#customerList").jqGrid('setSelection', rowid, true);
//				$("#customerList").jqGrid('setSelection', rowid,true );//选中点击的行
			},
			onSortCol: function(name,index){
				alert("Column Name: "+name+" Column Index: "+index);
			},
			onRightClickRow: function(rowid,iRow,iCol,e) {
				if(!self.ableHoverOrClick) return;
//				console.log("rtclick");
				$("#customerList").jqGrid('setSelection', rowid, true);
				com.myAmway.Customer.setViewSelectedRow(rowid);
			}
		});
		$("#customerList").jqGrid('navGrid', '#customerListPager', {
			search : true,
			edit : true,
			add : true,
			del : true
		}).jqGrid('navButtonAdd', '#customerListPager', {
			position:'last',
			title:'导出为Excel文件',
			caption:'',
			buttonicon:"ui-icon-add",
			onClickButton:com.myAmway.Customer.exportCustomerListXsl
		});
		this.rightClickMenu();
		this.bindDraggable();
		this.bindSearchButton();
	},
	
	bindDraggable : function() {
		$("#abc").draggable();
	},
	
	bindSearchButton : function() {
		$("#search input").click(function(){
			$("#customerList").jqGrid('searchGrid', 
				{name:['cn']}
			);
		});
	},
	
	setViewSelectedRow : function(rowid) {
		this.viewSelectedRow = rowid;
	},
	
	exportCustomerListXsl : function() {
 	   $.ajax({
		   url : "import",
		   success: function(html,textStatus, XMLHttpRequest) {
			   jAlert('Import Successful!', 'Confirm!');
		   },error: function () {
			   jAlert('Import Fail!', 'Return!');
		   }
	   });
	},
	
	disableHoverRows : function() {
		this.ableHoverOrClick = false;

	},
	
	enableHoverRows : function() {
		this.ableHoverOrClick = true;
	},
	
	rightClickMenu : function() {
		var self = this;
        var option = { width: 160, items: [
           { text: "基本信息", icon: "../../images/icons/ico2.gif", alias: "basic info", width: 160, action: basicInfoPopup},
           { text: "健康信息", icon: "../../images/icons/ico3.gif", alias: "health info", action: healthPopup },
           { text: "购货信息", icon: "../../images/icons/ico3.gif", alias: "buy record", action: orderPopup },
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
    	   self.disableHoverRows();
    	   var customer = com.myAmway.Customer;
    	   var basicInfoModal = $("#basicInfoModal");
    	   var rowData = $("#customerList").getRowData(com.myAmway.Customer.viewSelectedRow);
//    	   $("#customerList").disable();
    	   $.ajax({
    		   url : "view/"+rowData['customerId'],
    		   success: function(html,textStatus, XMLHttpRequest) {
    			   basicInfoModal.show();
    			   self.winPopup = basicInfoModal
    			   .dialog({ title: '基本信息', 
    				   width: "800px",
    				   position: "top",
    				   zIndex: 100,
    				   resizable : false,
    				   modal: true,
    				   close : function(event, ui) {
    					   self.enableHoverRows();
    				   }
    			   }).html(html);
    			   self.winPopup.dialog("open");
    			   self.disableHoverRows();
    		   },
    		   error: function(jqXHR, textStatus, errorThrown) {
    			   self.winPopup = basicInfoModal
    			   .dialog({ title: '错误信息', 
    				   width: "800px",
    				   position: "top",
    				   zIndex: 100,
    				   resizable : false,
    				   modal: true,
    				   close : function(event, ui) {
    					   self.enableHoverRows();
    				   }
    			   }).html(errorThrown);
    		   }
    	   });
       }
       function healthPopup() {
    	   var customer = com.myAmway.Customer;
    	   var healthModal = $("#healthModal");
    	   var rowData = $("#customerList").getRowData(com.myAmway.Customer.viewSelectedRow);
    	   $.ajax({
    		   url : "../health/list/"+rowData['customerId'],
    		   success: function(html,textStatus, XMLHttpRequest) {
		    	   healthModal.show();
		    	   self.winPopup = healthModal.dialog({ 
    	   				title: '健康信息',
    	   				resizable: false,
    	   				modal: true
		    	   }).html(html);
	        	   self.winPopup.dialog("open");
	        	   self.disableHoverRows();
    		   }
    	   });
       }
       function orderPopup() {
    	   var orderModal = $("#orderModal");
    	   var rowData = $("#customerList").getRowData(com.myAmway.Customer.viewSelectedRow);
    	   $.ajax({
    		   url : "../order/view/"+rowData['customerId'],
    		   success: function(html,textStatus, XMLHttpRequest) {
    			   orderModal.show();
		    	   self.winPopup = orderModal.dialog({ 
		    		   title: '购货信息',
     				   width: "800px",
    				   position: "top",
    				   zIndex: 100,
    				   resizable : false,
    				   modal: true,
    				   close : function(event, ui) {
    					   self.enableHoverRows();
    				   }
		    	   }).html(html);
	        	   self.winPopup.dialog("open");
	        	   self.disableHoverRows();
    		   },
    		   error: function(jqXHR, textStatus, errorThrown) {
    			   self.winPopup = basicInfoModal
    			   .dialog({ title: '错误信息', 
    				   width: "800px",
    				   position: "top",
    				   zIndex: 100,
    				   resizable : false,
    				   modal: true,
    				   close : function(event, ui) {
    					   self.enableHoverRows();
    				   }
    			   }).html(errorThrown);
    		   }
    	   });
       }
       function menuAction() {
           alert(this.data.alias);
       }
       function applyrule(menu) {               

       }
       function BeforeContextMenu() {
    	   if(!self.ableHoverOrClick) {
    		   jAlert("已打开窗口", "已打开顾客详细信息");
    		   return false;
    	   }
           return true;
       }
		$("#customerList").contextmenu(option);
	}
};

$(document).ready(function(){
	com.myAmway.Customer.init();
});
