package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.ElementIsSelected;
import io.perfeccionista.framework.exceptions.WebElementNotSelected;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.methods.WebIsSelectedAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_SELECTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_NOT_SELECTED;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_SELECTED_METHOD;

public class WebShouldBeSelectedMatcher implements WebIsSelectedAvailableMatcher {

    private final boolean positive;

    public WebShouldBeSelectedMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebIsSelectedAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_BE_SELECTED_METHOD, elementName)
                : assertInvocation(SHOULD_NOT_BE_SELECTED_METHOD, elementName);

        runCheck(invocationName,
                () -> {
                    boolean selected = element.isSelected();
                    if (positive) {
                        shouldBeSelected(element, selected);
                    } else {
                        shouldNotBeSelected(element, selected);
                    }
                });
    }

    protected void shouldBeSelected(WebIsSelectedAvailable element, boolean selected) {
        if (!selected) {
            throw WebElementNotSelected.assertionError(ELEMENT_NOT_SELECTED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
    }

    protected void shouldNotBeSelected(WebIsSelectedAvailable element, boolean selected) {
        if (selected) {
            throw ElementIsSelected.assertionError(ELEMENT_IS_SELECTED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
    }

}
