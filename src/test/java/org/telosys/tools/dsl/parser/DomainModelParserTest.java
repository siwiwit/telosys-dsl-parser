package org.telosys.tools.dsl.parser;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;
import org.telosys.tools.dsl.parser.model.DomainModel;

public class DomainModelParserTest {

	public File getFileByClassPath(String fileName) {
		URL url = DomainModelParser.class.getResource(fileName);
		if ( url != null ) {
			URI uri = null ;
			try {
				uri = url.toURI();
			} catch (URISyntaxException e) {
				throw new RuntimeException("Cannot convert URL to URI (file '" + fileName + "')");
			}
			return new File(uri);
		}
		else {
			throw new RuntimeException("File '" + fileName + "' not found");
		}
//		return new File(url.toURI());
	}

	@Test
	public void testFileWithData() {
		String modelLocation = "/models/test1-model/toto.model" ;
		System.out.println("Parsing model : " + modelLocation );
		DomainModelParser parser = new DomainModelParser();
		File file = getFileByClassPath(modelLocation);
		System.out.println("File : " + file );
		DomainModel model = parser.parse(file);
		System.out.println("Parsed : model name = " + model.getName());
		assertEquals( "MyModel", model.getName() ) ;
	}

	@Test
	public void testFileWithoutData() {
		String modelLocation = "/models/test2-model/toto.model" ;
		System.out.println("Parsing model : " + modelLocation );
		DomainModelParser parser = new DomainModelParser();
		File file = getFileByClassPath(modelLocation);
		System.out.println("File : " + file );
		DomainModel model = parser.parse(file);
		System.out.println("Parsed : model name = " + model.getName());
		assertEquals( "toto", model.getName() ) ;
	}

	@Test
	public void testFolderWithFileWithData() {
		String modelLocation = "/models/test1-model/" ;  // works with or without "/" at the end
		System.out.println("Parsing model : " + modelLocation );
		DomainModelParser parser = new DomainModelParser();
		File file = getFileByClassPath(modelLocation);
		System.out.println("File : " + file );
		DomainModel model = parser.parse(file);
		System.out.println("Parsed : model name = " + model.getName());
		assertEquals( "MyModel", model.getName() ) ;
	}

	@Test
	public void testFolderWithFileWithoutData() {
		String modelLocation = "/models/test2-model/" ;  // works with or without "/" at the end
		System.out.println("Parsing model : " + modelLocation );
		DomainModelParser parser = new DomainModelParser();
		File file = getFileByClassPath(modelLocation);
		System.out.println("File : " + file );
		DomainModel model = parser.parse(file);
		System.out.println("Parsed : model name = " + model.getName());
		assertEquals( "toto", model.getName() ) ;
	}

}
