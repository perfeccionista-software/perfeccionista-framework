package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.WebElementIsOpen;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.matcher.methods.WebDropDownAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebDropDownAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_OPEN;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_CLOSED_METHOD;

public class WebShouldBeClosedMatcher implements WebDropDownAvailableMatcher {

    @Override
    public void check(@NotNull WebDropDownAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = assertInvocation(SHOULD_BE_CLOSED_METHOD, elementName);

        MultipleAttemptInvocationWrapper.repeatInvocation(invocationName,
                () -> {
                    if (element.isOpen()) {
                        throw WebElementIsOpen.assertionError(ELEMENT_IS_OPEN.getMessage(element.getElementIdentifier().getLastUsedName()))
                                .setProcessed(true)
                                .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                    }
                });
    }

}
