package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.WebElementLabelValue;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.matcher.methods.WebGetLabelAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_LABEL_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;

@Deprecated
public class WebShouldHaveLabelStringMatcher implements WebGetLabelAvailableMatcher {

    private final String expectedText;
    private final boolean positive;

    public WebShouldHaveLabelStringMatcher(@NotNull String expectedText, boolean positive) {
        this.expectedText = expectedText;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebGetTextAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_TEXT_LABEL_METHOD, elementName, expectedText)
                : assertInvocation(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, elementName, expectedText);

        MultipleAttemptInvocationWrapper.repeatInvocation(invocationName,
                () -> {
                    String actualLabel = element.getText();
                    if (positive) {
                        shouldHaveLabel(element, actualLabel);
                    } else {
                        shouldNotHaveLabel(element, actualLabel);
                    }
                });
    }

    protected void shouldHaveLabel(WebGetTextAvailable element, String actualLabel) {
        if (!expectedText.equals(actualLabel)) {
            throw WebElementLabelValue.assertionError(ELEMENT_LABEL_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, actualLabel));
        }
    }

    protected void shouldNotHaveLabel(WebGetTextAvailable element, String actualLabel) {
        if (expectedText.equals(actualLabel)) {
            throw WebElementLabelValue.assertionError(ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, actualLabel));
        }
    }

}

