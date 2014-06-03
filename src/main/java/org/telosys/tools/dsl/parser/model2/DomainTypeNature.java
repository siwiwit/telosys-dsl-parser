package org.telosys.tools.dsl.parser.model2;

public enum DomainTypeNature {
		NEUTRAL_TYPE,  // e.g "string", "integer", "date", etc
		ENTITY,        // entity defined in the domain model ( .entity file )
		ENUMERATION    // enumeration defined in the domain model ( .enum file )
}
