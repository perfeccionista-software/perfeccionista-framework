package io.perfeccionista.framework.pagefactory.elements.locators;

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

    String component() default Components.ROOT;

    String id() default "";
    String css() default "";
    String xpath() default "";
    String name() default "";
    String className() default "";
    String tagName() default "";
    String linkText() default "";
    String partialLinkText() default "";

    boolean single() default true;
    boolean strictSearch() default true;
    boolean onlyWithinParent() default true;
    Class<? extends ElementCheck>[] elementChecks() default {};

}
