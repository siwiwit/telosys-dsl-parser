package org.telosys.tools.dsl.parser;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.DomainEnumeration;
import org.telosys.tools.dsl.parser.model.DomainEnumerationForDecimal;
import org.telosys.tools.dsl.parser.model.DomainEnumerationForInteger;
import org.telosys.tools.dsl.parser.model.DomainEnumerationForString;
import org.telosys.tools.dsl.parser.model.DomainEnumerationItem;

public class EnumerationParserTest {
    @Test(expected = EntityParserException.class)
    public void testParseMissingBrackets() {
        String testMissingBracket = "EnumTEST=3";

        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testMissingBracket);
        parser.parseFlattenContent("Enum");
    }
    @Test(expected = EntityParserException.class)
    public void testParseMissingBracket() {
        String testMissingBracket = "Enum{TEST=3";

        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testMissingBracket);
        parser.parseFlattenContent("Enum");
    }
    @Test(expected = EntityParserException.class)
    public void testParseMultipleEnum() {
        String testMultipleEnum = "Enum{TEST=3}EnumBis{TEST=5}";

        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testMultipleEnum);
        parser.parseFlattenContent("Enum");
    }
    
    @Test(expected = EntityParserException.class)
    public void testParseWithoutField() {
        String testWithoutField = "Enum{}";

        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testWithoutField);
        parser.parseFlattenContent("Enum");
    }
    
    @Test(expected = EntityParserException.class)
    public void testParseMissingComa() {
        String testMissingComa = "Enum{TEST=2TEST=4}";

        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testMissingComa);
        parser.parseFlattenContent("Enum");
    }
    
    @Test(expected = EntityParserException.class)
    public void testParseInvalidType() {
        String testInvalidType = "Enum:fake{TEST=2,TEST=4}";

        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testInvalidType);
        parser.parseFlattenContent("Enum");
    }
    
    @Test()
    public void testParseValidInt() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String testValid = "EnumTest:integer{TEST=3,TESTBIS=4}";
        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testValid);
        DomainEnumeration<BigInteger> toCompare = new DomainEnumerationForInteger("EnumTest");
        toCompare.addItem(new DomainEnumerationItem<BigInteger>("TEST", new BigInteger("3")));
        toCompare.addItem(new DomainEnumerationItem<BigInteger>("TESTBIS", new BigInteger("4")));
        Assert.assertEquals(toCompare, parser.parseFlattenContent("EnumTest"));
    }
    
    @Test()
    public void testParseValidDecimal() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String testValid = "EnumTest:decimal{TEST=3.4,TESTBIS=4.5}";
        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testValid);
        DomainEnumeration<BigDecimal> toCompare = new DomainEnumerationForDecimal("EnumTest");
        toCompare.addItem(new DomainEnumerationItem<BigDecimal>("TEST", new BigDecimal("3.4")));
        toCompare.addItem(new DomainEnumerationItem<BigDecimal>("TESTBIS", new BigDecimal("4.5")));
        Assert.assertEquals(toCompare, parser.parseFlattenContent("EnumTest"));
    }
    
    @Test()
    public void testParseValidString() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String testValid = "EnumTest:string{TEST=\"string1\",TESTBIS=\"string2\"}";
        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testValid);
        DomainEnumeration<String> toCompare = new DomainEnumerationForString("EnumTest");
        toCompare.addItem(new DomainEnumerationItem<String>("TEST", "string1"));
        toCompare.addItem(new DomainEnumerationItem<String>("TESTBIS", "string2"));
        Assert.assertEquals(toCompare, parser.parseFlattenContent("EnumTest"));
    }
    
    @Test(expected = EntityParserException.class)
    public void testParseInValidInt() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String testInvalid = "EnumTest:integer{TEST=3.4,TESTBIS=4}";
        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testInvalid);
        parser.parseFlattenContent("EnumTest");
    }
    
    @Test(expected = EntityParserException.class)
    public void testParseInValidDecimal() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String testInvalid = "EnumTest:decimal{TEST=\"String1\",TESTBIS=4.5}";
        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testInvalid);
        parser.parseFlattenContent("EnumTest");
    }
    
    @Test(expected = EntityParserException.class)
    public void testParseInvalidString() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String testInvalid = "EnumTest:string{TEST=3,TESTBIS=\"string2\"}";
        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testInvalid);
        parser.parseFlattenContent("EnumTest");
    }

	@Test
	public void testParseDefaultType() {
		String test = "EnumTest{ONE=1,TWO=2,THREE=3}";
		EnumerationParser parser = new EnumerationParser();
		parser.setFlattenContent(test);

		DomainEnumeration<BigInteger> toCompare = new DomainEnumerationForInteger("EnumTest");
		toCompare.addItem(new DomainEnumerationItem<BigInteger>("ONE", new BigInteger("1")));
		toCompare.addItem(new DomainEnumerationItem<BigInteger>("TWO", new BigInteger("2")));
		toCompare.addItem(new DomainEnumerationItem<BigInteger>("THREE", new BigInteger("3")));

		Assert.assertEquals(toCompare, parser.parseFlattenContent("EnumTest"));
	}
}
