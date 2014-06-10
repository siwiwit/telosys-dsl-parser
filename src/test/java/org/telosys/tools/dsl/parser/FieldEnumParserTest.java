package org.telosys.tools.dsl.parser;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.DomainEnumeration.TypeEnum;
import org.telosys.tools.dsl.parser.model.DomainEnumerationItem;

public class FieldEnumParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParseValidString() {
        String testValid = "TEST=\"string1\"";
        FieldEnumParser parser = new FieldEnumParser();
        DomainEnumerationItem<String> toCompare = new DomainEnumerationItem<String>("TEST", "string1");
        Assert.assertEquals(toCompare, parser.parseField(testValid, TypeEnum.STRING));
	}
	
	@Test
	public void testParseValidDecimal() {
        String testValid = "TEST=4.3";
        FieldEnumParser parser = new FieldEnumParser();
        DomainEnumerationItem<BigDecimal> toCompare = new DomainEnumerationItem<BigDecimal>("TEST", new BigDecimal("4.3"));
        Assert.assertEquals(toCompare, parser.parseField(testValid, TypeEnum.DECIMAL));
	}
	
	@Test
	public void testParseValidInteger() {
        String testValid = "TEST=4";
        FieldEnumParser parser = new FieldEnumParser();
        DomainEnumerationItem<BigInteger> toCompare = new DomainEnumerationItem<BigInteger>("TEST", new BigInteger("4"));
        Assert.assertEquals(toCompare, parser.parseField(testValid, TypeEnum.INTEGER));
	}
	
	@Test(expected=EntityParserException.class)
	public void testParseWithAnEmptyName() {
        String testInvalid = "=4";
        FieldEnumParser parser = new FieldEnumParser();
        parser.parseField(testInvalid, TypeEnum.INTEGER);
	}
	
	@Test(expected=EntityParserException.class)
	public void testParseWithAnEmptyValue() {
        String testInvalid = "TEST=";
        FieldEnumParser parser = new FieldEnumParser();
        parser.parseField(testInvalid, TypeEnum.INTEGER);
	}
	
	@Test(expected=EntityParserException.class)
	public void testParseWithAnEmptyValueString() {
        String testInvalid = "TEST=\"\"";
        FieldEnumParser parser = new FieldEnumParser();
        parser.parseField(testInvalid, TypeEnum.STRING);
	}
	
	@Test(expected=EntityParserException.class)
	public void testParseWithSimpleQuoteString() {
        String testInvalid = "TEST=\'abc\'";
        FieldEnumParser parser = new FieldEnumParser();
        parser.parseField(testInvalid, TypeEnum.STRING);
	}
	
	@Test(expected=EntityParserException.class)
	public void testParseWithoutQuoteString() {
        String testInvalid = "TEST=3";
        FieldEnumParser parser = new FieldEnumParser();
        parser.parseField(testInvalid, TypeEnum.STRING);
	}

    @Test
    public void testIsItemWithoutValue() {
        FieldEnumParser parser = new FieldEnumParser();

        String testWithValue = "TEST=3";
        Assert.assertFalse(parser.isItemWithoutValue(testWithValue));

        String testWithoutValue = "TEST";
        Assert.assertTrue(parser.isItemWithoutValue(testWithoutValue));
    }

    @Test
    public void testParseFieldIncrementation() {
        String testWithoutValue = "TEST";

        DomainEnumerationItem<BigInteger> compareTo = new DomainEnumerationItem<BigInteger>("TEST", new BigInteger("2"));

        FieldEnumParser parser = new FieldEnumParser();
        Assert.assertEquals(compareTo, parser.parseField(testWithoutValue, new BigInteger("1")));
    }

    @Test(expected = EntityParserException.class)
    public void testParseFieldIncrementationInvalid() {
        String testWithoutValue = "TEST=2";

        FieldEnumParser parser = new FieldEnumParser();
        parser.parseField(testWithoutValue, new BigInteger("1"));
    }
}
