package com.myAmway.ui;

import java.net.URLDecoder;
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
import com.myAmway.model.Customer;
import com.myAmway.model.Trace;
import com.myAmway.service.CustomerPorter;
import com.myAmway.service.CustomerService;
import com.myAmway.service.ProcessStatus;
import com.myAmway.ui.bean.json.CustomerJson;
import com.myAmway.util.HtmlUtils;

@Controller public class CustomerManageController {
	
	private transient Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="customerService")
	private CustomerService customerService;
	@Resource(name="customerPorter")
	private CustomerPorter customerPorter;

	@RequestMapping(value="/manage/customer/list")
	public String list(Model model) {
		return "manage/customer/list.jsp";
	}
	
	@RequestMapping(value = "/manage/customer/add")
	public String add(Model model, @ModelAttribute Customer customer) {
		customerService.saveCustomer(customer);
		return "manage/customer/add.jsp";
	}
	
	@RequestMapping(value="/manage/order/view/{customerId}")
	public String order(@PathVariable Integer customerId, Model model) {
		Customer u = customerService.getCustomer(customerId);
		model.addAttribute("customer", u);
		return "manage/order/view.jsp";
	}
	
	@RequestMapping(value="/manage/customer/view/{customerId}")
	public String view(@PathVariable Integer customerId, Model model) {
		Customer u = customerService.getCustomer(customerId);
		model.addAttribute("customer", u);
		return "manage/customer/view.jsp";
	}
	
	@RequestMapping(value="/manage/customer/edit/{customerId}", method=RequestMethod.GET)
	public String edit(@PathVariable Integer customerId, Model model) {
		Customer u = customerService.getCustomer(customerId);
		model.addAttribute("customer", u);
		return "manage/customer/edit.jsp";
	}
	
//	http://localhost:8080/myAmway/manage/customer/getCustomerList?_search=false&nd=1309016283099&rows=10&page=2&sidx=id&sord=desc
	@RequestMapping(value="/manage/customer/getCustomerList",method=RequestMethod.GET)
	@ResponseBody
	public String getCustomerList(HttpServletRequest request) {
		log.info("CustomerManageController.getCustomerList()");
		Page pageParams = PageSupport.getPage(request);
		List<Customer> customerList = customerService.getCustomerList(pageParams);
		Integer recordsCount = customerService.getAllCustomerCount();
//		List<Customer> customerList = customerService.getCustomerList();
		CustomerJson customerGrid = new CustomerJson(customerList, pageParams, recordsCount);
		log.info(customerGrid.toJson());
		return customerGrid.toJson();
	}
	
	@RequestMapping(value="/manage/customer/import",method=RequestMethod.GET)
	@ResponseBody
	public String importCustomerList() {
		ProcessStatus status = customerPorter.importer("E:/工作名单.xlsx");
		
		return "{\"status\":\"" + status + "\"}";
	}

	@RequestMapping(value="/manage/customer/addTrace", method=RequestMethod.GET)
	@ResponseBody
	public String addTrace(HttpServletRequest request) {
		String customerId = request.getParameter("customerId");
		String traceTime = request.getParameter("traceTime");
		String rating = request.getParameter("rating");
		String content = request.getParameter("content");
		
		Customer customer = customerService.getCustomer(Integer.valueOf(customerId));
		try {
			List<Trace> traceList = customer.getTraceList();
			Trace trace = new Trace();
			trace.setRating(rating);
			trace.setTraceTime(traceTime);
			traceList.add(trace);
			customer.setTraceList(traceList);
			
			trace.setCustomer(customer);
		
			content = URLDecoder.decode(content, "UTF-8");
			content = URLDecoder.decode(content, "UTF-8");
			content = HtmlUtils.htmlEscape(content);
			
			System.out.println("content:  " + content);
			trace.setRemark(content);
			customerService.updateCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\":\"fail\"}";
		}
		
		return "{\"status\":\"ok\"}";
//		return JSONObject.fromObject("{'status':'" + "aaa" + "'}");
	}
	
	@RequestMapping(value="/manage/customer/addDetail", method=RequestMethod.GET)
	@ResponseBody
	public String addDetail(HttpServletRequest request) {
		String customerId = request.getParameter("customerId");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		
		Customer customer = customerService.getCustomer(Integer.valueOf(customerId));
		try {
			customer.setMobile(mobile);
			customer.setEmail(email);
		
			customerService.updateCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\":\"fail\"}";
		}
		
		return "{\"status\":\"ok\"}";
//		return JSONObject.fromObject("{'status':'" + "aaa" + "'}");
	}
	
	public static void main(String[] args) {
//		JSONObject jsonObject = JSONObject.fromObject("{\"abc\":\"def\"}");
//		System.out.println(jsonObject.toString());
//		String content = "fdafda%E5%93%88%E5%93%88";
//		try {
//			content = URLDecoder.decode(content, "UTF-8");
//			content = URLDecoder.decode(content, "UTF-8");
//			System.out.println(content);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		
		System.out.println(HtmlUtils.htmlEscape("a<b\ncd>"));
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	public @ResponseBody Map<String, ? extends Object> create(@RequestBody Customer account, HttpServletResponse response) {
////	    Set<ConstraintViolation<Account>> failures = validator.validate(account);
////	    if (!failures.isEmpty()) {
////	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////	        return validationMessages(failures);
////	    } else {
////	        accounts.put(account.assignId(), account);
////	        return Collections.singletonMap("id", account.getId());
////	    }
//	}

}
