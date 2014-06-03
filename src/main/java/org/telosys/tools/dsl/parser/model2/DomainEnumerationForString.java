package org.telosys.tools.dsl.parser.model2;

import java.util.List;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public class DomainEnumerationForString extends DomainEnumeration<String> {

    public DomainEnumerationForString(String name) {
    	super(name);
    }

    public void addItem( String name, String value) {
        super.addItem(name, value);
    }

    public void addItem(DomainEnumerationItem<String> item) {
//    	if ( items.get(item.getName()) != null ) {
//    		throw new EntityParserException("Item '" + item.getName() + "' already defined in enum '" + this.getName() + "'");
//    	}
//        items.put(item.getName(), item);
        super.addItem(item);
    }

    public DomainEnumerationItem<String> getItem(String name) {
//    	return items.get(name);
    	return super.getItem(name);
    }

    public String getItemValue(String name) {
//    	DomainEnumerationItem<T> item = items.get(name);
//    	return ( item != null ? item.getValue() : null ) ;
    	return super.getItemValue(name);
    }

    public List<DomainEnumerationItem<String>> getItems() {
//    	List<DomainEnumerationItem<T>> list = new LinkedList<DomainEnumerationItem<T>>(items.values());
//        return list;
        return super.getItems();
    }

}
