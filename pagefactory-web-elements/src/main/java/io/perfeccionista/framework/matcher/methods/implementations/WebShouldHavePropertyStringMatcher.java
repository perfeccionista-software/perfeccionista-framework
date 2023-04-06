package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementAttributeValue;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.matcher.methods.WebElementAttributeAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_TEXT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_TEXT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_ATTRIBUTE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_ATTRIBUTE_TEXT_METHOD;

@Deprecated
public class WebShouldHavePropertyStringMatcher implements WebElementAttributeAvailableMatcher {

    private final String propertyName;
    private final String expectedText;
    private final boolean positive;

    public WebShouldHavePropertyStringMatcher(@NotNull String propertyName, @NotNull String expectedText, boolean positive) {
        this.propertyName = propertyName;
        this.expectedText = expectedText;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_ATTRIBUTE_TEXT_METHOD, elementName, propertyName, expectedText)
                : assertInvocation(SHOULD_NOT_HAVE_ATTRIBUTE_TEXT_METHOD, elementName, propertyName, expectedText);

        MultipleAttemptInvocationWrapper.repeatInvocation(invocationName,
                () -> {
                    String actualPropertyValue = element.getAttribute(propertyName).orElse("");
                    if (positive) {
                        shouldHavePropertyText(element, actualPropertyValue);
                    } else {
                        shouldNotHavePropertyText(element, actualPropertyValue);
                    }
                });
    }

    protected void shouldHavePropertyText(WebChildElement element, String actualPropertyValue) {
        if (!expectedText.equals(actualPropertyValue)) {
            throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_TEXT.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected text", expectedText))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

    protected void shouldNotHavePropertyText(WebChildElement element, String actualPropertyValue) {
        if (expectedText.equals(actualPropertyValue)) {
            throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_TEXT.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected text", expectedText))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

}
