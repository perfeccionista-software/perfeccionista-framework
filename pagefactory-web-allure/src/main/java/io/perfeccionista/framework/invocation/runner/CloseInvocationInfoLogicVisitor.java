package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.PreconditionViolation;
import io.perfeccionista.framework.exceptions.attachments.WebAllureAttachmentProcessor;
import io.qameta.allure.model.Status;

import java.util.function.Consumer;

import static io.perfeccionista.framework.invocation.runner.InvocationResult.InvocationStatus.EXCEPTION;
import static io.qameta.allure.Allure.getLifecycle;
import static io.qameta.allure.util.ResultsUtils.getStatus;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;

public class CloseInvocationInfoLogicVisitor implements Consumer<InvocationInfo> {
    private final WebAllureAttachmentProcessor attachmentProcessor = new WebAllureAttachmentProcessor(Environment.getCurrent());

    @Override
    public void accept(InvocationInfo invocationInfo) {
        if (EXCEPTION == invocationInfo.getLastStatus()) {
            Throwable throwable = invocationInfo.getResults().getLast()
                    .getThrowable()
                    .orElseThrow(() -> PreconditionViolation.exception("Exception status is set together with the exception"));
            getLifecycle().updateStep(invocationInfo.getUuid(), step -> step
                    .setStatus(getStatus(throwable).orElse(Status.BROKEN))
                    .setStatusDetails(getStatusDetails(throwable).orElse(null)));
        }
        attachmentProcessor.processAttachment(invocationInfo.getAttachment());
        getLifecycle().stopStep(invocationInfo.getUuid());
    }

}
