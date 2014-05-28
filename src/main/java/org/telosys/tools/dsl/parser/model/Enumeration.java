package org.telosys.tools.dsl.parser.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Enumeration<T> implements Type {
	public enum TypeEnum {
		INTEGER, STRING, DECIMAL
	};

	private TypeEnum type;

	private String name;

	private List<FieldEnum<T>> fieldsEnum;

	public Enumeration(String name, TypeEnum type) {
		this.type = type;
		this.name = name;
		fieldsEnum = new ArrayList<FieldEnum<T>>();
	}

	public void addField(FieldEnum<T> field) {
		this.fieldsEnum.add(field);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fieldsEnum == null) ? 0 : fieldsEnum.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Enumeration<?> other = (Enumeration<?>) obj;
		if (fieldsEnum == null) {
			if (other.fieldsEnum != null)
				return false;
		} else if (!fieldsEnum.equals(other.fieldsEnum))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
}
