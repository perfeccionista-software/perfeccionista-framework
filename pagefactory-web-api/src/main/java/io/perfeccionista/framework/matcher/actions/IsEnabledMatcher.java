package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.WebElementIsDisabled;
import io.perfeccionista.framework.exceptions.WebElementIsEnabled;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_DISABLED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_ENABLED;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_BE_ENABLED_METHOD;

public class IsEnabledMatcher implements IsEnabledAvailableMatcher {

    private final boolean positive;

    public IsEnabledMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull IsEnabledAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_BE_ENABLED_METHOD, element)
                : assertInvocation(SHOULD_BE_DISABLED_METHOD, element);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    boolean enabled = element.isEnabled();
                    if (positive) {
                        shouldBeEnabled(element, enabled);
                    } else {
                        shouldBeDisabled(element, enabled);
                    }
                });
    }

    protected void shouldBeEnabled(IsEnabledAvailable element, boolean enabled) {
        if (!enabled) {
            throw WebElementIsDisabled.assertionError(ELEMENT_IS_DISABLED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
    }

    protected void shouldBeDisabled(IsEnabledAvailable element, boolean enabled) {
        if (enabled) {
            throw WebElementIsEnabled.assertionError(ELEMENT_IS_ENABLED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
    }

}
