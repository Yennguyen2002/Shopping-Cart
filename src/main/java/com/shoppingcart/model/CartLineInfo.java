package com.shoppingcart.model;

public class CartLineInfo {

	private ProductInfo productInfo;

	private int quantity;
	
	private double amount;

	public CartLineInfo() {
		this.quantity = 0;
	}
	
	public double getAmount() {
		return this.productInfo.getPrice() * this.quantity;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}