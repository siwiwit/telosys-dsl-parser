package org.telosys.tools.dsl.parser;

import org.telosys.tools.dsl.parser.model.Field;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public class FieldParser {

    /**
     * @param fieldInfo
     * @return
     */
    private Field checkField(String fieldInfo) {
        String name = fieldInfo.substring(0, fieldInfo.indexOf(":")).trim();
        if (!name.matches("^[\\w]*$"))
            throw new EntityParserException("The name of the fields must not contains special char " + name);


        Field field = new Field(name, fieldInfo);

        return field;
    }
}
