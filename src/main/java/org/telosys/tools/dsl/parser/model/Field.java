package org.telosys.tools.dsl.parser.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private String name ;
    private Type type ;
    private List<Annotation> annotationList;

    public Field(String name, Type type){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (!annotationList.equals(field.annotationList)) return false;
        if (!name.equals(field.name)) return false;
        if (!type.equals(field.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (annotationList != null ? annotationList.hashCode() : 0);
        return result;
    }
}
