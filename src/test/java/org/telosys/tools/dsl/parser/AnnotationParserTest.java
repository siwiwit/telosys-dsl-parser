package org.telosys.tools.dsl.parser;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.tools.dsl.parser.model.Annotation;

import java.util.ArrayList;
import java.util.List;

public class AnnotationParserTest {
    @Test
    public void testParseAnnotationsValid() throws Exception {
        AnnotationParser annotationParser = new AnnotationParser();

        String field = "id:int{@Id};";

        List<Annotation> annotationList = new ArrayList<Annotation>();
        annotationList.add(new Annotation("Id"));

        Assert.assertEquals(annotationList, annotationParser.parseAnnotations(field));
    }

    @Test
    public void testParseWithoutAnotation() {
        AnnotationParser annotationParser = new AnnotationParser();

        String field = "id:int;";

        List<Annotation> annotationList = new ArrayList<Annotation>();

        Assert.assertEquals(annotationList, annotationParser.parseAnnotations(field));
    }

    @Test(expected = EntityParserException.class)
    public void testParseUnknownAnnotation() {
        AnnotationParser annotationParser = new AnnotationParser();

        String field = "id:int{@FalseAnnotation};";
        annotationParser.parseAnnotations(field);
    }

    @Test
    public void testParseAnnotationWithParam() {
        AnnotationParser annotationParser = new AnnotationParser();

        String field = "id:int{@Max(3)};";

        List<Annotation> annotationList = new ArrayList<Annotation>();
        annotationList.add(new Annotation("Max", "3"));

        Assert.assertEquals(annotationList, annotationParser.parseAnnotations(field));
    }

    @Test
    public void testParseMultipleAnnotations() {
        AnnotationParser annotationParser = new AnnotationParser();

        String field = "id:int{@Id,@Max(3)};";

        List<Annotation> annotationList = new ArrayList<Annotation>();
        annotationList.add(new Annotation("Id"));
        annotationList.add(new Annotation("Max", "3"));

        Assert.assertEquals(annotationList, annotationParser.parseAnnotations(field));
    }

    @Test(expected = EntityParserException.class)
    public void testParseMultipleAnnotationsWithWrongSyntax() {
        AnnotationParser annotationParser = new AnnotationParser();

        String field = "id:int{@Id;@Max(3)};";

        List<Annotation> annotationList = new ArrayList<Annotation>();
        annotationList.add(new Annotation("Id"));
        annotationList.add(new Annotation("Max", "3"));

        annotationParser.parseAnnotations(field);
    }

    @Test(expected = EntityParserException.class)
    public void testParseAnnotationWithMissingParameter() {
        AnnotationParser annotationParser = new AnnotationParser();

        String field = "id:int{@Max};";

        annotationParser.parseAnnotations(field);
    }

    @Test(expected = EntityParserException.class)
    public void testParseAnnotationWithEmptyParameter() {
        AnnotationParser annotationParser = new AnnotationParser();

        String field = "id:int{@Max()};";

        annotationParser.parseAnnotations(field);
    }

    @Test(expected = EntityParserException.class)
    public void testParseParameteredAnnotationWithWrongSyntax() {
        AnnotationParser annotationParser = new AnnotationParser();

        String field = "id:int{@Max(3};";

        annotationParser.parseAnnotations(field);
    }

    @Test(expected = EntityParserException.class)
    public void testParseAnnotationWithMissingBracket() {
        AnnotationParser annotationParser = new AnnotationParser();

        String field = "id:int{@Id;";

        annotationParser.parseAnnotations(field);
    }
}
