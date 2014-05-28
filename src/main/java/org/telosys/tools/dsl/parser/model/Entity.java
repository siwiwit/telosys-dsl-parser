package org.telosys.tools.dsl.parser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public class Entity{

    private String name;
    private List<Field> fields;

    public Entity(String name) {
        this.name = name;
        this.fields = new ArrayList<Field>();
    }

    public void addField(Field field) {
        this.fields.add(field);
    }

    public List<Field> getFields() {
        return this.fields;
    }

    public String toString() {
        String fieldRet = "";
        for (Field f : this.fields)
            fieldRet += f.toString() + " | ";
        return this.name + " [" + fieldRet + "]";
    }
    
    @Override
    public boolean equals(Object other) {
    	if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Entity))return false;
        Entity otherEntity = (Entity)other;
        if(!otherEntity.name.equals(name)) return false;
        if(otherEntity.fields.size() != fields.size()) return false;
        for (int i = 0; i < fields.size(); i++) {
			if(!otherEntity.fields.get(i).equals(fields.get(i))) return false;
		}
    	return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }
}
