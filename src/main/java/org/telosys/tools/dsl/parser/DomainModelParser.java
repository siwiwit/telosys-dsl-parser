package org.telosys.tools.dsl.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.telosys.tools.dsl.parser.model.DomainEntity;
import org.telosys.tools.dsl.parser.model.DomainModel;

public class DomainModelParser {

	private final static String DOT_MODEL = ".model";
	private final static String DOT_ENTITY = ".entity";
	private final static String DOT_ENUM = ".enum";

	/**
	 * Parse the given model
	 * 
	 * @param file
	 *            the ".model" file or a directory containing a ".model" file
	 * @return
	 */
	public final DomainModel parse(File file) {

		if (!file.exists()) {
			throw new EntityParserException("Cannot parse model : file '"
					+ file.toString() + "' doesn't exist");
		}
		if (file.isFile()) {
			if (file.getName().endsWith(DOT_MODEL)) {
				return parseModelFile(file);
			} else {
				throw new EntityParserException("Cannot parse model : file '"
						+ file.toString() + "' is not a model");
			}
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isFile() && f.getName().endsWith(DOT_MODEL)) {
					return parseModelFile(f);
				}
			}
			throw new EntityParserException(
					"Cannot parse model : no model file in '" + file.toString()
							+ "'");
		} else {
			throw new EntityParserException("Cannot parse model : '"
					+ file.toString() + "' is not a file or directory");
		}
	}

	private final DomainModel parseModelFile(File file) {

		Properties p = loadProperties(file);
		String modelName = p.getProperty("name");
		if (modelName == null || modelName.trim().length() == 0) {
			// use the file name as default name
			String fileName = file.getName();
			int i = fileName.indexOf(DOT_MODEL);
			modelName = fileName.substring(0, i);
		}

		File folder = file.getParentFile();
		Map<String, List<String>> files = getMapFiles(folder);
		DomainModel model = new DomainModel(modelName);
		List<String> enumerations = files.get(DOT_ENUM);

		EnumerationParser enumParser = new EnumerationParser();
		for (String enumeration : enumerations) {
			model.addEnumeration(enumParser.parse(new File(enumeration)));
		}

		List<String> entities = files.get(DOT_ENTITY);
		for (String entity : entities) {
			File entityFile = new File(entity);
			model.addEntity(new DomainEntity(entityFile.getName().substring(0,
					entityFile.getName().lastIndexOf("."))));
		}

		EntityParser entityParser = new EntityParser(model);
		for (String entity : entities) {
			model.putEntity(entityParser.parse(entity));
		}
		return model;
	}

	private Properties loadProperties(File propFile) {
		Properties props = new Properties();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(propFile);
			props.load(fis);
		} catch (IOException ioe) {
			throw new EntityParserException("Cannot load properties from file "
					+ propFile);
		} finally {

			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				// NOTHING TO DO
			}
		}
		return props;
	}

	/**
	 * Get all files name and their associate class from a foledr
	 * 
	 * @param folder
	 * @return
	 */
	private Map<String, List<String>> getMapFiles(File folder) {
		HashMap<String, List<String>> files = new HashMap<String, List<String>>();
		files.put(DOT_ENTITY, new ArrayList<String>());
		files.put(DOT_ENUM, new ArrayList<String>());

		String[] allFiles = folder.list();
		for (String fileName : allFiles) {
			String extension = fileName.substring(fileName.lastIndexOf("."));
			if (files.containsKey(extension)) {
				List<String> current = files.get(extension);
				current.add(folder.getAbsolutePath() + "/" + fileName);
				files.put(extension, current);
			}
		}
		return files;
	}

}
