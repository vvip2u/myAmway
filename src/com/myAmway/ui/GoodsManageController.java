package com.myAmway.ui;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myAmway.frm.orm.Page;
import com.myAmway.frm.orm.PageSupport;
import com.myAmway.model.Goods;
import com.myAmway.service.GoodsService;
import com.myAmway.service.ProcessStatus;
import com.myAmway.ui.bean.json.GoodsJson;

@Controller public class GoodsManageController {
	
	private transient Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="goodsService")
	private GoodsService goodsService;

	@RequestMapping(value="/manage/goods/list")
	public String list(Model model) {
		return "manage/goods/list.jsp";
	}
	
	@RequestMapping(value = "/manage/goods/add")
	public String add(Model model, @ModelAttribute Goods goods) {
		goodsService.saveGoods(goods);
		return "manage/goods/add.jsp";
	}
	
	@RequestMapping(value="/manage/goods/view/{goodsId}")
	public String view(@PathVariable Integer goodsId, Model model) {
		Goods g = goodsService.getGoods(goodsId);
		model.addAttribute("goods", g);
		return "manage/goods/view.jsp";
	}
	
	@RequestMapping(value="/manage/goods/edit/{goodsId}", method=RequestMethod.GET)
	public String edit(@PathVariable Integer goodsId, Model model) {
		Goods g = goodsService.getGoods(goodsId);
		model.addAttribute("goods", g);
		return "manage/goods/edit.jsp";
	}
	
	@RequestMapping(value="/manage/goods/getGoodsList",method=RequestMethod.GET)
	@ResponseBody
	public String getGoodsList(HttpServletRequest request) {
		Page pageParams = PageSupport.getPage(request);
		List<Goods> goodsList = goodsService.getGoodsList(pageParams);
		Integer recordsCount = goodsService.getAllGoodsCount();
		GoodsJson goodsGrid = new GoodsJson(goodsList, pageParams, recordsCount);
		return goodsGrid.toJson();
	}
	
	@RequestMapping(value="/manage/goods/import",method=RequestMethod.GET)
	@ResponseBody
	public String importGoodsList() {
//		ProcessStatus status = customerPorter.importer("E:/工作名单.xlsx");
		
		return "{'status':'" + ProcessStatus.READY + "'}";
	}



}
