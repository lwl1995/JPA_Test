package cn.taiji.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.domain.Dept;
import com.example.demo.service.DeptService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*import cn.com.taiji.domain.Dept;
import cn.com.taiji.domain.User;
import cn.com.taiji.service.DeptService;
import cn.com.taiji.service.UserService;*/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {

	private static final Logger log = LoggerFactory.getLogger(AppTest.class);

	@Inject
	DeptService deptService;

	@Inject
	private ObjectMapper objectMapper;

	@Ignore
	@Test
	public void test1() {
		for (int i = 0; i < 9; i++) {
			Dept dept = new Dept();
			dept.setDeptName("机构" + i);
			dept.setFlag(1);
			deptService.saveDept(dept);
		}

		List<Dept> list = deptService.findAllDepts();
		System.out.println(list.size());
	}

	/*@Ignore
	@Test
	public void test2() {
		for (int i = 0; i < 9; i++) {
			User user = new User();
			user.setUserName("用户" + i);
			user.setFlag(1);
			userService.saveUser(user);
		}

		List<User> list = userService.findAllUsers();
		System.out.println(list.size());
	}*/

	/*@Ignore
	@Test
	public void test3() {
		User user = userService.findByName("asd");
		// User user = userService.findUserById(1);
		// Dept dept = deptService.findDeptById(1);
		// List<Dept> depts = new ArrayList<Dept>();
		// depts.add(dept);
		// System.out.println(depts.size());
		// user.setDepts(depts);
		// user.getDepts().remove(0);
		userService.saveUser(user);
		System.out.println(user.getName() + "----user");
	}*/

	@Ignore
	@Test
	public void test4() {
		Dept dept = deptService.findDeptById(1);
		System.out.println(dept + "----dept");
	}

	@PersistenceContext
	EntityManager em;

	/*@Ignore
	@Test
	public void test5() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> c = cb.createQuery(User.class);
		Root<User> user = c.from(User.class);

		CriteriaQuery query = c.select(user).where(cb.equal(user.get("name"), "asd"));

		TypedQuery query1 = em.createQuery(query);

		List<User> list = query1.getResultList();

		System.out.println(query1.getResultList());

	}*/

	@Test
	public void testPage() {
		String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"deptName\", \"value\":\"机构8\"}]}}";
		Map searchParameters = new HashMap();
		try {
			searchParameters = objectMapper.readValue(map, new TypeReference<Map>() {
			});
		} catch (JsonParseException e) {
			log.error("JsonParseException{}:", e.getMessage());
		} catch (JsonMappingException e) {
			log.error("JsonMappingException{}:", e.getMessage());
		} catch (IOException e) {
			log.error("IOException{}:", e.getMessage());
		}

		Map mapDept = deptService.getPage(searchParameters);

		System.out.println(mapDept.get("total"));

		System.out.println(mapDept.get("depts"));
	}

	 



}
