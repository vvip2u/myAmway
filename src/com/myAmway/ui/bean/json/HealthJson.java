package com.myAmway.ui.bean.json;

import java.util.List;

import com.myAmway.frm.orm.Page;
import com.myAmway.model.Health;

public class HealthJson extends JsonPrototype<Health>{

	private static final long serialVersionUID = -2531924670083942677L;

	public HealthJson(List<Health> list, Page page, Integer records) {
		super(list, page, records);
	}

	public String toJson() {
		return null;
	}

}
