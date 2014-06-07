package org.telosys.tools.dsl.parser.utils;

import java.util.HashMap;
import java.util.Map;
/**
 * All the properties of the parser are in this class. 
 * To use this class : 
 * 			TelosysDSLProperties.getProperties().getProperty("property");
 * @author Jonhathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-06-07
 * @version 1.0
 *
 */
public class TelosysDSLProperties {

	/**
	 * All the properties
	 */
	private Map<String,String> properties;
	
	/**
	 * the singleton of this class
	 */
	private static TelosysDSLProperties instance;
	
	/**
	 * 
	 * @return all the properties
	 */
	public static TelosysDSLProperties getProperties(){
		if(instance == null){
			instance = new TelosysDSLProperties();
		}
		return instance;
	}
	/**
	 * private constructor which initialize all the properties
	 */
	private TelosysDSLProperties(){
		this.properties = new HashMap<String, String>();
		this.addValues();
	}
	/**
	 * Init all the properties
	 */
	private void addValues(){
		properties.put("start_comment", "//");
		properties.put("annotations", "Id,NotNull,Min#,Max#,SizeMin#,SizeMax#,Past,Future");
	}
	
	/**
	 * 
	 * @param key the key of the property
	 * @return the value of the property or null if there isn't a property
	 */
	public String getProperty(String key){
		return properties.get(key);
	}

}
