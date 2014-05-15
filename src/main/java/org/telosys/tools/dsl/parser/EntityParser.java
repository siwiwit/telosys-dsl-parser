package org.telosys.tools.dsl.parser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 
 * First entry point for the telosys entity parser
 * 
 * @author Jonathan Goncalvez, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public class EntityParser {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * 
	 * @param fileName
	 */
	public void parse(String fileName) {
		this.parse(new File(fileName));
	}

	/**
	 * 
	 * @param file
	 */
	public void parse(File file) {
		try {
			InputStream io = new FileInputStream(file);
			this.parse(file);
		} catch (FileNotFoundException e) {
			throw new EntityParserException("File Not found : "+file.getAbsolutePath());
		}
		
		
	}

	/**
	 * 
	 * @param is
	 */
	public void parse(InputStream is) {

	}

}
