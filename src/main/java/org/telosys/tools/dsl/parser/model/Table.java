package org.telosys.tools.dsl.parser.model;

import java.util.ArrayList;

/**
 * Created by Thomas on 15/05/14.
 */
public class Table {

    private String name;
    private ArrayList<Field> fields;

    public Table(String name) {
        this.name = name;
        this.fields = new ArrayList<Field>();
    }

    public void addField(Field field) {
        this.fields.add(field);
    }

    public ArrayList<Field> getFields() {
        return this.fields;
    }

    public String toString() {
        String fieldRet = "";
        for (Field f : this.fields)
            fieldRet += f.toString() + " | ";
        return this.name + " [" + "]";
    }
}
