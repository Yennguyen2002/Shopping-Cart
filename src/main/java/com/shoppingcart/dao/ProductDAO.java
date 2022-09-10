package com.shoppingcart.dao;

import com.shoppingcart.entity.Product;
import com.shoppingcart.model.PaginationResult;
import com.shoppingcart.model.ProductInfo;

public interface ProductDAO {

	public PaginationResult<ProductInfo> getAllProductInfos(int page, int maxResult, String likeName);

	public Product getProductByCode(String code);	

	public ProductInfo getProductInfoByCode(String code);
	
	public void saveProductInfo(ProductInfo productInfo);
}