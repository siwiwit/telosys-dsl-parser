package org.telosys.tools.dsl.parser.model;

import java.util.List;


public class Enumeration<T> implements Type{
	public enum TypeEnum { INTEGER, STRING, DECIMAL};
	private TypeEnum type;
	
	private String name;
	
	private List<FieldEnum<T>> fieldsEnum;

	public Enumeration( String name,TypeEnum type) {
		this.type = type;
		this.name = name;
	}
	
	
}
