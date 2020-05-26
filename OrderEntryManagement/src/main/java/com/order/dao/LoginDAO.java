package com.order.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO  {
	@PersistenceContext
	EntityManager entityManager;
	
	public long checkUser(String username) {
		Query query=entityManager.createNativeQuery
				("select count(*) from users where username=?");
		query.setParameter(1, username);
		List<BigInteger> l=query.getResultList();
		return  l.get(0).longValue();
		
		
	}
	public long checkPassword(String username,String password ) {
		Query query=entityManager.createNativeQuery
				("select count(*) from users where username=? and password=?");
		query.setParameter(1, username);
		query.setParameter(2, password);
		List<BigInteger> l=query.getResultList();
		return  l.get(0).longValue();
		
		
	}

}
