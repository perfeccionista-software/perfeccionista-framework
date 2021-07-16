package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementPropertyValue;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;

public class WebShouldHavePropertyNumberValueMatcher implements WebElementPropertyAvailableMatcher {

    private final String propertyName;
    private final NumberValue<? extends Number> expectedNumberValue;
    private final boolean positive;

    public WebShouldHavePropertyNumberValueMatcher(@NotNull String propertyName, @NotNull NumberValue<? extends Number> expectedNumberValue, boolean positive) {
        this.propertyName = propertyName;
        this.expectedNumberValue = expectedNumberValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebElementPropertyAvailable element) {
        var elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, elementName, propertyName, expectedNumberValue.getShortDescription())
                : assertInvocation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, elementName, propertyName, expectedNumberValue.getShortDescription());

        runCheck(invocationName,
                () -> {
                    String actualPropertyValue = element.getPropertyValue(propertyName);
                    if (positive) {
                        shouldHavePropertyNumber(element, actualPropertyValue);
                    } else {
                        shouldNotHavePropertyNumber(element, actualPropertyValue);
                    }
                });
    }

    protected void shouldHavePropertyNumber(WebElementPropertyAvailable element, String actualPropertyValue) {
        if (!expectedNumberValue.checkString(actualPropertyValue)) {
            throw WebElementPropertyValue.assertionError(ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedNumberValue.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

    protected void shouldNotHavePropertyNumber(WebElementPropertyAvailable element, String actualPropertyValue) {
        if (expectedNumberValue.checkString(actualPropertyValue)) {
            throw WebElementPropertyValue.assertionError(ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedNumberValue.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

}
