/**
 * 
 */
package com.marcestarlet.booksearcher.rest.model.dao;

import java.io.Serializable;
import java.util.List;

/**
 * DAO defines how the Data Access will be
 * @author MarceStarlet
 *
 * mongodb-basics
 */
public interface DAO <D extends Serializable>{
	
	/**
	 * Creates a new entity into the data store
	 * @param e Entity to be created
	 */
	public void create( D e );
	
	/**
	 * Updates an existent entity into the data store
	 * @param e Entity to be updated
	 */
	public void update( D e );
	
	/**
	 * Deletes an existent entity from the data store
	 * @param id ID of the entity to be deleted
	 */
	public void delete( final String id );
	
	/**
	 * Finds an entity by the ID
	 * @param id ID of the entity
	 * @return Entity found
	 */
	public D findById( final String id );
	
	/**
	 * Finds all the entities from the data store
	 * @return All the existent entities
	 */
	public List<D> findAll();
	
	/**
	 * Finds all the corresponding entities that matches the
	 * filters passed through
	 * @param filters Array of filters
	 * @return All the entities that matched
	 */
	public List<D> findByFilters( String filter );
}
