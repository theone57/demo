package com.all.lin.statrter.sensitive.method2;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataMaskingAnnotationIntrospector extends NopAnnotationIntrospector {

    @Override
    public Object findSerializer(Annotated am) {
        DataMasking annotation = am.getAnnotation(DataMasking.class);
        if (annotation != null) {
            return new DataMaskingSerializer(annotation.maskFunc().operation());
        }
        return null;
    }

}