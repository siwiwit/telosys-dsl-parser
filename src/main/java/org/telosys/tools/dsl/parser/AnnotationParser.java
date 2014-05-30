package org.telosys.tools.dsl.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telosys.tools.dsl.parser.model.Annotation;
import org.telosys.tools.dsl.parser.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @version 1.0
 */
public class AnnotationParser {
    private Logger logger;

    public AnnotationParser() {
        this.logger = LoggerFactory.getLogger(AnnotationParser.class);
    }

    /**
     * Parse content of brackets for annotations
     *
     * @param fieldInfo String
     * @return list of annotations found
     */
    List<Annotation> parseAnnotations(String fieldInfo) {

        // get index of first and last open brackets
        int bodyStart = fieldInfo.indexOf("{");
        int bodyEnd = fieldInfo.lastIndexOf("}");

        List<Annotation> list = new ArrayList<Annotation>();

        // no annotation found
        if ((bodyEnd < 0 && bodyStart >= 0) || (bodyEnd >= 0 && bodyStart < 0)) {
            String errorMessage = "There is a problem with the bracket. There's one missing";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        if (bodyEnd < 0 && bodyStart < 0) {
            return list;
        }

        fieldInfo = fieldInfo.substring(bodyStart+1, bodyEnd);

        // list of annotation found
        String[] annotationList = fieldInfo.split(",");
        // at least 1 annotation is required, if there are brackets
        if (annotationList.length < 1) {
            String errorMessage = "There is no annotation in the given information";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        // extract annotations
        for (String annotationString : annotationList) {
            Annotation annotation = this.parseSingleAnnotation(annotationString);
            list.add(annotation);
        }

        return list;
    }

    /**
     * @param annotationString String
     * @return Annotation created by the parser
     */
    private Annotation parseSingleAnnotation(String annotationString) {
        // start with a @
        if (annotationString.charAt(0) != '@') {
            String errorMessage = "An annotation must start with a '@' ";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        // find the name of the annotation
        int end = annotationString.length();

        // check if annotation has a parameter
        boolean containsParam = false;
        String param = "";
        if (annotationString.contains("(")) {
            end = annotationString.indexOf("(");
            param = annotationString.substring(end+1, annotationString.length() - 1);
            if (param.equals("")) {
                String errorMessage = "A parameter is required for this annotation : " + annotationString;
                this.logger.error(errorMessage);
                throw new EntityParserException(errorMessage);
            }
            containsParam = true;
        }


        String givenAnnotation = annotationString.substring(1, end);

        // check annotation exist
        String annotationAllowed = Utils.getProperty("annotations");
        String[] listAllowed = annotationAllowed.split(",");

        // find annotation
        for (String allowed : listAllowed) {
            if (allowed.contains(givenAnnotation)) {
                if (allowed.contains("#") && !containsParam) {
                    String errorMessage = "A parameter is required for this annotation : " + givenAnnotation;
                    this.logger.error(errorMessage);
                    throw new EntityParserException(errorMessage);
                }
                if (!allowed.contains("#") && containsParam) {
                    String errorMessage = "There is a not required parameter for this annotation : " + annotationString;
                    this.logger.error(errorMessage);
                    throw new EntityParserException(errorMessage);
                }

                if (containsParam) {
                    return new Annotation(givenAnnotation, param);
                } else {
                    return new Annotation(givenAnnotation);
                }
            }
        }

        String errorMessage = "No annotation has been configured yet ";
        this.logger.error(errorMessage);
        throw new EntityParserException(errorMessage);
    }
}