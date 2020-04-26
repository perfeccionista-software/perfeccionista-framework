package io.perfeccionista.framework.action.runner;

import io.perfeccionista.framework.action.wrappers.CheckActionWrapper;
import io.perfeccionista.framework.action.wrappers.LogicActionWrapper;
import org.jetbrains.annotations.NotNull;

public class DefaultActionRunnerConfiguration implements ActionRunnerConfiguration {

    @Override
    public @NotNull Class<? extends ActionRunner> getActionRunnerImplementation(@NotNull Class<?> actionWrapper) {
        if (LogicActionWrapper.class.equals(actionWrapper)) {
            return LogicActionRunner.class;
        } else if (CheckActionWrapper.class.equals(actionWrapper)) {
            return CheckActionRunner.class;
        }
        return EmptyActionRunner.class;
    }

}
