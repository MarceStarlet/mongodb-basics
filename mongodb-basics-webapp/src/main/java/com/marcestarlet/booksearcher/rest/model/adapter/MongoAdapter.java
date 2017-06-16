/**
 * 
 */
package com.marcestarlet.booksearcher.rest.model.adapter;

import java.io.Serializable;

import org.bson.Document;

/**
 * MongoDB Adapter interface
 * @author MarceStarlet
 *
 * mongodb-basics
 */
public interface MongoAdapter<M extends Serializable> {
	
	/**
	 * Maps a POJO entity to a MongoDB Document
	 * @param pojo
	 * @return Document
	 */
	public Document toDocument( M pojo );
	
	/**
	 * Maps a MongoDB Document to a POJO entity
	 * @param doc
	 * @return
	 */
	public M toPOJO( Document doc );
}
