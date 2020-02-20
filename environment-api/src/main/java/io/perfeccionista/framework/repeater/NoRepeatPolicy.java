package io.perfeccionista.framework.repeater;

import org.jetbrains.annotations.NotNull;
import org.junit.platform.engine.TestExecutionResult;

import java.util.Deque;

/**
 * TODO: JavaDoc
 */
public class NoRepeatPolicy implements RepeatPolicy {

    @Override
    public @NotNull RepeatCondition getRepeatCondition() {
        return new NoRepeatCondition();
    }

    public static class NoRepeatCondition implements RepeatCondition {

        @Override
        public boolean testCondition(Deque<TestExecutionResult> testResults) {
            return false;
        }

    }

}
