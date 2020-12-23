package io.perfeccionista.framework.value;

import io.perfeccionista.framework.SimpleParallelTest;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(PerfeccionistaExtension.class)
class BigDecimalValueTest extends SimpleParallelTest {

    @Test
    void bigDecimalValuePositiveTest(ValueService value) {
        assertTrue(value.bigDecimalEquals(new BigDecimal("23.58"))
                .check(new BigDecimal("23.58")));
        assertTrue(value.bigDecimalGreaterThan(new BigDecimal("23.58"))
                .check(new BigDecimal("23.59")));
        assertTrue(value.bigDecimalGreaterThanOrEqual(new BigDecimal("23.58"))
                .check(new BigDecimal("23.58")));
        assertTrue(value.bigDecimalGreaterThanOrEqual(new BigDecimal("23.58"))
                .check(new BigDecimal("23.59")));
        assertTrue(value.bigDecimalLessThan(new BigDecimal("23.58"))
                .check(new BigDecimal("22.5")));
        assertTrue(value.bigDecimalLessThanOrEqual(new BigDecimal("23.58"))
                .check(new BigDecimal("23.58")));
        assertTrue(value.bigDecimalLessThanOrEqual(new BigDecimal("23.58"))
                .check(new BigDecimal("23.12")));
        assertTrue(value.bigDecimalNotEquals(new BigDecimal("23.58"))
                .check(new BigDecimal("23.28")));
    }

    @Test
    void bigDecimalValueNegativeTest(ValueService value) {
        assertFalse(value.bigDecimalEquals(new BigDecimal("23.58"))
                .check(new BigDecimal("33.58")));
        assertFalse(value.bigDecimalGreaterThan(new BigDecimal("23.58"))
                .check(new BigDecimal("23.28")));
        assertFalse(value.bigDecimalGreaterThanOrEqual(new BigDecimal("23.58"))
                .check(new BigDecimal("23.18")));
        assertFalse(value.bigDecimalLessThan(new BigDecimal("23.58"))
                .check(new BigDecimal("25.58")));
        assertFalse(value.bigDecimalLessThanOrEqual(new BigDecimal("23.58"))
                .check(new BigDecimal("27.58")));
        assertFalse(value.bigDecimalNotEquals(new BigDecimal("23.58"))
                .check(new BigDecimal("23.58")));
    }

    @Test
    void bigDecimalValueWithActualValueTransformerTest(ValueService value) {
        assertTrue(value.bigDecimalEquals(new BigDecimal("23.58")).transformActual(actual -> actual.subtract(new BigDecimal("3")))
                .check(new BigDecimal("26.58")));
    }

    @Test
    void bigDecimalValueWithExpectedValueTransformerTest(ValueService value) {
        assertTrue(value.bigDecimalEquals(new BigDecimal("23.58")).transformExpected(expected -> expected.multiply(new BigDecimal("2")))
                .check(new BigDecimal("47.16")));
    }

}
