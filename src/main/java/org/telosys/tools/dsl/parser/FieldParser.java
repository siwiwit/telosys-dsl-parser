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
 */
public class FieldParser {

    /**
     * Single parser for all annotations
     */
    private AnnotationParser annotationParser;

    /**
     * Logger for tracing all events
     */
    private Logger logger;
    
    /**
     * Curent Model
     */
	private DomainModel model;

    /**
     * Constructor with context as a DomainModel
     * @param model Context of the current field to parse
     */
    public FieldParser(DomainModel model) {
        this.annotationParser = new AnnotationParser();
        this.logger = LoggerFactory.getLogger(FieldParser.class);
        this.model = model;
    }

    /**
     * Parse a single field with its own informations
     * @param fieldInfo String
     * @return The parsed field
     */
    DomainEntityField parseField(String fieldInfo) {
        int startDescription = fieldInfo.indexOf(":");
        String name = fieldInfo.substring(0, startDescription);

        // description and field is required
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

        // find end of descritpion
        int end;
        if (fieldInfo.contains("{")) {
            end = fieldInfo.indexOf("{");
        } else {
            end = fieldInfo.length();
        }

        String typeName = fieldInfo.substring(startDescription+1, end);
        int cardinality = 1 ;

        if (isTypeArray(typeName)){
            int startArray = typeName.lastIndexOf("[");
            int endArray = typeName.lastIndexOf("]");
            System.out.println(typeName);
            if  (endArray-startArray == 1){
                cardinality = -1;
                typeName = typeName.substring(0,startArray);
            }else{

                String figure = typeName.substring(startArray+1, endArray);
                try{
                    cardinality = Integer.parseInt(figure);
                    typeName = typeName.substring(0,startArray);
                }catch (Exception e){
                    String errorMessage = "The cardinality for " + typeName + " is not correct";
                    this.logger.error(errorMessage);
                    throw new EntityParserException(errorMessage);
                }
            }
        }

        if (typeName.length() == 0) {
            String errorMessage = "The type of the field is missing";
            this.logger.error(errorMessage);
            throw new EntityParserException(errorMessage);
        }

        DomainType type;
        if (this.isTypeEnum(typeName)) {
        	
            if (!this.model.getEnumerationNames().contains(typeName.substring(1))) {
                String errorMessage = "The enumeration " + typeName.substring(1) + " does not exist";
                this.logger.error(errorMessage);
                throw new EntityParserException(errorMessage);
            } else {
            	type = this.model.getEnumeration(typeName.substring(1));
            }
        } else if(DomainNeutralTypes.exists(typeName)){
            type = DomainNeutralTypes.getType(typeName);
        } else {
        	if(!model.getEntityNames().contains(typeName)) {
        		String errorMessage = "The type of the field is incorrect";
        		this.logger.error(errorMessage);
        		throw new EntityParserException(errorMessage);
        	} else {
        		type = model.getEntity(typeName);
        	}
        }

        DomainEntityField field = new DomainEntityField(name, type, cardinality);
        List<DomainEntityFieldAnnotation> annotations = this.annotationParser.parseAnnotations(fieldInfo);
        field.setAnnotationList(annotations);

        return field;
    }

    /**
     * Check if the given param is an enum with the specific char
     * @param type The type of the field
     * @return bool - true if it's an enum
     */
    private boolean isTypeEnum(String type) {
        return type.startsWith("#");
    }

    /**
     * Check if the given param is an array of oject
     * @param type The type of the field
     * @return bool - true if it's an array
     */
    private boolean isTypeArray(String type) {
        return type.contains("[") && type.contains("]");
    }
}
