package io.perfeccionista.framework.service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Как это сочетается с приоритетом внутри конфигурации???
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(SetServiceConfigurations.class)
public @interface SetServiceConfiguration {

    Class<? extends Service> serviceClass();

    Class<? extends ServiceConfiguration> serviceConfigurationClass();

    int order() default 0;

    boolean disabled() default false;

}
