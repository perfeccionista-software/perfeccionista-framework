package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.WebElementColor;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetColorAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_COLOR_IS_EQUAL_EXPECTED_COLOR;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_COLOR_IS_NOT_EQUAL_EXPECTED_COLOR;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

public class WebShouldHaveColorElementCondition implements WebElementCondition {

    private String componentName;
    private final String cssProperty;
    private final Color expectedColor;
    private boolean positive;

    public WebShouldHaveColorElementCondition(@NotNull String cssProperty, @NotNull Color expectedColor) {
        this.componentName = ROOT;
        this.cssProperty = cssProperty;
        this.expectedColor = expectedColor;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        Color actualColor = element.getColor(componentName, cssProperty);
        if (positive) {
            shouldHaveColor(element, actualColor, expectedColor, componentName);
        } else {
            shouldNotHaveColor(element, actualColor, expectedColor, componentName);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_HAVE_COLOR_METHOD, elementName, componentName, cssProperty, expectedColor.toString())
                : assertInvocation(SHOULD_NOT_HAVE_COLOR_METHOD, elementName, componentName, cssProperty, expectedColor.toString());
    }

    @Override
    public WebShouldHaveColorElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebGetColorAvailable interface
        return this;
    }

    @Override
    public WebShouldHaveColorElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldHaveColorElementCondition inverse() {
        this.positive = !positive;
        return this;
    }


    protected void shouldHaveColor(WebGetColorAvailable element, Color actualColor, Color expectedColor, String componentName) {
        if (!expectedColor.equals(actualColor)) {
            throw WebElementColor.assertionError(ELEMENT_COLOR_IS_NOT_EQUAL_EXPECTED_COLOR.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual color", actualColor.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected color", expectedColor.toString()));
        }
    }

    protected void shouldNotHaveColor(WebGetColorAvailable element, Color actualColor, Color expectedColor, String componentName) {
        if (expectedColor.equals(actualColor)) {
            throw WebElementColor.assertionError(ELEMENT_COLOR_IS_EQUAL_EXPECTED_COLOR.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual color", actualColor.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected color", expectedColor.toString()));
        }
    }

}
