package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebTextListBlockTextCondition implements WebTextListBlockCondition {

    private final StringValue stringValue;
    private final NumberValue<? extends Number> numberValue;

    public WebTextListBlockTextCondition(StringValue stringValue) {
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebTextListBlockTextCondition(NumberValue<? extends Number> numberValue) {
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebTextListBlockTextCondition inverse() {

        return this;
    }

    @Override
    public WebTextListBlockTextCondition and(WebTextListBlockCondition condition) {
        return null;
    }

    @Override
    public WebTextListBlockTextCondition or(WebTextListBlockCondition condition) {
        return null;
    }

}
