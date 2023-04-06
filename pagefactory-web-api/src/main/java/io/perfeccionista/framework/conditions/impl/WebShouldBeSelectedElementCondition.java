package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.ElementCast;
import io.perfeccionista.framework.exceptions.ElementIsSelected;
import io.perfeccionista.framework.exceptions.WebElementNotSelected;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_CANNOT_BE_CASTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_SELECTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_NOT_SELECTED;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.SELECTED;
import static io.perfeccionista.framework.utils.CastUtils.isSubtypeOf;
import static io.perfeccionista.framework.utils.WebElementUtils.castWebChildElement;

public class WebShouldBeSelectedElementCondition implements WebElementCondition {

    private String componentName;
    private boolean positive;

    public WebShouldBeSelectedElementCondition() {
        this.componentName = SELECTED;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        WebIsSelectedAvailable elementWithSelectedState = castWebChildElement(element, WebIsSelectedAvailable.class);
        boolean selected = elementWithSelectedState.isSelected(componentName);
        if (positive) {
            shouldBeSelected(elementWithSelectedState, selected);
        } else {
            shouldNotBeSelected(elementWithSelectedState, selected);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_BE_SELECTED_METHOD, elementName)
                : assertInvocation(SHOULD_NOT_BE_SELECTED_METHOD, elementName);
    }

    @Override
    public WebShouldBeSelectedElementCondition validate(@NotNull WebChildElement element) {
        if (!isSubtypeOf(element, WebIsSelectedAvailable.class)) {
            throw ElementCast.exception(ELEMENT_CANNOT_BE_CASTED.getMessage(WebIsSelectedAvailable.class.getCanonicalName()))
                    .addFirstAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
        return this;
    }

    @Override
    public WebShouldBeSelectedElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldBeSelectedElementCondition inverse() {
        this.positive = !positive;
        return this;
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
