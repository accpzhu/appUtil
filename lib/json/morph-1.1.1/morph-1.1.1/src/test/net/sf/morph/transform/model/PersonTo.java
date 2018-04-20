package net.sf.morph.transform.model;

import java.util.List;

/**
 * Person implementation for transfer to a different tier of the application.
 * 
 * @author Matt Sgarlata
 * @since Dec 2, 2005
 */
public class PersonTo implements Person {
	private String name;
	private List children;
	private Address homeAddress;
	
	public List getChildren() {
		return children;
	}
	public void setChildren(List children) {
		this.children = children;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return name;
	}
}