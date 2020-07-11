package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.exceptions.NumberValueParseException;
import io.perfeccionista.framework.value.checker.NumberChecker;
import org.jetbrains.annotations.Nullable;

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
            throw new NumberValueParseException(NUMBER_VALUE_TO_DOUBLE_PARSING_FAILED.getMessage(actual));
        }
    }

}
