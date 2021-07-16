package io.perfeccionista.framework.matcher.result.implementations;

import io.perfeccionista.framework.exceptions.ResultVerification;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.FILTERED_ELEMENT_CONTAINS_NON_NULL_RESULT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_NULL_RESULT_METHOD;
import static java.util.Objects.nonNull;

public class WebShouldHaveNullResultMatcher<T> implements WebMultipleIndexedResultMatcher<T> {

    @Override
    public void check(@NotNull WebMultipleIndexedResult<T, ? extends WebChildElement> result) {
        WebChildElement element = result.getElement();
        var elementName = element.getElementIdentifier().getLastUsedName();

        InvocationInfo invocationName = assertInvocation(SHOULD_HAVE_NULL_RESULT_METHOD, elementName);

        runCheck(invocationName, () -> {
            result.getResults().forEach((index, value) -> {
                if (nonNull(value)) {
                    throw ResultVerification.assertionError(FILTERED_ELEMENT_CONTAINS_NON_NULL_RESULT.getMessage(index))
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                }
            });
        });
    }

}
