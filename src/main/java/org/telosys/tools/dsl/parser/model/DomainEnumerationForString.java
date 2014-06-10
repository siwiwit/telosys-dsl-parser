package org.telosys.tools.dsl.parser.model;

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
        super.addItem(item);
    }

    public DomainEnumerationItem<String> getItem(String name) {
    	return super.getItem(name);
    }

    public String getItemValue(String name) {
    	return super.getItemValue(name);
    }

    public List<DomainEnumerationItem<String>> getItems() {
        return super.getItems();
    }

}
