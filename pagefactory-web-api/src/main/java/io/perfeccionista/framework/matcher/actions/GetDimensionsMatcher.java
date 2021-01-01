package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementDimensions;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.measurements.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetDimensionsAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_DIMENSIONS_ARE_EQUAL_EXPECTED_DIMENSIONS;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_DIMENSIONS_ARE_NOT_EQUAL_EXPECTED_DIMENSIONS;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;

public class GetDimensionsMatcher implements GetDimensionsAvailableMatcher {

    private final String componentName;
    private final Dimensions expectedDimensions;
    private final boolean positive;

    public GetDimensionsMatcher(@NotNull String componentName, @NotNull Dimensions expectedDimensions, boolean positive) {
        this.componentName = componentName;
        this.expectedDimensions = expectedDimensions;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull GetDimensionsAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_HAVE_DIMENSIONS_METHOD, element, componentName, expectedDimensions)
                : assertInvocation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, element, componentName, expectedDimensions);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    Dimensions actualDimensions = element.getDimensions(componentName);
                    if (positive) {
                        shouldHaveDimensions(element, actualDimensions, expectedDimensions, componentName);
                    } else {
                        shouldNotHaveDimensions(element, actualDimensions, expectedDimensions, componentName);
                    }
                });
    }

    protected void shouldHaveDimensions(GetDimensionsAvailable element, Dimensions actualDimensions, Dimensions expectedDimensions, String componentName) {
        if (!expectedDimensions.equals(actualDimensions)) {
            throw WebElementDimensions.assertionError(ELEMENT_DIMENSIONS_ARE_NOT_EQUAL_EXPECTED_DIMENSIONS.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual dimensions", actualDimensions.toString()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected dimensions", expectedDimensions.toString()));
        }
    }

    protected void shouldNotHaveDimensions(GetDimensionsAvailable element, Dimensions actualDimensions, Dimensions expectedDimensions, String componentName) {
        if (expectedDimensions.equals(actualDimensions)) {
            throw WebElementDimensions.assertionError(ELEMENT_DIMENSIONS_ARE_EQUAL_EXPECTED_DIMENSIONS.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual dimensions", actualDimensions.toString()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected dimensions", expectedDimensions.toString()));
        }
    }

}
