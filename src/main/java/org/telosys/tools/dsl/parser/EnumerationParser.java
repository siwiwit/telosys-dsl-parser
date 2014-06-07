package org.telosys.tools.dsl.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.StringTokenizer;

import org.telosys.tools.dsl.parser.model.DomainEnumeration;
import org.telosys.tools.dsl.parser.model.DomainEnumeration.TypeEnum;
import org.telosys.tools.dsl.parser.model.DomainEnumerationForDecimal;
import org.telosys.tools.dsl.parser.model.DomainEnumerationForInteger;
import org.telosys.tools.dsl.parser.model.DomainEnumerationForString;
import org.telosys.tools.dsl.parser.utils.StringUtils;
import org.telosys.tools.dsl.parser.utils.TelosysDSLProperties;

public class EnumerationParser {

	/**
	 * Content of the File
	 */
	private String formattedContent;
	
	/**
	 * Flatten Content of the File
	 */
	private String flattenContent;
	
	/**
	 * Field Enummeration Parser
	 */
	private FieldEnumParser fieldEnumParser;
	
	public EnumerationParser(){
		this.fieldEnumParser = new FieldEnumParser();
	}
	/**
	 *
	 * @param fileName
	 */
	public void parse(String fileName) {
		this.parse(new File(fileName));
	}

	/**
	 *
	 * @param file
	 */
	public DomainEnumeration<?> parse(File file) {
		try {
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			InputStream io = new FileInputStream(file);
			return this.parse(io, file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			throw new EntityParserException("File Not found : "
					+ file.getAbsolutePath());
		}

	}

	
	/**
	 *
	 * @param is
	 */
	public DomainEnumeration<?> parse(InputStream is, String path) {
		File file = new File(path);

		formattedContent = StringUtils.readStream(is);
		flattenContent = computeFlattenContent();
		DomainEnumeration<?> res = parseFlattenContent(file.getName()
				.substring(0, file.getName().lastIndexOf(".")));
		return res;
	}
	
	public DomainEnumeration<?> parseFlattenContent(String filename) {
        // get index of first and last open brackets
        int bodyStart = flattenContent.indexOf("{");
        int bodyEnd = flattenContent.lastIndexOf("}");

        checkStructure(bodyStart, bodyEnd);

        // body required
        if (bodyEnd - bodyStart == 1)
            throw new EntityParserException("A field is required");

        String enumName = flattenContent.substring(0, bodyStart).trim();
        TypeEnum type = TypeEnum.INTEGER;
        
        String[] split = enumName.split(":"); 
        if(split.length==2){
        	enumName = split[0];
        	String enumType = split[1];
        	if(enumType.equals("integer")){
        		type = TypeEnum.INTEGER;
        	}else if (enumType.equals("string")){
        		type = TypeEnum.STRING;
        	}else if (enumType.equals("decimal")){
        		type = TypeEnum.DECIMAL;
        	}else {
        		throw new EntityParserException("The type of the Enum have to be int, string or decimal and nothing else");
        	}
        } else {
        	throw new EntityParserException("There is something wrong with the head of the enum");
        }
        
        // the closing bracket must be at the end
//        if (bodyEnd == flattenContent.length())
//            throw new EntityParserException("There's something wrong with the end of the body");

        if (!enumName.equals(filename))
            throw new EntityParserException("The name of the file does not match with the enum name");

        // the first later of an entity must be upper case
        if (!Character.isUpperCase(flattenContent.charAt(0)))
            throw new EntityParserException("The name of the entity must start with an upper case");

        // only simple chars are allowed
        if (!enumName.matches("^[A-Z][\\w]*$"))
            throw new EntityParserException("The name must not contains special char" + enumName);

        // create object
        DomainEnumeration<?> enumeration = null;
        if(type == TypeEnum.INTEGER){
        	enumeration = new DomainEnumerationForInteger(enumName);
        }else if(type == TypeEnum.STRING){
        	enumeration = new DomainEnumerationForString(enumName);
        } else if(type == TypeEnum.DECIMAL){
        	enumeration = new DomainEnumerationForDecimal(enumName);
        }  

        // find all fields
        String body = flattenContent.substring(bodyStart + 1, bodyEnd).trim();
//        if (body.lastIndexOf(";") != body.length()-1  )
//            throw new EntityParserException("A semilicon is missing ");

        String[] fieldEnumList = body.split(",");
        // at least 1 field is required
        if (fieldEnumList.length < 1) {
            throw new EntityParserException("This enum must contains at least one field");
        }

        // extract fields
        for (String field : fieldEnumList) {
            enumeration.addItem(fieldEnumParser.parseField(field, type));
        }
        return enumeration;
	}

	private void checkStructure(int bodyStart, int bodyEnd) {
		// name required before body
        if (bodyStart < 0)
            throw new EntityParserException("There's something wrong with the beginning of the body");

        // end of body required
        if (bodyEnd < 1)
            throw new EntityParserException("There's something wrong with the end of the body");
	}
	public String computeFlattenContent() {
		StringTokenizer content = new StringTokenizer(formattedContent, "\r\n");
		StringBuilder stringBuilder = new StringBuilder();
		while (content.hasMoreElements()) {
			String line = content.nextElement().toString().trim();

			if (line.contains(TelosysDSLProperties.getProperties().getProperty("start_comment"))) {
				line = line.substring(0,
						line.indexOf(TelosysDSLProperties.getProperties().getProperty("start_comment")));
			}

			if (line.length() > 0) {
				stringBuilder.append(line.replace(" ", ""));
			}
		}
	 return stringBuilder.toString();
	}
	
	
	public String getFlattenContent() {
		return flattenContent;
	}
	public void setFlattenContent(String flattenContent) {
		this.flattenContent = flattenContent;
	}
}
