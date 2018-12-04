package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Person;



public interface PersonRepository extends JpaRepository<Person, Integer>,JpaSpecificationExecutor<Person>{

	//Person中查询最大的id
	@Query("select p from Person p where p.id=(select max(p.id) from Person p2)")
	Person getMaxIdPerson();
	
	//传参第一种
	@Query("select p from Person p where p.lastName=?1 and p.email=?2")
	List<Person> findParam(String lastName,String email);	
	
	
	//传参第二种
	@Query("select p from Person p where p.lastName= :lastName and p.email=:email")
	List<Person> findParam2(@Param("lastName") String lastName,@Param("email") String email);	
	
	//模糊查询
	@Query("select p from Person p where p.lastName like %:lastName% or p.email like %:email%")
	List<Person> testLike(@Param("lastName") String lastName,@Param("email") String email);
	
	//更新(jpql不支持insert),update和delete操作必须用@Modifying修饰，使用事务@Transactional，在service层标注
	@Modifying
	@Query("update Person p set p.email=:email where id =:id")
	void updateParam(@Param("id") Integer id,@Param("email") String email);
	
	
	//删除
	@Modifying
	@Query("delete from Person p where p.email=:email and id=:id")
	void deleteParam(@Param("email") String email,@Param("id") Integer id);
	
	
}
 