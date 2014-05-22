package org.telosys.tools.dsl.parser.model;

/**
 *
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-22
 * @version 1.0
 */
public class Annotation {

    private String name;
    private String parameter;
    private boolean hasParameter;

    public Annotation(String name){
        this.name = name;
        this.hasParameter = true;
    }

    public Annotation(String name, String param){
        this.name = name;
        this.parameter = param;
        this.hasParameter = true;
    }

    boolean hasParameter() {return this.hasParameter;}


    public String getName() {
        return name;
    }

    public String getParameter() {
        return parameter;
    }

}
