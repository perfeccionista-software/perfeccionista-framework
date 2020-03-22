package io.perfeccionista.framework.pagefactory.filter.stringblock;

import io.perfeccionista.framework.value.number.NumberValue;

// TODO: Добавить тут возможность делать AND / OR

public class WebStringBlockIndexCondition implements WebStringBlockCondition {

    private final NumberValue<Integer> value;

    public WebStringBlockIndexCondition(NumberValue<Integer> value) {
        this.value = value;
    }

    @Override
    public WebStringBlockIndexCondition and(WebStringBlockCondition condition) {
        return null;
    }

    @Override
    public WebStringBlockIndexCondition or(WebStringBlockCondition condition) {
        return null;
    }

}
