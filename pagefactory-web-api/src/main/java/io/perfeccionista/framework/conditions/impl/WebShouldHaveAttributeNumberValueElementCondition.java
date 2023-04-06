package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.WebElementAttributeValue;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementAttributeAvailable;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_VALUE_IS_MISSING;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_ATTRIBUTE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_ATTRIBUTE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

public class WebShouldHaveAttributeNumberValueElementCondition implements WebElementCondition {

    private String componentName;
    private final String attributeName;
    private final NumberValue<? extends Number> expectedNumberValue;
    private boolean positive;

    public WebShouldHaveAttributeNumberValueElementCondition(@NotNull String attributeName, @NotNull NumberValue<? extends Number> expectedNumberValue) {
        this.componentName = ROOT;
        this.attributeName = attributeName;
        this.expectedNumberValue = expectedNumberValue;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        if (positive) {
            shouldHaveAttributeNumber(element);
        } else {
            shouldNotHaveAttributeNumber(element);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_HAVE_ATTRIBUTE_NUMBER_METHOD, elementName, componentName, attributeName, expectedNumberValue.getShortDescription())
                : assertInvocation(SHOULD_NOT_HAVE_ATTRIBUTE_NUMBER_METHOD, elementName, componentName, attributeName, expectedNumberValue.getShortDescription());
    }

    @Override
    public WebShouldHaveAttributeNumberValueElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebElementAttributeAvailable interface
        return this;
    }

    @Override
    public WebShouldHaveAttributeNumberValueElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldHaveAttributeNumberValueElementCondition inverse() {
        this.positive = !positive;
        return this;
    }

    protected void shouldHaveAttributeNumber(WebElementAttributeAvailable element) {
        Optional<String> optionalActualAttributeValue = element.getAttribute(componentName, attributeName);
        if (optionalActualAttributeValue.isPresent()) {
            String actualAttributeValue = optionalActualAttributeValue.get();
            if (!expectedNumberValue.checkString(actualAttributeValue)) {
                throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage(attributeName, componentName))
                        .setProcessed(true)
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedNumberValue.toString()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualAttributeValue));
            }
        } else {
            throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_VALUE_IS_MISSING.getMessage(attributeName, element.getElementIdentifier().getLastUsedName(), componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedNumberValue.toString()));
        }
    }

    protected void shouldNotHaveAttributeNumber(WebElementAttributeAvailable element) {
        Optional<String> optionalActualAttributeValue = element.getAttribute(componentName, attributeName);
        if (optionalActualAttributeValue.isPresent()) {
            String actualAttributeValue = optionalActualAttributeValue.get();
            if (expectedNumberValue.checkString(actualAttributeValue)) {
                throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_VALUE.getMessage(attributeName))
                        .setProcessed(true)
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Expected value", expectedNumberValue.toString()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualAttributeValue));
            }
        }
    }

}
