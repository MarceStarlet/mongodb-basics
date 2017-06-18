/**
 * 
 */
package com.marcestarlet.booksearcher.rest.service;

import java.util.List;

import com.marcestarlet.booksearcher.rest.model.entity.Book;

/**
 * BookService defines which operations will be exposed 
 * as services.
 * @author MarceStarlet
 *
 * mongodb-basics
 */
public interface BookService {

	/**
	 * Creates a new Book
	 * @param book
	 */
	public void createBook( Book book );
	
	/**
	 * Updates an existent Book
	 * @param book
	 */
	public void updateBook( Book book );
	
	/**
	 * Delete and existent Book by its ISBN identifier
	 * @param isbn
	 */
	public void deleteBook( String isbn );
	
	/**
	 * Finds all Books stored
	 * @return all books found
	 */
	public List<Book> findAllBooks();
	
	/**
	 * Finds a Book by its ISBN
	 * @param isbn
	 * @return book that matches de ISBN
	 */
	public Book findBookByISBN( String isbn );
	
	/**
	 * Find all Books that matches the filter
	 * Possible filters:
	 * 	title
	 *  description
	 *  edition
	 *  editorial
	 *  classification
	 *  author
	 * @param filter
	 * @return all books that matches the filter
	 */
	public List<Book> findBookByFilter( String filter );
	
}
