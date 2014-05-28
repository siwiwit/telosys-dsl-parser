package org.telosys.tools.dsl.parser;

import junit.framework.Assert;
import org.junit.Test;

import java.io.File;


public class IntegrationParserTest {


    @Test
    public void testParseValidFiles() throws Exception {
        EntityParser parser = new EntityParser();
        File folder = new File("src/test/resources/entity_test/valid");
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                parser.parse(fileEntry);
            }
        }
    }

    @Test
    public void testParseInvalidFiles() throws Exception {
        EntityParser parser = new EntityParser();
        File folder = new File("src/test/resources/entity_test/invalid/");
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                EntityParserException errorDetected = null ;
                try {
                    parser.parse(fileEntry);
                } catch (EntityParserException e) {
                    errorDetected = e ;
                }
                Assert.assertNotNull(errorDetected);
            }
        }
    }

}
