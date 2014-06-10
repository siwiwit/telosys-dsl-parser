package org.telosys.tools.dsl.parser.model;

import org.telosys.tools.dsl.parser.EntityParserException;

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class DomainNeutralTypes {

    // Neutral type list of predefined names
    public final static String STRING = "string";
    public final static String INTEGER = "integer";
    public final static String DECIMAL = "decimal";
    public final static String BOOLEAN = "boolean";
    public final static String DATE = "date";
    public final static String TIME = "time";
    public final static String TIMESTAMP = "timestamp";
    public final static String BLOB = "blob";
    public final static String CLOB = "clob";


    private final static String[] names = {STRING, INTEGER, DECIMAL, BOOLEAN, DATE, TIME, TIMESTAMP, BLOB, CLOB};

    private final static Hashtable<String, DomainNeutralType> neutralTypes = new Hashtable<String, DomainNeutralType>();

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

    public final static boolean exists(String typeName) {
        return neutralTypes.containsKey(typeName);
    }

    public final static DomainNeutralType getType(String typeName) {
        if (neutralTypes.containsKey(typeName)) {
            return neutralTypes.get(typeName);
        } else {
            throw new EntityParserException("Invalid neutral type name '" + typeName + "'");
        }
    }

    public final static List<String> getNames() {
        return new LinkedList<String>(neutralTypes.keySet());
    }

    public final static List<String> getSortedNames() {
        List<String> list = getNames();
        Collections.sort(list);
        return list;
    }

}
