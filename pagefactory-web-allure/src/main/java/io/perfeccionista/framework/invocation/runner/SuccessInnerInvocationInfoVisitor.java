package io.perfeccionista.framework.invocation.runner;

import io.qameta.allure.model.Status;

import java.util.function.Consumer;

import static io.qameta.allure.Allure.getLifecycle;

public class SuccessInnerInvocationInfoVisitor implements Consumer<InvocationInfo> {

    @Override
    public void accept(InvocationInfo invocationInfo) {
        getLifecycle().updateStep(invocationInfo.getUuid(), step -> step.setStatus(Status.PASSED));
        getLifecycle().stopStep(invocationInfo.getUuid());
    }

}

