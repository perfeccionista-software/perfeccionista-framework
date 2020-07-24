package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.TextWebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
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
@Repeatable(UseWebMappedTableColumns.class)
public @interface UseWebMappedTableColumn {

    String name();

    Class<? extends WebMappedBlock> headerClass() default TextWebMappedBlock.class;

    WebLocator headerLocator() default @WebLocator;

    Class<? extends WebMappedBlock> bodyClass() default TextWebMappedBlock.class;

    WebLocator bodyLocator() default @WebLocator;

    Class<? extends WebMappedBlock> footerClass() default TextWebMappedBlock.class;

    WebLocator footerLocator() default @WebLocator;

}