package org.telosys.tools.dsl.parser;

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
        EntityParser ep = new EntityParser();
        ep.parse(args[0]);

    }
}
