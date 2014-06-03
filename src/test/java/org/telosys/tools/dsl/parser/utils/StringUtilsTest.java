package org.telosys.tools.dsl.parser.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class StringUtilsTest {
    @Test
    public void testReadStream() throws Exception {
        File file = new File("src/test/resources/entity_test/valid/Employee.entity");
        InputStream inputStream = new FileInputStream(file);

        String formattedContent = StringUtils.readStream(inputStream);

        String compareTo = "Employee {\n" +
                "\tid : integer {@Id}; // the id\n" +
                "\tfirstName : string ;\n" +
                "\tbirthDate : date ;\n" +
                "}\n";

        Assert.assertEquals(compareTo, formattedContent);
    }
}
