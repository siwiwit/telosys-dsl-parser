package org.telosys.tools.dsl.parser.model2;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public class DomainEnumerationForInteger extends DomainEnumeration<BigInteger> {

    public DomainEnumerationForInteger(String name) {
    	super(name);
    }

    public void addItem( String name, BigInteger value) {
        super.addItem(name, value);
    }

    public void addItem(DomainEnumerationItem<BigInteger> item) {
        super.addItem(item);
    }

    public DomainEnumerationItem<BigInteger> getItem(String name) {
    	return super.getItem(name);
    }

    public BigInteger getItemValue(String name) {
    	return super.getItemValue(name);
    }

    public List<DomainEnumerationItem<BigInteger>> getItems() {
        return super.getItems();
    }

}
