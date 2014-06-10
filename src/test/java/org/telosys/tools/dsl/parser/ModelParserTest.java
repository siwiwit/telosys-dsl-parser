package org.telosys.tools.dsl.parser;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.DomainEntity;
import org.telosys.tools.dsl.parser.model.DomainEntityField;
import org.telosys.tools.dsl.parser.model.DomainEntityFieldAnnotation;
import org.telosys.tools.dsl.parser.model.DomainModel;
import org.telosys.tools.dsl.parser.model.DomainNeutralTypes;

public class ModelParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParseModelWithAndEntity() throws Exception {
		File folder = new File(
				"src/test/resources/model_test/valid/model_WithAnEntity");
		DomainModelParser parser = new DomainModelParser();
		DomainModel model = parser.parse(folder);
		DomainModel modelToCompare = new DomainModel("ModelWithAnEntity");
		DomainEntity employee = new DomainEntity("Employee");
		DomainEntityField id = new DomainEntityField("id", DomainNeutralTypes.getType("integer"));
		id.addAnnotation(new DomainEntityFieldAnnotation("Id"));
		employee.addField(id);
		employee.addField(new DomainEntityField("firstName", DomainNeutralTypes.getType("string")));
		employee.addField(new DomainEntityField("birthDate", DomainNeutralTypes.getType("date")));
		modelToCompare.addEntity(employee);
		System.out.println(model);
		System.out.println(modelToCompare);
		assertEquals(modelToCompare,model);
		

	}
}
