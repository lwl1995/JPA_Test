package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Dept;





/**
 * 
 * 类名称：DeptRepository 机构单位Repository   
 * 类描述：   
 * 创建人：wanghw
 * 创建时间：2018年2月5日 下午8:40:09 
 * @version
 */
public interface DeptRepository extends JpaRepository<Dept, Integer>,JpaSpecificationExecutor<Dept>{
	
	/**
	 * 查询机构树
	 * @return
	 */
	@Query("select d from Dept d left join fetch d.children")
	List<Dept> findDeptTree();

	/**
	 * 查询树根
	 * @return
	 */
	@Query("select d from Dept d where d.parent is null and d.flag=1   order by d.deptIndex")
	List<Dept> findRoots();
	
	/**
	 * 标记为删除
	 * @param id
	 */
	@Modifying
	@Query("update Dept d set d.flag=0 where d.id=:id")
	void updateFlag(@Param("id") String id);
	
	/**
	 * 查询所有（未标记为删除的）
	 * @return
	 */
	@Query("select d from Dept d where d.flag=1")
	List<Dept> findAllDepts();
 
 
	/**
	 * 
	 * @Description:  查询树根
	 * @param deptName
	 * @param id
	 * @return List<Dept>  
	 * @throws
	 * @author wanghw
	 * @date 2017年4月25日
	 */
	List<Dept> findByFlagAndParentIsNullOrderByDeptIndexAsc(@Param("flag") int i);
	
	@Query("select d from Dept d where d.id=:id")
	Dept findById(@Param("id") Integer id);	

}
