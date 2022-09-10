package com.shoppingcart.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingcart.dao.AccountDAO;
import com.shoppingcart.entity.Account;


@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Account getAccountByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT ACC FROM Account ACC WHERE ACC.userName = :USERNAME";
		Query<Account> query = session.createQuery(hql);
		query.setParameter("USERNAME", userName);
		Account account = (Account) query.uniqueResult();
		return account;
	}

}