package org.telosys.tools.dsl.parser;

import org.telosys.tools.dsl.parser.model.Annotation;
import org.telosys.tools.dsl.parser.model.Field;

import java.util.List;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @version 1.0
 * @date 2014-05-13
 */
public class FieldParser {
    private AnnotationParser annotationParser;

    public FieldParser() {
        this.annotationParser = new AnnotationParser();
    }

    /**
     * @param fieldInfo
     * @return
     */
    Field parseField(String fieldInfo) {
        int startDescription = fieldInfo.indexOf(":");
        String name = fieldInfo.substring(0, startDescription);
        if (!name.matches("^[\\w]*$"))
            throw new EntityParserException("The name of the fields must not contains special char " + name);
        if (name.length() == 0) {
            throw new EntityParserException("The name of the field is missing");
        }

        int end;
        if (fieldInfo.contains("{")) {
            end = fieldInfo.indexOf("{");
        } else {
            end = fieldInfo.length();
        }

        String type = fieldInfo.substring(startDescription+1, end);
        if (type.length() == 0) {
            throw new EntityParserException("The type of the field is missing");
        }

        Field field = new Field(name, type);
        List<Annotation> annotations = this.annotationParser.parseAnnotations(fieldInfo);
        field.setAnnotationList(annotations);

        return field;
    }
}
