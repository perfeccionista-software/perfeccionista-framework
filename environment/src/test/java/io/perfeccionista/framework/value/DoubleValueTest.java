package io.perfeccionista.framework.value;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import io.perfeccionista.framework.value.number.NumberValue;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.value.Values.doubleEquals;
import static io.perfeccionista.framework.value.Values.doubleGreaterThan;
import static io.perfeccionista.framework.value.Values.doubleGreaterThanOrEqual;
import static io.perfeccionista.framework.value.Values.doubleLessThan;
import static io.perfeccionista.framework.value.Values.doubleLessThanOrEqual;
import static io.perfeccionista.framework.value.Values.doubleNotEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoubleValueTest extends AbstractParallelTestWithEnvironment {

    @Test
    void doubleValuePositiveTest() {
        assertAll(
                () -> assertTrue(doubleEquals(58d)
                        .check(58d)),
                () -> assertTrue(doubleGreaterThan(58d)
                        .check(158.25d)),
                () -> assertTrue(doubleGreaterThanOrEqual(58d)
                        .check(58d)),
                () -> assertTrue(doubleGreaterThanOrEqual(58d)
                        .check(59.3d)),
                () -> assertTrue(doubleLessThan(58d)
                        .check(57d)),
                () -> assertTrue(doubleLessThanOrEqual(58d)
                        .check(52d)),
                () -> assertTrue(doubleLessThanOrEqual(58d)
                        .check(58d)),
                () -> assertTrue(doubleNotEquals(58d)
                        .check(55d))
        );
    }

    @Test
    void doubleValueNegativeTest() {
        assertAll(
                () -> assertFalse(doubleEquals(58d)
                        .check(55d)),
                () -> assertFalse(doubleGreaterThan(58d)
                        .check(57d)),
                () -> assertFalse(doubleGreaterThanOrEqual(58d)
                        .check(21d)),
                () -> assertFalse(doubleLessThan(58d)
                        .check(159d)),
                () -> assertFalse(doubleLessThanOrEqual(58d)
                        .check(60d)),
                () -> assertFalse(doubleNotEquals(58d)
                        .check(58d))
        );
    }

    @Test
    void doubleValueWithActualValueTransformerTest() {
        NumberValue<Double> expectedValue = doubleEquals(58d)
                .transformActual(actual -> actual - 3d);
        assertTrue(expectedValue.check(61d));
    }

    @Test
    void doubleValueWithExpectedValueTransformerTest() {
        NumberValue<Double> expectedValue = doubleEquals(58d)
                .transformExpected(expected -> expected * 2d);
        assertTrue(expectedValue.check(116d));
    }

}
