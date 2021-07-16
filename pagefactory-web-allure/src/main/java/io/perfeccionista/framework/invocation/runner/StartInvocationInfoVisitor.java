package io.perfeccionista.framework.invocation.runner;

import io.qameta.allure.model.StepResult;

import java.util.ArrayList;
import java.util.function.Consumer;

import static io.qameta.allure.Allure.getLifecycle;

public class StartInvocationInfoVisitor implements Consumer<InvocationInfo> {

    @Override
    public void accept(InvocationInfo invocationInfo) {
        if (invocationInfo.getResults().isEmpty()) {
            getLifecycle().startStep(invocationInfo.getUuid(),
                    new StepResult().setName(invocationInfo.getInvocationName()));
        }
        getLifecycle().updateStep(invocationInfo.getUuid(), step -> {
            step.setSteps(new ArrayList<>());
        });
    }

}
