package org.telosys.tools.dsl.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EntityParserTest {
	@Before
	public void setUp() throws Exception {

	}

	@Test(expected = EntityParserException.class)
	public void testParseFileWithAFileWhichDontExist() {
		File file = new File("entity_test/nul.entity");
		EntityParser parser = new EntityParser();
		parser.parse(file);
	}

    @Test
    public void testComputeFlatten() {
        String formattedContent = "Employee {\n" +
                "\tid : id {@test}; // the id\n" +
                "\tfirstName : string ;\n" +
                "\tbirthDate : date ;\n" +
                "}";

        EntityParser parser = new EntityParser(formattedContent);
        String flattenContent = parser.computeFlattenContent();

        String compareTo = "Employee{id:id{@test};firstName:string;birthDate:date;}";
        Assert.assertEquals(compareTo, flattenContent);
    }
}
