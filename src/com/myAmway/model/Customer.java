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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 顾客信息
 * @author Charles King
 *
 */
@Entity
@Table(name="t_customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = -5762413844024582422L;
	
	private Integer customerId;
	private String name;
	private String age;
	private String address;
	private String mobile;
	private String mobile2;
	private String gender;
	private String birth;
	private String profession;
	private boolean marriage;
	private String guCode;
	private String company;
	private String industry;
	private String province;
	private String bizCardPath;
	private String guDate;
	private String email;
	
	private List<Health> healthList = new ArrayList<Health>();
	private List<Trace> traceList = new ArrayList<Trace>();
	private List<Order> orderList = new ArrayList<Order>();
	private BusinessCard businessCard;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="age")
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="mobile2")
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="birth")
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="profession")
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	@Column(name="marriage")
	public boolean isMarriage() {
		return marriage;
	}
	public void setMarriage(boolean marriage) {
		this.marriage = marriage;
	}
	@Column(name="gu_code")
	public String getGuCode() {
		return guCode;
	}
	public void setGuCode(String guCode) {
		this.guCode = guCode;
	}
	@Column(name="company")
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Column(name="industry")
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	@Column(name="province")
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name="biz_card_path")
	public String getBizCardPath() {
		return bizCardPath;
	}
	public void setBizCardPath(String bizCardPath) {
		this.bizCardPath = bizCardPath;
	}
	@Column(name="gu_date")
	public String getGuDate() {
		return guDate;
	}
	public void setGuDate(String guDate) {
		this.guDate = guDate;
	}
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Health> getHealthList() {
		return healthList;
	}
	public void setHealthList(List<Health> healthList) {
		this.healthList = healthList;
	}
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Trace> getTraceList() {
		return traceList;
	}
	public void setTraceList(List<Trace> traceList) {
		this.traceList = traceList;
	}
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	public BusinessCard getBusinessCard() {
		return businessCard;
	}
	public void setBusinessCard(BusinessCard businessCard) {
		this.businessCard = businessCard;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("name:     " + this.name).append("\t")
		  .append("age:      " + this.age).append("\t")
		  .append("birth:    " + this.birth).append("\t")
		  .append("mobile:   " + this.mobile).append("\t")
		  .append("gender:   " + this.gender).append("\t")
		  .append("marriage: " + this.marriage).append("\t")
		  .append("address:  " + this.address);
		
		return sb.toString();
	}
	
}
