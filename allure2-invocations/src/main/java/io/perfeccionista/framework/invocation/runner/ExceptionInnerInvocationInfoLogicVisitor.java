package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.exceptions.PreconditionViolation;
import io.qameta.allure.model.Status;

import java.util.function.Consumer;

import static io.qameta.allure.Allure.getLifecycle;
import static io.qameta.allure.util.ResultsUtils.getStatus;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;

public class ExceptionInnerInvocationInfoLogicVisitor implements Consumer<InvocationInfo> {

    @Override
    public void accept(InvocationInfo invocationInfo) {
        Throwable throwable = invocationInfo.getCurrent()
                .orElseThrow(() -> PreconditionViolation.exception("On stage 'exception' current result can't be null"))
                .getThrowable()
                .orElseThrow(() -> PreconditionViolation.exception("Exception status is set together with the exception"));
        getLifecycle().updateStep(invocationInfo.getUuid(), step -> step
                .setStatus(getStatus(throwable).orElse(Status.BROKEN))
                .setStatusDetails(getStatusDetails(throwable).orElse(null)));
        getLifecycle().stopStep(invocationInfo.getUuid());
    }

}

