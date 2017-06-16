/**
 * 
 */
package com.marcestarlet.booksearcher.rest.model.dao;

import java.util.List;

import com.marcestarlet.booksearcher.rest.model.entity.Book;

/**
 * BookDAO defines how the operations for a Book will be
 * @author MarceStarlet
 *
 * mongodb-basics
 */
public interface BookDAO extends DAO<Book> {
	
	/**
	 * Creates a new book into the data store
	 * @param b Book to be created
	 */
	public void create( Book b );
	
	/**
	 * Updates an existent book into the data store
	 * @param b Book to be updated
	 */
	public void update( Book b );
	
	/**
	 * Deletes an existent book from the data store
	 * @param id ID of the book to be deleted
	 */
	public void delete( final String id );
	
	/**
	 * Finds a book by the ID
	 * @param id ID of the book
	 * @return Book found
	 */
	public Book findById( final String id );
	
	/**
	 * Finds all the books from the data store
	 * @return All the existent books
	 */
	public List<Book> findAll();
	
	/**
	 * Finds all the corresponding books that matches the
	 * filters passed through
	 * @param filters Array of filters
	 * @return All the books that matched
	 */
	public List<Book> findByFilters( String filter );

}
