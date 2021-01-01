package io.perfeccionista.framework.repeater;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import io.perfeccionista.framework.repeater.policy.RepeatCondition;
import io.perfeccionista.framework.repeater.policy.RepeatMode;
import io.perfeccionista.framework.repeater.policy.RepeatPolicy;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.engine.TestExecutionResult.Status;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.SAME_THREAD)
class RepeatedOnConditionTest extends AbstractParallelTestWithEnvironment {

    private static final AtomicInteger testRepeatCounterForParent = new AtomicInteger(0);

    @Disabled
    @SuppressWarnings("LoopStatementThatDoesntLoop")
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
        public @NotNull RepeatCondition getRepeatCondition() {
            return testResults -> testResults.removeLast().getStatus() != Status.SUCCESSFUL;
        }

        @Override
        public int maxAttempt() {
            return 7;
        }
    }

}
