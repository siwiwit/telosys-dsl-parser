package org.telosys.tools.dsl.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.dsl.EntityParserException;
import org.telosys.tools.dsl.parser.model.DomainEntity;
import org.telosys.tools.dsl.parser.model.DomainEntityField;
import org.telosys.tools.dsl.parser.model.DomainEntityFieldAnnotation;
import org.telosys.tools.dsl.parser.model.DomainModel;
import org.telosys.tools.dsl.parser.model.DomainNeutralTypes;

public class EntityParserTest {
	@Before
	public void setUp() throws Exception {}

	@Test(expected = EntityParserException.class)
	public void testParseFileWithAFileWhichDoesntExist() {
		File file = new File("entity_test/nul.entity");
		EntityParser parser = new EntityParser(new DomainModel("model"));
		parser.parse(file);
	}

    @Test
    public void testComputeFlatten() {
        String formattedContent = "Employee {\n" +
                "\tid : id {@test}; // the id\n" +
                "\tfirstName : string ;\n" +
                "\tbirthDate : date ;\n" +
                "}\n";
        
        EntityParser parser = new EntityParser(formattedContent, new DomainModel("model"));
        String flattenContent = parser.computeFlattenContent();

        String compareTo = "Employee{id:id{@test};firstName:string;birthDate:date;}";
        Assert.assertEquals(compareTo, flattenContent);
    }

    @Test(expected = EntityParserException.class)
    public void testParseMissingBracket() {
        String testMissingBracket = "Entityid:integer;";

        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testMissingBracket);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseMultipleEntities() {
        String testMultipleEntities = "Entity{id:integer;}Entity2{id:integer;}";

        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testMultipleEntities);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseWithoutField() {
        String testEntityWithoutField = "Entity{}";

        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testEntityWithoutField);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseEntityWithIllegalCharacters() {
        String testEntityNameIllegalCharacters = "E#n_tité{id:integer;}";

        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testEntityNameIllegalCharacters);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseFieldWithIllegalCharacters() {
        String testEntityFieldIllegalCharacters = "Entity{ié#_:integer;}";

        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testEntityFieldIllegalCharacters);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseMissingSemiColumn() {
        String testMissingSemiColumn = "Entity{id:integer;name:string}";

        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testMissingSemiColumn);
        parser.parseFlattenContent("Entity");
    }

    @Test(expected = EntityParserException.class)
    public void testParseMissingLastBracket() {
        String testMissingLastBracket = "Entity{id:integer;";

        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testMissingLastBracket);
        parser.parseFlattenContent("Entity");
    }
    
    @Test(expected = EntityParserException.class)
    public void testParseWithTwoIds() {
        String testWithTwoIds = "Entity{id:integer{@Id};idbis:integer{@Id};}";

        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testWithTwoIds);
        parser.parseFlattenContent("Entity");
    }
    
    @Test(expected = EntityParserException.class)
    public void testParseWithAnIdInAnArray() {
        String testWithAnIdInAnArray = "Entity{id:integer[]{@Id};}";

        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testWithAnIdInAnArray);
        parser.parseFlattenContent("Entity");
    }
    
    @Test(expected = EntityParserException.class)
    public void testParseWithAnIdInAClob() {
        String testWithAnIdInAClob = "Entity{id:clob{@Id};}";

        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testWithAnIdInAClob);
        parser.parseFlattenContent("Entity");
    }
    
    @Test(expected = EntityParserException.class)
    public void testParseWithAnIdInABlob() {
        String testWithAnIdInABlob = "Entity{id:blob{@Id};}";

        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testWithAnIdInABlob);
        parser.parseFlattenContent("Entity");
    }

    @Test()
    public void testParseValid() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String testValid = "Entity{id:integer{@Id,@Max(3)};}";
        
        EntityParser parser = new EntityParser(new DomainModel("model"));
        parser.setFlattenContent(testValid);
        
        FieldParser mockFieldParser = EasyMock.createMock(FieldParser.class);
        //Field
        DomainEntityField fieldId = new DomainEntityField("id", DomainNeutralTypes.getType("integer"));
        List<DomainEntityFieldAnnotation> annotationList = new ArrayList<DomainEntityFieldAnnotation>();
        annotationList.add(new DomainEntityFieldAnnotation("Id"));
        annotationList.add(new DomainEntityFieldAnnotation("Max", "3"));
        fieldId.setAnnotationList(annotationList);
        
        //mock fieldParser
        EasyMock.expect(mockFieldParser.parseField("id:integer{@Id,@Max(3)}")).andReturn(fieldId);
        java.lang.reflect.Field field = parser.getClass().getDeclaredField("fieldParser");
        field.setAccessible(true);
        field.set(parser, mockFieldParser);
        EasyMock.replay(mockFieldParser);
        
        DomainEntity entity = parser.parseFlattenContent("Entity");
        
        DomainEntity toCompare = new DomainEntity("Entity");
        EasyMock.verify(mockFieldParser);
        toCompare.addField(fieldId);
        Assert.assertEquals(toCompare, entity);
        
    }
}
