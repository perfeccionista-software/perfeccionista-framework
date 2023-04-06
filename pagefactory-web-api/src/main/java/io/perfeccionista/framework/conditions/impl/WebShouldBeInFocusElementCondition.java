package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.WebElementInFocus;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsInFocusAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IN_FOCUS;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_NOT_IN_FOCUS;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.FOCUS;

public class WebShouldBeInFocusElementCondition implements WebElementCondition {

    private String componentName;
    private boolean positive;

    public WebShouldBeInFocusElementCondition() {
        this.componentName = FOCUS;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        boolean inFocus = element.isInFocus(componentName);
        if (positive) {
            shouldBeInFocus(element, inFocus);
        } else {
            shouldNotBeInFocus(element, inFocus);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_BE_IN_FOCUS_METHOD, elementName)
                : assertInvocation(SHOULD_NOT_BE_IN_FOCUS_METHOD, elementName);
    }

    @Override
    public WebShouldBeInFocusElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebIsInFocusAvailable interface
        return this;
    }

    @Override
    public WebShouldBeInFocusElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldBeInFocusElementCondition inverse() {
        this.positive = !positive;
        return this;
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
