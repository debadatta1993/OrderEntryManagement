package com.order.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.order.entity.Order;
import com.order.entity.OrderProduct;
import com.order.entity.Product;

@Repository
public class OrderDAO {

	@Autowired
	SessionFactory sessionFactory;

	public Product getProduct(int productID) {
		Session session = sessionFactory.openSession();
		return session.get(Product.class, productID);

	}

	public List<Order> getOrder(Date start, Date end, int userId) {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.between("orderDate", start, end));
		criteria.add(Restrictions.eq("userId", userId));
		List<Order> orders = criteria.list();
		orders.stream().forEach(s -> s.setProducts(getOrderProducts(Integer.parseInt(s.getOrderId()))));
		return orders;
	}

	public List<OrderProduct> getOrderDetails(int orderId, String Pname) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(OrderProduct.class);
		criteria.add(Restrictions.eq("orderId", orderId));
		criteria.add(Restrictions.eq("productName", Pname));
		List<OrderProduct> orders = criteria.list();
		return orders;
	}

	public List<OrderProduct> getOrderProducts(int orderId) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(OrderProduct.class);
		criteria.add(Restrictions.eq("orderId", orderId));
		return criteria.list();

	}

	public void addItem(OrderProduct orderProducts) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(orderProducts);
		session.getTransaction().commit();
	}

	public void updateItem(OrderProduct orderProducts) {
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("update OrderProduct set quantity = :quantity" + " where orderId = "
						+ ":orderId AND productName =:productName");
		query.setParameter("quantity", orderProducts.getQuantity());
		query.setParameter("orderId", orderProducts.getOrderId());
		query.setParameter("productName", orderProducts.getProductName());
		session.beginTransaction();
		int result = query.executeUpdate();
		session.getTransaction().commit();
		System.out.println(result);
	}

}
