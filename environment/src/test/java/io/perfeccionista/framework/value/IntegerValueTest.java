package io.perfeccionista.framework.value;

import io.perfeccionista.framework.SimpleParallelTest;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(PerfeccionistaExtension.class)
public class IntegerValueTest extends SimpleParallelTest {

    @Test
    void integerValuePositiveTest(ValueService value) {
        assertTrue(value.intEquals(58)
                .check(58));
        assertTrue(value.intGreaterThan(58)
                .check(158));
        assertTrue(value.intGreaterThanOrEqual(58)
                .check(58));
        assertTrue(value.intGreaterThanOrEqual(58)
                .check(59));
        assertTrue(value.intLessThan(58)
                .check(57));
        assertTrue(value.intLessThanOrEqual(58)
                .check(52));
        assertTrue(value.intLessThanOrEqual(58)
                .check(58));
        assertTrue(value.intNotEquals(58)
                .check(55));
    }

    @Test
    void integerValueNegativeTest(ValueService value) {
        assertFalse(value.intEquals(58)
                .check(55));
        assertFalse(value.intGreaterThan(58)
                .check(57));
        assertFalse(value.intGreaterThanOrEqual(58)
                .check(21));
        assertFalse(value.intLessThan(58)
                .check(159));
        assertFalse(value.intLessThanOrEqual(58)
                .check(60));
        assertFalse(value.intNotEquals(58)
                .check(58));
    }

    @Test
    void integerValueWithActualValueTransformerTest(ValueService value) {
        assertTrue(value.intEquals(58).transformActual(actual -> actual - 3)
                .check(61));
    }

    @Test
    void integerValueWithExpectedValueTransformerTest(ValueService value) {
        assertTrue(value.intEquals(58).transformExpected(expected -> expected * 2)
                .check(116));
    }

}
