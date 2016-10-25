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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = -301542599522105351L;
	
	private Integer productId;
	private String name;
	private String shortName;
	private Double weight;
	private Double price;
	private String productCode;
	private List<Goods> goodsList = new ArrayList<Goods>();
	private List<Stock> stockList = new ArrayList<Stock>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	@Column(name="short_name")
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	@Column(name="weight")
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	@Column(name="price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Column(name="product_code")
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Stock> getStockList() {
		return stockList;
	}
	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("name:     " + this.name).append("\t")
		  .append("shortName:      " + this.shortName).append("\t")
		  .append("price:      " + this.price).append("\t")
		  .append("weight:      " + this.weight);
		
		return sb.toString();
	}
	
}
