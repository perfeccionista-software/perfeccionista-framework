package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Repeatable(AndroidElementProperties.class)
public @interface AndroidElementProperty {

    String name();

    AndroidLocator uiLocator();

    Class<? extends ElementPropertyExtractor<MobileChildElement>> extractor();

}