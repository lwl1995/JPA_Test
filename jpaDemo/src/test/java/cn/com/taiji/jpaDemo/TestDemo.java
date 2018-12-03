package cn.com.taiji.jpaDemo;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories;

import cn.com.taiji.jpaDemo.beanwork.Category;
import cn.com.taiji.jpaDemo.beanwork.Customer;
import cn.com.taiji.jpaDemo.beanwork.Item;
import cn.com.taiji.jpaDemo.beanwork.Order;

public class TestDemo {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;
	
	@Before
	public void before() {
		
		entityManagerFactory = Persistence.createEntityManagerFactory("jpaDemo");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}
	
	
	@After
	public void destroy(){
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	
	/*多对一*/
	//保存
	@Test
	public void testManyToOnePerson() {
		
		Customer customer = new Customer();
		customer.setAge(12);
		customer.setEmail("tom@guigu.com");
		customer.setLastName("Tony");
		customer.setBirth(new Date());
		customer.setCreatetime(new Date());
		
		
		Order order1 = new Order();
		order1.setOrderName("order1");
		Order order2 = new Order();
		order2.setOrderName("order2");
		
		//设置关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		//执行保存工作(最好先保存一的一端。在保存多的一端)
		entityManager.persist(customer);
		entityManager.persist(order1);
		entityManager.persist(order2);		
	}
	
	//查询
	@Test
	public void testManyToOneFind() {
		Order order = entityManager.find(Order.class, 1);
		System.out.println(order.getOrderName());
		System.out.println(order.getCustomer().getLastName());
		
	} 
	
	
	//删除
	@Test
	public void testManyToOneRemove() {
		Order order = entityManager.find(Order.class, 1);
		entityManager.remove(order);
		
	}
	
	//修改
	@Test
	public void testManyToOneUpdate(){
		Order order = entityManager.find(Order.class, 2);
		order.getCustomer().setLastName("FFF");
	}
	
	
	
	/*多对多*/
	/*保存*/
	@Test
	public void ManyToMany() {
		
		Item i1 = new Item();
		i1.setItemName("i1");
		
		Item i2 = new Item();
		i2.setItemName("i2");
		
		Category c1 = new Category();
		c1.setCategoryName("c1");
		
		Category c2 = new Category();
		c2.setCategoryName("c2");
		
		
		//设置关联关系
		i1.getCategories().add(c1);
		i1.getCategories().add(c2);
		
		i2.getCategories().add(c1);
		i2.getCategories().add(c2);
		
		c1.getItems().add(i1);
		c1.getItems().add(i2);
		
		c2.getItems().add(i1);
		c2.getItems().add(i2);
		
		entityManager.persist(i1);
		entityManager.persist(i2);
		entityManager.persist(c1);
		entityManager.persist(c2);
	}
	
	//查询
	@Test
	public void testManyToManyFind() {
		Item find = entityManager.find(Item.class, 1);
		System.out.println(find.getItemName());
		System.out.println(find.getCategories().size());
	}
	
	
	
	
}
