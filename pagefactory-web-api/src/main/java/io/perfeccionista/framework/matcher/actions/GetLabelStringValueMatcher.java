package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.WebElementLabelValue;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_LABEL_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;

public class GetLabelStringValueMatcher implements GetLabelAvailableMatcher {

    private final StringValue expectedStringValue;
    private final boolean positive;

    public GetLabelStringValueMatcher(@NotNull StringValue expectedStringValue, boolean positive) {
        this.expectedStringValue = expectedStringValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull GetLabelAvailable element) {
        InvocationName invocationName = positive
                ? InvocationName.of(SHOULD_HAVE_TEXT_LABEL_METHOD, this, expectedStringValue)
                : InvocationName.of(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, this, expectedStringValue);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    String actualLabel = element.getActionImplementation(GET_LABEL_METHOD, String.class)
                            .execute(element);
                    if (positive) {
                        shouldHaveLabel(element, actualLabel);
                    } else {
                        shouldNotHaveLabel(element, actualLabel);
                    }
                });
    }

    protected void shouldHaveLabel(GetLabelAvailable element, String actualLabel) {
        if (!expectedStringValue.check(actualLabel)) {
            throw WebElementLabelValue.assertionError(ELEMENT_LABEL_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedStringValue));
        }
    }

    protected void shouldNotHaveLabel(GetLabelAvailable element, String actualLabel) {
        if (expectedStringValue.check(actualLabel)) {
            throw WebElementLabelValue.assertionError(ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedStringValue));
        }
    }

}
