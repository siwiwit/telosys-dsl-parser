package org.telosys.tools.dsl.parser.model2;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.telosys.tools.dsl.parser.EntityParserException;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public abstract class DomainEnumeration<T> extends DomainType {

	public enum TypeEnum {
		INTEGER, STRING, DECIMAL
	};
//    private final Class<?>  itemClass;
    private final Map<String,DomainEnumerationItem<T>> items;

//    public DomainEnumeration(String name, Class<?> itemClass) {
    public DomainEnumeration(String name) {
    	super(name, DomainTypeNature.ENUMERATION);
//    	this.itemClass = itemClass ;
        this.items = new Hashtable<String,DomainEnumerationItem<T>>();
    }

//    protected Class<?> getType() {
//    	return itemClass ;
//    }
    
    public int getNumberOfItems() {
    	return items.size() ;
    }
    
    public void addItem(String name, T value) {
    	DomainEnumerationItem<T> item = new DomainEnumerationItem<T>(name, value);
    	addItem(item);
    }
    
    public void addItem(DomainEnumerationItem<T> item) {
    	if ( items.get(item.getName()) != null ) {
    		throw new EntityParserException("Item '" + item.getName() + "' already defined in enum '" + this.getName() + "'");
    	}
        items.put(item.getName(), item);
    }

    public DomainEnumerationItem<T> getItem(String name) {
    	return items.get(name);
    }

    public T getItemValue(String name) {
    	DomainEnumerationItem<T> item = items.get(name);
    	return ( item != null ? item.getValue() : null ) ;
    }

    public List<DomainEnumerationItem<T>> getItems() {
    	List<DomainEnumerationItem<T>> list = new LinkedList<DomainEnumerationItem<T>>(items.values());
        return list;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		DomainEnumeration<T> other = (DomainEnumeration<T>) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

}
