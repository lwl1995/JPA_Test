package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Person;
import com.example.demo.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository repository;
	
	
	
	//查询最大的id
	public Person maxId() {
		
		return repository.getMaxIdPerson();
	}
	
	//更新
	
	@Transactional
	public void Param(Integer id,String email) {
		
		repository.updateParam(id,email);
	}

	//删除
	@Transactional
	public void deleteParam(Integer id,String email) {
		
		repository.deleteParam(email,id);
	}
	
}
