package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementPropertyNotFound;
import io.perfeccionista.framework.exceptions.WebElementPropertyValue;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyHolder;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_CONTAINS_EXPECTED_TEXT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_TEXT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_NOT_FOUND;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_PROPERTY_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_HAVE_PROPERTY_TEXT_METHOD;

public class WebElementPropertyTextMatcher implements WebElementPropertyAvailableMatcher {

    private final String propertyName;
    private final String expectedText;
    private final boolean positive;

    public WebElementPropertyTextMatcher(@NotNull String propertyName, @NotNull String expectedText, boolean positive) {
        this.propertyName = propertyName;
        this.expectedText = expectedText;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebElementPropertyAvailable element) {
        InvocationName invocationName = positive
                ? InvocationName.of(SHOULD_HAVE_PROPERTY_TEXT_METHOD, this, propertyName, expectedText)
                : InvocationName.of(SHOULD_NOT_HAVE_PROPERTY_TEXT_METHOD, this, propertyName, expectedText);

        WebElementPropertyHolder propertyHolder = element.getProperty(propertyName)
                .orElseThrow(() -> WebElementPropertyNotFound.exception(ELEMENT_PROPERTY_NOT_FOUND.getMessage(propertyName))
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson())));

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    String actualPropertyValue = element.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class)
                            .execute(element, propertyHolder);
                    if (positive) {
                        shouldHavePropertyText(element, actualPropertyValue);
                    } else {
                        shouldNotHavePropertyText(element, actualPropertyValue);
                    }
                });
    }

    protected void shouldHavePropertyText(WebElementPropertyAvailable element, String actualPropertyValue) {
        if (!expectedText.equals(actualPropertyValue)) {
            throw WebElementPropertyValue.assertionError(ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_TEXT.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected text", expectedText))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

    protected void shouldNotHavePropertyText(WebElementPropertyAvailable element, String actualPropertyValue) {
        if (expectedText.equals(actualPropertyValue)) {
            throw WebElementPropertyValue.assertionError(ELEMENT_PROPERTY_CONTAINS_EXPECTED_TEXT.getMessage(propertyName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Expected text", expectedText))
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Actual value", actualPropertyValue));
        }
    }

}
