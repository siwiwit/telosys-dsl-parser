package org.telosys.tools.dsl.parser.model;

public abstract class DomainType {

    /**
     * Type name 
     * e.g. "string", "integer", "Book" (entity), "BookType" (enum), etc 
     */
    private final String name;
    
	/**
	 * Type nature : neutral type, entity or enumeration
	 */
	private final DomainTypeNature nature ;
	
	
	public DomainType(String name, DomainTypeNature nature) {
		super();
		this.name   = name;
		this.nature = nature;
	}

	public final String getName() {
		return name ;
	}
	
	public final DomainTypeNature getNature() {
		return nature ;
	}
	
	public final boolean isNeutralType() {
		return nature == DomainTypeNature.NEUTRAL_TYPE ;
	}
	
	public final boolean isEntity() {
		return nature == DomainTypeNature.ENTITY ;
	}
	
	public final boolean isEnumeration() {
		return nature == DomainTypeNature.ENUMERATION ;
	}
	
}
