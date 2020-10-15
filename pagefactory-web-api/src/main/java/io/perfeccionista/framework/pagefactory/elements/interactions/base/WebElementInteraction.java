package io.perfeccionista.framework.pagefactory.elements.interactions.base;

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
@Repeatable(WebElementInteractions.class)
public @interface WebElementInteraction {

    String name();

    Class<? extends WebElementInteractionImplementation> implementation();

}
