package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.exceptions.NumberValueNotMatch;
import io.perfeccionista.framework.exceptions.NumberValueParse;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.value.checker.NumberChecker;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.NUMBER_VALUE_NOT_MATCH;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.NUMBER_VALUE_TO_DOUBLE_PARSING_FAILED;

public class DefaultDoubleValue extends AbstractNumberValue<Double> {

    public DefaultDoubleValue(NumberChecker<Double> numberChecker) {
        super(numberChecker);
    }

    @Override
    public boolean checkString(@Nullable String actual) {
        if (actual == null) {
            return check(null);
        }
        try {
            Double actualValue = Double.valueOf(actual);
            return check(actualValue);
        } catch (Exception e) {
            throw NumberValueParse.exception(NUMBER_VALUE_TO_DOUBLE_PARSING_FAILED.getMessage(actual));
        }
    }

    @Override
    public NumberValue<Double> shouldMatch(Double actual) {
        if (check(actual)) {
            return this;
        }
        throw NumberValueNotMatch.assertionError(NUMBER_VALUE_NOT_MATCH.getMessage())
                .addLastAttachmentEntry(ValueAttachmentEntry.of(this));
    }

}
