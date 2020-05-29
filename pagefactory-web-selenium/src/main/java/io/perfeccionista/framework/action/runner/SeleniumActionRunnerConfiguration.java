package io.perfeccionista.framework.action.runner;

import io.perfeccionista.framework.action.wrappers.CheckActionWrapper;
import io.perfeccionista.framework.action.wrappers.LogicActionWrapper;
import org.jetbrains.annotations.NotNull;

public class SeleniumActionRunnerConfiguration implements ActionRunnerConfiguration {

    @Override
    public @NotNull Class<? extends ActionRunner> getActionRunnerImplementation(@NotNull Class<?> actionWrapper) {
        if (CheckActionWrapper.class.equals(actionWrapper)) {
            return SeleniumCheckActionRunner.class;
        }
        if (LogicActionWrapper.class.equals(actionWrapper)) {
            return SeleniumLogicActionRunner.class;
        }
        return EmptyActionRunner.class;
    }

}
