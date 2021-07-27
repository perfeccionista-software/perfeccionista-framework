package io.perfeccionista.framework.pagefactory.elements.properties.base;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

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
@Repeatable(WebElementTextProperties.class)
public @interface WebElementTextProperty {

    String name();

    WebLocator locator() default @WebLocator(selfNode = true); // локатор по умолчанию указывает на родительский элемент

}
