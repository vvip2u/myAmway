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
@Table(name="t_stock")
public class Stock implements Serializable {
	
	private static final long serialVersionUID = -4945638032413331995L;
	
	private Integer stockId;
	private Product product;
	private Integer quantity;
	private Double total;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="stock_id")
	public Integer getStockId() {
		return stockId;
	}
	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="product_id")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Column(name="quantity")
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Column(name="total")
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
}
