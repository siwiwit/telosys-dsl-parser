package org.telosys.tools.dsl.parser.model2;

/**
 *
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-22
 * @version 1.0
 */
public class DomainEntityFieldAnnotation {

    private String name;
    private String parameter;
    private boolean hasParameter;

    public DomainEntityFieldAnnotation(String name){
        this.name = name;
        this.parameter = "";
        this.hasParameter = false;
    }

    public DomainEntityFieldAnnotation(String name, String param){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainEntityFieldAnnotation that = (DomainEntityFieldAnnotation) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (parameter != null ? !parameter.equals(that.parameter) : that.parameter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (parameter != null ? parameter.hashCode() : 0);
        result = 31 * result + (hasParameter ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "@{" +
                "name='" + name + '\'' +
                ", parameter='" + parameter + '\'' +
                '}';
    }
}
