/**
 * 
 */
package com.marcestarlet.booksearcher.rest.model.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcestarlet.booksearcher.rest.model.adapter.MongoBookAdapter;
import com.marcestarlet.booksearcher.rest.model.entity.Book;
import com.mongodb.Block;
import com.mongodb.Function;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * MongoBookDAOImp implements a DAO for a Book using MongoDB driver
 * @author MarceStarlet
 *
 * mongodb-basics
 */
public class MongoBookDAOImp implements BookDAO{
	
	private Logger log = LoggerFactory.getLogger( MongoBookDAOImp.class );
	
	private static final String BOOKS_COLLECTION = "books";
	private static final String TITLE = "title";
	private static final String EDITORIAL = "editorial";
	private static final String DESCRIPTION = "description";
	private static final String CLASSIFICATION = "classification";
	private static final String AUTHOR = "author";
	
	
	private MongoCollection<Document> asyncBooksCollection;
	private com.mongodb.client.MongoCollection<Document> syncBooksCollection;
	
	private MongoBookAdapter bookAdapter;
	
	public MongoBookDAOImp() {
		asyncBooksCollection = MongoDAOFactory.asyncDB.getCollection( BOOKS_COLLECTION );
		syncBooksCollection = MongoDAOFactory.syncDB.getCollection( BOOKS_COLLECTION );
		bookAdapter = new MongoBookAdapter();
	}

	/* (non-Javadoc)
	 * @see com.marcestarlet.booksearcher.rest.model.dao.BookDAO#create(com.marcestarlet.booksearcher.rest.model.entity.Book)
	 */
	@Override
	public void create( Book b ) {
		
		log.info("In create method");
		
		asyncBooksCollection.insertOne( bookAdapter.toDocument( b ),
								 ( final Void result, final Throwable t ) -> {
									if( null == t ) {
				                       	log.info( "Document inserted!" );
			                       	}else {
			                    		log.error( "An error has occurred while inserting one document: {}", t );
			                    	}
								 });
		
	}

	/* (non-Javadoc)
	 * @see com.marcestarlet.booksearcher.rest.model.dao.BookDAO#update(com.marcestarlet.booksearcher.rest.model.entity.Book)
	 */
	@Override
	public void update(Book b) {
		asyncBooksCollection.replaceOne(
									eq( "isbn", b.getIsbn() ), 
									bookAdapter.toDocument( b ) , 
									( final UpdateResult result, final Throwable t ) -> {
				                        if( null == t ) {
				                       		log.info( " Document modified, count: {} ", result.getModifiedCount() );
				                       	}else {
				                    		log.error( "An error has occurred while updating: {}", t );
				                    	}
				                    });
		
	}

	/* (non-Javadoc)
	 * @see com.marcestarlet.booksearcher.rest.model.dao.BookDAO#delete(java.lang.String)
	 */
	@Override
	public void delete( String id ) {
		asyncBooksCollection.deleteOne(  eq( "isbn", id ),  
									( final DeleteResult result, final Throwable t ) -> {
				                    	if( null == t ) {
				                    		log.info( " Document deleted, count: {} ",result.getDeletedCount() );
				                    	}else {
				                    		log.error( "An error has occurred while deleting: {}", t );
				                    	}
					                });
		
	}

	/* (non-Javadoc)
	 * @see com.marcestarlet.booksearcher.rest.model.dao.BookDAO#findById(java.lang.String)
	 */
	@Override
	public Book findById( String id ) {
		
		Book book = syncBooksCollection.find( eq( "isbn", id ) )
						               .map( new Function<Document, Book>() {
						            	   // map from Document to Book
						            	   @Override
						            	   public Book apply( Document doc ) {
						            		   return bookAdapter.toPOJO( doc );
						            	   }
						               })
						               .first();

		return book;
	}

	/* (non-Javadoc)
	 * @see com.marcestarlet.booksearcher.rest.model.dao.BookDAO#findAll()
	 */
	@Override
	public List<Book> findAll() {
		List<Book> allBooks = new ArrayList<>();
		
		syncBooksCollection.find()
							.forEach( (Block<Document>) doc -> {
								allBooks.add( bookAdapter.toPOJO( doc ));
							});
		
		return allBooks;
	}

	/* (non-Javadoc)
	 * @see com.marcestarlet.booksearcher.rest.model.dao.BookDAO#findByFilters(java.lang.Object[])
	 */
	@Override
	public List<Book> findByFilters( String filter ) {
		
		/*
		 * title {title: "book title"}
		 * description {description: "book description"}
		 * editorial {editorial: "editorial"}
		 * classification {classification: "book classification"}
		 * author {author: "book author"}
		 */
		List<Book> books = new ArrayList<>();
		
		String[] splittedFilter = filter.split(":");
		String value = splittedFilter[1];
		filter = splittedFilter[0].toLowerCase();
		
		log.info("filter {} - value {}", filter, value);
		 
		if (filter.equals(TITLE)) {
			books = filterByTitle(value);
		} else if (filter.equals(DESCRIPTION)) {
			books = filterByDescription(value);
		} else if (filter.equals(EDITORIAL)) {
			books = filterByEditorial(value);
		} else if (filter.equals(CLASSIFICATION)) {
			books = filterByClassification(value);
		} else if (filter.equals(AUTHOR)) {
			books = filterByAuthor(value);
		} else {
			books = null;
			log.warn("No filter found, returned null books");
		}
		
		return books;
	}
	
	private List<Book> filterByTitle( String value ){
		
		List<Book> books = new ArrayList<>();
		
		syncBooksCollection.find( 
								regex( "title", value, "i" ) 
							)
							.forEach( (Block<Document>) doc -> {
								books.add( bookAdapter.toPOJO( doc ));
							});
		
		return books;
	}
	
	private List<Book> filterByDescription( String value ){
		List<Book> books = new ArrayList<>();
		
		syncBooksCollection.find( 
								regex( "description", value, "i" ) 
							)
							.forEach( (Block<Document>) doc -> {
								books.add( bookAdapter.toPOJO( doc ));
							});
		
		return books;
	}
	
	private List<Book> filterByEditorial( String value ){
		List<Book> books = new ArrayList<>();
		
		syncBooksCollection.find( 
								regex( "editorial", value, "i" ) 
							)
							.forEach( (Block<Document>) doc -> {
								books.add( bookAdapter.toPOJO( doc ));
							});
		
		return books;
	}
	
	private List<Book> filterByClassification( String value ){
		List<Book> books = new ArrayList<>();
		
		syncBooksCollection.find( 
								regex( "classifications", value, "i" ) 
							)
							.forEach( (Block<Document>) doc -> {
								books.add( bookAdapter.toPOJO( doc ));
							});
		return books;
	}
	
	private List<Book> filterByAuthor( String value ){
		List<Book> books = new ArrayList<>();
		
		syncBooksCollection.find( 
								regex( "authors.name", value, "i" ) 
							)
							.forEach( (Block<Document>) doc -> {
								books.add( bookAdapter.toPOJO( doc ));
							});
		return books;
	}

}
