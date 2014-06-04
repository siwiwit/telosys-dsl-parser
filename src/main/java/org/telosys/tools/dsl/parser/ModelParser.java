package org.telosys.tools.dsl.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.telosys.tools.dsl.parser.model.DomainModel;

public class ModelParser {
	
	public DomainModel parse(String folder) {
		DomainModel model = new DomainModel(folder);
		File folderF = new File(folder);
		List<String> enumerations = listEnum(folderF);
		EnumerationParser enumParser = new EnumerationParser();
		for(String enumeration : enumerations){
			model.addEnumeration(enumParser.parse(new File(enumeration)));
		}
		
		List<String> entities = listEntities(folderF);
		EntityParser entityParser = new EntityParser(model);
		for(String entity : entities){
				model.addEntity(entityParser.parse(entity));
		}
		return model;
	}

	
	public List<String> listEntities(File folder) {
		return listFiles(folder, "entity");
	}
	
	public List<String> listEnum(File folder) {
		return listFiles(folder, "enum");
	}
	
	public List<String> listFiles(File folder, String extension){
		List<String> files = new ArrayList<String>();

		int i;
		String[] allFiles = folder.list();
		for (i = 0; i < allFiles.length; i++) {
			if (allFiles[i].endsWith("."+extension) == true) {
				files.add(folder.getAbsolutePath()+"/"+allFiles[i]);
			}
		}
		
		return files;
	}

}
