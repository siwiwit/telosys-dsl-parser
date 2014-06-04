package org.telosys.tools.dsl.parser;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.DomainEntityField;
import org.telosys.tools.dsl.parser.model.DomainEntityFieldAnnotation;
import org.telosys.tools.dsl.parser.model.DomainModel;
import org.telosys.tools.dsl.parser.model.DomainNeutralType;
import org.telosys.tools.dsl.parser.model.DomainNeutralTypes;

public class FieldParserTest {
    @Test
    public void testParseFieldValid() throws Exception {
        String fieldInfo = "id:integer";

        DomainEntityField compareTo = new DomainEntityField("id", DomainNeutralTypes.getType("integer"));

        FieldParser fieldParser = new FieldParser(new DomainModel("model"));
        Assert.assertEquals(compareTo, fieldParser.parseField(fieldInfo));
    }

    @Test(expected = EntityParserException.class)
    public void testParseFieldWithoutType() throws Exception {
        String fieldInfo = "id:";

        FieldParser fieldParser = new FieldParser(new DomainModel("model"));
        fieldParser.parseField(fieldInfo);
    }

    @Test(expected = EntityParserException.class)
    public void testParseFieldWithoutName() throws Exception {
        String fieldInfo = ":integer";

        FieldParser fieldParser = new FieldParser(new DomainModel("model"));
        fieldParser.parseField(fieldInfo);
    }

    @Test()
    public void testParseFieldWithAnnotation() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String fieldInfo = "id:integer{@Id}";
        DomainEntityField compareTo = new DomainEntityField("id", DomainNeutralTypes.getType("integer"));
        List<DomainEntityFieldAnnotation> annotationList = new ArrayList<DomainEntityFieldAnnotation>();
        annotationList.add(new DomainEntityFieldAnnotation("Id"));
        compareTo.setAnnotationList(annotationList);

        FieldParser fieldParser = new FieldParser(new DomainModel("model"));
        
        //mock annotationParser
        AnnotationParser mockAnnotationParser = EasyMock.createMock(AnnotationParser.class);
        EasyMock.expect(mockAnnotationParser.parseAnnotations("id:integer{@Id}")).andReturn(annotationList);
        java.lang.reflect.Field field = fieldParser.getClass().getDeclaredField("annotationParser");
        field.setAccessible(true);
        field.set(fieldParser, mockAnnotationParser);
        EasyMock.replay(mockAnnotationParser);
          
        Assert.assertEquals(compareTo, fieldParser.parseField(fieldInfo));
        EasyMock.verify(mockAnnotationParser);
    }
    //TODO
    @Ignore
    @Test
    public void testParseFieldWithEnum() {
        String fieldInfo = "id:#Gender";

        DomainEntityField compareTo = new DomainEntityField("id", new DomainNeutralType("Enum=#Gender"));

        FieldParser fieldParser = new FieldParser(new DomainModel("model"));
        Assert.assertEquals(compareTo, fieldParser.parseField(fieldInfo));
    }
}
