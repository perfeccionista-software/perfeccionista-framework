package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.WebElementAttributeValue;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementAttributeAvailable;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_TEXT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_TEXT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ATTRIBUTE_VALUE_IS_MISSING;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_ATTRIBUTE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_ATTRIBUTE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

public class WebShouldHaveAttributeStringElementCondition implements WebElementCondition {

    private String componentName;
    private final String attributeName;
    private final String expectedText;
    private boolean positive;

    public WebShouldHaveAttributeStringElementCondition(@NotNull String attributeName, @NotNull String expectedText) {
        this.componentName = ROOT;
        this.attributeName = attributeName;
        this.expectedText = expectedText;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        if (positive) {
            shouldHaveAttributeText(element);
        } else {
            shouldNotHaveAttributeText(element);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_HAVE_ATTRIBUTE_TEXT_METHOD, elementName, componentName, attributeName, expectedText)
                : assertInvocation(SHOULD_NOT_HAVE_ATTRIBUTE_TEXT_METHOD, elementName, componentName, attributeName, expectedText);
    }

    @Override
    public WebShouldHaveAttributeStringElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebElementAttributeAvailable interface
        return this;
    }

    @Override
    public WebShouldHaveAttributeStringElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldHaveAttributeStringElementCondition inverse() {
        this.positive = !positive;
        return this;
    }

    protected void shouldHaveAttributeText(WebElementAttributeAvailable element) {
        Optional<String> optionalActualAttributeValue = element.getAttribute(componentName, attributeName);
        if (optionalActualAttributeValue.isPresent()) {
            String actualAttributeValue = optionalActualAttributeValue.get();
            if (!expectedText.equals(actualAttributeValue)) {
                throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_TEXT.getMessage(attributeName, componentName))
                        .setProcessed(true)
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Expected text", expectedText))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualAttributeValue));
            }
        } else {
            throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_VALUE_IS_MISSING.getMessage(attributeName, element.getElementIdentifier().getLastUsedName(), componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected text", expectedText));
        }
    }

    protected void shouldNotHaveAttributeText(WebElementAttributeAvailable element) {
        Optional<String> optionalActualAttributeValue = element.getAttribute(componentName, attributeName);
        if (optionalActualAttributeValue.isPresent()) {
            String actualAttributeValue = optionalActualAttributeValue.get();
            if (expectedText.equals(actualAttributeValue)) {
                throw WebElementAttributeValue.assertionError(ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_TEXT.getMessage(attributeName))
                        .setProcessed(true)
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Expected text", expectedText))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Actual value", actualAttributeValue));
            }
        }
    }

}
