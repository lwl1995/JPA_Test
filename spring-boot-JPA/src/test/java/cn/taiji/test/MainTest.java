package cn.taiji.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

public class MainTest {
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void findId() {
		Customer one = customerService.findOne(5);
		System.out.println(one);
		
	}
}
