package io.perfeccionista.framework.invocation.runner;

import io.qameta.allure.model.StepResult;

import java.util.ArrayList;
import java.util.function.Consumer;

import static io.qameta.allure.Allure.getLifecycle;

public class StartInnerInvocationInfoVisitor implements Consumer<InvocationInfo> {
    private final AllureInvocationNameFormatter allureNameFormatter = new AllureInvocationNameFormatter();

    @Override
    public void accept(InvocationInfo invocationInfo) {
        getLifecycle().startStep(invocationInfo.getUuid(),
                new StepResult().setName(invocationInfo.getFormattedName(allureNameFormatter)));
        getLifecycle().updateStep(invocationInfo.getUuid(), step -> {
            step.setSteps(new ArrayList<>());
        });
    }

}

