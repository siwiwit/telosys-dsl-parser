package org.telosys.tools.dsl.parser;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.Enumeration;
import org.telosys.tools.dsl.parser.model.FieldEnum;
import org.telosys.tools.dsl.parser.model.Enumeration.TypeEnum;

public class FieldEnumParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParseValidString() {
        String testValid = "TEST=\"string1\"";
        FieldEnumParser parser = new FieldEnumParser();
        FieldEnum<String> toCompare = new FieldEnum<String>("TEST", "string1");
        Assert.assertEquals(toCompare, parser.parseField(testValid, TypeEnum.STRING));
	}
	
	@Test
	public void testParseValidDecimal() {
        String testValid = "TEST=4.3";
        FieldEnumParser parser = new FieldEnumParser();
        FieldEnum<BigDecimal> toCompare = new FieldEnum<BigDecimal>("TEST", new BigDecimal("4.3"));
        Assert.assertEquals(toCompare, parser.parseField(testValid, TypeEnum.DECIMAL));
	}
	
	@Test
	public void testParseValidInteger() {
        String testValid = "TEST=4";
        FieldEnumParser parser = new FieldEnumParser();
        FieldEnum<BigInteger> toCompare = new FieldEnum<BigInteger>("TEST", new BigInteger("4"));
        Assert.assertEquals(toCompare, parser.parseField(testValid, TypeEnum.INTEGER));
	}
	
	@Test(expected=EntityParserException.class)
	public void testParseWithAnEmptyName() {
        String testValid = "=4";
        FieldEnumParser parser = new FieldEnumParser();
        parser.parseField(testValid, TypeEnum.INTEGER);
	}
	
	@Test(expected=EntityParserException.class)
	public void testParseWithAnEmptyValue() {
        String testValid = "TEST=";
        FieldEnumParser parser = new FieldEnumParser();
        parser.parseField(testValid, TypeEnum.INTEGER);
	}
	
	@Test(expected=EntityParserException.class)
	public void testParseWithAnEmptyValueString() {
        String testValid = "TEST=\"\"";
        FieldEnumParser parser = new FieldEnumParser();
        parser.parseField(testValid, TypeEnum.STRING);
	}
	
	@Test(expected=EntityParserException.class)
	public void testParseWithSimpleQuoteString() {
        String testValid = "TEST=\'abc\'";
        FieldEnumParser parser = new FieldEnumParser();
        parser.parseField(testValid, TypeEnum.STRING);
	}
	
	@Test(expected=EntityParserException.class)
	public void testParseWithoutQuoteString() {
        String testValid = "TEST=3";
        FieldEnumParser parser = new FieldEnumParser();
        parser.parseField(testValid, TypeEnum.STRING);
	}

}
