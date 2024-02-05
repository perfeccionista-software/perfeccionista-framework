package io.perfeccionista.framework.value;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObjectValueTest extends AbstractParallelTestWithEnvironment {

    private static final Integer expectedValue = Integer.valueOf(777);
    private static final Integer actualEqualsValue = Integer.valueOf(777);
    private static final Integer actualNotEqualsValue = Integer.valueOf(333);
    private static final Integer actualSameValue = expectedValue;

    @Test
    void objectValuePositiveTest(ValueService value) {
        assertAll(
                () -> assertTrue(value.objectEquals(expectedValue)
                        .check(actualEqualsValue)),
                () -> assertTrue(value.objectSame(expectedValue)
                        .check(actualSameValue))
        );
    }

    @Test
    void objectValueNegativeTest(ValueService value) {
        assertAll(
                () -> assertFalse(value.objectEquals(expectedValue)
                        .check(actualNotEqualsValue)),
                () -> assertFalse(value.objectSame(expectedValue)
                        .check(actualEqualsValue))
        );
    }

    @Test
    void objectValueInversePositiveTest(ValueService value) {
        assertAll(
                () -> assertFalse(value.objectEquals(expectedValue).inverse()
                        .check(actualEqualsValue)),
                () -> assertFalse(value.objectSame(expectedValue).inverse()
                        .check(actualSameValue))
        );
    }

    @Test
    void objectValueInverseNegativeTest(ValueService value) {
        assertAll(
                () -> assertTrue(value.objectEquals(expectedValue).inverse()
                        .check(actualNotEqualsValue)),
                () -> assertTrue(value.objectSame(expectedValue).inverse()
                        .check(actualEqualsValue))
        );
    }


}
