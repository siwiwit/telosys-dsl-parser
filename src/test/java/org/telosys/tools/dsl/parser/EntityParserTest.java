package org.telosys.tools.dsl.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.Entity;
import org.telosys.tools.dsl.parser.model.Field;

import java.io.File;

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
                "}\n";

        EntityParser parser = new EntityParser(formattedContent);
        String flattenContent = parser.computeFlattenContent();

        String compareTo = "Employee{id:id{@test};firstName:string;birthDate:date;}";
        Assert.assertEquals(compareTo, flattenContent);
    }

    @Test(expected = EntityParserException.class)
    public void testParseMissingBracket() {
        String testMissingBracket = "Entityid:int;";

        EntityParser parser = new EntityParser();
        parser.setFlattenContent(testMissingBracket);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseMultipleEntities() {
        String testMultipleEntities = "Entity{id:int;}Entity2{id:int;}";

        EntityParser parser = new EntityParser();
        parser.setFlattenContent(testMultipleEntities);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseWithoutField() {
        String testEntityWithoutField = "Entity{}";

        EntityParser parser = new EntityParser();
        parser.setFlattenContent(testEntityWithoutField);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseEntityWithIllegalCharacters() {
        String testEntityNameIllegalCharacters = "E#n_tité{id:int;}";

        EntityParser parser = new EntityParser();
        parser.setFlattenContent(testEntityNameIllegalCharacters);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseFieldWithIllegalCharacters() {
        String testEntityFieldIllegalCharacters = "Entity{ié#_:int;}";

        EntityParser parser = new EntityParser();
        parser.setFlattenContent(testEntityFieldIllegalCharacters);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseMissingSemiColumn() {
        String testMissingSemiColumn = "Entity{id:int;name:string}";

        EntityParser parser = new EntityParser();
        parser.setFlattenContent(testMissingSemiColumn);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseMissingLastBracket() {
        String testMissingLastBracket = "Entity{id:int;";

        EntityParser parser = new EntityParser();
        parser.setFlattenContent(testMissingLastBracket);
        parser.parseFlattenContent("Entity");
    }

    @Test()
    public void testParseValid() {
        String testValid = "Entity{id:int;}";

        EntityParser parser = new EntityParser();
        parser.setFlattenContent(testValid);
        Entity entity = parser.parseFlattenContent("Entity");

        Entity toCompare = new Entity("Entity");
        Field fieldId = new Field("id", "id:int");
        toCompare.addField(fieldId);

        System.out.println(entity);
        System.out.println(toCompare);

        Assert.assertEquals(toCompare, entity);
    }
}
