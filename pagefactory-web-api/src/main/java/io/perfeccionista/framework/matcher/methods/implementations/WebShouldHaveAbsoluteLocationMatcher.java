package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.WebElementLocation;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetElementBoundsAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_LOCATION_IS_EQUAL_EXPECTED_LOCATION;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_LOCATION_IS_NOT_EQUAL_EXPECTED_LOCATION;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_ABSOLUTE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_ABSOLUTE_LOCATION_METHOD;

public class WebShouldHaveAbsoluteLocationMatcher implements WebGetElementBoundsAvailableMatcher {

    private final String componentName;
    private final Point2D expectedLocation;
    private final boolean positive;

    public WebShouldHaveAbsoluteLocationMatcher(@NotNull String componentName, @NotNull Point2D expectedLocation, boolean positive) {
        this.componentName = componentName;
        this.expectedLocation = expectedLocation;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebGetElementBoundsAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_ABSOLUTE_LOCATION_METHOD, elementName, componentName, expectedLocation.toString())
                : assertInvocation(SHOULD_NOT_HAVE_ABSOLUTE_LOCATION_METHOD, elementName, componentName, expectedLocation.toString());

        runCheck(invocationName,
                () -> {
                    ElementBounds actualElementBounds = element.getElementBounds(componentName);
                    if (positive) {
                        shouldHaveLocation(element, actualElementBounds.getAbsoluteLocation(), expectedLocation, componentName);
                    } else {
                        shouldNotHaveLocation(element, actualElementBounds.getAbsoluteLocation(), expectedLocation, componentName);
                    }
                });
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

