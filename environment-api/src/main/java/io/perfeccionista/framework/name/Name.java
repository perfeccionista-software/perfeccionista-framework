package io.perfeccionista.framework.name;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Repeatable(Names.class)
public @interface Name {

    /**
     * Имя аннотируемой сущности
     */
    String value();

    /**
     * Признак устаревшего имени
     */
    boolean deprecated() default false;

}
