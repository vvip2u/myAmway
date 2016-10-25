package com.myAmway.frm.orm;

import javax.servlet.http.HttpServletRequest;

public class PageSupport {
	
	private static Integer page;
	
	public static void setPage(Integer page) {
		PageSupport.page = page;
	}

	private static Page getPage(Integer page, Integer rows) {
		Page pageParams = new Page();
		pageParams.setPage(page);
		pageParams.setRows(rows);
		setPage(page);
		return pageParams;
	}

	public static Page getPage(HttpServletRequest request) {
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		Integer page = Integer.valueOf(request.getParameter("page")); 
		return getPage(page, rows);
	}
	
	public static Integer getPage() {
		return page;
	}
	
}
