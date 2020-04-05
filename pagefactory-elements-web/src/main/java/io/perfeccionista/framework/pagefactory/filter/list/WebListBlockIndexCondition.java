package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.value.number.NumberValue;

public class WebListBlockIndexCondition implements WebListBlockCondition {

    private final NumberValue<Integer> value;

    public WebListBlockIndexCondition(NumberValue<Integer> value) {
        this.value = value;
    }

    @Override
    public WebListBlockCondition and(WebListBlockCondition condition) {
        return null;
    }

    @Override
    public WebListBlockCondition or(WebListBlockCondition condition) {
        return null;
    }

}
