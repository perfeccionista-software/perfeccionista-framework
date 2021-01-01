package io.perfeccionista.framework.invocation;

import io.perfeccionista.framework.invocation.runner.CheckInvocationRunner;
import io.perfeccionista.framework.invocation.runner.EmptyInvocationRunner;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.runner.LogicInvocationRunner;
import io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper;
import io.perfeccionista.framework.invocation.wrapper.LogicInvocationWrapper;
import org.jetbrains.annotations.NotNull;

// TODO: Добавить возможность переопределять значения из проперти или переменных окружения
public class DefaultInvocationServiceConfiguration implements InvocationServiceConfiguration {

    @Override
    public @NotNull Class<? extends InvocationRunner> getInvocationRunnerImplementation(@NotNull Class<?> invocationWrapper) {
        if (CheckInvocationWrapper.class.equals(invocationWrapper)) {
            return CheckInvocationRunner.class;
        }
        if (LogicInvocationWrapper.class.equals(invocationWrapper)) {
            return LogicInvocationRunner.class;
        }
        return EmptyInvocationRunner.class;
    }
}
