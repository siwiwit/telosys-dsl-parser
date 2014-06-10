package org.telosys.tools.dsl.parser.model;

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.telosys.tools.dsl.parser.EntityParserException;

/**
 * Root class for a Domain Model built after Domain Specific Language text file parsing
 * @author L.Guerin
 *
 */
public class DomainModel {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entities == null) ? 0 : entities.hashCode());
		result = prime * result
				+ ((enumerations == null) ? 0 : enumerations.hashCode());
		result = prime * result
				+ ((modelName == null) ? 0 : modelName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainModel other = (DomainModel) obj;
		if (entities == null) {
			if (other.entities != null)
				return false;
		} else if (!entities.equals(other.entities))
			return false;
		if (enumerations == null) {
			if (other.enumerations != null)
				return false;
		} else if (!enumerations.equals(other.enumerations))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		return true;
	}

	private final String modelName ;  
	
//	private final String modelVersion ; 
//	
//	private final String modelDescription ;  
	
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
	 * Returns an entity for the given name (or null if not found)
	 * @param entityName
	 * @return
	 */
	public final DomainEntity getEntity (String entityName) {
		return entities.get(entityName);
	}
	
	/**
	 * Returns the number of entities currently defined in the model
	 * @return
	 */
	public final int getNumberOfEntities() {
		return entities.size();
	}
	
	/**
	 * Returns all the entity names (in alphabetical order)
	 * @return
	 */
	public final List<String> getEntityNames() {
		List<String> names = new LinkedList<String>( entities.keySet() ) ;
		Collections.sort(names);
		return names ;
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
	 * Returns an enumeration for the given name (or null if not found)
	 * @param enumerationName
	 * @return
	 */
	public final DomainEnumeration<?> getEnumeration (String enumerationName) {
		return enumerations.get(enumerationName);
	}
	
	/**
	 * Returns the number of enumerations currently defined in the model
	 * @return
	 */
	public final int getNumberOfEnumerations() {
		return enumerations.size();
	}

	/**
	 * Returns all the enumeration names (in alphabetical order)
	 * @return
	 */
	public final List<String> getEnumerationNames() {
		List<String> names = new LinkedList<String>( enumerations.keySet() ) ;
		Collections.sort(names);
		return names ;
	}
	
	//------------------------------------------------------------------------------------------
	// ALL
	//------------------------------------------------------------------------------------------
	/**
	 * Returns all the defined names, entities and enumerations (in alphabetical order)
	 * @return
	 */
	public final List<String> getAllNames() {
		//List<String> names = new LinkedList<String>( enumerations.keySet() ) ;
//		
//		Collections.sort(names);
//		return names ;
		// TODO
		throw new RuntimeException("NOT IMPLEMENTED");
	}
	/**
	 * Put all the fields of the entity into an entity from the list of the fields of the model which has the same name
	 * @param entity
	 */
	public void putEntity(DomainEntity entity){
		entity.copyIn(getEntity(entity.getName()));
	}

	@Override
	public String toString() {
		return "DomainModel [modelName=" + modelName + ", entities=" + entities
				+ ", enumerations=" + enumerations + "]";
	}
	
	
}
