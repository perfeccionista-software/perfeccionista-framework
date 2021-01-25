package io.perfeccionista.framework.pagefactory.elements.interactions.base;

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
@Repeatable(MobileElementInteractions.class)
public @interface MobileElementInteraction {

    String name();

    Class<? extends MobileElementInteractionImplementation> implementation();

}
