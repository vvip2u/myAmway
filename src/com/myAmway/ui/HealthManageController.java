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
import com.myAmway.model.Health;
import com.myAmway.service.HealthService;
import com.myAmway.ui.bean.json.HealthJson;

@Controller public class HealthManageController {
	
	private transient Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="healthService")
	private HealthService healthService;

	@RequestMapping(value="/manage/health/list/{customerId}")
	public String list(@PathVariable Integer customerId, Model model) {
		List<Health> healthList = healthService.getHealthListByCustomerId(customerId);
		model.addAttribute("healthList", healthList);
		return "manage/health/list.jsp";
	}
	
	@RequestMapping(value = "/manage/health/add")
	public String add(Model model, @ModelAttribute Health health) {
		healthService.saveHealth(health);
		return "manage/health/add.jsp";
	}
	
	@RequestMapping(value="/manage/health/view/{healthId}")
	public String view(@PathVariable Integer healthId, Model model) {
		Health u = healthService.getHealth(healthId);
		model.addAttribute("health", u);
		return "manage/health/view.jsp";
	}
	
	@RequestMapping(value="/manage/health/edit/{healthId}", method=RequestMethod.GET)
	public String edit(@PathVariable Integer healthId, Model model) {
		Health u = healthService.getHealth(healthId);
		model.addAttribute("health", u);
		return "manage/health/edit.jsp";
	}
	
	@RequestMapping(value="/manage/health/getHealthList",method=RequestMethod.GET)
	@ResponseBody
	public String getHealthList(HttpServletRequest request) {
		Page pageParams = PageSupport.getPage(request);
		List<Health> healthList = healthService.getHealthList(pageParams);
		Integer recordsCount = healthService.getAllHealthCount();
		HealthJson healthGrid = new HealthJson(healthList, pageParams, recordsCount);
		return healthGrid.toJson();
	}

}
