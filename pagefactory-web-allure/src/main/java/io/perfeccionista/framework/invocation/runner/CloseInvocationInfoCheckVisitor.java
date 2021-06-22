package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.PreconditionViolation;
import io.perfeccionista.framework.exceptions.attachments.WebAllureAttachmentProcessor;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaAssertionError;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserService;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.util.Optional;
import java.util.function.Consumer;

import static io.perfeccionista.framework.invocation.runner.InvocationResult.InvocationStatus.EXCEPTION;
import static io.qameta.allure.Allure.getLifecycle;
import static io.qameta.allure.util.ResultsUtils.getStatus;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;

public class CloseInvocationInfoCheckVisitor implements Consumer<InvocationInfo> {
    private final WebAllureAttachmentProcessor attachmentProcessor = new WebAllureAttachmentProcessor(Environment.getCurrent());
    private final AllureInvocationStatisticsFormatter allureStatisticsFormatter = new AllureInvocationStatisticsFormatter();

    @Override
    public void accept(InvocationInfo invocationInfo) {
        if (EXCEPTION == invocationInfo.getLastStatus()) {
            boolean primaryExceptionProcessed = Optional.ofNullable(WebAllureCheckInvocationRunner.primaryExceptionProcessed.get())
                    .orElse(false);
            var throwable = invocationInfo.getResults().getLast()
                    .getThrowable()
                    .orElseThrow(() -> PreconditionViolation.exception("Exception status is set together with the exception"));
            if (!primaryExceptionProcessed) {
                Allure.addAttachment("Execution statistics", invocationInfo.getFormattedStatistics(allureStatisticsFormatter));
                if (throwable instanceof PerfeccionistaRuntimeException) {
                    PerfeccionistaRuntimeException exception = ((PerfeccionistaRuntimeException) throwable);
                    exception.prepareAttachmentDescription();
                    exception.getAttachment().ifPresent(attachmentProcessor::processAttachment);
                }
                if (throwable instanceof PerfeccionistaAssertionError) {
                    PerfeccionistaAssertionError assertionError = ((PerfeccionistaAssertionError) throwable);
                    assertionError.prepareAttachmentDescription();
                    assertionError.getAttachment().ifPresent(attachmentProcessor::processAttachment);
                }
                takeScreenshot(Environment.getCurrent()).ifPresent(screenshot ->
                        Allure.addAttachment("Web Browser Screenshot",
                                "image/png",
                                new ByteArrayInputStream(screenshot.getRaw()),
                                "png"));
                attachmentProcessor.processAttachment(invocationInfo.getAttachment());
            }
            getLifecycle().updateStep(invocationInfo.getUuid(), step -> step
                    .setStatus(getStatus(throwable).orElse(Status.BROKEN))
                    .setStatusDetails(getStatusDetails(throwable).orElse(null)));
        }
        getLifecycle().stopStep(invocationInfo.getUuid());
    }

    protected Optional<Screenshot> takeScreenshot(@NotNull Environment environment) {
        WebBrowserService webBrowserService = environment.getService(WebBrowserService.class);
        if (webBrowserService.isActiveDispatcherRunning()) {
            return Optional.of(webBrowserService.getActiveDispatcher()
                    .window()
                    .getPageScreenshot());
        }
        return Optional.empty();
    }

}


