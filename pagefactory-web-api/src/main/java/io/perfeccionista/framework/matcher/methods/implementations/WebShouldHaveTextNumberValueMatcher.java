package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.WebElementTextValue;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_NUMBER_METHOD;

public class WebShouldHaveTextNumberValueMatcher implements WebGetTextAvailableMatcher {

    private final NumberValue<?> expectedNumberValue;
    private final boolean positive;

    public WebShouldHaveTextNumberValueMatcher(@NotNull NumberValue<?> expectedNumberValue, boolean positive) {
        this.expectedNumberValue = expectedNumberValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebGetTextAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_NUMBER_METHOD, elementName, expectedNumberValue.getShortDescription())
                : assertInvocation(SHOULD_NOT_HAVE_NUMBER_METHOD, elementName, expectedNumberValue.getShortDescription());

        runCheck(invocationName,
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
        if (!expectedNumberValue.checkString(actualText)) {
            throw WebElementTextValue.assertionError(ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumberValue));
        }
    }

    protected void shouldNotHaveText(WebGetTextAvailable element, String actualText) {
        if (expectedNumberValue.checkString(actualText)) {
            throw WebElementTextValue.assertionError(ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumberValue));
        }
    }

}

