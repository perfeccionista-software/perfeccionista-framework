package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.attachments.WebAllureAttachmentProcessor;
import io.qameta.allure.model.Status;

import java.util.function.Consumer;

import static io.qameta.allure.Allure.getLifecycle;

public class SuccessInnerInvocationInfoVisitor implements Consumer<InvocationInfo> {
    private final WebAllureAttachmentProcessor attachmentProcessor = new WebAllureAttachmentProcessor(Environment.getCurrent());

    @Override
    public void accept(InvocationInfo invocationInfo) {
        getLifecycle().updateStep(invocationInfo.getUuid(), step -> step.setStatus(Status.PASSED));
        attachmentProcessor.processAttachment(invocationInfo.getAttachment());
        getLifecycle().stopStep(invocationInfo.getUuid());
    }

}

