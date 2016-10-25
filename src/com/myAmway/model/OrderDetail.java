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
 * 订货详细表
 * @author Charles King
 *
 */
@Entity
@Table(name = "t_order_detail")
public class OrderDetail implements Serializable {
	
	private static final long serialVersionUID = -94241574663664203L;
	
	private Integer orderDetailId;
	private Product product;
	private Integer amount;
	private Order order;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_detail_id")
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Column(name="amount")
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
