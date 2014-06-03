package org.telosys.tools.dsl.parser.model2;

import java.util.ArrayList;
import java.util.List;

public class DomainEntityField {

    private final String     name ;
    private final DomainType type ;
    
    // TODO : Hashtable (each annotation is unique for a field)
    private List<DomainEntityFieldAnnotation> annotationList; // TODO : final

    public DomainEntityField(String name, DomainType type){
        this.name = name;
        this.type = type;
        this.annotationList = new ArrayList<DomainEntityFieldAnnotation>();
    }

    public void setAnnotationList(List<DomainEntityFieldAnnotation> annotationList) {
        this.annotationList = annotationList;
    }
    
	public final String getName() {
		return name ;
	}	
    
	public final DomainType getType() {
		return type ;
	}	
	
	public final String getTypeName() {
		return type.getName() ;
	}	
        
	public final boolean isNeutralType() {
		return type.isNeutralType() ;
	}
	
	public final boolean isEntity() {
		return type.isEntity() ;
	}
	
	public final boolean isEnumeration() {
		return type.isEnumeration() ;
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

        DomainEntityField field = (DomainEntityField) o;

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
