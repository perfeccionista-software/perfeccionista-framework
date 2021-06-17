package io.perfeccionista.framework.matcher.result.implementations;

import io.perfeccionista.framework.exceptions.WebElementSize;
import io.perfeccionista.framework.exceptions.attachments.SizeAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_SIZE_MATCH;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_SIZE_NOT_MATCH;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_SIZE_METHOD;

public class WebShouldHaveSizeNumberMatcher implements WebIndexesMatcher {

    private final Integer expectedSize;
    private final boolean positive;

    public WebShouldHaveSizeNumberMatcher(int expectedSize, boolean positive) {
        this.expectedSize = expectedSize;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebMultipleIndexedResult<Integer, ? extends WebChildElement> result) {
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_SIZE_METHOD, this, expectedSize)
                : assertInvocation(SHOULD_NOT_HAVE_SIZE_METHOD, this, expectedSize);

        WebChildElement element = result.getElement();

        runCheck(invocationName, () -> {
            int actualSize = result.getSize();
            if (positive) {
                shouldHaveSize(element, actualSize);
            } else {
                shouldNotHaveSize(element, actualSize);
            }
        });
    }

    protected void shouldHaveSize(WebChildElement element, Integer actualSize) {
        if (!expectedSize.equals(actualSize)) {
            throw WebElementSize.assertionError(FILTERED_ELEMENT_SIZE_NOT_MATCH.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(SizeAttachmentEntry.of(expectedSize, actualSize));
        }
    }

    protected void shouldNotHaveSize(WebChildElement element, Integer actualSize) {
        if (expectedSize.equals(actualSize)) {
            throw WebElementSize.assertionError(FILTERED_ELEMENT_SIZE_MATCH.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(SizeAttachmentEntry.of(expectedSize, actualSize));
        }
    }

}
