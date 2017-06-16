/**
 * 
 */
package com.marcestarlet.booksearcher.rest.model.entity;

import java.io.Serializable;

/**
 * POJO that represents an Author
 * @author MarceStarlet
 *
 * mongodb-basics
 */
public class Author implements Serializable {

	private static final long serialVersionUID = -2871137830031769598L;
	
	private String name;
	private String biography;
	
	public Author() {
		
	}
	
	public Author(String name, String biography) {
		this.name = name;
		this.biography = biography;
	}

	/**
	 * @return the firstName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the biography
	 */
	public String getBiography() {
		return biography;
	}

	/**
	 * @param biography the biography to set
	 */
	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	
}
