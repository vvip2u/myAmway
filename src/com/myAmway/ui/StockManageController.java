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
import com.myAmway.model.Stock;
import com.myAmway.service.StockService;
import com.myAmway.service.ProcessStatus;
import com.myAmway.ui.bean.json.StockJson;

@Controller public class StockManageController {
	
	private transient Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="stockService")
	private StockService stockService;

	@RequestMapping(value="/manage/stock/list")
	public String list(Model model) {
		return "manage/stock/list.jsp";
	}
	
	@RequestMapping(value = "/manage/stock/add")
	public String add(Model model, @ModelAttribute Stock stock) {
		stockService.saveStock(stock);
		return "manage/stock/add.jsp";
	}
	
	@RequestMapping(value="/manage/stock/view/{stockId}")
	public String view(@PathVariable Integer stockId, Model model) {
		Stock g = stockService.getStock(stockId);
		model.addAttribute("stock", g);
		return "manage/stock/view.jsp";
	}
	
	@RequestMapping(value="/manage/stock/edit/{stockId}", method=RequestMethod.GET)
	public String edit(@PathVariable Integer stockId, Model model) {
		Stock g = stockService.getStock(stockId);
		model.addAttribute("stock", g);
		return "manage/stock/edit.jsp";
	}
	
	@RequestMapping(value="/manage/stock/getStockList",method=RequestMethod.GET)
	@ResponseBody
	public String getStockList(HttpServletRequest request) {
		Page pageParams = PageSupport.getPage(request);
		List<Stock> stockList = stockService.getStockList(pageParams);
		Integer recordsCount = stockService.getAllStockCount();
		StockJson stockGrid = new StockJson(stockList, pageParams, recordsCount);
		return stockGrid.toJson();
	}
	
	@RequestMapping(value="/manage/stock/import",method=RequestMethod.GET)
	@ResponseBody
	public String importStockList() {
//		ProcessStatus status = customerPorter.importer("E:/工作名单.xlsx");
		
		return "{'status':'" + ProcessStatus.READY + "'}";
	}



}
