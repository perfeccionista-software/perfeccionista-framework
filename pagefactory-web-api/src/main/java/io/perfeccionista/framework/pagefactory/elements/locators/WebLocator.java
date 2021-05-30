package io.perfeccionista.framework.pagefactory.elements.locators;

import io.perfeccionista.framework.pagefactory.elements.ElementComponents;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.intellij.lang.annotations.Language;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Этой аннотацией мы задаем локаторы, используемые в элементах
 * При каждом обращении к локатору выполняются проверки, описанные в nodeChecks().
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Repeatable(WebLocators.class)
public @interface WebLocator {

    String component() default ElementComponents.ROOT;

    @Language("selenium-id")
    String id() default "";
    @Language("CSS")
    String css() default "";
    @Language("XPath")
    String xpath() default "";
    @Language("selenium-class")
    String className() default "";
    @Language("selenium-html-tag")
    String tagName() default "";
    @Language("selenium-name")
    String name() default "";
    String dti() default "";
    String text() default "";
    String containsText() default "";

    boolean selfNode() default false;
    boolean single() default true;
    boolean strictSearch() default true;
    boolean onlyWithinParent() default true;
    Class<? extends EndpointHandler<Void>>[] invokeOnCall() default {};

}
