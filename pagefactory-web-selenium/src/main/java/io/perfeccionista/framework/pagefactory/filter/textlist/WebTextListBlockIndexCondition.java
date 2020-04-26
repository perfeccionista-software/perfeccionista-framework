package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.value.number.NumberValue;

public class WebTextListBlockIndexCondition implements WebTextListBlockCondition {

    private final NumberValue<Integer> value;

    public WebTextListBlockIndexCondition(NumberValue<Integer> value) {
        this.value = value;
    }

    @Override
    public WebTextListBlockIndexCondition and(WebTextListBlockCondition condition) {
        return null;
    }

    @Override
    public WebTextListBlockIndexCondition or(WebTextListBlockCondition condition) {
        return null;
    }

}
