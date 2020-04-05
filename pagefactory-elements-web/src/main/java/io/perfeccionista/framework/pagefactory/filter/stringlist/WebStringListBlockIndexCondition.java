package io.perfeccionista.framework.pagefactory.filter.stringlist;

import io.perfeccionista.framework.value.number.NumberValue;

// TODO: Добавить тут возможность делать AND / OR

public class WebStringListBlockIndexCondition implements WebStringListBlockCondition {

    private final NumberValue<Integer> value;

    public WebStringListBlockIndexCondition(NumberValue<Integer> value) {
        this.value = value;
    }

    @Override
    public WebStringListBlockIndexCondition and(WebStringListBlockCondition condition) {
        return null;
    }

    @Override
    public WebStringListBlockIndexCondition or(WebStringListBlockCondition condition) {
        return null;
    }

}
