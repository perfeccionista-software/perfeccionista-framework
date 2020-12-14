package io.perfeccionista.framework.value;

import io.perfeccionista.framework.SimpleParallelTest;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(PerfeccionistaExtension.class)
class DoubleValueTest extends SimpleParallelTest {

    @Test
    void doubleValuePositiveTest(ValueService value) {
        assertTrue(value.doubleEquals(58d)
                .check(58d));
        assertTrue(value.doubleGreaterThan(58d)
                .check(158.25d));
        assertTrue(value.doubleGreaterThanOrEqual(58d)
                .check(58d));
        assertTrue(value.doubleGreaterThanOrEqual(58d)
                .check(59.3d));
        assertTrue(value.doubleLessThan(58d)
                .check(57d));
        assertTrue(value.doubleLessThanOrEqual(58d)
                .check(52d));
        assertTrue(value.doubleLessThanOrEqual(58d)
                .check(58d));
        assertTrue(value.doubleNotEquals(58d)
                .check(55d));
    }

    @Test
    void doubleValueNegativeTest(ValueService value) {
        assertFalse(value.doubleEquals(58d)
                .check(55d));
        assertFalse(value.doubleGreaterThan(58d)
                .check(57d));
        assertFalse(value.doubleGreaterThanOrEqual(58d)
                .check(21d));
        assertFalse(value.doubleLessThan(58d)
                .check(159d));
        assertFalse(value.doubleLessThanOrEqual(58d)
                .check(60d));
        assertFalse(value.doubleNotEquals(58d)
                .check(58d));
    }

    @Test
    void doubleValueWithActualValueTransformerTest(ValueService value) {
        assertTrue(value.doubleEquals(58d).transformActual(actual -> actual - 3d)
                .check(61d));
    }

    @Test
    void doubleValueWithExpectedValueTransformerTest(ValueService value) {
        assertTrue(value.doubleEquals(58d).transformExpected(expected -> expected * 2d)
                .check(116d));
    }

}
