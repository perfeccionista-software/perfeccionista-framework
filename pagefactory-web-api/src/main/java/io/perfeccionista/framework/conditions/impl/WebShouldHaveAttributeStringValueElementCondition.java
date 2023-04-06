package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.WebElementAttributeValue;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_VALUE_IS_MISSING;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

public class WebShouldHaveAttributeStringValueElementCondition implements WebElementCondition {

    private String componentName;
    private final String attributeName;
    private final StringValue expectedStringValue;
    private boolean positive;

    public WebShouldHaveAttributeStringValueElementCondition(@NotNull String propertyName, @NotNull StringValue expectedStringValue) {
        this.componentName = ROOT;
        this.attributeName = propertyName;
        this.expectedStringValue = expectedStringValue;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        if (positive) {
            shouldHavePropertyValue(element);
        } else {
            shouldNotHavePropertyValue(element);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_HAVE_ATTRIBUTE_VALUE_METHOD, elementName, componentName, attributeName, expectedStringValue.getShortDescription())
                : assertInvocation(SHOULD_NOT_HAVE_ATTRIBUTE_VALUE_METHOD, elementName, componentName, attributeName, expectedStringValue.getShortDescription());
    }

    @Override
    public WebShouldHaveAttributeStringValueElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebElementAttributeAvailable interface
        return this;
    }

    @Override
    public WebShouldHaveAttributeStringValueElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldHaveAttributeStringValueElementCondition inverse() {
        this.positive = !positive;
        return this;
    }

    protected void shouldHavePropertyValue(WebChildElement element) {
        Optional<String> optionalActualAttributeValue = element.getAttribute(componentName, attributeName);
        if (optionalActualAttributeValue.isPresent()) {
            String actualAttributeValue = optionalActualAttributeValue.get();
            if (!expectedStringValue.check(actualAttributeValue)) {
                throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage(attributeName, componentName))
                        .setProcessed(true)
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedStringValue.toString()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualAttributeValue));
            }
        } else {
            throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_VALUE_IS_MISSING.getMessage(attributeName, element.getElementIdentifier().getLastUsedName(), componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedStringValue.toString()));
        }
    }

    protected void shouldNotHavePropertyValue(WebChildElement element) {
        Optional<String> optionalActualAttributeValue = element.getAttribute(componentName, attributeName);
        if (optionalActualAttributeValue.isPresent()) {
            String actualAttributeValue = optionalActualAttributeValue.get();
            if (expectedStringValue.check(actualAttributeValue)) {
                throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_VALUE.getMessage(attributeName, componentName))
                        .setProcessed(true)
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedStringValue.toString()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualAttributeValue));
            }
        }
    }

}
