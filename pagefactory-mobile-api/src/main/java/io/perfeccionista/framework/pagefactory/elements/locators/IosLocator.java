package io.perfeccionista.framework.pagefactory.elements.locators;

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
@Repeatable(IosLocators.class)
public @interface IosLocator {

    String component() default ROOT;

    String accessibilityId() default "";
    String className() default "";
    String id() default "";
    String name() default "";
    String xpath() default "";
    String image() default "";

    String iosClassChain() default "";
    String iosNsPredicate() default "";

    boolean single() default true;
    boolean strictSearch() default true;
    boolean onlyWithinParent() default true;

}
