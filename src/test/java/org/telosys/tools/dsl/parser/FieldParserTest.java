package org.telosys.tools.dsl.parser;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.Annotation;
import org.telosys.tools.dsl.parser.model.Field;
import org.telosys.tools.dsl.parser.model.NeutralType;

import java.util.ArrayList;
import java.util.List;

public class FieldParserTest {
    @Test
    public void testParseFieldValid() throws Exception {
        String fieldInfo = "id:int";

        Field compareTo = new Field("id", new NeutralType("int"));

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
    public void testParseFieldWithAnnotation() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String fieldInfo = "id:int{@Id}";
        Field compareTo = new Field("id", new NeutralType("int"));
        List<Annotation> annotationList = new ArrayList<Annotation>();
        annotationList.add(new Annotation("Id"));
        compareTo.setAnnotationList(annotationList);

        FieldParser fieldParser = new FieldParser();
        
        //mock annotationParser
        AnnotationParser mockAnnotationParser = EasyMock.createMock(AnnotationParser.class);
        EasyMock.expect(mockAnnotationParser.parseAnnotations("id:int{@Id}")).andReturn(annotationList);
        java.lang.reflect.Field field = fieldParser.getClass().getDeclaredField("annotationParser");
        field.setAccessible(true);
        field.set(fieldParser, mockAnnotationParser);
        EasyMock.replay(mockAnnotationParser);
          
        Assert.assertEquals(compareTo, fieldParser.parseField(fieldInfo));
        EasyMock.verify(mockAnnotationParser);
    }

    @Test
    public void testParseFieldWithEnum() {
        String fieldInfo = "id:#Gender";

        Field compareTo = new Field("id", new NeutralType("Enum=#Gender"));

        FieldParser fieldParser = new FieldParser();
        Assert.assertEquals(compareTo, fieldParser.parseField(fieldInfo));
    }
}
