package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementInFocus;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsInFocusAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IN_FOCUS;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_NOT_IN_FOCUS;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_IN_FOCUS_METHOD;

public class WebShouldBeInFocusMatcher implements WebIsInFocusAvailableMatcher {

    private final boolean positive;

    public WebShouldBeInFocusMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebIsInFocusAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_BE_IN_FOCUS_METHOD, elementName)
                : assertInvocation(SHOULD_NOT_BE_IN_FOCUS_METHOD, elementName);

        runCheck(invocationName,
                () -> {
                    boolean inFocus = element.isInFocus();
                    if (positive) {
                        shouldBeInFocus(element, inFocus);
                    } else {
                        shouldNotBeInFocus(element, inFocus);
                    }
                });
    }

    protected void shouldBeInFocus(WebIsInFocusAvailable element, boolean inFocus) {
        if (!inFocus) {
            throw WebElementNotInFocus.assertionError(ELEMENT_NOT_IN_FOCUS.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        }
    }

    protected void shouldNotBeInFocus(WebIsInFocusAvailable element, boolean inFocus) {
        if (inFocus) {
            throw WebElementInFocus.assertionError(ELEMENT_IN_FOCUS.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        }
    }

}
