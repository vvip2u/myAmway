package com.myAmway.ui.bean.json;

import java.io.Serializable;
import java.util.List;

import com.myAmway.frm.orm.Page;
import com.myAmway.frm.util.jqgrid.JqGridDataSource;

public class JsonPrototype<T> extends JqGridDataSource implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 4334553675358317722L;
	
	protected List<T> list;
	protected Integer records;
	protected Page page;
	
	public JsonPrototype(List<T> list, Page page, Integer records) {
		this.list = list;
		this.records = records;
		this.page = page;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
