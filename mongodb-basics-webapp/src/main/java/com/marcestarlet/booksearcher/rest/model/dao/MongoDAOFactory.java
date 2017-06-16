/**
 * 
 */
package com.marcestarlet.booksearcher.rest.model.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientURI;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoDatabase;

/**
 * MongoDAOFactory creates DAOs for MongoDB
 * @author MarceStarlet
 *
 * mongodb-basics
 */
public class MongoDAOFactory {

	private static final String MONGO_URI = "mongodb://localhost";
	private static final String MONGO_DATABASE = "booksearcherdb";
	
	private static MongoDAOFactory factory = new MongoDAOFactory();;

	@SuppressWarnings("rawtypes")
	private static Map<String, Supplier<DAO>> map = new HashMap<>();
	
	public static final MongoClient asyncMongoClient;
    public static final MongoDatabase asyncDB;
    
    public static final com.mongodb.MongoClient syncMongoClient;
    public static final com.mongodb.client.MongoDatabase syncDB;

	static {
		map.put( "book", MongoBookDAOImp::new );
		
		asyncMongoClient = MongoClients.create( new ConnectionString( MONGO_URI ) );
		asyncDB = asyncMongoClient.getDatabase( MONGO_DATABASE );
		
		syncMongoClient = new com.mongodb.MongoClient ( new MongoClientURI( MONGO_URI ) );
		syncDB = syncMongoClient.getDatabase( MONGO_DATABASE );
	}
	
	private MongoDAOFactory() {
	}
	
	/**
	 * get a MongoDAOFactory instance
	 * @return MongoDAOFactory
	 */
	public static MongoDAOFactory getInstance(){
		if(null != factory ){
			return factory;
		}else{
			return new MongoDAOFactory();
		}
	}
	
	/**
	 * get Mongo DAO
	 * @param daoType
	 * @return DAO<"Type">
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("rawtypes")
	public DAO getDAO( String daoType ) throws IllegalArgumentException{
		
		Supplier<DAO> dao = map.get( daoType );
		
		if( null != dao ){
			return dao.get();
		}else{
			throw new IllegalArgumentException("No such dao type \"" + daoType + "\" found");
		}
	}
}
