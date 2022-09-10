package com.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {

	private int orderNum;

	private CustomerInfo customerInfo;

	private List<CartLineInfo> cartLineInfos = new ArrayList<CartLineInfo>();

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}
	
	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<CartLineInfo> getCartLineInfos() {
		return cartLineInfos;
	}

	private CartLineInfo getCartLineInfoByCode(String code) {
		for (CartLineInfo cartLineInfo : this.cartLineInfos) {//kiểm tra product đã tồn tại trong giỏ hàng chưa
			if (cartLineInfo.getProductInfo().getCode().equals(code)) {
				return cartLineInfo;
			}
		}
		return null;
	}

	public void addProduct(ProductInfo productInfo, int quantity) {
		CartLineInfo cartLineInfo = getCartLineInfoByCode(productInfo.getCode());

		if (cartLineInfo == null) {
			cartLineInfo = new CartLineInfo();
			cartLineInfo.setQuantity(0);
			cartLineInfo.setProductInfo(productInfo);
			this.cartLineInfos.add(cartLineInfo);
		}

		int newQuantity = cartLineInfo.getQuantity() + quantity;
		if (newQuantity <= 0) {
			this.cartLineInfos.remove(cartLineInfo);
		} else {
			cartLineInfo.setQuantity(newQuantity);
		}
	}

	public void validate() {

	}

	public void updateProduct(String code, int quantity) {
		CartLineInfo cartLineInfo = getCartLineInfoByCode(code);

		if (cartLineInfo != null) {
			if (quantity <= 0) {
				this.cartLineInfos.remove(cartLineInfo);
			} else {
				cartLineInfo.setQuantity(quantity);
			}
		}
	}

	public void removeProduct(ProductInfo productInfo) {
		CartLineInfo cartLineInfo = getCartLineInfoByCode(productInfo.getCode());
		if (cartLineInfo != null) {
			this.cartLineInfos.remove(cartLineInfo);
		}
	}

	public boolean isEmpty() {
		return this.cartLineInfos.isEmpty();
	}

	public boolean isValidCustomer() {
		return this.customerInfo != null && this.customerInfo.isValid();
	}

	public int getQuantityTotal() {
		int quantity = 0;
		for (CartLineInfo cartLineInfo : this.cartLineInfos) {
			quantity += cartLineInfo.getQuantity();//3 + 5 = 8
		}
		return quantity;
	}

	public double getAmountTotal() {
		double total = 0;
		for (CartLineInfo cartLineInfo : this.cartLineInfos) {
			total += cartLineInfo.getAmount();//300+250
		}
		return total;
	}

	public void updateQuantity(CartInfo cartForm) {
		if (cartForm != null) {
			List<CartLineInfo> cartLineInfos = cartForm.getCartLineInfos();
			for (CartLineInfo cartLineInfo : cartLineInfos) {
				updateProduct(cartLineInfo.getProductInfo().getCode(), cartLineInfo.getQuantity());
			}
		}
	}
	
	

}