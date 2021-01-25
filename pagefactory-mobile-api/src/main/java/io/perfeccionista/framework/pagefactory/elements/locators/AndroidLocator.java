package io.perfeccionista.framework.pagefactory.elements.locators;

import org.intellij.lang.annotations.Language;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Repeatable(AndroidLocators.class)
public @interface AndroidLocator {

    String component() default ROOT;

    @Language("selenium-id")
    String id() default "";
    @Language("XPath")
    String xpath() default "";
    @Language("selenium-name")
    String name() default "";
    @Language("selenium-html-tag")
    String tagName() default "";
    @Language("selenium-class")
    String className() default "";
    String accessibilityId() default "";

    String text() default "";
    String containsText() default "";

    String androidViewTag() default "";
    String androidDataMatcher() default "";

    boolean single() default true;
    boolean strictSearch() default true;
    boolean onlyWithinParent() default true;

}
