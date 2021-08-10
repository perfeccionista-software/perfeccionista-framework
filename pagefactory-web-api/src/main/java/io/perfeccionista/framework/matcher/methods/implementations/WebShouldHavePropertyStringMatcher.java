package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementPropertyValue;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_CONTAINS_EXPECTED_TEXT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_TEXT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_PROPERTY_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_PROPERTY_TEXT_METHOD;

public class WebShouldHavePropertyStringMatcher implements WebElementPropertyAvailableMatcher {

    private final String propertyName;
    private final String expectedText;
    private final boolean positive;

    public WebShouldHavePropertyStringMatcher(@NotNull String propertyName, @NotNull String expectedText, boolean positive) {
        this.propertyName = propertyName;
        this.expectedText = expectedText;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebElementPropertyAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_PROPERTY_TEXT_METHOD, elementName, propertyName, expectedText)
                : assertInvocation(SHOULD_NOT_HAVE_PROPERTY_TEXT_METHOD, elementName, propertyName, expectedText);

        runCheck(invocationName,
                () -> {
                    String actualPropertyValue = element.getPropertyValue(propertyName);
                    if (positive) {
                        shouldHavePropertyText(element, actualPropertyValue);
                    } else {
                        shouldNotHavePropertyText(element, actualPropertyValue);
                    }
                });
    }

    protected void shouldHavePropertyText(WebElementPropertyAvailable element, String actualPropertyValue) {
        if (!expectedText.equals(actualPropertyValue)) {
            throw WebElementPropertyValue.assertionError(ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_TEXT.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected text", expectedText))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

    protected void shouldNotHavePropertyText(WebElementPropertyAvailable element, String actualPropertyValue) {
        if (expectedText.equals(actualPropertyValue)) {
            throw WebElementPropertyValue.assertionError(ELEMENT_PROPERTY_CONTAINS_EXPECTED_TEXT.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected text", expectedText))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

}
