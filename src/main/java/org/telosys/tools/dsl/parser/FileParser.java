package org.telosys.tools.dsl.parser;

import org.telosys.tools.dsl.parser.model.Field;
import org.telosys.tools.dsl.parser.model.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
     * @param str
     * @param filename
     * @return
     */
    public Table checkSyntax(String str, String filename) {

        // get index of first and last open brackets
        int bodyStart = str.indexOf("{");
        int bodyEnd = str.lastIndexOf("}");

        // simple check
        if (bodyStart < 0)
            throw new EntityParserException("There's something wrong with the beginning of the body");

        if (bodyEnd < 1)
            throw new EntityParserException("There's something wrong with the end of the body");

        if (bodyEnd - bodyStart == 1)
            throw new EntityParserException("A field is required");

        String entityName = str.substring(0, bodyStart).trim();

        if (bodyEnd == str.length())
            throw new EntityParserException("There's something wrong with the end of the body");

        if (!entityName.equals(filename))
            throw new EntityParserException("The name of the file does not match with the entity name");

        if (!Character.isUpperCase(str.charAt(0)))
            throw new EntityParserException("The name of the entity must start with an upper case");

        if (!entityName.matches("^[A-Z][\\w]*$"))
            throw new EntityParserException("The name must not contains special char" + entityName);

        // create object
        Table table = new Table(entityName);

        // find all fields
        String body = str.substring(bodyStart + 1, bodyEnd).trim();
        if (body.lastIndexOf(";") != body.length()-1  )
            throw new EntityParserException("A semilicon is missing ");

        String[] fieldList = body.split(";");
        System.out.println(str);
        if (fieldList.length < 1) {
            throw new EntityParserException("This entity must contains at least one field");
        }

        for (String field : fieldList) {
            Field f = this.checkFields(field);
            table.addField(f);
        }
        return table;
    }

    /**
     * @param fieldInfo
     * @return
     */
    private Field checkFields(String fieldInfo) {
        String name = fieldInfo.substring(0, fieldInfo.indexOf(":")).trim();
        if (!name.matches("^[\\w]*$"))
            throw new EntityParserException("The name of the fields must not contains special char " + name);

        Field field = new Field(name);
        return field;
    }
}