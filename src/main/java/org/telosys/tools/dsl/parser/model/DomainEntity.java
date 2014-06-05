package org.telosys.tools.dsl.parser.model;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.telosys.tools.dsl.parser.EntityParserException;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public class DomainEntity extends DomainType {

    private final Hashtable<String,DomainEntityField> fields;

    public DomainEntity(String name) {
    	super(name, DomainTypeNature.ENTITY);
        this.fields = new Hashtable<String,DomainEntityField>();
    }

    public void addField(DomainEntityField field) {
    	if ( fields.containsKey(field.getName()) ) {
    		throw new EntityParserException("Field '" + field.getName() + "' already defined");
    	}
        fields.put(field.getName(), field);
    }

    public List<DomainEntityField> getFields() {
    	return new LinkedList<DomainEntityField>(fields.values());
    }

    public DomainEntityField getField( String fieldName ) {
    	return fields.get(fieldName);
    }

    public int getNumberOfFields() {
    	return fields.size() ;
    }

    public String toString() {
        String fieldRet = "";
        for (DomainEntityField f : fields.values() )
            fieldRet += f.toString() + " | ";
        return this.getName() + " [" + fieldRet + "]";
    }
    
    @Override
    public boolean equals(Object other) {
    	if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof DomainEntity))return false;
        DomainEntity otherEntity = (DomainEntity)other;
        if(!otherEntity.getName().equals(this.getName())) return false;
        if(otherEntity.fields.size() != fields.size()) return false;
//        for (int i = 0; i < fields.size(); i++) {
//			if(!otherEntity.getFields().get(i).equals(getFields().get(i))) return false;
//		}
        if(!otherEntity.fields.equals(fields)) return false;
    	return true;
    }

    @Override
    public int hashCode() {
        int result = this.getName() != null ? this.getName().hashCode() : 0;
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }
}
