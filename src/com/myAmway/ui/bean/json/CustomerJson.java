package com.myAmway.ui.bean.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.myAmway.frm.orm.Page;
import com.myAmway.frm.util.jqgrid.JqGridDataSource;
import com.myAmway.model.Customer;

public class CustomerJson extends JsonPrototype<Customer> {
	
	private static final long serialVersionUID = 5674116855853426943L;

	public CustomerJson(List<Customer> list, Page page, Integer records) {
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
			Customer customer = list.get(i);
			cell.add(customer.getCustomerId() + "");
			cell.add(customer.getName());
			cell.add(customer.getAge());
			cell.add(customer.getMobile());
			cell.add(customer.getGender());
			cell.add(customer.getBirth());
			cell.add(customer.getMobile2());
			row.put("id", customer.getCustomerId() + "");
			row.put("cell", cell);
			rows.add(row);
		}
		
		json.setRows(rows);
		return json.toJSONString();
	}
	
}
