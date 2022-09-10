package com.shoppingcart.dao;

import java.util.List;

import com.shoppingcart.model.CartInfo;
import com.shoppingcart.model.OrderDetailInfo;
import com.shoppingcart.model.OrderInfo;
import com.shoppingcart.model.PaginationResult;

public interface OrderDAO {

	public void saveOrder(CartInfo cartInfo);
	
	public PaginationResult<OrderInfo> getAllOrderInfos(int page, int maxResult);
	
	public OrderInfo getOrderInfoById(String orderId);
	
	public List<OrderDetailInfo> getAllOrderDetailInfos(String orderId);
	
}