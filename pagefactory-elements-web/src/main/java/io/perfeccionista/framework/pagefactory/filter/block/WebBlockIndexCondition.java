package io.perfeccionista.framework.pagefactory.filter.block;

import io.perfeccionista.framework.value.number.NumberValue;

public class WebBlockIndexCondition implements WebBlockCondition {

    private final NumberValue<Integer> value;

    public WebBlockIndexCondition(NumberValue<Integer> value) {
        this.value = value;
    }

    @Override
    public WebBlockCondition and(WebBlockCondition condition) {
        return null;
    }

    @Override
    public WebBlockCondition or(WebBlockCondition condition) {
        return null;
    }

}
