package cn.com.taiji.jpaDemo;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.crypto.Data;

import cn.com.taiji.jpaDemo.beanwork.Customer;

public class TestCustomer {

	public static void main(String[] args) {
		
		//1.创建EntityManagerFactory
				
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaDemo");
				
				
				//2.创建EntityManager
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				
				
				//3.开启事务
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				
				
				//4进行持久化操作
				Customer customer = new Customer();
				customer.setAge(12);
				customer.setEmail("tom@guigu.com");
				customer.setLastName("Tony");
				customer.setBirth(new Date());
				customer.setCreatetime(new Date());
				
				//相当于save
				entityManager.persist(customer);
				
				//5.提交事务
				transaction.commit();
				
				
				//6.关闭EntityManager
				entityManager.close();
				
				
				//7.关闭EntityManagerFactory
				entityManagerFactory.close();
				
			}

	}


