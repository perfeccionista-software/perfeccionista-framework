package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementInFocus;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.IsInFocusAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IN_FOCUS;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_NOT_IN_FOCUS;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_BE_IN_FOCUS_METHOD;

public class IsInFocusMatcher implements IsInFocusAvailableMatcher {

    private final boolean positive;

    public IsInFocusMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull IsInFocusAvailable element) {
        InvocationName invocationName = positive
                ? InvocationName.of(SHOULD_BE_IN_FOCUS_METHOD, element)
                : InvocationName.of(SHOULD_NOT_BE_IN_FOCUS_METHOD, element);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    boolean inFocus = element.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class)
                            .execute(element);
                    if (positive) {
                        shouldBeInFocus(element, inFocus);
                    } else {
                        shouldNotBeInFocus(element, inFocus);
                    }
                });
    }

    protected void shouldBeInFocus(IsInFocusAvailable element, boolean inFocus) {
        if (!inFocus) {
            throw WebElementNotInFocus.assertionError(ELEMENT_NOT_IN_FOCUS.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        }
    }

    protected void shouldNotBeInFocus(IsInFocusAvailable element, boolean inFocus) {
        if (inFocus) {
            throw WebElementInFocus.assertionError(ELEMENT_IN_FOCUS.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        }
    }

}
