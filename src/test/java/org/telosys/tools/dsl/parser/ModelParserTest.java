package org.telosys.tools.dsl.parser;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.dsl.EntityParserException;
import org.telosys.tools.dsl.parser.model.DomainEntity;
import org.telosys.tools.dsl.parser.model.DomainEntityField;
import org.telosys.tools.dsl.parser.model.DomainEntityFieldAnnotation;
import org.telosys.tools.dsl.parser.model.DomainEnumeration;
import org.telosys.tools.dsl.parser.model.DomainEnumerationForString;
import org.telosys.tools.dsl.parser.model.DomainEnumerationItem;
import org.telosys.tools.dsl.parser.model.DomainModel;
import org.telosys.tools.dsl.parser.model.DomainNeutralTypes;

public class ModelParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParseModelWithAndEntity() throws Exception {
		File folder = new File("src/test/resources/model_test/valid/model_withAnEntity");
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
		assertEquals(modelToCompare,model);
	}
	
	@Test
	public void testParseModelWithTwoEntities() throws Exception {
		File folder = new File("src/test/resources/model_test/valid/model_withTwoEntities");
		DomainModelParser parser = new DomainModelParser();
		DomainModel model = parser.parse(folder);
		DomainModel modelToCompare = new DomainModel("ModelWithTwoEntities");
		DomainEntity country = new DomainEntity("Country");
		DomainEntityField idCountry = new DomainEntityField("id", DomainNeutralTypes.getType("integer"));
		idCountry.addAnnotation(new DomainEntityFieldAnnotation("Id"));
		country.addField(idCountry);
		country.addField(new DomainEntityField("name", DomainNeutralTypes.getType("string")));
		modelToCompare.addEntity(country);
		
		DomainEntity employee = new DomainEntity("Employee");
		DomainEntityField id = new DomainEntityField("id", DomainNeutralTypes.getType("integer"));
		id.addAnnotation(new DomainEntityFieldAnnotation("Id"));
		employee.addField(id);
		employee.addField(new DomainEntityField("firstName", DomainNeutralTypes.getType("string")));
		employee.addField(new DomainEntityField("birthDate", DomainNeutralTypes.getType("date")));
		DomainEntityField countryField = new DomainEntityField("country", country);
		countryField.addAnnotation(new DomainEntityFieldAnnotation("Max", "3"));
		employee.addField(countryField);
		modelToCompare.addEntity(employee);
		assertEquals(modelToCompare,model);
	}
	
	@Test
	public void testParseModelWithAnEnum() throws Exception {
		File folder = new File("src/test/resources/model_test/valid/model_withAnEnum");
		DomainModelParser parser = new DomainModelParser();
		DomainModel model = parser.parse(folder);
		DomainModel modelToCompare = new DomainModel("ModelWithAnEnum");
		DomainEnumeration<String> enumeration = new DomainEnumerationForString("Pays");
		enumeration.addItem(new DomainEnumerationItem<String>("FR", "France"));
		enumeration.addItem(new DomainEnumerationItem<String>("EN", "Angleterre"));
		enumeration.addItem(new DomainEnumerationItem<String>("ES", "Espagne"));
		modelToCompare.addEnumeration(enumeration);
		assertEquals(modelToCompare,model);
	}
	
	
	@Test
	public void testParseModelWithAnEnumAndAnEntity() throws Exception {
		File folder = new File("src/test/resources/model_test/valid/model_withAnEnumAndAnEntity/");
		DomainModelParser parser = new DomainModelParser();
		DomainModel model = parser.parse(folder);
		DomainModel modelToCompare = new DomainModel("ModelWithAnEnumAndAnEntity");
		DomainEnumeration<String> enumeration = new DomainEnumerationForString("Pays");
		enumeration.addItem(new DomainEnumerationItem<String>("FR", "France"));
		enumeration.addItem(new DomainEnumerationItem<String>("EN", "Angleterre"));
		enumeration.addItem(new DomainEnumerationItem<String>("ES", "Espagne"));
		
		DomainEntity employee = new DomainEntity("Employee");
		DomainEntityField id = new DomainEntityField("id", DomainNeutralTypes.getType("integer"));
		id.addAnnotation(new DomainEntityFieldAnnotation("Id"));
		employee.addField(id);
		employee.addField(new DomainEntityField("firstName", DomainNeutralTypes.getType("string")));
		employee.addField(new DomainEntityField("birthDate", DomainNeutralTypes.getType("date")));
		DomainEntityField countryField = new DomainEntityField("country", enumeration );
		employee.addField(countryField);
		modelToCompare.addEntity(employee);
		modelToCompare.addEnumeration(enumeration);
		assertEquals(modelToCompare,model);
	}
	

	@Test
	public void testParseModelWithTwoEnumAndTwoEntity() throws Exception {
		File folder = new File("src/test/resources/model_test/valid/model_withTwoEnumAndTwoEntity/");
		DomainModelParser parser = new DomainModelParser();
		DomainModel model = parser.parse(folder);
		DomainModel modelToCompare = new DomainModel("ModelWithTwoEnumAndTwoEntity");
		
		DomainEnumeration<String> country = new DomainEnumerationForString("Country");
		country.addItem(new DomainEnumerationItem<String>("FR", "France"));
		country.addItem(new DomainEnumerationItem<String>("EN", "Angleterre"));
		country.addItem(new DomainEnumerationItem<String>("ES", "Espagne"));

		DomainEnumeration<String> gender = new DomainEnumerationForString("Gender");
		gender.addItem(new DomainEnumerationItem<String>("H", "Homme"));
		gender.addItem(new DomainEnumerationItem<String>("F", "Femme"));
		
		DomainEntity employee = new DomainEntity("Employee");
		DomainEntityField employeeId = new DomainEntityField("id", DomainNeutralTypes.getType("integer"));
		employeeId.addAnnotation(new DomainEntityFieldAnnotation("Id"));
		employeeId.addAnnotation(new DomainEntityFieldAnnotation("NotNull"));
		employee.addField(employeeId);
		DomainEntityField employeePoste = new DomainEntityField("poste", DomainNeutralTypes.getType("string"), -1);
		employee.addField(employeePoste);
		employeePoste.addAnnotation(new DomainEntityFieldAnnotation("NotNull"));
		
		DomainEntity person = new DomainEntity("Person");
		DomainEntityField personId = new DomainEntityField("id", DomainNeutralTypes.getType("integer"));
		personId.addAnnotation(new DomainEntityFieldAnnotation("Id"));
		person.addField(personId);
		person.addField(new DomainEntityField("firstName", DomainNeutralTypes.getType("string")));
		person.addField(new DomainEntityField("lastName", DomainNeutralTypes.getType("string")));
		person.addField(new DomainEntityField("birthDate", DomainNeutralTypes.getType("date")));
		DomainEntityField countryField = new DomainEntityField("country", country, 999 );
		person.addField(countryField);
		DomainEntityField genderField = new DomainEntityField("gender", gender );
		person.addField(genderField);
		
		modelToCompare.addEntity(employee);
		modelToCompare.addEntity(person);
		modelToCompare.addEnumeration(country);
		modelToCompare.addEnumeration(gender);
		assertEquals(modelToCompare,model);
	}

	@Test
	public void testParseModelWithTwoEnumAndTwoEntityDirty() throws Exception {
		File folder = new File("src/test/resources/model_test/valid/model_withTwoEnumAndTwoEntity_dirty/");
		DomainModelParser parser = new DomainModelParser();
		DomainModel model = parser.parse(folder);
		DomainModel modelToCompare = new DomainModel("ModelWithTwoEnumAndTwoEntityDirty");
		
		DomainEnumeration<String> country = new DomainEnumerationForString("Country");
		country.addItem(new DomainEnumerationItem<String>("FR", "France"));
		country.addItem(new DomainEnumerationItem<String>("EN", "Angleterre"));
		country.addItem(new DomainEnumerationItem<String>("ES", "Espagne"));

		DomainEnumeration<String> gender = new DomainEnumerationForString("Gender");
		gender.addItem(new DomainEnumerationItem<String>("H", "Homme"));
		gender.addItem(new DomainEnumerationItem<String>("F", "Femme"));
		
		DomainEntity employee = new DomainEntity("Employee");
		DomainEntityField employeeId = new DomainEntityField("id", DomainNeutralTypes.getType("integer"));
		employeeId.addAnnotation(new DomainEntityFieldAnnotation("Id"));
		employeeId.addAnnotation(new DomainEntityFieldAnnotation("NotNull"));
		employee.addField(employeeId);
		DomainEntityField employeePoste = new DomainEntityField("poste", DomainNeutralTypes.getType("string"), -1);
		employee.addField(employeePoste);
		employeePoste.addAnnotation(new DomainEntityFieldAnnotation("NotNull"));
		
		DomainEntity person = new DomainEntity("Person");
		DomainEntityField personId = new DomainEntityField("id", DomainNeutralTypes.getType("integer"));
		personId.addAnnotation(new DomainEntityFieldAnnotation("Id"));
		person.addField(personId);
		person.addField(new DomainEntityField("firstName", DomainNeutralTypes.getType("string")));
		person.addField(new DomainEntityField("lastName", DomainNeutralTypes.getType("string")));
		person.addField(new DomainEntityField("birthDate", DomainNeutralTypes.getType("date")));
		DomainEntityField countryField = new DomainEntityField("country", country, 998 );
		person.addField(countryField);
		DomainEntityField genderField = new DomainEntityField("gender", gender );
		person.addField(genderField);
		
		modelToCompare.addEntity(employee);
		modelToCompare.addEntity(person);
		modelToCompare.addEnumeration(country);
		modelToCompare.addEnumeration(gender);
		assertEquals(modelToCompare,model);
	}
	

	@Test(expected=EntityParserException.class)
	public void testParseModelWithTwoEnumAndTwoEntityWhitespaces() throws Exception {
		File folder = new File("src/test/resources/model_test/valid/model_withTwoEnumAndTwoEntity_whitespaces/");
		DomainModelParser parser = new DomainModelParser();
		DomainModel model = parser.parse(folder);
		DomainModel modelToCompare = new DomainModel("ModelWithTwoEnumAndTwoEntityWhitespaces");
		
	}

}
