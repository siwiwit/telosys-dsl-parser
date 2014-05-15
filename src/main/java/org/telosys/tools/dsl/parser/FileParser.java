package org.telosys.tools.dsl.parser;

import java.io.*;

public class FileParser {
	/**
	 * @param inputStream
	 * @return
	 */
	public String readStream(InputStream inputStream) {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();

				if (line.contains(Utils.getProperty("start_comment"))) {
					line = line.substring(0, line.indexOf(Utils.getProperty("start_comment")));
				}

				if (line.length() > 0) {
					stringBuilder.append(line.replace(" ", ""));
				}
			}
		} catch (IOException e) {
			throw new EntityParserException("Error while reading the stream : " + e.getMessage());
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new EntityParserException("Error while closing the stream : " + e.getMessage());
			}
		}

		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param str
	 * @param filename
	 * @return
	 */
	public String checkSyntax(String str, String filename) {

		int bodyStart = str.indexOf("{");
		int bodyEnd = str.lastIndexOf("}");

		if (bodyStart == 0) {
			throw new EntityParserException("There's something wrong with the beginning of the body");
		}

		if (bodyEnd == str.length()) {
			throw new EntityParserException("There's something wrong with the end of the body");
		}

		String entityName = str.substring(0, bodyStart).trim();
		if (!entityName.equals(filename)) {
			throw new EntityParserException("The name of the file does not match with the entity name");
		}

		if (!Character.isUpperCase(str.charAt(0))) {
			throw new EntityParserException("The name of the entity must start with an upper case");
		}

		// Table table = new Table(entityName);
		String body = str.substring(bodyStart + 1, bodyEnd - 1).trim();
		String[] fieldList = body.split(";");
		String ret = entityName + "[" ;
		for (String field : fieldList) {
			ret += field + "||";
			// Field f = this.checkFields(field);
			// table.add(f);
		}
		return ret + "]";
	}
}