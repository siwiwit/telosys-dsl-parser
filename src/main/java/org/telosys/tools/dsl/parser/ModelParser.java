package org.telosys.tools.dsl.parser;

import org.telosys.tools.dsl.parser.model.DomainModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelParser {

    private Map<String, List<String>> files;
    private String[] types = {"entity", "enum"};
    private File folder;

    public ModelParser(String folder) {
        this.folder = new File(folder);
        this.files = new HashMap<String, List<String>>();
    }


    public DomainModel parse() {
        DomainModelParser domainModelParser = new DomainModelParser();
        DomainModel model = domainModelParser.parse(this.folder);

        List<String> enumerations = getListEnumFiles();

        EnumerationParser enumParser = new EnumerationParser();
        for (String enumeration : enumerations) {
            model.addEnumeration(enumParser.parse(new File(enumeration)));
        }

        List<String> entities = getListEntities();
        EntityParser entityParser = new EntityParser(model);
        for (String entity : entities) {
            model.addEntity(entityParser.parse(entity));
        }
        return model;
    }


    public List<String> getListEntities() {
        if (!this.files.containsKey("entity")) {
            this.getListFiles();
        }
        return this.files.get("entity");
    }

    public List<String> getListEnumFiles() {
        if (!this.files.containsKey("enum")) {
            this.getListFiles();
        }
        return this.files.get("enum");
    }

    private void getListFiles() {
        for (String type : this.types) {
            this.files.put(type, new ArrayList<String>());
        }

        String[] allFiles = this.folder.list();
        for (String fileName : allFiles) {
            String extension = fileName.substring(fileName.lastIndexOf("." )); 
            if (this.files.containsKey(extension)) {
                List<String> current = this.files.get(extension);
                current.add(fileName);
                this.files.put(extension, current);
            }
        }

    }

}
