package com.example.demo;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Person;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {

	@Inject
	CustomerService customerService;
	
	@Inject
	PersonService personService;
		
	@Inject
	PersonRepository personRepository;

	//查询
	@Test
	public void contextLoads() {
		
		Customer one = customerService.findOne(5);
		System.out.println(one);	
	}
	
	@Test
	//查询最大的id
	public void maxId() {
		
		Person maxIdPerson = personRepository.getMaxIdPerson();
		System.out.println(maxIdPerson);
		
		
	}
	
	//第一种传参
	@Test
	public void findParam() {
		
		List<Person> findParam = personRepository.findParam("lisi", "123");
		System.out.println(findParam);
		
		
	}
	
	//第二种传参
	@Test
	public void findParam2() {
		
		List<Person> findParam2 = personRepository.findParam2("lisi", "123");
		System.out.println(findParam2);
			
	}
	
	//模糊查询
		@Test
	public void testLike() {
			
		List<Person> findParam2 = personRepository.testLike("li", "12");
		System.out.println(findParam2);
				
		}
		
	//更新
	
	@Test
	public void updateParam() {
		
		personService.Param(1, "123456");
			
		}
	
	//保存
	@Test
	public void save() {
		Person p = new Person();
		p.setAddress("nihoa123");
		p.setLastName("你好");
		p.setId(56);
		p.setBirth(new Date());
		p.setEmail("7758");
		
		personRepository.saveAndFlush(p);
	}
	
	//删除
	@Test
	public void deleteParam() {
		
		personService.deleteParam(1,"123456");
			
		}
	
}
