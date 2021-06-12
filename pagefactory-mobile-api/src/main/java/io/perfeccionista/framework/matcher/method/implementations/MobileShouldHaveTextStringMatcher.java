package io.perfeccionista.framework.matcher.method.implementations;

import io.perfeccionista.framework.exceptions.MobileElementTextValue;
import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.method.MobileGetTextAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_TEXT_METHOD;

public class MobileShouldHaveTextStringMatcher implements MobileGetTextAvailableMatcher {

    private final String expectedText;
    private final boolean positive;

    public MobileShouldHaveTextStringMatcher(@NotNull String expectedText, boolean positive) {
        this.expectedText = expectedText;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull MobileGetTextAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_HAVE_TEXT_METHOD, this, expectedText)
                : assertInvocation(SHOULD_NOT_HAVE_TEXT_METHOD, this, expectedText);

        runCheck(invocationName,
                () -> {
                    String actualText = element.getText();
                    if (positive) {
                        shouldHaveText(element, actualText);
                    } else {
                        shouldNotHaveText(element, actualText);
                    }
                });
    }

    protected void shouldHaveText(MobileGetTextAvailable element, String actualText) {
        if (!expectedText.equals(actualText)) {
            throw MobileElementTextValue.assertionError(ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, actualText));
        }
    }

    protected void shouldNotHaveText(MobileGetTextAvailable element, String actualText) {
        if (expectedText.equals(actualText)) {
            throw MobileElementTextValue.assertionError(ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, actualText));
        }
    }

}
