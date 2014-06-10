package org.telosys.tools.dsl.parser.model;

import org.telosys.tools.dsl.EntityParserException;

import java.util.*;

public class DomainEntityField {

    private final String name;
    private final DomainType type;
    private final int cardinality;
    public static final int THIRTY_ONE = 31; // TODO rename

    // TODO : Hashtable (each annotation is unique for a field)
    //  private List<DomainEntityFieldAnnotation> annotationList; // TODO : final

    private final Map<String, DomainEntityFieldAnnotation> annotations = new Hashtable<String, DomainEntityFieldAnnotation>();

    public DomainEntityField(String name, DomainType type) {
        this.name = name;
        this.type = type;
        this.cardinality = 1;
//        this.annotationList = new ArrayList<DomainEntityFieldAnnotation>();
    }

    public DomainEntityField(String name, DomainType type, int cardinality) {
        this.name = name;
        this.type = type;
        this.cardinality = cardinality;
        //    this.annotationList = new ArrayList<DomainEntityFieldAnnotation>();
    }

    public void setAnnotationList(List<DomainEntityFieldAnnotation> annotationList) {
        for (DomainEntityFieldAnnotation annotation : annotationList) {
            addAnnotation(annotation);
        }
    }

    public void addAnnotation(DomainEntityFieldAnnotation annotation) {
        if (!annotations.containsKey(annotation.getName())) {
            annotations.put(annotation.getName(), annotation);
        } else {
            throw new EntityParserException("The annotation " + annotation.getName() + " is already define in the field " + this.getName());
        }
    }

    public final String getName() {
        return name;
    }

    public final DomainType getType() {
        return type;
    }

    public final int getCardinality() {
        return cardinality;
    }

    public final String getTypeName() {
        return type.getName();
    }

    public final boolean isNeutralType() {
        return type.isNeutralType();
    }

    public final boolean isEntity() {
        return type.isEntity();
    }

    public final boolean isEnumeration() {
        return type.isEnumeration();
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", annotationList=" + annotations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DomainEntityField field = (DomainEntityField) o;

        if (!annotations.equals(field.annotations)) {
            return false;
        }
        if (!name.equals(field.name)) {
            return false;
        }
        if (!type.equals(field.type)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = THIRTY_ONE * result + (type != null ? type.hashCode() : 0);
        result = THIRTY_ONE * result + (annotations != null ? annotations.hashCode() : 0);
        return result;
    }

    /**
     * Returns all the annotation names (in alphabetical order)
     *
     * @return
     */
    public final List<String> getAnnotationNames() {
        List<String> names = new LinkedList<String>(annotations.keySet());
        Collections.sort(names);
        return names;
    }
}
