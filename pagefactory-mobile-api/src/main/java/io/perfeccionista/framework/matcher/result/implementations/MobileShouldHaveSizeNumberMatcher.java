package io.perfeccionista.framework.matcher.result.implementations;

import io.perfeccionista.framework.exceptions.MobileElementSize;
import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.SizeAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.FILTERED_ELEMENT_SIZE_MATCH;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.FILTERED_ELEMENT_SIZE_NOT_MATCH;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_SIZE_METHOD;

public class MobileShouldHaveSizeNumberMatcher implements MobileIndexesMatcher {
    private final Integer expectedSize;
    private final boolean positive;
    public MobileShouldHaveSizeNumberMatcher(Integer expectedSize, boolean positive) {
        this.expectedSize = expectedSize;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull MobileMultipleIndexedResult<Integer, ? extends MobileChildElement> result) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_HAVE_SIZE_METHOD, this, expectedSize)
                : assertInvocation(SHOULD_NOT_HAVE_SIZE_METHOD, this, expectedSize);

        MobileChildElement element = result.getElement();

        runCheck(element.getEnvironment(), invocationName, () -> {
            int actualSize = result.getSize();
            if (positive) {
                shouldHaveSize(element, actualSize);
            } else {
                shouldNotHaveSize(element, actualSize);
            }
        });
    }

    protected void shouldHaveSize(MobileChildElement element, Integer actualSize) {
        if (!expectedSize.equals(actualSize)) {
            throw MobileElementSize.assertionError(FILTERED_ELEMENT_SIZE_NOT_MATCH.getMessage(actualSize, expectedSize))
                    .setProcessed(true)
                    .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(SizeAttachmentEntry.of(expectedSize, actualSize));
        }
    }

    protected void shouldNotHaveSize(MobileChildElement element, Integer actualSize) {
        if (expectedSize.equals(actualSize)) {
            throw MobileElementSize.assertionError(FILTERED_ELEMENT_SIZE_MATCH.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(SizeAttachmentEntry.of(expectedSize, actualSize));
        }
    }
}
