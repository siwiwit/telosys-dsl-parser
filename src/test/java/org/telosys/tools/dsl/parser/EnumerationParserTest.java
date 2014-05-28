package org.telosys.tools.dsl.parser;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.Enumeration;
import org.telosys.tools.dsl.parser.model.Enumeration.TypeEnum;
import org.telosys.tools.dsl.parser.model.FieldEnum;

public class EnumerationParserTest {

	@Before
	public void setUp() throws Exception {
	}

    @Test()
    public void testParseValid() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String testValid = "EnumTest:int{TEST=3,TESTBIS=4}";
        
        EnumerationParser parser = new EnumerationParser();
        parser.setFlattenContent(testValid);
        
        

        Enumeration<BigInteger> toCompare = new Enumeration<BigInteger>("EnumTest", TypeEnum.INTEGER);
        toCompare.addField(new FieldEnum<BigInteger>("TEST", new BigInteger("3")));
        toCompare.addField(new FieldEnum<BigInteger>("TESTBIS", new BigInteger("4")));
        Assert.assertEquals(toCompare, parser.parseFlattenContent("EnumTest"));
        
    }

}
