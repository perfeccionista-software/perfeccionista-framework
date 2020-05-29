package io.perfeccionista.framework.action.runner;

import org.jetbrains.annotations.NotNull;

public class DefaultActionRunnerConfiguration implements ActionRunnerConfiguration {

    @Override
    public @NotNull Class<? extends ActionRunner> getActionRunnerImplementation(@NotNull Class<?> actionWrapper) {
        return EmptyActionRunner.class;
    }

}
