package org.telosys.tools.dsl.parser.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.telosys.tools.dsl.parser.EntityParserException;

public class StringUtils {

    /**
     * @param inputStream
     * @return
     */
    public static String readStream(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder ret = new StringBuilder();
        try {
            
        	
        	while ((line = bufferedReader.readLine()) != null) {
                ret.append(line+ "\n");
            }
        } catch (IOException e) {
            throw new EntityParserException("Error while reading the stream : " + e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new EntityParserException("Error while closing the stream : " + e.getMessage());
            }
        }
      
        return stringBuilder.toString();
    }
}
