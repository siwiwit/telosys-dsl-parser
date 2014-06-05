package org.telosys.tools.dsl.parser;

import junit.framework.Assert;

import org.junit.Test;
import org.telosys.tools.dsl.parser.model.DomainModel;

import java.io.File;


public class IntegrationParserTest {


    @Test
    public void testParseValidFiles() throws Exception {
        File folder = new File("src/test/resources/entity_test/valid");
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
            	EntityParser parser = new EntityParser(new DomainModel("model"));
                parser.parse(fileEntry);
            }
        }
    }

    @Test
    public void testParseInvalidFiles() throws Exception {
        File folder = new File("src/test/resources/entity_test/invalid/");
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                EntityParserException errorDetected = null ;
                try {
                	EntityParser parser = new EntityParser(new DomainModel("model"));
                    parser.parse(fileEntry);
                } catch (EntityParserException e) {
                    errorDetected = e ;
                }
                Assert.assertNotNull(errorDetected);
            }
        }
    }

}
