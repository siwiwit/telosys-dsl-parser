package org.telosys.tools.dsl.parser;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileParserTest extends TestCase {
    @Test
    public void testReadStream() throws FileNotFoundException {
        FileParser fileParser = new FileParser();
        InputStream inputStream = new FileInputStream(new File("entity_test/Employee.entity"));


        String file = fileParser.readStream(inputStream);
        System.out.println(file);
    }
    
    @Test
    public void testParseFile() throws FileNotFoundException {
        FileParser fileParser = new FileParser();
        InputStream inputStream = new FileInputStream(new File("entity_test/Employee.entity"));


        String file = fileParser.checkSyntax(fileParser.readStream(inputStream), "Employee");
        System.out.println(file);
    }

    @Test
    public void testParsingRules() {
        String testValid = "Entity{id:int;}";
        String testMissingBracket = "Entityid:int;";
        String testMultipleEntities = "Entity{id:int;}Entity2{id:int;}";
        String testEntityWithoutField = "Entity{}";
        String testEntityNameIllegalCharacters = "E#n_tité{id:int;}";
        String testEntityFieldIllegalCharacters = "Entity{ié#_:int;}";
        String testMissingSemiColumn = "Entity{id:int;name:string}";
        String testMissingLastBracket = "Entity{id:int;";

        FileParser fileParser = new FileParser();
        fileParser.checkSyntax(testValid, "Entity");

        try {
            fileParser.checkSyntax(testMissingBracket, "Entity");
            fail("Brackets are missing");
        } catch (EntityParserException e) {}

        try {
            fileParser.checkSyntax(testMultipleEntities, "Entity");
            fail("Multiple entities are defined");
        } catch (EntityParserException e) {}

        try {
            fileParser.checkSyntax(testEntityWithoutField, "Entity");
            fail("No field are defined");
        } catch (EntityParserException e) {}

        try {
            fileParser.checkSyntax(testEntityNameIllegalCharacters, "E#n_tité");
            fail("Entity name contains illegal characters");
        } catch (EntityParserException e) {}

        try {
            fileParser.checkSyntax(testEntityFieldIllegalCharacters, "Entity");
            fail("Entity field contains illegal characters");
        } catch (EntityParserException e) {}

        try {
            fileParser.checkSyntax(testMissingSemiColumn, "Entity");
            fail("Last semicolumn is missing");
        } catch (EntityParserException e) {}

        try {
            fileParser.checkSyntax(testMissingLastBracket, "Entity");
            fail("Last bracket is missing");
        } catch (EntityParserException e) {}
    }
}
