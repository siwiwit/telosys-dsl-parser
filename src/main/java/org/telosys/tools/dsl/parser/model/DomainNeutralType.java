package org.telosys.tools.dsl.parser.model;


public class DomainNeutralType extends DomainType {

    public DomainNeutralType(String name) {
        super(name, DomainTypeNature.NEUTRAL_TYPE);
    }

    @Override
    public String toString() {
        return "DomainNeutralType [getName()=" + getName() + ", getNature()=" + getNature() + "]";
    }


}
