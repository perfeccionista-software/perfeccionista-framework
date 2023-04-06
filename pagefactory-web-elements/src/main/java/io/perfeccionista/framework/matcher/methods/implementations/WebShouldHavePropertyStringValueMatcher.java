package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementAttributeValue;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.matcher.methods.WebElementAttributeAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_ATTRIBUTE_VALUE_METHOD;

@Deprecated
public class WebShouldHavePropertyStringValueMatcher implements WebElementAttributeAvailableMatcher {

    private final String propertyName;
    private final StringValue expectedStringValue;
    private final boolean positive;

    public WebShouldHavePropertyStringValueMatcher(@NotNull String propertyName, @NotNull StringValue expectedStringValue, boolean positive) {
        this.propertyName = propertyName;
        this.expectedStringValue = expectedStringValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_ATTRIBUTE_VALUE_METHOD, elementName, propertyName, expectedStringValue.getShortDescription())
                : assertInvocation(SHOULD_NOT_HAVE_ATTRIBUTE_VALUE_METHOD, elementName, propertyName, expectedStringValue.getShortDescription());

        MultipleAttemptInvocationWrapper.repeatInvocation(invocationName,
                () -> {
                    String actualPropertyValue = element.getAttribute(propertyName).orElse("");
                    if (positive) {
                        shouldHavePropertyValue(element, actualPropertyValue);
                    } else {
                        shouldNotHavePropertyValue(element, actualPropertyValue);
                    }
                });
    }

    protected void shouldHavePropertyValue(WebChildElement element, String actualPropertyValue) {
        if (!expectedStringValue.check(actualPropertyValue)) {
            throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedStringValue.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

    protected void shouldNotHavePropertyValue(WebChildElement element, String actualPropertyValue) {
        if (expectedStringValue.check(actualPropertyValue)) {
            throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_VALUE.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedStringValue.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

}
