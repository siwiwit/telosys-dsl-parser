package org.telosys.tools.dsl.parser.utils;

import org.telosys.tools.dsl.EntityParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class StringUtils {
    private StringUtils(){}
    /**
     * Read content from a file
     *
     * @param inputStream
     * @return The content
     */
    public static String readStream(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder ret = new StringBuilder();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                ret.append(line + "\n");
            }
        } catch (IOException e) {
            throw new EntityParserException("Error while reading the stream : " + e.getMessage()
                    + "\n Documentation : " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new EntityParserException("Error while closing the stream : " + e.getMessage()
                        + "\n Documentation : " + e);
            }
        }

        return ret.toString();
    }
}
