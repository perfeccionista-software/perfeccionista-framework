package io.perfeccionista.framework.pagefactory.elements.states.base;

import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.states.CheckStringAttributeMobileElementStateExtractor;

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
@Repeatable(MobileElementStates.class)
public @interface MobileElementState {

    String name();


    AndroidLocator androidLocator() default @AndroidLocator; // локатор по умолчанию указывает на родительский элемент

    Class<? extends MobileElementStateExtractor> androidExtractor() default CheckStringAttributeMobileElementStateExtractor.class;

    String[] androidParams() default {};


    IosLocator iosLocator() default @IosLocator; // локатор по умолчанию указывает на родительский элемент

    Class<? extends MobileElementStateExtractor> iosExtractor() default CheckStringAttributeMobileElementStateExtractor.class;

    String[] iosParams() default {};

}

