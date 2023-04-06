package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.WebElementIsDisabled;
import io.perfeccionista.framework.exceptions.WebElementIsEnabled;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.matcher.methods.WebIsEnabledAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_DISABLED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_ENABLED;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_ENABLED_METHOD;

@Deprecated
public class WebShouldBeEnabledMatcher implements WebIsEnabledAvailableMatcher {

    private final boolean positive;

    public WebShouldBeEnabledMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebIsEnabledAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_BE_ENABLED_METHOD, elementName)
                : assertInvocation(SHOULD_BE_DISABLED_METHOD, elementName);

        MultipleAttemptInvocationWrapper.repeatInvocation(invocationName,
                () -> {
                    boolean enabled = element.isEnabled();
                    if (positive) {
                        shouldBeEnabled(element, enabled);
                    } else {
                        shouldBeDisabled(element, enabled);
                    }
                });
    }

    protected void shouldBeEnabled(WebIsEnabledAvailable element, boolean enabled) {
        if (!enabled) {
            throw WebElementIsDisabled.assertionError(ELEMENT_IS_DISABLED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
    }

    protected void shouldBeDisabled(WebIsEnabledAvailable element, boolean enabled) {
        if (enabled) {
            throw WebElementIsEnabled.assertionError(ELEMENT_IS_ENABLED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
    }

}
