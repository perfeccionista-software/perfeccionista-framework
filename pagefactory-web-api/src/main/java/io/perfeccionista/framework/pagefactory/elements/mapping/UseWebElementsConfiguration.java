package io.perfeccionista.framework.pagefactory.elements.mapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Устанавливается над классом страницы
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface UseWebElementsConfiguration {

//    Class<? extends ElementsConfiguration> value();

}
