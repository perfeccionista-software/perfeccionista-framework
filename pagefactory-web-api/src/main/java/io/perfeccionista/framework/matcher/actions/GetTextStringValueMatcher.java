package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.WebElementTextValue;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_HAVE_TEXT_METHOD;

public class GetTextStringValueMatcher implements GetTextAvailableMatcher {

    private final StringValue expectedStringValue;
    private final boolean positive;

    public GetTextStringValueMatcher(@NotNull StringValue expectedStringValue, boolean positive) {
        this.expectedStringValue = expectedStringValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull GetTextAvailable element) {
        InvocationName invocationName = positive
                ? InvocationName.of(SHOULD_HAVE_TEXT_METHOD, this, expectedStringValue)
                : InvocationName.of(SHOULD_NOT_HAVE_TEXT_METHOD, this, expectedStringValue);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    String actualText = element.getActionImplementation(GET_TEXT_METHOD, String.class)
                            .execute(element);
                    if (positive) {
                        shouldHaveText(element, actualText);
                    } else {
                        shouldNotHaveText(element, actualText);
                    }
                });
    }

    protected void shouldHaveText(GetTextAvailable element, String actualText) {
        if (!expectedStringValue.check(actualText)) {
            throw WebElementTextValue.assertionError(ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedStringValue));
        }
    }

    protected void shouldNotHaveText(GetTextAvailable element, String actualText) {
        if (expectedStringValue.check(actualText)) {
            throw WebElementTextValue.assertionError(ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedStringValue));
        }
    }

}
