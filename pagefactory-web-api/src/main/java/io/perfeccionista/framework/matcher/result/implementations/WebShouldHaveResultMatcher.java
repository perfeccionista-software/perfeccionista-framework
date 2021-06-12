package io.perfeccionista.framework.matcher.result.implementations;

import io.perfeccionista.framework.exceptions.ResultVerification;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_DOES_NOT_CONTAIN_EXPECTED_RESULT;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_EXPECTED_RESULT_METHOD;

public class WebShouldHaveResultMatcher<T> implements WebMultipleIndexedResultMatcher<T> {

    private final T expectedResult;

    public WebShouldHaveResultMatcher(@NotNull T expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Override
    public void check(@NotNull WebMultipleIndexedResult<T, ? extends WebChildElement> result) {
        InvocationName invocationName = assertInvocation(SHOULD_HAVE_EXPECTED_RESULT_METHOD, this);

        WebChildElement element = result.getElement();

        runCheck(invocationName, () -> {
            AtomicBoolean match = new AtomicBoolean(false);
            result.getResults().forEach((index, value) -> {
                if (expectedResult.equals(value)) {
                    match.set(true);
                }
            });
            if (!match.get()) {
                throw ResultVerification.assertionError(FILTERED_ELEMENT_DOES_NOT_CONTAIN_EXPECTED_RESULT.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
            }
        });
    }

}
