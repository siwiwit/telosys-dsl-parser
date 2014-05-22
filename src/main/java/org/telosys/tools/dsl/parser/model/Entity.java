package org.telosys.tools.dsl.parser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public class Entity {

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
}
