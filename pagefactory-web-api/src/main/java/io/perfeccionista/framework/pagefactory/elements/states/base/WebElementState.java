package io.perfeccionista.framework.pagefactory.elements.states.base;

import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;
import io.perfeccionista.framework.pagefactory.elements.states.IsPresentStateExtractor;

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
@Repeatable(WebElementStates.class)
public @interface WebElementState {

    String name();

    WebSelector selector() default @WebSelector; // локатор по умолчанию указывает на родительский элемент

    Class<? extends WebElementStateExtractor> extractor() default IsPresentStateExtractor.class;

}

