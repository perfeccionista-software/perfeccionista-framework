package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.DefaultMobileTextBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
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
@Repeatable(UseMappedMobileTableColumns.class)
public @interface UseMappedMobileTableColumn {

    String name();

    Class<? extends MobileBlock> headerClass() default DefaultMobileTextBlock.class;

    AndroidLocator androidHeaderLocator() default @AndroidLocator;

    IosLocator iosHeaderLocator() default @IosLocator;

    Class<? extends MobileBlock> bodyClass() default DefaultMobileTextBlock.class;

    AndroidLocator androidBodyLocator() default @AndroidLocator;

    IosLocator iosBodyLocator() default @IosLocator;

    Class<? extends MobileBlock> footerClass() default DefaultMobileTextBlock.class;

    AndroidLocator androidFooterLocator() default @AndroidLocator;

    IosLocator iosFooterLocator() default @IosLocator;

}
