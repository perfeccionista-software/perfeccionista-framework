package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
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
@Repeatable(UseMappedWebTableColumns.class)
public @interface UseMappedWebTableColumn {

    String name();

    Class<? extends WebBlock> headerClass() default DefaultWebTextBlock.class;

    WebLocator headerLocator() default @WebLocator;

    Class<? extends WebBlock> bodyClass() default DefaultWebTextBlock.class;

    WebLocator bodyLocator() default @WebLocator;

    Class<? extends WebBlock> footerClass() default DefaultWebTextBlock.class;

    WebLocator footerLocator() default @WebLocator;

}