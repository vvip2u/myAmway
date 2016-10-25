package com.myAmway.frm.util.jqgrid;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import com.myAmway.model.Customer;
import com.myAmway.ui.bean.json.CustomerJson;

public class JqGridDataSource implements JSONAware, JSONStreamAware {

	/**
	 * jqGrid 的数据源类
	 * 
	 * @author rikugun
	 * 
	 */

	private int records = 0;
	private int page = 0;
	private int total = 0;
	private List<Map<String, Object>> rows = new LinkedList<Map<String, Object>>();
	private JSONObject json = new JSONObject();

	/**
	 * 添加一行记录
	 * 
	 * @param row
	 *            输出的记录
	 * @return 当前数据源,方便链式写法 ds.add("a row").add("other row");
	 */
	public JqGridDataSource addRow(Map<String, Object> row) {
		rows.add(row);
		return this;
	}

	/**
	 * 构造json对象
	 */
	private void build() {
		json.put("records", new Integer(records));
		json.put("page", new Integer(page));
		json.put("total", new Integer(total));
		json.put("rows", rows);
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}

	public String toJSONString() {
		build();
		return json.toJSONString();
	}

	public void writeJSONString(Writer out) throws IOException {
		build();
		json.writeJSONString(out);
	}
	
}
