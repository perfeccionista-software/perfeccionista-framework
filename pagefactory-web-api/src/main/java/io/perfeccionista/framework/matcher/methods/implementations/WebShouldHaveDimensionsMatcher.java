package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementDimensions;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetElementBoundsAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_DIMENSIONS_ARE_EQUAL_EXPECTED_DIMENSIONS;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_DIMENSIONS_ARE_NOT_EQUAL_EXPECTED_DIMENSIONS;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;

public class WebShouldHaveDimensionsMatcher implements WebGetElementBoundsAvailableMatcher {

    private final String componentName;
    private final Dimensions2D expectedDimensions;
    private final boolean positive;

    public WebShouldHaveDimensionsMatcher(@NotNull String componentName, @NotNull Dimensions2D expectedDimensions, boolean positive) {
        this.componentName = componentName;
        this.expectedDimensions = expectedDimensions;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebGetElementBoundsAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_HAVE_DIMENSIONS_METHOD, element, componentName, expectedDimensions)
                : assertInvocation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, element, componentName, expectedDimensions);

        runCheck(invocationName,
                () -> {
                    ElementBounds actualElementBounds = element.getElementBounds(componentName);
                    if (positive) {
                        shouldHaveDimensions(element, actualElementBounds.getDimensions(), expectedDimensions, componentName);
                    } else {
                        shouldNotHaveDimensions(element, actualElementBounds.getDimensions(), expectedDimensions, componentName);
                    }
                });
    }

    protected void shouldHaveDimensions(WebGetElementBoundsAvailable element, Dimensions2D actualDimensions, Dimensions2D expectedDimensions, String componentName) {
        if (!expectedDimensions.equals(actualDimensions)) {
            throw WebElementDimensions.assertionError(ELEMENT_DIMENSIONS_ARE_NOT_EQUAL_EXPECTED_DIMENSIONS.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual dimensions", actualDimensions.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected dimensions", expectedDimensions.toString()));
        }
    }

    protected void shouldNotHaveDimensions(WebGetElementBoundsAvailable element, Dimensions2D actualDimensions, Dimensions2D expectedDimensions, String componentName) {
        if (expectedDimensions.equals(actualDimensions)) {
            throw WebElementDimensions.assertionError(ELEMENT_DIMENSIONS_ARE_EQUAL_EXPECTED_DIMENSIONS.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Actual dimensions", actualDimensions.toString()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Expected dimensions", expectedDimensions.toString()));
        }
    }

}
