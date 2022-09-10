package com.shoppingcart.dao;

import com.shoppingcart.entity.Account;

public interface AccountDAO {

	public Account getAccountByUserName(String userName);

}