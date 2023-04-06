package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.WebElementDimensions;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetElementBoundsAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_DIMENSIONS_ARE_EQUAL_EXPECTED_DIMENSIONS;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_DIMENSIONS_ARE_NOT_EQUAL_EXPECTED_DIMENSIONS;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

public class WebShouldHaveDimensionsElementCondition implements WebElementCondition {

    private String componentName;
    private final Dimensions2D expectedDimensions;
    private boolean positive;

    public WebShouldHaveDimensionsElementCondition(@NotNull Dimensions2D expectedDimensions) {
        this.componentName = ROOT;
        this.expectedDimensions = expectedDimensions;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        ElementBounds actualElementBounds = element.getElementBounds(componentName);
        if (positive) {
            shouldHaveDimensions(element, actualElementBounds.getDimensions(), expectedDimensions, componentName);
        } else {
            shouldNotHaveDimensions(element, actualElementBounds.getDimensions(), expectedDimensions, componentName);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_HAVE_DIMENSIONS_METHOD, elementName, componentName, expectedDimensions.toString())
                : assertInvocation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, elementName, componentName, expectedDimensions.toString());
    }

    @Override
    public WebShouldHaveDimensionsElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebGetElementBoundsAvailable interface
        return this;
    }

    @Override
    public WebShouldHaveDimensionsElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldHaveDimensionsElementCondition inverse() {
        this.positive = !positive;
        return this;
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
