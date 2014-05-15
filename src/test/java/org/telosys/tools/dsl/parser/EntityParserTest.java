package org.telosys.tools.dsl.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class EntityParserTest {
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testParseString() {
		
		EntityParser parser = new EntityParser();
		parser.parse("entity_test/Employee.entity");
	}

	@Test
	public void testParseFile() {
		File file = new File("entity_test/Employee.entity");
		EntityParser parser = new EntityParser();
		parser.parse(file);
	}
	
	@Test
	public void testParseInputStream() throws FileNotFoundException {
		FileInputStream file = new FileInputStream(new File("entity_test/Employee.entity"));
		EntityParser parser = new EntityParser();
		parser.parse(file);
	}
	
	@Test(expected=EntityParserException.class)
	public void testParseFileWithAFileWichDontExist(){
		File file = new File("entity_test/nul.entity");
		EntityParser parser = new EntityParser();
		parser.parse(file);
	}


}
