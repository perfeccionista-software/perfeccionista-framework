package io.perfeccionista.framework.value;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import io.perfeccionista.framework.value.number.NumberValue;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.value.Values.intEquals;
import static io.perfeccionista.framework.value.Values.intGreaterThan;
import static io.perfeccionista.framework.value.Values.intGreaterThanOrEqual;
import static io.perfeccionista.framework.value.Values.intLessThan;
import static io.perfeccionista.framework.value.Values.intLessThanOrEqual;
import static io.perfeccionista.framework.value.Values.intNotEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegerValueTest extends AbstractParallelTestWithEnvironment {

    @Test
    void integerValuePositiveTest(ValueService value) {
        assertAll(
                () -> assertTrue(intEquals(58)
                        .check(58)),
                () -> assertTrue(intGreaterThan(58)
                        .check(158)),
                () -> assertTrue(intGreaterThanOrEqual(58)
                        .check(58)),
                () -> assertTrue(intGreaterThanOrEqual(58)
                        .check(59)),
                () -> assertTrue(intLessThan(58)
                        .check(57)),
                () -> assertTrue(intLessThanOrEqual(58)
                        .check(52)),
                () -> assertTrue(intLessThanOrEqual(58)
                        .check(58)),
                () -> assertTrue(intNotEquals(58)
                        .check(55))
        );
    }

    @Test
    void integerValueNegativeTest(ValueService value) {
        assertAll(
                () -> assertFalse(intEquals(58)
                        .check(55)),
                () -> assertFalse(intGreaterThan(58)
                        .check(57)),
                () -> assertFalse(intGreaterThanOrEqual(58)
                        .check(21)),
                () -> assertFalse(intLessThan(58)
                        .check(159)),
                () -> assertFalse(intLessThanOrEqual(58)
                        .check(60)),
                () -> assertFalse(intNotEquals(58)
                        .check(58))
        );
    }

    @Test
    void integerValueWithActualValueTransformerTest(ValueService value) {
        NumberValue<Integer> expectedValue = intEquals(58)
                .transformActual(actual -> actual - 3);
        assertTrue(expectedValue.check(61));
    }

    @Test
    void integerValueWithExpectedValueTransformerTest(ValueService value) {
        NumberValue<Integer> expectedValue = intEquals(58)
                .transformExpected(expected -> expected * 2);
        assertTrue(expectedValue.check(116));
    }

}
