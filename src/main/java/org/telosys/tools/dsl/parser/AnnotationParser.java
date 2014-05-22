package org.telosys.tools.dsl.parser;

import com.sun.jmx.snmp.internal.SnmpSubSystem;
import org.telosys.tools.dsl.parser.model.Annotation;
import org.telosys.tools.dsl.parser.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @version 1.0
 */
public class AnnotationParser {

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
            throw new EntityParserException("There is a problem with the bracket. There's one missing");
        }

        if (bodyEnd < 0 && bodyStart < 0) {
            return list;
        }

        fieldInfo = fieldInfo.substring(bodyStart+1, bodyEnd);

        // list of annotation found
        String[] annotationList = fieldInfo.split(",");
        // at least 1 annotation is required, if there are brackets
        if (annotationList.length < 1) {
            throw new EntityParserException("There is no annotation in the given information");
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
            throw new EntityParserException("An annotation must start with a '@' ");
        }

        // find the name of the annotation
        int end = annotationString.length();

        // check if annotation has a parameter
        boolean containsParam = false;
        String param = "";
        if (annotationString.contains("(")) {
            end = annotationString.indexOf("(");
            param = annotationString.substring(end+1, annotationString.length() - 1);
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
                    throw new EntityParserException("A parameter is required for this annotation : " + givenAnnotation);
                }
                if (!allowed.contains("#") && containsParam) {
                    throw new EntityParserException("There is a not required parameter for this annotation : " + annotationString);
                }

                if (containsParam) {
                    return new Annotation(givenAnnotation, param);
                } else {
                    return new Annotation(givenAnnotation);
                }
            }
        }

        throw new EntityParserException("No annotation has been configured yet " );
    }
}