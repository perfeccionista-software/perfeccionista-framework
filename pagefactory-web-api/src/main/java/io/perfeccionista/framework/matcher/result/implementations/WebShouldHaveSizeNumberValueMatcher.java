package io.perfeccionista.framework.matcher.result.implementations;

import io.perfeccionista.framework.exceptions.WebElementSize;
import io.perfeccionista.framework.exceptions.attachments.SizeAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_SIZE_MATCH;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_SIZE_NOT_MATCH;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_SIZE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_SIZE_VALUE_METHOD;

public class WebShouldHaveSizeNumberValueMatcher implements WebIndexesMatcher {

    private final NumberValue<Integer> expectedValue;
    private final boolean positive;

    public WebShouldHaveSizeNumberValueMatcher(@NotNull NumberValue<Integer> expectedValue, boolean positive) {
        this.expectedValue = expectedValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebMultipleIndexedResult<Integer, ? extends WebChildElement> result) {
        WebChildElement element = result.getElement();
        String elementName = element.getElementIdentifier().getLastUsedName();

        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_SIZE_VALUE_METHOD, elementName, expectedValue.getShortDescription())
                : assertInvocation(SHOULD_NOT_HAVE_SIZE_VALUE_METHOD, elementName, expectedValue.getShortDescription());


        repeatInvocation(invocationName, () -> {
            int actualSize = result.getSize();
            if (positive) {
                shouldHaveSize(element, actualSize);
            } else {
                shouldNotHaveSize(element, actualSize);
            }
        });
    }

    protected void shouldHaveSize(WebChildElement element, Integer actualSize) {
        if (!expectedValue.check(actualSize)) {
            throw WebElementSize.assertionError(FILTERED_ELEMENT_SIZE_NOT_MATCH.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(SizeAttachmentEntry.of(expectedValue));
        }
    }

    protected void shouldNotHaveSize(WebChildElement element, Integer actualSize) {
        if (expectedValue.check(actualSize)) {
            throw WebElementSize.assertionError(FILTERED_ELEMENT_SIZE_MATCH.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(SizeAttachmentEntry.of(expectedValue));
        }
    }

}
