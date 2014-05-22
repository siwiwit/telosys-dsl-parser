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
    	return name +" : "+ properties;
    }
    
    
    @Override
    public boolean equals(Object other){
    	if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Field))return false;
        Field otherField= (Field)other;
        if(!otherField.name.equals(name)) return false;
        if(!otherField.properties.equals(properties)) return false;
        
        return true;
    }
}
