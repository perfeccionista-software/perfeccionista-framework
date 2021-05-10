package io.perfeccionista.framework.fixture;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface FixtureParameter {

    String name();

    String description() default "";

    String examples() default "";

    boolean required() default false;

}
