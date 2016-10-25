package com.myAmway.frm.orm;

import java.io.Serializable;

public class Page implements Serializable {
	
	private static final long serialVersionUID = 5173693991469246560L;
	
	private Integer page;
	private Integer rows;
	private String sord;
	private String idx;
	private String _search;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String get_search() {
		return _search;
	}
	public void set_search(String _search) {
		this._search = _search;
	}
	
}
