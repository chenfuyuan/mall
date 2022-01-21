package com.learn.project.common.utils;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import java.io.Serializable;

/**
 *
 *
 * @author chenfuyuan
 * @date 2022/1/21 18:48
 */
public class DateFormatterAnnotationIntrospector extends JacksonAnnotationIntrospector{


    private static final long serialVersionUID = 4725634584748276395L;

    @Override
    public Object findSerializer(Annotated annotated) {
        DateFormat format = annotated.getAnnotation(DateFormat.class);
        if (format != null) {
            return new DateSerializer(format.pattern());
        }

        return super.findSerializer(annotated);
    }
}
