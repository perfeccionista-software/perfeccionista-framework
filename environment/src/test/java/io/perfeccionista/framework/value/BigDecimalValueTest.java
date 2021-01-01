package io.perfeccionista.framework.value;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import io.perfeccionista.framework.value.number.NumberValue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.perfeccionista.framework.value.Values.bigDecimalEquals;
import static io.perfeccionista.framework.value.Values.bigDecimalGreaterThan;
import static io.perfeccionista.framework.value.Values.bigDecimalGreaterThanOrEqual;
import static io.perfeccionista.framework.value.Values.bigDecimalLessThan;
import static io.perfeccionista.framework.value.Values.bigDecimalLessThanOrEqual;
import static io.perfeccionista.framework.value.Values.bigDecimalNotEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BigDecimalValueTest extends AbstractParallelTestWithEnvironment {

    @Test
    void bigDecimalValuePositiveTest() {
        assertAll(
                () -> assertTrue(bigDecimalEquals(new BigDecimal("23.58"))
                        .check(new BigDecimal("23.58"))),
                () -> assertTrue(bigDecimalGreaterThan(new BigDecimal("23.58"))
                        .check(new BigDecimal("23.59"))),
                () -> assertTrue(bigDecimalGreaterThanOrEqual(new BigDecimal("23.58"))
                        .check(new BigDecimal("23.58"))),
                () -> assertTrue(bigDecimalGreaterThanOrEqual(new BigDecimal("23.58"))
                        .check(new BigDecimal("23.59"))),
                () -> assertTrue(bigDecimalLessThan(new BigDecimal("23.58"))
                        .check(new BigDecimal("22.5"))),
                () -> assertTrue(bigDecimalLessThanOrEqual(new BigDecimal("23.58"))
                        .check(new BigDecimal("23.58"))),
                () -> assertTrue(bigDecimalLessThanOrEqual(new BigDecimal("23.58"))
                        .check(new BigDecimal("23.12"))),
                () -> assertTrue(bigDecimalNotEquals(new BigDecimal("23.58"))
                        .check(new BigDecimal("23.28")))
        );
    }

    @Test
    void bigDecimalValueNegativeTest() {
        assertAll(
                () -> assertFalse(bigDecimalEquals(new BigDecimal("23.58"))
                        .check(new BigDecimal("33.58"))),
                () -> assertFalse(bigDecimalGreaterThan(new BigDecimal("23.58"))
                        .check(new BigDecimal("23.28"))),
                () -> assertFalse(bigDecimalGreaterThanOrEqual(new BigDecimal("23.58"))
                        .check(new BigDecimal("23.18"))),
                () -> assertFalse(bigDecimalLessThan(new BigDecimal("23.58"))
                        .check(new BigDecimal("25.58"))),
                () -> assertFalse(bigDecimalLessThanOrEqual(new BigDecimal("23.58"))
                        .check(new BigDecimal("27.58"))),
                () -> assertFalse(bigDecimalNotEquals(new BigDecimal("23.58"))
                        .check(new BigDecimal("23.58")))
        );
    }

    @Test
    void bigDecimalValueWithActualValueTransformerTest() {
        NumberValue<BigDecimal> expectedValue = bigDecimalEquals(new BigDecimal("23.58"))
                .transformActual(actual -> actual.subtract(new BigDecimal("3")));
        assertTrue(expectedValue.check(new BigDecimal("26.58")));
    }

    @Test
    void bigDecimalValueWithExpectedValueTransformerTest(ValueService value) {
        NumberValue<BigDecimal> expectedValue = bigDecimalEquals(new BigDecimal("23.58"))
                .transformExpected(expected -> expected.multiply(new BigDecimal("2")));
        assertTrue(expectedValue.check(new BigDecimal("47.16")));
    }

}
