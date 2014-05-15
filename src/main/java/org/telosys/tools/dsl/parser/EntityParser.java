package org.telosys.tools.dsl.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 
 * First entry point for the telosys entity parser
 * 
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public class EntityParser {

	/**
	 * Call parser from cli
	 * @param args
	 */
	public static void main(String[] args) {
		// check argument exist
		if (args == null)
			throw new EntityParserException("No file given");

		if (args.length != 1)
			throw new EntityParserException("A single parameter is required");

		// call parser tool
		EntityParser ep = new EntityParser();
		ep.parse(args[0]);

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
			this.parse(io);
		} catch (FileNotFoundException e) {
			throw new EntityParserException("File Not found : "
					+ file.getAbsolutePath());
		}

	}

	/**
	 * 
	 * @param is
	 */
	public void parse(InputStream is) {

	}

}
