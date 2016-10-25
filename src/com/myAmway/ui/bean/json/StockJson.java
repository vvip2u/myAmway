package com.myAmway.ui.bean.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.myAmway.frm.orm.Page;
import com.myAmway.frm.util.jqgrid.JqGridDataSource;
import com.myAmway.model.Stock;

public class StockJson extends JsonPrototype<Stock>{
	
	private static final long serialVersionUID = 5456106740273976389L;

	public StockJson(List<Stock> list, Page page, Integer records) {
		super(list, page, records);
	}

	public String toJson() {
		int pageSize = page.getRows();
		JqGridDataSource json = new JqGridDataSource();
		json.setPage(page.getPage());
		json.setRecords(records);
		Integer total = (records % pageSize == 0) ? (records / pageSize) : ((records / pageSize) + 1);
		json.setTotal(total);
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> row = new  LinkedHashMap<String, Object>();
			List<String> cell = new ArrayList<String>();
			Stock stock = list.get(i);
			cell.add(stock.getStockId() + "");
			cell.add(stock.getProduct().getName());
			cell.add(stock.getProduct().getShortName());
			cell.add(stock.getQuantity() + "");
			cell.add(stock.getTotal() + "");
			row.put("id", stock.getStockId() + "");
			row.put("cell", cell);
			rows.add(row);
		}
		
		json.setRows(rows);
		return json.toJSONString();
	}

}
