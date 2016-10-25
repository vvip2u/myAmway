/**
 * 
 */
var option = {
	width : 160,
	items : [ {
		text : "基本信息",
		icon : "images/icons/ico2.gif",
		alias : "Add node",
		width : 160,
		action : menuAction
	}, {
		text : "健康信息",
		icon : "images/icons/ico3.gif",
		alias : "Edit node",
		action : menuAction
	}, {
		text : "购货信息",
		icon : "images/icons/ico3.gif",
		alias : "Remove node",
		action : menuAction
	}, {
		type : "splitLine"
	}, {
		text : "保存位置",
		icon : "images/icons/ico4.gif",
		alias : "1-4",
		action : menuAction
	}, {
		text : "重新排列",
		icon : "images/icons/ico4.gif",
		alias : "1-4",
		action : menuAction
	}, {
		type : "splitLine"
	}, {
		text : "视图",
		icon : "images/icons/ico6.gif",
		alias : "1-6",
		type : "group",
		width : 180,
		items : [ {
			text : "展开所有节点",
			icon : "images/icons/ico6-1.gif",
			alias : "4-1",
			action : menuAction
		}, {
			text : "收起所有节点",
			icon : "images/icons/ico6-2.gif",
			alias : "4-2",
			action : menuAction
		} ]
	}, {
		type : "splitLine"
	}, {
		text : "属性",
		icon : "images/icons/ico5.gif",
		alias : "1-5",
		action : menuAction
	}

	],
	onShow : applyrule,
	onContextMenu : BeforeContextMenu
};
function menuAction() {
	alert(this.data.alias);
}
function applyrule(menu) {
	if (this.id == "target2") {
		menu.applyrule({
			name : "target2",
			disable : true,
			items : [ "Edit node", "Remove node", "爱普生", "洗衣机", "收音机" ]
		});
	} else {
		menu.applyrule({
			name : "all",
			disable : true,
			items : []
		});
	}
}
function BeforeContextMenu() {
	return this.id != "target3";
}
$("#target,#target2,#target3").contextmenu(option);