package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.WebElementIsSelected;
import io.perfeccionista.framework.exceptions.WebElementNotSelected;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_SELECTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_NOT_SELECTED;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_BE_SELECTED_METHOD;

public class IsSelectedMatcher implements IsSelectedAvailableMatcher {

    private final boolean positive;

    public IsSelectedMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull IsSelectedAvailable element) {
        InvocationName invocationName = positive
                ? assertInvocation(SHOULD_BE_SELECTED_METHOD, element)
                : assertInvocation(SHOULD_NOT_BE_SELECTED_METHOD, element);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    boolean selected = element.isSelected();
                    if (positive) {
                        shouldBeSelected(element, selected);
                    } else {
                        shouldNotBeSelected(element, selected);
                    }
                });
    }

    protected void shouldBeSelected(IsSelectedAvailable element, boolean selected) {
        if (!selected) {
            throw WebElementNotSelected.assertionError(ELEMENT_NOT_SELECTED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
    }

    protected void shouldNotBeSelected(IsSelectedAvailable element, boolean selected) {
        if (selected) {
            throw WebElementIsSelected.assertionError(ELEMENT_IS_SELECTED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
    }

}
