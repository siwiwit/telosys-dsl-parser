package org.telosys.tools.dsl.parser;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.Annotation;
import org.telosys.tools.dsl.parser.model.Field;

import java.util.ArrayList;
import java.util.List;

public class FieldParserTest {
    @Test
    public void testParseFieldValid() throws Exception {
        String fieldInfo = "id:int";

        Field compareTo = new Field("id", "int");

        FieldParser fieldParser = new FieldParser();
        Assert.assertEquals(compareTo, fieldParser.parseField(fieldInfo));
    }

    @Test(expected = EntityParserException.class)
    public void testParseFieldWithoutType() throws Exception {
        String fieldInfo = "id:";

        FieldParser fieldParser = new FieldParser();
        fieldParser.parseField(fieldInfo);
    }

    @Test(expected = EntityParserException.class)
    public void testParseFieldWithoutName() throws Exception {
        String fieldInfo = ":int";

        FieldParser fieldParser = new FieldParser();
        fieldParser.parseField(fieldInfo);
    }

    @Test()
    public void testParseFieldWithAnnotation() throws Exception {
        String fieldInfo = "id:int{@Id}";

        Field compareTo = new Field("id", "int");
        List<Annotation> annotationList = new ArrayList<Annotation>();
        annotationList.add(new Annotation("Id"));
        compareTo.setAnnotationList(annotationList);

        FieldParser fieldParser = new FieldParser();
        Assert.assertEquals(compareTo, fieldParser.parseField(fieldInfo));
    }
}
