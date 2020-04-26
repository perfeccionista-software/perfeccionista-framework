package io.perfeccionista.framework.repeater;

import org.junit.jupiter.api.Disabled;
import org.opentest4j.TestAbortedException;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class RepeatedOnConditionTestInheritor extends RepeatedOnConditionTest {

    private static final AtomicInteger testRepeatCounterForInheritor = new AtomicInteger(0);

    @Disabled
    @TestRepeatedOnCondition(TestRepeatPolicy.class)
    void repeatedTestWithCustomRepeatPolicy() {
        while (testRepeatCounterForInheritor.getAndIncrement() < 6) {
            throw new TestAbortedException("Expected exception");
        }
        assertEquals(7, testRepeatCounterForInheritor.get());
    }

}
