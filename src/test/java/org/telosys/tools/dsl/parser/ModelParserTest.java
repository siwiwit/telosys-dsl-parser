package org.telosys.tools.dsl.parser;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.DomainModel;

public class ModelParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
    public void testParseValidModels() throws Exception {
        File folder = new File("src/test/resources/model_test/valid");
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
            	DomainModelParser parser = new DomainModelParser();
                DomainModel model = parser.parse(fileEntry);
                System.out.println(model);
            }
        }
    }

}
