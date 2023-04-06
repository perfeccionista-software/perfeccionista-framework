package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementAttributeValue;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.matcher.methods.WebElementAttributeAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementAttributeAvailable;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_ATTRIBUTE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_ATTRIBUTE_NUMBER_METHOD;

@Deprecated
public class WebShouldHavePropertyNumberValueMatcher implements WebElementAttributeAvailableMatcher {

    private final String propertyName;
    private final NumberValue<? extends Number> expectedNumberValue;
    private final boolean positive;

    public WebShouldHavePropertyNumberValueMatcher(@NotNull String propertyName, @NotNull NumberValue<? extends Number> expectedNumberValue, boolean positive) {
        this.propertyName = propertyName;
        this.expectedNumberValue = expectedNumberValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_ATTRIBUTE_NUMBER_METHOD, elementName, propertyName, expectedNumberValue.getShortDescription())
                : assertInvocation(SHOULD_NOT_HAVE_ATTRIBUTE_NUMBER_METHOD, elementName, propertyName, expectedNumberValue.getShortDescription());

        MultipleAttemptInvocationWrapper.repeatInvocation(invocationName,
                () -> {
                    String actualPropertyValue = element.getAttribute(propertyName).orElse("");
                    if (positive) {
                        shouldHavePropertyNumber(element, actualPropertyValue);
                    } else {
                        shouldNotHavePropertyNumber(element, actualPropertyValue);
                    }
                });
    }

    protected void shouldHavePropertyNumber(WebElementAttributeAvailable element, String actualPropertyValue) {
        if (!expectedNumberValue.checkString(actualPropertyValue)) {
            throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedNumberValue.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

    protected void shouldNotHavePropertyNumber(WebElementAttributeAvailable element, String actualPropertyValue) {
        if (expectedNumberValue.checkString(actualPropertyValue)) {
            throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_VALUE.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedNumberValue.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

}
