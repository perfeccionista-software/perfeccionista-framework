package io.perfeccionista.framework.pagefactory.elements.locators;

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
@Repeatable(AndroidLocators.class)
public @interface AndroidLocator {

    String component() default Components.ROOT;

    String id() default "";
    String css() default "";
    String xpath() default "";
    String name() default "";
    String className() default "";
    String tagName() default "";
    String linkText() default "";
    String partialLinkText() default "";

    String accessibilityId() default "";
    String androidDataMatcher() default "";
    String androidUIAutomator() default "";
    String androidViewTag() default "";

    boolean single() default true;

}

