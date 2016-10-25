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

@Entity
@Table(name="t_health")
public class Health implements Serializable {

	private static final long serialVersionUID = -8133436161806571801L;
	
	private Integer healthId;
	private Customer customer;
	private String examDatetime;
	private String reportPath;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="health_id")
	public Integer getHealthId() {
		return healthId;
	}
	public void setHealthId(Integer healthId) {
		this.healthId = healthId;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Column(name="exam_datetime")
	public String getExamDatetime() {
		return examDatetime;
	}
	public void setExamDatetime(String examDatetime) {
		this.examDatetime = examDatetime;
	}
	@Column(name="report_path")
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	
}
