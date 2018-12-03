package cn.com.taiji.jpaDemo.beanwork;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="JPA_CATEGORIES")
public class Category {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String categoryName;
	
	@ManyToMany(mappedBy="categories")  //mappedBy=的是下面Item中的categories
	private Set<Item> items = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	} 
	
	

}
