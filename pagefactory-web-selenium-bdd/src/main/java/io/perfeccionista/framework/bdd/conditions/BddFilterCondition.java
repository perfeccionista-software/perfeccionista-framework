package io.perfeccionista.framework.bdd.conditions;

import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface BddFilterCondition {

    Class<? extends WebFilterBuilder<?, ?>> value();

}
