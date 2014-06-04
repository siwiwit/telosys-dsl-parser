package org.telosys.tools.dsl.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telosys.tools.dsl.parser.model.DomainEntity;
import org.telosys.tools.dsl.parser.model.DomainEntityField;
import org.telosys.tools.dsl.parser.model.DomainModel;
import org.telosys.tools.dsl.parser.utils.StringUtils;
import org.telosys.tools.dsl.parser.utils.Utils;

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

    private Logger logger;

    /**
     * the current model
     */
	private DomainModel model;

    public EntityParser(DomainModel model) {
        this.formattedContent = "";
        this.flattenContent = "";
        this.fieldParser = new FieldParser(model);
        this.logger = LoggerFactory.getLogger(EntityParser.class);
        this.model = model;
    }

    public EntityParser(String formattedContent, DomainModel model) {
        this.formattedContent = formattedContent;
        this.flattenContent = "";
        this.fieldParser = new FieldParser(model);
        this.logger = LoggerFactory.getLogger(EntityParser.class);
        this.model = model;
    }

    /**
     * @param fileName
     */
    public DomainEntity parse(String fileName) {
        return this.parse(new File(fileName));
    }

    /**
     * @param file
     */
    public DomainEntity parse(File file) {
        try {
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            InputStream io = new FileInputStream(file);
            return this.parse(io, file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            throw new EntityParserException("File Not found : "
                    + file.getAbsolutePath());
        }

    }

    /**
     * @param is
     */
    public DomainEntity parse(InputStream is, String path) {
        File file = new File(path);

        formattedContent = StringUtils.readStream(is);
        flattenContent = computeFlattenContent();
        int indexPoint = file.getName().lastIndexOf(".");
        if (indexPoint >= 0) {
            return parseFlattenContent(file.getName().substring(0, indexPoint));
        } else {
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
    public DomainEntity parseFlattenContent(String filename) {
        this.logger.info("Parsing of the file " + filename);

        // get index of first and last open brackets
        int bodyStart = flattenContent.indexOf("{");
        int bodyEnd = flattenContent.lastIndexOf("}");

        checkStructure(bodyStart, bodyEnd);

        // body required
        if (bodyEnd - bodyStart == 1) {
            String errorMessage = "A field is required";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        String entityName = flattenContent.substring(0, bodyStart).trim();

        // the filename must de equal to entity name
        if (!entityName.equals(filename)) {
            String errorMessage = "The name of the file does not match with the entity name";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        // the first later of an entity must be upper case
        if (!Character.isUpperCase(flattenContent.charAt(0))) {
            String errorMessage = "The name of the entity must start with an upper case";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        // only simple chars are allowed
        if (!entityName.matches("^[A-Z][\\w]*$")) {
            String errorMessage = "The name must not contains special char " + entityName;
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        // create object
        DomainEntity table = new DomainEntity(entityName);

        // find all fields
        String body = flattenContent.substring(bodyStart + 1, bodyEnd).trim();
        if (body.lastIndexOf(";") != body.length() - 1) {
            String errorMessage = "A semilicon is missing";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        String[] fieldList = body.split(";");
        // at least 1 field is required
        if (fieldList.length < 1) {
            String errorMessage = "This entity must contains at least one field";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        // extract fields
        for (String field : fieldList) {
            DomainEntityField f = fieldParser.parseField(field);
            table.addField(f);
        }
        return table;
    }

    private void checkStructure(int bodyStart, int bodyEnd) {
        // name required before body
        if (bodyStart < 0) {
            String errorMessage = "There's something wrong with the beginning of the body";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        // end of body required
        if (bodyEnd < 1) {
            String errorMessage = "There's something wrong with the end of the body";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }
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
