package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.ElementCast;
import io.perfeccionista.framework.exceptions.WebElementIsDisabled;
import io.perfeccionista.framework.exceptions.WebElementIsEnabled;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_CANNOT_BE_CASTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_DISABLED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_ENABLED;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;
import static io.perfeccionista.framework.utils.CastUtils.isSubtypeOf;
import static io.perfeccionista.framework.utils.WebElementUtils.castWebChildElement;

public class WebShouldBeEnabledElementCondition implements WebElementCondition {

    private String componentName;
    private boolean positive;

    public WebShouldBeEnabledElementCondition() {
        this.componentName = ENABLED;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        WebIsEnabledAvailable elementWithEnabledState = castWebChildElement(element, WebIsEnabledAvailable.class);
        boolean enabled = elementWithEnabledState.isEnabled(componentName);
        if (positive) {
            shouldBeEnabled(elementWithEnabledState, enabled);
        } else {
            shouldBeDisabled(elementWithEnabledState, enabled);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_BE_ENABLED_METHOD, elementName)
                : assertInvocation(SHOULD_BE_DISABLED_METHOD, elementName);
    }

    @Override
    public WebShouldBeEnabledElementCondition validate(@NotNull WebChildElement element) {
        if (!isSubtypeOf(element, WebIsEnabledAvailable.class)) {
            throw ElementCast.exception(ELEMENT_CANNOT_BE_CASTED.getMessage(WebIsEnabledAvailable.class.getCanonicalName()))
                    .addFirstAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
        return this;
    }

    @Override
    public WebShouldBeEnabledElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldBeEnabledElementCondition inverse() {
        this.positive = !positive;
        return this;
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
