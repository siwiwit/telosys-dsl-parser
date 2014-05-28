package org.telosys.tools.dsl.parser.model;

public class FieldEnum<T> {

	private String name;
	
	private T value;

	public FieldEnum(String name, T value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	
}
