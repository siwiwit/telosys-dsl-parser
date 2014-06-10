package org.telosys.tools.dsl.parser.model;

import org.telosys.tools.dsl.EntityParserException;

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class DomainNeutralTypes {

    // Neutral type list of predefined names
    public static final String STRING = "string";
    public static final String INTEGER = "integer";
    public static final String DECIMAL = "decimal";
    public static final String BOOLEAN = "boolean";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String TIMESTAMP = "timestamp";
    public static final String BLOB = "blob";
    public static final String CLOB = "clob";


    private static final String[] names = {STRING, INTEGER, DECIMAL, BOOLEAN, DATE, TIME, TIMESTAMP, BLOB, CLOB};

    private static final Hashtable<String, DomainNeutralType> neutralTypes = new Hashtable<String, DomainNeutralType>();

    //	private final static void define(DomainNeutralType type) {
//		neutralTypes.put(type.getName(), type);
//	}
//	static {
//		define(new DomainNeutralType("string"));
//		define(new DomainNeutralType("integer"));
//		define(new DomainNeutralType("decimal"));
//		define(new DomainNeutralType("boolean"));
//		define(new DomainNeutralType("date"));
//		define(new DomainNeutralType("time"));
//		define(new DomainNeutralType("timestamp"));
//		define(new DomainNeutralType("blob"));
//		define(new DomainNeutralType("clob"));
//	}
    static {
        for (String name : names) {
            DomainNeutralType type = new DomainNeutralType(name);
            neutralTypes.put(type.getName(), type);
        }
    }

    public static final boolean exists(String typeName) {
        return neutralTypes.containsKey(typeName);
    }

    public static final DomainNeutralType getType(String typeName) {
        if (neutralTypes.containsKey(typeName)) {
            return neutralTypes.get(typeName);
        } else {
            throw new EntityParserException("Invalid neutral type name '" + typeName + "'");
        }
    }

    public static final List<String> getNames() {
        return new LinkedList<String>(neutralTypes.keySet());
    }

    public static final List<String> getSortedNames() {
        List<String> list = getNames();
        Collections.sort(list);
        return list;
    }

}
