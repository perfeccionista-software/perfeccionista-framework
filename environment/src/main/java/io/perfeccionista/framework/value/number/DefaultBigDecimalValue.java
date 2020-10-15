package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.exceptions.NumberValueParse;
import io.perfeccionista.framework.value.checker.NumberChecker;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.NUMBER_VALUE_TO_BIG_DECIMAL_PARSING_FAILED;

public class DefaultBigDecimalValue extends AbstractNumberValue<BigDecimal> {

    public DefaultBigDecimalValue(NumberChecker<BigDecimal> numberChecker) {
        super(numberChecker);
    }

    @Override
    public boolean checkString(@Nullable String actual) {
        if (actual == null) {
            return check(null);
        }
        try {
            BigDecimal actualValue = new BigDecimal(actual);
            return check(actualValue);
        } catch (Exception e) {
            throw NumberValueParse.exception(NUMBER_VALUE_TO_BIG_DECIMAL_PARSING_FAILED.getMessage(actual));
        }
    }

}
