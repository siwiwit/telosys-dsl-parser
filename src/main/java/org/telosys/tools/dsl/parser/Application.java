package org.telosys.tools.dsl.parser;

import org.telosys.tools.dsl.parser.model.DomainModel;

/**
 *
 * @author Jonathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-22
 * @version 1.0
 */
public class Application {

    /**
     * Call parser from cli
     * @param args
     */
    public static void main(String[] args) {
        // check argument exist
        if (args == null)
            throw new EntityParserException("No file given");

        if (args.length != 1)
            throw new EntityParserException("A single parameter is required");

        // call parser tool
        ModelParser dm = new ModelParser();
        dm.parse(args[0]);
    }
}
