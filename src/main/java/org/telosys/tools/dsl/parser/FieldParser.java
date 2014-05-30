package org.telosys.tools.dsl.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telosys.tools.dsl.parser.model.Annotation;
import org.telosys.tools.dsl.parser.model.Field;
import org.telosys.tools.dsl.parser.model.NeutralType;
import org.telosys.tools.dsl.parser.model.Type;

import java.util.List;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @version 1.0
 * @date 2014-05-13
 */
public class FieldParser {
    private AnnotationParser annotationParser;

    private Logger logger;

    public FieldParser() {
        this.annotationParser = new AnnotationParser();
        this.logger = LoggerFactory.getLogger(FieldParser.class);
    }

    /**
     * @param fieldInfo
     * @return
     */
    Field parseField(String fieldInfo) {
        int startDescription = fieldInfo.indexOf(":");
        String name = fieldInfo.substring(0, startDescription);
        if (!name.matches("^[\\w]*$")) {
            String errorMessage = "The name of the fields must not contains special char " + name;
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }
        if (name.length() == 0) {
            String errorMessage = "The name of the field is missing";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        int end;
        if (fieldInfo.contains("{")) {
            end = fieldInfo.indexOf("{");
        } else {
            end = fieldInfo.length();
        }

        String typeName = fieldInfo.substring(startDescription+1, end);
        if (typeName.length() == 0) {
            String errorMessage = "The type of the field is missing";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        Type type;
        if (this.isTypeEnum(typeName)) {
            type = new NeutralType("Enum=" + typeName);
        } else {
            type = new NeutralType(typeName);
        }

        Field field = new Field(name, type);
        List<Annotation> annotations = this.annotationParser.parseAnnotations(fieldInfo);
        field.setAnnotationList(annotations);

        return field;
    }

    private boolean isTypeEnum(String type) {
        return type.startsWith("#");
    }
}
