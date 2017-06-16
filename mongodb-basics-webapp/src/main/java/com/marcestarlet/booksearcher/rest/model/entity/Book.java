/**
 * 
 */
package com.marcestarlet.booksearcher.rest.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * POJO that represents a Book
 * @author MarceStarlet
 *
 * mongodb-basics
 */
public class Book implements Serializable {
	
	private static final long serialVersionUID = -8744084147547035285L;
	
	private String isbn;
	private String title;
	private String description;
	private int    edition;
	private String editorial;
	private List<String> classifications;
	private List<Author> authors;
	
	public Book() {
		
	}
	
	public Book(String isbn, String title, String description, int edition, 
			List<String> classifications, String editorial, List<Author> authors) {
		this.isbn = isbn;
		this.title = title;
		this.description = description;
		this.edition = edition;
		this.classifications = classifications;
		this.editorial = editorial;
		this.authors = authors;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the edition
	 */
	public int getEdition() {
		return edition;
	}

	/**
	 * @param edition the edition to set
	 */
	public void setEdition(int edition) {
		this.edition = edition;
	}

	/**
	 * @return the clasifications
	 */
	public List<String> getClassifications() {
		return classifications;
	}

	/**
	 * @param clasifications the clasifications to set
	 */
	public void setClassifications(List<String> classifications) {
		this.classifications = classifications;
	}

	/**
	 * @return the editorials
	 */
	public String getEditorial() {
		return editorial;
	}

	/**
	 * @param editorials the editorials to set
	 */
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

}
