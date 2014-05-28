package org.telosys.tools.dsl.parser;

import org.telosys.tools.dsl.parser.model.Entity;
import org.telosys.tools.dsl.parser.model.Field;
import org.telosys.tools.dsl.parser.utils.StringUtils;
import org.telosys.tools.dsl.parser.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.StringTokenizer;

/**
 * First entry point for the telosys entity parser
 *
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @version 1.0
 * @date 2014-05-13
 */
public class EntityParser {

    /**
     * Content of the File
     */
    private String formattedContent;

    /**
     * Flatten Content of the File
     */
    private String flattenContent;

    /**
     * fieldParser used to parse fields
     */
    private FieldParser fieldParser;

    public EntityParser() {
        this.formattedContent = "";
        this.flattenContent = "";
        fieldParser = new FieldParser();
    }

    public EntityParser(String formattedContent) {
        this.formattedContent = formattedContent;
        this.flattenContent = "";
        fieldParser = new FieldParser();
    }

    /**
     * @param fileName
     */
    public void parse(String fileName) {
        this.parse(new File(fileName));
    }

    /**
     * @param file
     */
    public void parse(File file) {
        try {
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            InputStream io = new FileInputStream(file);
            this.parse(io, file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            throw new EntityParserException("File Not found : "
                    + file.getAbsolutePath());
        }

    }

    /**
     * @param is
     */
    public void parse(InputStream is, String path) {
        File file = new File(path);

        formattedContent = StringUtils.readStream(is);
        flattenContent = computeFlattenContent();
        int indexPoint = file.getName().lastIndexOf(".");
        if (indexPoint >= 0) {
            Entity res = parseFlattenContent(file.getName().substring(0, indexPoint));
            System.out.println(res.toString());
        }else{
            throw new EntityParserException("The filename has no extension");
        }
    }

    public String computeFlattenContent() {
        StringTokenizer content = new StringTokenizer(formattedContent, "\r\n");
        StringBuilder stringBuilder = new StringBuilder();
        while (content.hasMoreElements()) {
            String line = content.nextElement().toString().trim();

            if (line.contains(Utils.getProperty("start_comment"))) {
                line = line.substring(0,
                        line.indexOf(Utils.getProperty("start_comment")));
            }

            if (line.length() > 0) {
                stringBuilder.append(line.replace(" ", ""));
            }
        }
        return stringBuilder.toString();
    }


    /**
     * @param filename The filename to check the content
     * @return An entity wich contain the name of the entity, and all its fields
     */
    public Entity parseFlattenContent(String filename) {

        // get index of first and last open brackets
        int bodyStart = flattenContent.indexOf("{");
        int bodyEnd = flattenContent.lastIndexOf("}");

        checkStructure(bodyStart, bodyEnd);

        // body required
        if (bodyEnd - bodyStart == 1)
            throw new EntityParserException("A field is required");

        String entityName = flattenContent.substring(0, bodyStart).trim();

        // the closing bracket must be at the end
        if (bodyEnd == flattenContent.length())
            throw new EntityParserException("There's something wrong with the end of the body");

        // the filename must de equal to entity name
        if (!entityName.equals(filename))
            throw new EntityParserException("The name of the file does not match with the entity name");

        // the first later of an entity must be upper case
        if (!Character.isUpperCase(flattenContent.charAt(0)))
            throw new EntityParserException("The name of the entity must start with an upper case");

        // only simple chars are allowed
        if (!entityName.matches("^[A-Z][\\w]*$"))
            throw new EntityParserException("The name must not contains special char" + entityName);

        // create object
        Entity table = new Entity(entityName);

        // find all fields
        String body = flattenContent.substring(bodyStart + 1, bodyEnd).trim();
        if (body.lastIndexOf(";") != body.length() - 1)
            throw new EntityParserException("A semilicon is missing ");

        String[] fieldList = body.split(";");
        // at least 1 field is required
        if (fieldList.length < 1) {
            throw new EntityParserException("This entity must contains at least one field");
        }

        // extract fields
        for (String field : fieldList) {
            Field f = fieldParser.parseField(field);
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

    public String getFormattedContent() {
        return formattedContent;
    }

    public String getFlattenContent() {
        return flattenContent;
    }

    public void setFlattenContent(String flattenContent) {
        this.flattenContent = flattenContent;
    }
}
