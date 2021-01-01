package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.WebElementTextValue;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_HAVE_TEXT_METHOD;

public class GetTextMatcher implements GetTextAvailableMatcher {

    private final String expectedText;
    private final boolean positive;

    public GetTextMatcher(@NotNull String expectedText, boolean positive) {
        this.expectedText = expectedText;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull GetTextAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_HAVE_TEXT_METHOD, this, expectedText)
                : assertInvocation(SHOULD_NOT_HAVE_TEXT_METHOD, this, expectedText);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    String actualText = element.getText();
                    if (positive) {
                        shouldHaveText(element, actualText);
                    } else {
                        shouldNotHaveText(element, actualText);
                    }
                });
    }

    protected void shouldHaveText(GetTextAvailable element, String actualText) {
        if (!expectedText.equals(actualText)) {
            throw WebElementTextValue.assertionError(ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, actualText));
        }
    }

    protected void shouldNotHaveText(GetTextAvailable element, String actualText) {
        if (expectedText.equals(actualText)) {
            throw WebElementTextValue.assertionError(ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, actualText));
        }
    }

}
