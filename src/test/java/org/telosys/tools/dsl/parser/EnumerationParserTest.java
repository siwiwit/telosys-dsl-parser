package org.telosys.tools.dsl.parser;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.Enumeration;
import org.telosys.tools.dsl.parser.model.Enumeration.TypeEnum;
import org.telosys.tools.dsl.parser.model.FieldEnum;

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
        String testValid = "EnumTest:int{TEST=3,TESTBIS=4}";
        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testValid);
        Enumeration<BigInteger> toCompare = new Enumeration<BigInteger>("EnumTest", TypeEnum.INTEGER);
        toCompare.addField(new FieldEnum<BigInteger>("TEST", new BigInteger("3")));
        toCompare.addField(new FieldEnum<BigInteger>("TESTBIS", new BigInteger("4")));
        Assert.assertEquals(toCompare, parser.parseFlattenContent("EnumTest"));
    }
    
    @Test()
    public void testParseValidDecimal() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String testValid = "EnumTest:decimal{TEST=3.4,TESTBIS=4.5}";
        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testValid);
        Enumeration<BigDecimal> toCompare = new Enumeration<BigDecimal>("EnumTest", TypeEnum.DECIMAL);
        toCompare.addField(new FieldEnum<BigDecimal>("TEST", new BigDecimal("3.4")));
        toCompare.addField(new FieldEnum<BigDecimal>("TESTBIS", new BigDecimal("4.5")));
        Assert.assertEquals(toCompare, parser.parseFlattenContent("EnumTest"));
    }
    
    @Test()
    public void testParseValidString() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String testValid = "EnumTest:string{TEST=\"string1\",TESTBIS=\"string2\"}";
        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testValid);
        Enumeration<String> toCompare = new Enumeration<String>("EnumTest", TypeEnum.STRING);
        toCompare.addField(new FieldEnum<String>("TEST", "string1"));
        toCompare.addField(new FieldEnum<String>("TESTBIS", "string2"));
        Assert.assertEquals(toCompare, parser.parseFlattenContent("EnumTest"));
    }
    
    @Test(expected = EntityParserException.class)
    public void testParseInValidInt() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String testInvalid = "EnumTest:int{TEST=3.4,TESTBIS=4}";
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
}
