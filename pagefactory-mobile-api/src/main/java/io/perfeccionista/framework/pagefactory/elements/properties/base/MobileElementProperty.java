package io.perfeccionista.framework.pagefactory.elements.properties.base;

import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;

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
@Repeatable(MobileElementProperties.class)
public @interface MobileElementProperty {

    String name();

    AndroidLocator androidLocator() default @AndroidLocator; // локатор по умолчанию указывает на родительский элемент

    Class<? extends MobileElementPropertyExtractor> androidExtractor();

    String[] androidParams() default {};

    IosLocator iosLocator() default @IosLocator; // локатор по умолчанию указывает на родительский элемент

    Class<? extends MobileElementPropertyExtractor> iosExtractor();

    String[] iosParams() default {};

}
