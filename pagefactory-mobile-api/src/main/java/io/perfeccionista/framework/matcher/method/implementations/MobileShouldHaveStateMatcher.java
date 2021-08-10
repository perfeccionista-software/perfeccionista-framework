package io.perfeccionista.framework.matcher.method.implementations;

import io.perfeccionista.framework.exceptions.ElementDoesNotHaveExpectedState;
import io.perfeccionista.framework.exceptions.ElementHasUnexpectedState;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementStateAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_DOES_NOT_HAVE_EXPECTED_STATE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_HAS_UNEXPECTED_STATE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_STATE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_STATE_METHOD;

public class MobileShouldHaveStateMatcher implements MobileElementStateAvailableMatcher {

    private final String stateName;
    private final boolean positive;

    public MobileShouldHaveStateMatcher(@NotNull String stateName, boolean positive) {
        this.stateName = stateName;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull MobileElementStateAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_HAVE_STATE_METHOD, elementName, stateName)
                : assertInvocation(SHOULD_NOT_HAVE_STATE_METHOD, elementName, stateName);

        runCheck(invocationName,
                () -> {
                    boolean actualState = element.hasState(stateName);
                    if (positive) {
                        shouldHaveState(element, actualState);
                    } else {
                        shouldNotHaveState(element, actualState);
                    }
                });
    }

    protected void shouldHaveState(MobileElementStateAvailable element, boolean actualState) {
        if (!actualState) {
            throw ElementDoesNotHaveExpectedState.assertionError(ELEMENT_DOES_NOT_HAVE_EXPECTED_STATE.getMessage(element.getElementIdentifier().getLastUsedName(), stateName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        }
    }

    protected void shouldNotHaveState(MobileElementStateAvailable element, boolean actualState) {
        if (actualState) {
            throw ElementHasUnexpectedState.assertionError(ELEMENT_HAS_UNEXPECTED_STATE.getMessage(element.getElementIdentifier().getLastUsedName(), stateName))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        }
    }

}
