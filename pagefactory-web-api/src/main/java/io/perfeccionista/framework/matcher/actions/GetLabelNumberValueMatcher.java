package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.WebElementLabelValue;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_LABEL_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;

public class GetLabelNumberValueMatcher implements GetLabelAvailableMatcher {

    private final NumberValue<?> expectedNumberValue;
    private final boolean positive;

    public GetLabelNumberValueMatcher(@NotNull NumberValue<?> expectedNumberValue, boolean positive) {
        this.expectedNumberValue = expectedNumberValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull GetLabelAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_HAVE_TEXT_LABEL_METHOD, this, expectedNumberValue)
                : assertInvocation(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, this, expectedNumberValue);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    String actualLabel = element.getLabel();
                    if (positive) {
                        shouldHaveLabel(element, actualLabel);
                    } else {
                        shouldNotHaveLabel(element, actualLabel);
                    }
                });
    }

    protected void shouldHaveLabel(GetLabelAvailable element, String actualLabel) {
        if (!expectedNumberValue.checkString(actualLabel)) {
            throw WebElementLabelValue.assertionError(ELEMENT_LABEL_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumberValue));
        }
    }

    protected void shouldNotHaveLabel(GetLabelAvailable element, String actualLabel) {
        if (expectedNumberValue.checkString(actualLabel)) {
            throw WebElementLabelValue.assertionError(ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumberValue));
        }
    }

}

