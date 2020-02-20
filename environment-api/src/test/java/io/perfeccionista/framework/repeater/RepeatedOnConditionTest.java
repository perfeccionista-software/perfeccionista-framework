package io.perfeccionista.framework.repeater;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.engine.TestExecutionResult.Status;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.SAME_THREAD)
@ExtendWith(PerfeccionistaExtension.class)
class RepeatedOnConditionTest {

    private static final AtomicInteger testRepeatCounterForParent = new AtomicInteger(0);

    @SuppressWarnings("LoopStatementThatDoesntLoop")
    @Disabled
    @TestRepeatedOnCondition(TestRepeatPolicy.class)
    void repeatedTestWithCustomRepeatPolicy() {
        while (testRepeatCounterForParent.getAndIncrement() < 6) {
            throw new RuntimeException("Expected exception");
//            throw new TestAbortedException("Expected exception");
        }
        assertEquals(7, testRepeatCounterForParent.get());
    }

    protected static class TestRepeatPolicy implements RepeatPolicy {

        @Override
        public @NotNull RepeatMode getRepeatMode() {
            return RepeatMode.REPEAT_IF;
        }

        @Override
        public RepeatCondition getRepeatCondition() {
            return testResults -> testResults.removeLast().getStatus() != Status.SUCCESSFUL;
        }

        @Override
        public int maxAttempt() {
            return 7;
        }
    }

}
