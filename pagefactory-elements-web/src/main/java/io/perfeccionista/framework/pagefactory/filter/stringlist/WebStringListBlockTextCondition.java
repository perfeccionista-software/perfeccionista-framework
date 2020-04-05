package io.perfeccionista.framework.pagefactory.filter.stringlist;

import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebStringListBlockTextCondition implements WebStringListBlockCondition {

    private final StringValue stringValue;
    private final NumberValue<? extends Number> numberValue;

    public WebStringListBlockTextCondition(StringValue stringValue) {
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebStringListBlockTextCondition(NumberValue<? extends Number> numberValue) {
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    @Override
    public WebStringListBlockTextCondition and(WebStringListBlockCondition condition) {
        return null;
    }

    @Override
    public WebStringListBlockTextCondition or(WebStringListBlockCondition condition) {
        return null;
    }

}
