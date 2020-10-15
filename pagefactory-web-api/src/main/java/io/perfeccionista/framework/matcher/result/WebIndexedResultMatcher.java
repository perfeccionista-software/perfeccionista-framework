package io.perfeccionista.framework.matcher.result;

import io.perfeccionista.framework.exceptions.WebResultVerification;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_DOES_NOT_CONTAIN_EXPECTED_RESULT;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_EXPECTED_RESULT_METHOD;

public class WebIndexedResultMatcher<T> implements WebMultipleIndexedResultMatcher<T> {

    private final T expectedResult;

    public WebIndexedResultMatcher(@NotNull T expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Override
    public void check(@NotNull WebMultipleIndexedResult<T, ? extends WebChildElement> result) {
        InvocationName invocationName = InvocationName.of(SHOULD_HAVE_EXPECTED_RESULT_METHOD, this);

        WebChildElement element = result.getElement();

        runCheck(element.getEnvironment(), invocationName, () -> {
            AtomicBoolean match = new AtomicBoolean(false);
            result.getValues().forEach((index, value) -> {
                if (expectedResult.equals(value)) {
                    match.set(true);
                }
            });
            if (!match.get()) {
                throw WebResultVerification.assertionError(FILTERED_ELEMENT_DOES_NOT_CONTAIN_EXPECTED_RESULT.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
            }
        });
    }

}
