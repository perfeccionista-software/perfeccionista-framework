package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementLocation;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLocationAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_LOCATION_IS_EQUAL_EXPECTED_LOCATION;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_LOCATION_IS_NOT_EQUAL_EXPECTED_LOCATION;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_HAVE_LOCATION_METHOD;

public class GetLocationMatcher implements GetLocationAvailableMatcher {

    private final String componentName;
    private final Location expectedLocation;
    private final boolean positive;

    public GetLocationMatcher(@NotNull String componentName, @NotNull Location expectedLocation, boolean positive) {
        this.componentName = componentName;
        this.expectedLocation = expectedLocation;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull GetLocationAvailable element) {
        InvocationName invocationName = positive
                ? InvocationName.of(SHOULD_HAVE_LOCATION_METHOD, element, componentName, expectedLocation)
                : InvocationName.of(SHOULD_NOT_HAVE_LOCATION_METHOD, element, componentName, expectedLocation);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    Location actualLocation = element.getActionImplementation(GET_LOCATION_METHOD, Location.class)
                            .execute(element, componentName);
                    if (positive) {
                        shouldHaveLocation(element, actualLocation, expectedLocation, componentName);
                    } else {
                        shouldNotHaveLocation(element, actualLocation, expectedLocation, componentName);
                    }
                });
    }

    protected void shouldHaveLocation(GetLocationAvailable element, Location actualLocation, Location expectedLocation, String componentName) {
        if (!expectedLocation.equals(actualLocation)) {
            throw WebElementLocation.assertionError(ELEMENT_LOCATION_IS_NOT_EQUAL_EXPECTED_LOCATION.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual location", actualLocation.toString()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected location", expectedLocation.toString()));
        }
    }

    protected void shouldNotHaveLocation(GetLocationAvailable element, Location actualLocation, Location expectedLocation, String componentName) {
        if (expectedLocation.equals(actualLocation)) {
            throw WebElementLocation.assertionError(ELEMENT_LOCATION_IS_EQUAL_EXPECTED_LOCATION.getMessage(componentName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual location", actualLocation.toString()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected location", expectedLocation.toString()));
        }
    }

}
