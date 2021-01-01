package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementPropertyValue;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;

public class WebElementPropertyNumberValueMatcher implements WebElementPropertyAvailableMatcher {

    private final String propertyName;
    private final NumberValue<? extends Number> expectedNumberValue;
    private final boolean positive;

    public WebElementPropertyNumberValueMatcher(@NotNull String propertyName, @NotNull NumberValue<? extends Number> expectedNumberValue, boolean positive) {
        this.propertyName = propertyName;
        this.expectedNumberValue = expectedNumberValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebElementPropertyAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, this, propertyName, expectedNumberValue)
                : assertInvocation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, this, propertyName, expectedNumberValue);

        runCheck(element.getEnvironment(), invocationName,
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
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected value", expectedNumberValue.toString()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

    protected void shouldNotHavePropertyNumber(WebElementPropertyAvailable element, String actualPropertyValue) {
        if (expectedNumberValue.checkString(actualPropertyValue)) {
            throw WebElementPropertyValue.assertionError(ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected value", expectedNumberValue.toString()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

}
