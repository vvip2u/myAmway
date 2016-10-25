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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 名片表
 * @author Charles King
 *
 */
@Entity
@Table(name="t_business_card")
public class BusinessCard implements Serializable {
	
	private static final long serialVersionUID = -1038208876962134733L;
	/**
	 * card表主键
	 */
	private Integer cardId;
	/**
	 * card路径
	 */
	private String cardPath;
//	/**
//	 * 引用外键指向customer
//	 */
//	private Customer customer;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="card_id")
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	@Column(name="card_path")
	public String getCardPath() {
		return cardPath;
	}
	public void setCardPath(String cardPath) {
		this.cardPath = cardPath;
	}
//	@OneToOne(mappedBy="customer")
//	public Customer getCustomer() {
//		return customer;
//	}
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
	
	
}
