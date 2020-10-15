package io.perfeccionista.framework.plugin;

import io.perfeccionista.framework.color.Color;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация позволяет выделить вызов метода в коде указанным цветом
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Colorize {

    Class<? extends Color> value();

}
