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
}
