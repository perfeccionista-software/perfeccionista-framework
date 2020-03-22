package io.perfeccionista.framework.pagefactory.filter.stringblock;

import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebStringBlockValueCondition implements WebStringBlockCondition {

    private final StringValue stringValue;
    private final NumberValue<?> numberValue;

    public WebStringBlockValueCondition(StringValue stringValue) {
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebStringBlockValueCondition(NumberValue<?> numberValue) {
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    @Override
    public WebStringBlockValueCondition and(WebStringBlockCondition condition) {
        return null;
    }

    @Override
    public WebStringBlockValueCondition or(WebStringBlockCondition condition) {
        return null;
    }

}
