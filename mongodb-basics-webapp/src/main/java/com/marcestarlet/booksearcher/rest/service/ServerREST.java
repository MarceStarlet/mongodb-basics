/**
 * 
 */
package com.marcestarlet.booksearcher.rest.service;

import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.marcestarlet.booksearcher.rest.model.entity.Book;

/**
 * ServerREST is the main class that initiates a jetty
 * server in http://localhost:4567 and exposes a REST API
 * of a book searcher example using MongoDB
 * @author MarceStarlet
 *
 * mongodb-basics
 */
public class ServerREST {
	
	// jackson mapper object
	private static ObjectMapper mapper = new ObjectMapper();

	private static BookServiceImp service = new BookServiceImp();
	
	/**
	 * dataToJson transforms a POJO from our data model to JSON
	 * @param data POJO
	 * @return a json string
	 */
	public static String dataToJson( Object data ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }
	
	public static void main(String[] args) {
    	
		Logger log = LoggerFactory.getLogger("com.marcestarlet.booksearcher.rest.service.ServerREST");
		
		/*
		 * Book Searcher REST API Paths:
		 * /booksearcher
		 *   /books
		 *     /create
		 *     /update
		 *     /remove
		 *     /find
		 *       /all
		 *       /isbn
		 *       /filter
		 */
		
    	path("/booksearcher", () -> {
    		before("/*", (q, a) -> log.info("Received api call"));
    		path("/books", () -> {
    			post("/create", (request, response) -> {
    				Book book = mapper.readValue( request.body(), Book.class );
    				
    				if( null != book) {
    					service.createBook( book );
    					response.status(200); //OK
    					return book.getIsbn();
    				}else {
    					response.status(400); //Bad request
    					return "";
    				} 
    			});
    			put("/update", (request, response) -> {
    				Book book = mapper.readValue( request.body() , Book.class );
    				if( null != book) {
	    				service.updateBook( book );
	    				response.status(200);
	    				return book.getIsbn(); //OK
    				}else {
    					response.status(400); //Bad request
    					return "";
    				}
    			});
    			delete("/remove/:isbn", (request, response) -> {
    				String isbn = request.params(":isbn");
    				if( null != isbn && !isbn.isEmpty() ) {
	    				service.deleteBook( isbn );
	    				response.status(200); //OK
	    				return ""; 
    				}else {
    					response.status(400); //Bad request
    					return "";
    				}
    			});
    			get("/find/all", (request, response) -> {
    				List<Book> books = service.findAllBooks();
    				response.status(200);
    				response.type("application/json");
    				return dataToJson( books ); 
    			});
    			get("/find/isbn/:isbn", (request, response) -> {
    				Book book = service.findBookByISBN( request.params( ":isbn" ) );
    				response.status(200);
    				response.type("application/json");
    				return dataToJson( book ); 
    			});
    			get("/find/filter/:filter", (request, response) -> {
    				List<Book> books = service.findBookByFilter( request.params( ":filter" ) );
    				response.status(200);
    				response.type("application/json");
    				return dataToJson( books ); 
    			});
    		});
    	});
	}

}
