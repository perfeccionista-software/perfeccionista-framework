package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.WebElementColor;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.GetColorAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_COLOR_IS_EQUAL_EXPECTED_COLOR;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_COLOR_IS_NOT_EQUAL_EXPECTED_COLOR;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_HAVE_COLOR_METHOD;

public class GetColorMatcher implements GetColorAvailableMatcher {

    private final String componentName;
    private final String cssProperty;
    private final Color expectedColor;
    private final boolean positive;

    public GetColorMatcher(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor, boolean positive) {
        this.componentName = componentName;
        this.cssProperty = cssProperty;
        this.expectedColor = expectedColor;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull GetColorAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_HAVE_COLOR_METHOD, element, componentName, cssProperty, expectedColor)
                : assertInvocation(SHOULD_NOT_HAVE_COLOR_METHOD, element, componentName, cssProperty, expectedColor);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    Color actualColor = element.getColor(componentName, cssProperty);
                    if (positive) {
                        shouldHaveColor(element, actualColor, expectedColor, componentName);
                    } else {
                        shouldNotHaveColor(element, actualColor, expectedColor, componentName);
                    }
                });
    }

    protected void shouldHaveColor(GetColorAvailable element, Color actualColor, Color expectedColor, String componentName) {
        if (!expectedColor.equals(actualColor)) {
            throw WebElementColor.assertionError(ELEMENT_COLOR_IS_NOT_EQUAL_EXPECTED_COLOR.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual color", actualColor.toString()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected color", expectedColor.toString()));
        }
    }

    protected void shouldNotHaveColor(GetColorAvailable element, Color actualColor, Color expectedColor, String componentName) {
        if (expectedColor.equals(actualColor)) {
            throw WebElementColor.assertionError(ELEMENT_COLOR_IS_EQUAL_EXPECTED_COLOR.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual color", actualColor.toString()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected color", expectedColor.toString()));
        }
    }

}
