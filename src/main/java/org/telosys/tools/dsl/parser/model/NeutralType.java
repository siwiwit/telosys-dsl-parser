package org.telosys.tools.dsl.parser.model;

public class NeutralType implements Type {
    private String name;

    public NeutralType(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NeutralType that = (NeutralType) o;

        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "NeutralType{" +
                "name='" + name + '\'' +
                '}';
    }
}
