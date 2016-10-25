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
@Table(name="t_goods")
public class Goods implements Serializable {
	
	private static final long serialVersionUID = 5298397102221145191L;
	
	private Integer goodsId;
	private Product product;
	private Double price;
	private Integer quantity;
	private String produceTime;
	private String guaranteePeriod;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="goods_id")
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="product_id")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Column(name="price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Column(name="quantity")
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Column(name="produce_time")
	public String getProduceTime() {
		return produceTime;
	}
	public void setProduceTime(String produceTime) {
		this.produceTime = produceTime;
	}
	@Column(name="guarantee_period")
	public String getGuaranteePeriod() {
		return guaranteePeriod;
	}
	public void setGuaranteePeriod(String guaranteePeriod) {
		this.guaranteePeriod = guaranteePeriod;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("name:     " + this.goodsId).append("\t")
		  .append("shortName:   " + this.product.getShortName()).append("\t")
		  .append("price:      " + this.product.getPrice()).append("\t")
		  .append("gender:   " + this.quantity).append("\t")
		  .append("marriage: " + this.produceTime).append("\t")
		  .append("address:  " + this.guaranteePeriod);
		
		return sb.toString();
	}
	
}
