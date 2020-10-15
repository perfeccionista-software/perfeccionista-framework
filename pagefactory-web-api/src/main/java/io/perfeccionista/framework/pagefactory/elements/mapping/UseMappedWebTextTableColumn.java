package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
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
@Repeatable(UseMappedWebTextTableColumns.class)
public @interface UseMappedWebTextTableColumn {

    String name();

    Class<? extends DefaultWebTextBlock> headerClass() default DefaultWebTextBlock.class;

    WebLocator headerLocator() default @WebLocator;

    Class<? extends DefaultWebTextBlock> bodyClass() default DefaultWebTextBlock.class;

    WebLocator bodyLocator() default @WebLocator;

    Class<? extends DefaultWebTextBlock> footerClass() default DefaultWebTextBlock.class;

    WebLocator footerLocator() default @WebLocator;

}
