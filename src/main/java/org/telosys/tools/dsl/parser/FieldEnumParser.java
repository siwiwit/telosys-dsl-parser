package org.telosys.tools.dsl.parser;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telosys.tools.dsl.parser.model.DomainEnumeration.TypeEnum;
import org.telosys.tools.dsl.parser.model.DomainEnumerationItem;

/**
 *
 * @param <T>
 */
public class FieldEnumParser<T> {
	 Logger logger = LoggerFactory.getLogger(EntityParser.class);
	DomainEnumerationItem<?> parseField(String fieldInfo, BigInteger value) {
		if(!isItemWithoutValue(fieldInfo)){
			throw new EntityParserException("this item has to be without value : "+ fieldInfo);
		}
		
		checkName(fieldInfo);
		return new DomainEnumerationItem<BigInteger>(fieldInfo,value.add(new BigInteger("1")));
	}

	/**
	 * @param fieldInfo
	 * @return
	 */
	DomainEnumerationItem<?> parseField(String fieldInfo, TypeEnum type) {
		if(!fieldInfo.contains("=")) {
			String textError = "The fields does not contains value " + fieldInfo;
			logger.error(textError);
			throw new EntityParserException(textError);
		}
		int startDescription = fieldInfo.indexOf("=");
		String name = fieldInfo.substring(0, startDescription);
		checkName(name);

		int end = fieldInfo.length();

		String value = fieldInfo.substring(startDescription + 1, end);
		if (value.length() == 0) {
			throw new EntityParserException("The value of the field is missing");
		}

		DomainEnumerationItem<?> field = null;
		if (type == TypeEnum.INTEGER) {
			field = new DomainEnumerationItem<BigInteger>(name, (BigInteger) getValue(
					value, TypeEnum.INTEGER));
		} else if (type == TypeEnum.DECIMAL) {
			field = new DomainEnumerationItem<BigDecimal>(name, (BigDecimal) getValue(
					value, TypeEnum.DECIMAL));
		} else if (type == TypeEnum.STRING) {
			field = new DomainEnumerationItem<String>(name, (String) getValue(value,
					TypeEnum.STRING));
		}
		return field;
	}

	public Object getValue(String value, TypeEnum type) {

		switch (type) {
		case INTEGER:
			try {
				return new BigInteger(value);

			} catch (NumberFormatException e) {
				throw new EntityParserException("the value : " + value
						+ " must be a Big Integer");
			}

		case DECIMAL:
			try {
				return new BigDecimal(value);
			} catch (NumberFormatException e) {
				throw new EntityParserException("the value : " + value
						+ " must be a Big Decimal");
			}

		case STRING:

			if (value.charAt(0) == '"' && value.charAt(1) != '"'
					&& value.charAt(value.length() - 1) == '"') {
				return value.substring(1, value.length() - 1);
			} else {
				throw new EntityParserException("the value : " + value
						+ " must be a String");
			}

		}
		// unreachable code
		return null;
	}
	
	private void checkName(String name) {
		if (!name.matches("^[A-Z]*$"))
			throw new EntityParserException(
					"The name of the fields must contains only uppercase "
							+ name);
		if (name.length() == 0) {
			throw new EntityParserException("The name of the field is missing");
		}
	}
	 
	public static boolean isItemWithoutValue(String item){
		return !item.contains("=");
	}
}
