package org.telosys.tools.dsl.parser.model;

import java.math.BigDecimal;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @version 1.0
 * @date 2014-05-13
 */
public class DomainEnumerationForDecimal extends DomainEnumeration<BigDecimal> {

    public DomainEnumerationForDecimal(String name) {
        super(name);
    }

	@Override
	public String toString() {
		return getName() +" : decimal "+ super.toString();
	}
    
    
}
