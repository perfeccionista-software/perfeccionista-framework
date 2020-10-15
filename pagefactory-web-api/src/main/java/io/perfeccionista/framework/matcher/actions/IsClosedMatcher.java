package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.WebElementIsOpen;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_OPEN;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_BE_OPEN_METHOD;

public class IsClosedMatcher implements IsOpenAvailableMatcher {

    @Override
    public void check(@NotNull IsOpenAvailable element) {
        InvocationName invocationName = InvocationName.of(SHOULD_BE_OPEN_METHOD, element);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    if (element.isOpen()) {
                        throw WebElementIsOpen.assertionError(ELEMENT_IS_OPEN.getMessage(element.getElementIdentifier().getLastUsedName()))
                                .setProcessed(true)
                                .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                    }
                });
    }

}
