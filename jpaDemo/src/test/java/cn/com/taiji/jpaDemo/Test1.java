package cn.com.taiji.jpaDemo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import cn.com.taiji.jpaDemo.bean.Address;
import cn.com.taiji.jpaDemo.bean.Authority;
import cn.com.taiji.jpaDemo.bean.People;
import cn.com.taiji.jpaDemo.bean.User;

public class Test1 {



	public static void main(String[] args) {
		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaDemo");

		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();

		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		Address address = new Address();
		address.setAddress("23156");
		
		/*一对一*/
		People person = new People();
		person.setName("lisi");
		person.setSex("男");
		person.setAddress(address);
		
		/*多对多
		Authority authority = new Authority();
		authority.setName("权限");
		
		Authority authority1 = new Authority();
		authority1.setName("权限1");
		
		List<Authority> list = new ArrayList<Authority>();
		list.add(authority1);
		list.add(authority);
		
		User user= new User();
		user.setUsername("wangwu");
		user.setPassword("123456");
		user.setAuthorityList(list);
		
		*/

		// 添加user到数据库，相当于hibernate的save();
		entityManager.persist(address);
		entityManager.persist(person);
		/*entityManager.persist(authority);
		entityManager.persist(authority1);
		entityManager.persist(user);*/
		

		
		// 5. 提交事务
		transaction.commit();

		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}

 

}
