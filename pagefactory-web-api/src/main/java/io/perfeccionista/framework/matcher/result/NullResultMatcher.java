package io.perfeccionista.framework.matcher.result;

import io.perfeccionista.framework.exceptions.WebResultVerification;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_CONTAINS_NON_NULL_RESULT;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_NULL_RESULT_METHOD;
import static java.util.Objects.nonNull;

public class NullResultMatcher<T> implements WebMultipleIndexedResultMatcher<T> {

    @Override
    public void check(@NotNull WebMultipleIndexedResult<T, ? extends WebChildElement> result) {
        InvocationName invocationName = assertInvocation(SHOULD_HAVE_NULL_RESULT_METHOD, this);

        WebChildElement element = result.getElement();

        runCheck(element.getEnvironment(), invocationName, () -> {
            result.getValues().forEach((index, value) -> {
                if (nonNull(value)) {
                    throw WebResultVerification.assertionError(FILTERED_ELEMENT_CONTAINS_NON_NULL_RESULT.getMessage(index))
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                }
            });
        });
    }

}
