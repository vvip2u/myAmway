package com.myAmway.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 订货信息
 * @author Charles King
 *
 */
@Entity
@Table(name="t_order")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 858555136699052233L;
	
	private Integer orderId;
	
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	
	private Customer customer;
	
	private String orderCode;
	
	private Integer parentOrderId;
	
	private String orderTime;
	
	private String serviceTime;
	
	private String serviceAddress;
	
	private boolean finish;
	
	private String description;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Column(name="order_code")
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	@Column(name="parent_order_id")
	public Integer getParentOrderId() {
		return parentOrderId;
	}

	public void setParentOrderId(Integer parentOrderId) {
		this.parentOrderId = parentOrderId;
	}
	@Column(name="order_time")
	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	@Column(name="service_time")
	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	@Column(name="service_address")
	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	@Column(name="finish")
	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
