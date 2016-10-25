package com.myAmway.ui.bean.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.myAmway.frm.orm.Page;
import com.myAmway.frm.util.jqgrid.JqGridDataSource;
import com.myAmway.model.Goods;

public class GoodsJson extends JsonPrototype<Goods>{

	private static final long serialVersionUID = 4552986045064503636L;
	
	public GoodsJson(List<Goods> list, Page page, Integer records) {
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
			Goods goods = list.get(i);
			cell.add(goods.getGoodsId() + "");
			cell.add(goods.getProduct().getName());
			cell.add(goods.getProduct().getShortName());
			cell.add(goods.getQuantity() + "");
			cell.add(goods.getPrice() + "");
			cell.add(goods.getProduceTime());
			cell.add(goods.getGuaranteePeriod());
			row.put("id", goods.getGoodsId() + "");
			row.put("cell", cell);
			rows.add(row);
		}
		
		json.setRows(rows);
		return json.toJSONString();
	}

}
