/**
 * 
 */
package com.marcestarlet.booksearcher.rest.model.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcestarlet.booksearcher.rest.model.entity.Author;
import com.marcestarlet.booksearcher.rest.model.entity.Book;

/**
 * @author MarceStarlet
 *
 * mongodb-basics
 */
public class MongoBookDAOImpTest {
	
	private Logger log = LoggerFactory.getLogger( MongoBookDAOImpTest.class );
	
	private static final String BOOK_DAO = "book";
	private BookDAO bookDAO = null;
	
	@Before
	public void init() {
		bookDAO = (BookDAO) MongoDAOFactory.getInstance().getDAO( BOOK_DAO );
	}

	
	public void testCreate() throws Exception {
		
		List<Author> authors = new ArrayList<>(4);
		authors.add(new Author("Author Test 1","Author Test biography lorem ipsum"));
		authors.add(new Author("Author Test 2","Author Test biography lorem ipsum"));
		authors.add(new Author("Author Test 3","Author Test biography lorem ipsum"));
		authors.add(new Author("Author Test 4","Author Test biography lorem ipsum"));
		
		Book book = new Book();
		book.setIsbn("1234567890");
		book.setTitle("Book Test");
		book.setDescription("Book Test description lorem ipsum");
		book.setEdition(2015);
		book.setEditorial("Editorial Test");
		book.setClassifications(Arrays.asList("Catergory Test 1", "Category Test 2"));
		book.setAuthors(authors);
		
		bookDAO.create( book );
		
		Thread.sleep(35000);
	}
	
	
	public void testFindById() throws Exception {
		
		Book book = bookDAO.findById( "1234567890" );
		log.info("Book found: {}", book);
		
	}
	
	
	public void testFindAll() throws Exception {
		
		List<Book> books = bookDAO.findAll();
		for( Book b : books) {
			log.info("Book {}: {},", b.getIsbn(), b);
		}
	}
	
	
	public void testFindByFilters() throws Exception {
		
		List<Book> books = bookDAO.findByFilters("title:test");
		for( Book b : books) {
			log.info("Book {}: {},", b.getIsbn(), b);
		}
	}
	
	
	public void testUpdate() throws Exception {
		
		List<Author> authors = new ArrayList<>(4);
		authors.add(new Author("Update Author Test 1","Update Author Test biography lorem ipsum"));
		authors.add(new Author("Update Author Test 2","Update Author Test biography lorem ipsum"));
		authors.add(new Author("Update Author Test 3","Update Author Test biography lorem ipsum"));
		authors.add(new Author("Update Author Test 4","Update Author Test biography lorem ipsum"));
		
		Book book = new Book();
		book.setIsbn("1234567890");
		book.setTitle("Update Book Test");
		book.setDescription("Update Book Test description lorem ipsum");
		book.setEdition(2015);
		book.setEditorial("Update Editorial Test");
		book.setClassifications(Arrays.asList("Update Catergory Test 1", "Update Category Test 2"));
		book.setAuthors(authors);
		
		bookDAO.update( book );
		
		Thread.sleep(3000);
	}
	
	@Test
	public void testDelete() throws Exception {
		
		bookDAO.delete( "1234567890" );
		
		Thread.sleep(3000);
	} 

}
