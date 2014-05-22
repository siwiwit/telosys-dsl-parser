package org.telosys.tools.dsl.parser.model;

/**
 * Created by Thomas on 15/05/14.
 */
public class Field {

    private String name ;
    private String properties ;

    public Field(String name, String properties){
        this.name = name;
        this.properties = properties;
    }
    
    @Override
    public String toString() {
    	return name;
    }
}
