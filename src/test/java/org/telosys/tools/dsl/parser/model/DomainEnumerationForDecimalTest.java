package org.telosys.tools.dsl.parser.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.telosys.tools.dsl.parser.EntityParserException;

public class DomainEnumerationForDecimalTest {

	@Test
	public void test() {
		DomainEnumerationForDecimal en = new DomainEnumerationForDecimal("Values");
		
		assertTrue ( en.isEnumeration() );
		assertFalse ( en.isEntity() );
		assertFalse ( en.isNeutralType() );
		assertTrue( en.getNumberOfItems() == 0 ) ;
		
		en.addItem("V1", new BigDecimal("10.50") );
		assertTrue( en.getNumberOfItems() == 1 ) ;
		
		en.addItem("V2", new BigDecimal("222") );
		assertTrue( en.getNumberOfItems() == 2 ) ;
		
		assertEquals( en.getItemValue("V1"), new BigDecimal( "10.50") ) ;
		assertEquals( en.getItemValue("V2"), new BigDecimal("222") ) ;
		
		BigDecimal v1 = en.getItemValue("V1");
		BigDecimal v2 = en.getItemValue("V2");
		v1.add(v2);
		assertEquals( v1.add(v2), new BigDecimal("232.50") ) ;
	}
	
	@Test ( expected = EntityParserException.class )
	public void testItemDuplicated1() {
		DomainEnumerationForDecimal en = new DomainEnumerationForDecimal("Values");
		en.addItem("V1", new BigDecimal("10.50") );
		en.addItem("V1", new BigDecimal("11") );
	}

}
