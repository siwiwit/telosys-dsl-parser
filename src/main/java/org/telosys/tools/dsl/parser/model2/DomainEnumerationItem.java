package org.telosys.tools.dsl.parser.model2;

public class DomainEnumerationItem<T> {

	private final String name ;
	
	private final T value ;

	public DomainEnumerationItem(String name, T value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public T getValue() {
		return value;
	}
	
	
}
