package io.perfeccionista.framework.invocation.runner;

import io.qameta.allure.model.StepResult;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static io.qameta.allure.Allure.getLifecycle;

public class StartInvocationInfoVisitor implements Consumer<InvocationInfo> {

    private final String defaultStepName;

    public StartInvocationInfoVisitor(@NotNull String defaultStepName) {
        this.defaultStepName = defaultStepName;
    }

    @Override
    public void accept(InvocationInfo invocationInfo) {
        if (invocationInfo.getResults().isEmpty()) {
            getLifecycle().startStep(invocationInfo.getUuid(), new StepResult().setName(defaultStepName));
        }
    }

}
