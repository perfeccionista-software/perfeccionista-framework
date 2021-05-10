package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;

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
@Repeatable(WebElementActions.class)
public @interface WebElementAction {

    String name();

    Class<? extends EndpointHandler<?>> handler();

}
