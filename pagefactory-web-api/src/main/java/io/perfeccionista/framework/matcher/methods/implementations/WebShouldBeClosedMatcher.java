package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.WebElementIsOpen;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.methods.WebDropDownAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebDropDownAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_OPEN;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_OPEN_METHOD;

public class WebShouldBeClosedMatcher implements WebDropDownAvailableMatcher {

    @Override
    public void check(@NotNull WebDropDownAvailable element) {
        InvocationName invocationName = assertInvocation(SHOULD_BE_OPEN_METHOD, element);

        runCheck(invocationName,
                () -> {
                    if (element.isOpen()) {
                        throw WebElementIsOpen.assertionError(ELEMENT_IS_OPEN.getMessage(element.getElementIdentifier().getLastUsedName()))
                                .setProcessed(true)
                                .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                    }
                });
    }

}
