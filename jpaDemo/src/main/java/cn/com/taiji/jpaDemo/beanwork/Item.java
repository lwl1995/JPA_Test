package cn.com.taiji.jpaDemo.beanwork;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.util.comparator.InvertibleComparator;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Entity
@Table(name="JPA_ITEM")
public class Item {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="ITEM_NAME")
	private String itemName;
	
	
	// JoinTable(name="IEEM_CATEGORY"):中间表的名字      @JoinColumn(name="ITEM_ID"):外键的名称     referencedColumnName="ID"(可以省):  本类的id           @JoinColumn(name="CATEGORY_ID"):另一个对象(categories)的主键   referencedColumnName="ID"(可以省):另一个对象(categories)的id   
	@JoinTable(name="ITEM_CATEGORY",joinColumns= {@JoinColumn(name="ITEM_ID",referencedColumnName="ID")},inverseJoinColumns= {@JoinColumn(name="CATEGORY_ID",referencedColumnName="ID")})
	@ManyToMany
	private Set<Category> categories = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	

}
