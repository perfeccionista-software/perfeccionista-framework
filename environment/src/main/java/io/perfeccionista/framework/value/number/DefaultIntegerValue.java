package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.exceptions.NumberValueParse;
import io.perfeccionista.framework.value.checker.NumberChecker;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.NUMBER_VALUE_TO_INTEGER_PARSING_FAILED;

public class DefaultIntegerValue extends AbstractNumberValue<Integer> {

    public DefaultIntegerValue(NumberChecker<Integer> numberChecker) {
        super(numberChecker);
    }

    @Override
    public boolean checkString(@Nullable String actual) {
        if (actual == null) {
            return check(null);
        }
        try {
            Integer actualValue = Integer.valueOf(actual);
            return check(actualValue);
        } catch (Exception e) {
            throw NumberValueParse.exception(NUMBER_VALUE_TO_INTEGER_PARSING_FAILED.getMessage(actual));
        }
    }

}
