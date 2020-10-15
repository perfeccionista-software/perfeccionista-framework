package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.DefaultWebRadioButtonBlock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
public @interface UseMappedWebRadioButtonBlock {

    Class<? extends DefaultWebRadioButtonBlock> value();

}


