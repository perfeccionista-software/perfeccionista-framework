package io.perfeccionista.framework.pagefactory.elements.properties.base;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.properties.StringAttributeWebElementPropertyExtractor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO JavaDoc + Inherited
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Repeatable(WebElementProperties.class)
public @interface WebElementProperty {

    String name();

    WebLocator locator() default @WebLocator; // локатор по умолчанию указывает на родительский элемент

    Class<? extends WebElementPropertyExtractor> extractor() default StringAttributeWebElementPropertyExtractor.class;

    String[] params() default {};

}
