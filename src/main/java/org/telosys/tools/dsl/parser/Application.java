package org.telosys.tools.dsl.parser;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telosys.tools.dsl.EntityParserException;
import org.telosys.tools.dsl.parser.model.DomainModel;

/**
 * @author Jonhathan Goncalves, Mathieu Herbert, Thomas Legendre
 * @version 1.0
 * @date 2014-05-22
 */
public final class Application {
	static Logger logger = LoggerFactory.getLogger(Application.class);
    /**
     * Call parser from cli
     *
     * @param args
     */
    public static void main(String[] args) {
        // check argument exist
        if (args == null) {
            throw new EntityParserException("No file given");
        }

        if (args.length != 1) {
            throw new EntityParserException("A single parameter is required");
        }
        logger.error("ok");
        // call parser tool
        DomainModelParser dm = new DomainModelParser();
        DomainModel model = dm.parse(new File(args[0]));
        System.out.println(model);
    }
}
