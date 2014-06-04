package org.telosys.tools.dsl.parser.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public class DomainEnumerationForDecimal extends DomainEnumeration<BigDecimal> {

    public DomainEnumerationForDecimal(String name) {
    	super(name);
    }

    public void addItem( String name, BigDecimal value) {
        super.addItem(name, value);
    }

    public void addItem(DomainEnumerationItem<BigDecimal> item) {
        super.addItem(item);
    }

    public DomainEnumerationItem<BigDecimal> getItem(String name) {
    	return super.getItem(name);
    }

    public BigDecimal getItemValue(String name) {
    	return super.getItemValue(name);
    }

    public List<DomainEnumerationItem<BigDecimal>> getItems() {
        return super.getItems();
    }

}
