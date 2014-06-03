package org.telosys.tools.dsl.parser.model2;

import java.util.List;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public class DomainEnumerationForInteger extends DomainEnumeration<Integer> {

    public DomainEnumerationForInteger(String name) {
    	super(name);
    }

    public void addItem( String name, Integer value) {
        super.addItem(name, value);
    }

    public void addItem(DomainEnumerationItem<Integer> item) {
        super.addItem(item);
    }

    public DomainEnumerationItem<Integer> getItem(String name) {
    	return super.getItem(name);
    }

    public Integer getItemValue(String name) {
    	return super.getItemValue(name);
    }

    public List<DomainEnumerationItem<Integer>> getItems() {
        return super.getItems();
    }

}
