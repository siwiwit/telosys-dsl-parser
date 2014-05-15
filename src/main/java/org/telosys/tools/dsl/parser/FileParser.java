package org.telosys.tools.dsl.parser;

import java.io.*;

public class FileParser {
    /**
     * @param inputStream
     * @return
     */
    public String readStream(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();

                if (line.contains(Utils.getProperty("start_comment"))) {
                    line = line.substring(0, line.indexOf(Utils.getProperty("start_comment")));
                }

                if (line.length() > 0) {
                    stringBuilder.append(line.replace(" ", ""));
                }
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