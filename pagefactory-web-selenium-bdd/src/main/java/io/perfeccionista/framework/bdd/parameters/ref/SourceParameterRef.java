package io.perfeccionista.framework.bdd.parameters.ref;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation for those membership parameters which cannot be uniquely determined.<br/>
 * Example:
 * <pre>
 *  {@code
 *  @Given("click on {element} if {element} property {property} has {value}")
 *  void someStepDefinition(ElementParameter<Clickable> firstElement,
 *                          ElementParameter secondElement,
 *                          @SourceParameterRef("secondElement") PropertyArgumentHolder firstElementProperty,
 *                          ValueParameter value) { ... }
 *  }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PARAMETER)
public @interface SourceParameterRef {

    String value();

}
