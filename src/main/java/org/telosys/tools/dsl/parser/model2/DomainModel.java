package org.telosys.tools.dsl.parser.model2;

import java.util.Hashtable;

import org.telosys.tools.dsl.parser.EntityParserException;

/**
 * Root class for a Domain Model built after Domain Specific Language text file parsing
 * @author L.Guerin
 *
 */
public class DomainModel {

	private final String modelName ; // useful ??? 
	
	// NB :
	// Do not accept an entity and an enumeration with the same name /!\
	//
	private final Hashtable<String,DomainEntity> entities  = new Hashtable<String,DomainEntity>() ;
	
	private final Hashtable<String,DomainEnumeration<?>> enumerations = new Hashtable<String,DomainEnumeration<?>>() ;
	
	
	/**
	 * Constructor
	 * @param modelName  
	 */
	public DomainModel(String modelName) {
		super();
		this.modelName = modelName; // Just store the name of the folder containing the DSL files (.entity and .enum)
	}

	/**
	 * Returns the model name
	 * @return
	 */
	public final String getName() {
		return modelName ;
	}
	
	private final void checkName(String name) {
		// Do not accept an entity/enumeration with a "neutral type" name
		if ( DomainNeutralTypes.exists(name) ) {
			throw new EntityParserException("Reserved name '" + name + "' (neutral type)");
		}
		
		// Do not accept an entity and an enumeration with the same name /!\
		if ( entities.get(name) != null ) {
			throw new EntityParserException("An entity already exists with name '" + name + "'");
		}
		if ( enumerations.get(name) != null ) {
			throw new EntityParserException("An enumeration already exists with name '" + name + "'");
		}
	}

	//------------------------------------------------------------------------------------------
	// ENTITIES
	//------------------------------------------------------------------------------------------
	/**
	 * Stores a new entity <br>
	 * Supposed to be called once for each entity
	 * @param entity
	 */
	public final void addEntity (DomainEntity entity) {
		checkName(entity.getName());
		entities.put(entity.getName(), entity);
	}
	
	/**
	 * Returns an entity for the given name 
	 * @param entityName
	 * @return
	 */
	public final DomainEntity getEntity (String entityName) {
		return entities.get(entityName);
	}
	
	public int getNumberOfEntities() {
		return entities.size();
	}
	
	
	//------------------------------------------------------------------------------------------
	// ENUMERATION
	//------------------------------------------------------------------------------------------
	/**
	 * Stores a new enumeration <br>
	 * Supposed to be called once for each enumeration
	 * @param enumeration
	 */
	public final void addEnumeration (DomainEnumeration<?> enumeration) {
		checkName(enumeration.getName());
		enumerations.put(enumeration.getName(), enumeration);
	}
	
	/**
	 * Returns an enumeration for the given name 
	 * @param enumerationName
	 * @return
	 */
	public final DomainEnumeration<?> getEnumeration (String enumerationName) {
		return enumerations.get(enumerationName);
	}
	
	public int getNumberOfEnumerations() {
		return enumerations.size();
	}
}
