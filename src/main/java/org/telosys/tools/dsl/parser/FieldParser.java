package org.telosys.tools.dsl.parser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telosys.tools.dsl.parser.model.DomainEntityField;
import org.telosys.tools.dsl.parser.model.DomainEntityFieldAnnotation;
import org.telosys.tools.dsl.parser.model.DomainModel;
import org.telosys.tools.dsl.parser.model.DomainNeutralTypes;
import org.telosys.tools.dsl.parser.model.DomainType;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @version 1.0
 * @date 2014-05-13
 */
public class FieldParser {
    private AnnotationParser annotationParser;

    private Logger logger;
    
    /**
     * Curent Model
     */
	private DomainModel model;

    public FieldParser(DomainModel model) {
        this.annotationParser = new AnnotationParser();
        this.logger = LoggerFactory.getLogger(FieldParser.class);
        this.model = model;
    }

    /**
     * @param fieldInfo
     * @return
     */
    DomainEntityField parseField(String fieldInfo) {
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

        DomainType type;
        if (this.isTypeEnum(typeName)) {
        	//TODO
        	throw new EntityParserException("Not implemented yet!");
//            type = new NeutralType("Enum=" + typeName);
        } else if(DomainNeutralTypes.exists(typeName)){
            type = DomainNeutralTypes.getType(typeName);
        } else {
        	String errorMessage = "The type of the field is incorrect";
        	this.logger.error(errorMessage);
        	throw new EntityParserException(errorMessage);
        }

        DomainEntityField field = new DomainEntityField(name, type);
        List<DomainEntityFieldAnnotation> annotations = this.annotationParser.parseAnnotations(fieldInfo);
        field.setAnnotationList(annotations);

        return field;
    }

    private boolean isTypeEnum(String type) {
        return type.startsWith("#");
    }
}
