package io.perfeccionista.framework.pagefactory.elements.selectors;

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
@Repeatable(WebSelectors.class)
public @interface WebSelector {

    boolean selfNode() default false;

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

    boolean single() default true;
    boolean strictSearch() default true;
    boolean fromParent() default true;
    boolean onlyWithinParent() default false;
    Class<? extends EndpointHandler<Void>>[] invokeOnCall() default {};

}
