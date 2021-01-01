package io.perfeccionista.framework.repeater;

import io.perfeccionista.framework.repeater.policy.RepeatPolicy;
import org.junit.jupiter.api.TestTemplate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: JavaDoc
 */
@Documented
@TestTemplate
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TestRepeatedOnCondition {

    Class<? extends RepeatPolicy> value() default RepeatPolicy.class;

}
