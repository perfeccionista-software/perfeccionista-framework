package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.WebElementColor;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetColorAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_COLOR_IS_EQUAL_EXPECTED_COLOR;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_COLOR_IS_NOT_EQUAL_EXPECTED_COLOR;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_COLOR_METHOD;

public class WebShouldHaveColorMatcher implements WebGetColorAvailableMatcher {

    private final String componentName;
    private final String cssProperty;
    private final Color expectedColor;
    private final boolean positive;

    public WebShouldHaveColorMatcher(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor, boolean positive) {
        this.componentName = componentName;
        this.cssProperty = cssProperty;
        this.expectedColor = expectedColor;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebGetColorAvailable element) {
        var elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_COLOR_METHOD, elementName, componentName, cssProperty, expectedColor.toString())
                : assertInvocation(SHOULD_NOT_HAVE_COLOR_METHOD, elementName, componentName, cssProperty, expectedColor.toString());

        runCheck(invocationName,
                () -> {
                    Color actualColor = element.getColor(componentName, cssProperty);
                    if (positive) {
                        shouldHaveColor(element, actualColor, expectedColor, componentName);
                    } else {
                        shouldNotHaveColor(element, actualColor, expectedColor, componentName);
                    }
                });
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
