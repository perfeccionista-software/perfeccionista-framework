package io.perfeccionista.framework.service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(UseServices.class)
public @interface UseService {

    Class<? extends Service> service();

    Class<? extends ServiceConfiguration> configuration();

    boolean disabled() default false;

    int order() default 0;

}

