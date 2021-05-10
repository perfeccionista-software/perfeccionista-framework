package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.WebElementTextValue;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_TEXT_METHOD;

public class WebShouldHaveTextStringMatcher implements WebGetTextAvailableMatcher {

    private final String expectedText;
    private final boolean positive;

    public WebShouldHaveTextStringMatcher(@NotNull String expectedText, boolean positive) {
        this.expectedText = expectedText;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebGetTextAvailable element) {
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

    protected void shouldHaveText(WebGetTextAvailable element, String actualText) {
        if (!expectedText.equals(actualText)) {
            throw WebElementTextValue.assertionError(ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, actualText));
        }
    }

    protected void shouldNotHaveText(WebGetTextAvailable element, String actualText) {
        if (expectedText.equals(actualText)) {
            throw WebElementTextValue.assertionError(ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, actualText));
        }
    }

}
