package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;

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
@Repeatable(MobileElementActions.class)
public @interface MobileElementAction {

    String name();

    Class<? extends EndpointHandler<?>> handler();

}

