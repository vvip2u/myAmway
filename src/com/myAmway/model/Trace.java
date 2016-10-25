package com.myAmway.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 跟进表
 * @author Charles King
 * 
 */
@Entity
@Table(name="t_trace")
public class Trace implements Serializable {
	
	private static final long serialVersionUID = -3440030311526964319L;
	
	/**
	 * trace 主键
	 */
	private Integer traceId;
	/**
	 * 顾客
	 */
	private Customer customer;
	/**
	 * 跟进事件
	 */
	private String traceTime;
	/**
	 * 跟进记录
	 */
	private String remark;
	/**
	 * 打分
	 */
	private String rating;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="trace_id")
	public Integer getTraceId() {
		return traceId;
	}
	public void setTraceId(Integer traceId) {
		this.traceId = traceId;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Column(name="trace_time")
	public String getTraceTime() {
		return traceTime;
	}
	public void setTraceTime(String traceTime) {
		this.traceTime = traceTime;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="rating")
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
	
}
