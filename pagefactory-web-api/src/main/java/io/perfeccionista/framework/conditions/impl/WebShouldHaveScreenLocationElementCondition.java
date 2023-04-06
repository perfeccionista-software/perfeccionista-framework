package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.WebElementLocation;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetElementBoundsAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_LOCATION_IS_EQUAL_EXPECTED_LOCATION;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_LOCATION_IS_NOT_EQUAL_EXPECTED_LOCATION;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_SCREEN_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_SCREEN_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

public class WebShouldHaveScreenLocationElementCondition implements WebElementCondition {

    private String componentName;
    private final Point2D expectedLocation;
    private boolean positive;

    public WebShouldHaveScreenLocationElementCondition(@NotNull Point2D expectedLocation) {
        this.componentName = ROOT;
        this.expectedLocation = expectedLocation;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        ElementBounds actualElementBounds = element.getElementBounds(componentName);
        if (positive) {
            shouldHaveLocation(element, actualElementBounds.getScreenLocation(), expectedLocation, componentName);
        } else {
            shouldNotHaveLocation(element, actualElementBounds.getScreenLocation(), expectedLocation, componentName);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_HAVE_SCREEN_LOCATION_METHOD, elementName, componentName, expectedLocation.toString())
                : assertInvocation(SHOULD_NOT_HAVE_SCREEN_LOCATION_METHOD, elementName, componentName, expectedLocation.toString());
    }

    @Override
    public WebShouldHaveScreenLocationElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebGetElementBoundsAvailable interface
        return this;
    }

    @Override
    public WebShouldHaveScreenLocationElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldHaveScreenLocationElementCondition inverse() {
        this.positive = !positive;
        return this;
    }

    protected void shouldHaveLocation(WebGetElementBoundsAvailable element, Point2D actualLocation, Point2D expectedLocation, String componentName) {
        if (!expectedLocation.equals(actualLocation)) {
            throw WebElementLocation.assertionError(ELEMENT_LOCATION_IS_NOT_EQUAL_EXPECTED_LOCATION.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual location", actualLocation.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected location", expectedLocation.toString()));
        }
    }

    protected void shouldNotHaveLocation(WebGetElementBoundsAvailable element, Point2D actualLocation, Point2D expectedLocation, String componentName) {
        if (expectedLocation.equals(actualLocation)) {
            throw WebElementLocation.assertionError(ELEMENT_LOCATION_IS_EQUAL_EXPECTED_LOCATION.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual location", actualLocation.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected location", expectedLocation.toString()));
        }
    }

}
