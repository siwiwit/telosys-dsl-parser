package org.telosys.tools.dsl.parser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 15/05/14.
 */
public class Field {

    private String name ;
    private String type ;
    private List<Annotation> annotationList;

    public Field(String name, String type){
        this.name = name;
        this.type = type;
        this.annotationList = new ArrayList<Annotation>();
    }

    public void setAnnotationList(List<Annotation> annotationList) {
        this.annotationList = annotationList;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", annotationList=" + annotationList +
                '}';
    }

    @Override
    public boolean equals(Object other){
    	if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Field))return false;
        Field otherField= (Field)other;
        if(!otherField.name.equals(name)) return false;
        if(!otherField.type.equals(type)) return false;
        if(!otherField.annotationList.equals(annotationList)) return false;
        
        return true;
    }
}
