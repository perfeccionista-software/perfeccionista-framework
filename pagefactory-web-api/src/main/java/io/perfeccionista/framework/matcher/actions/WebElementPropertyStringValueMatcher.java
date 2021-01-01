package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementPropertyValue;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;

public class WebElementPropertyStringValueMatcher implements WebElementPropertyAvailableMatcher {

    private final String propertyName;
    private final StringValue expectedStringValue;
    private final boolean positive;

    public WebElementPropertyStringValueMatcher(@NotNull String propertyName, @NotNull StringValue expectedStringValue, boolean positive) {
        this.propertyName = propertyName;
        this.expectedStringValue = expectedStringValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebElementPropertyAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, this, propertyName, expectedStringValue)
                : assertInvocation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, this, propertyName, expectedStringValue);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    String actualPropertyValue = element.getPropertyValue(propertyName);
                    if (positive) {
                        shouldHavePropertyValue(element, actualPropertyValue);
                    } else {
                        shouldNotHavePropertyValue(element, actualPropertyValue);
                    }
                });
    }

    protected void shouldHavePropertyValue(WebElementPropertyAvailable element, String actualPropertyValue) {
        if (!expectedStringValue.check(actualPropertyValue)) {
            throw WebElementPropertyValue.assertionError(ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected value", expectedStringValue.toString()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

    protected void shouldNotHavePropertyValue(WebElementPropertyAvailable element, String actualPropertyValue) {
        if (expectedStringValue.check(actualPropertyValue)) {
            throw WebElementPropertyValue.assertionError(ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected value", expectedStringValue.toString()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

}
