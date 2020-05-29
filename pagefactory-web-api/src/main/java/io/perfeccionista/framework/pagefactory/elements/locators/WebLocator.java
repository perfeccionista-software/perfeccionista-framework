package io.perfeccionista.framework.pagefactory.elements.locators;

import io.perfeccionista.framework.pagefactory.jsfunction.JsFunction;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;

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

    String component() default WebComponents.ROOT;

    String id() default "";
    String css() default "";
    String xpath() default "";
    String className() default "";
    String tagName() default "";
    String name() default "";
    String text() default "";
    String partialText() default "";

    boolean single() default true;
    boolean strictSearch() default true;
    boolean onlyWithinParent() default true;
    Class<? extends JsFunction<Void>>[] invokeOnCall() default {};

}
