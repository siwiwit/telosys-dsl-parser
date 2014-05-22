package org.telosys.tools.dsl.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.telosys.tools.dsl.parser.model.Entity;
import org.telosys.tools.dsl.parser.model.Field;
import org.telosys.tools.dsl.parser.utils.Utils;

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
     * @param str The string extract from the file entity
     * @param filename The filename to check the content
     * @return A table wich contain the name of the entity, and all its fields
     */
    public Entity checkSyntax(String str, String filename) {

        // get index of first and last open brackets
        int bodyStart = str.indexOf("{");
        int bodyEnd = str.lastIndexOf("}");

        checkStructure(bodyStart, bodyEnd);

        // body required
        if (bodyEnd - bodyStart == 1)
            throw new EntityParserException("A field is required");

        String entityName = str.substring(0, bodyStart).trim();

        // the closing bracket must be at the end
        if (bodyEnd == str.length())
            throw new EntityParserException("There's something wrong with the end of the body");

        // the filename must de equal to entity name
        if (!entityName.equals(filename))
            throw new EntityParserException("The name of the file does not match with the entity name");

        // the first later of an entity must be upper case
        if (!Character.isUpperCase(str.charAt(0)))
            throw new EntityParserException("The name of the entity must start with an upper case");

        // only simple chars are allowed
        if (!entityName.matches("^[A-Z][\\w]*$"))
            throw new EntityParserException("The name must not contains special char" + entityName);

        // create object
        Entity table = new Entity(entityName);

        // find all fields
        String body = str.substring(bodyStart + 1, bodyEnd).trim();
        if (body.lastIndexOf(";") != body.length()-1  )
            throw new EntityParserException("A semilicon is missing ");

        String[] fieldList = body.split(";");
        // at least 1 field is required
        if (fieldList.length < 1) {
            throw new EntityParserException("This entity must contains at least one field");
        }

        // extract fields
        for (String field : fieldList) {
            Field f = this.checkFields(field);
            table.addField(f);
        }
        return table;
    }

	private void checkStructure(int bodyStart, int bodyEnd) {
		// name required before body
        if (bodyStart < 0)
            throw new EntityParserException("There's something wrong with the beginning of the body");

        // end of body required
        if (bodyEnd < 1)
            throw new EntityParserException("There's something wrong with the end of the body");
	}

    /**
     * @param fieldInfo
     * @return
     */
    private Field checkFields(String fieldInfo) {
        String name = fieldInfo.substring(0, fieldInfo.indexOf(":")).trim();
        if (!name.matches("^[\\w]*$"))
            throw new EntityParserException("The name of the fields must not contains special char " + name);

        Field field = new Field(fieldInfo);

        return field;
    }
}