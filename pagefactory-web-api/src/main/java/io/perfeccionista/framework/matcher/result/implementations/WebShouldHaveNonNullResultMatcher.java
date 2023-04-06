package io.perfeccionista.framework.matcher.result.implementations;

import io.perfeccionista.framework.exceptions.ResultVerification;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.FILTERED_ELEMENT_CONTAINS_NULL_RESULT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_NOT_NULL_RESULT_METHOD;
import static java.util.Objects.isNull;

public class WebShouldHaveNonNullResultMatcher<T> implements WebMultipleIndexedResultMatcher<T> {

    @Override
    public void check(@NotNull WebMultipleIndexedResult<T, ? extends WebChildElement> result) {
        WebChildElement element = result.getElement();
        String elementName = element.getElementIdentifier().getLastUsedName();

        InvocationInfo invocationName = assertInvocation(SHOULD_HAVE_NOT_NULL_RESULT_METHOD, elementName);

        repeatInvocation(invocationName, () -> {
            result.getResults().forEach((index, value) -> {
                if (isNull(value)) {
                    throw ResultVerification.assertionError(FILTERED_ELEMENT_CONTAINS_NULL_RESULT.getMessage(index))
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                }
            });
        });
    }

}

