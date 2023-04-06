package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.ElementCast;
import io.perfeccionista.framework.exceptions.WebElementTextValue;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_CANNOT_BE_CASTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;
import static io.perfeccionista.framework.pagefactory.elements.options.GetTextOptions.getTextForComponent;
import static io.perfeccionista.framework.utils.CastUtils.isSubtypeOf;
import static io.perfeccionista.framework.utils.WebElementUtils.castWebChildElement;

public class WebShouldHaveTextNumberValueElementCondition implements WebElementCondition {

    private String componentName;
    private final NumberValue<?> expectedNumberValue;
    private boolean positive;

    public WebShouldHaveTextNumberValueElementCondition(@NotNull NumberValue<?> expectedNumberValue) {
        this.componentName = TEXT;
        this.expectedNumberValue = expectedNumberValue;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        WebGetTextAvailable elementWithText = castWebChildElement(element, WebGetTextAvailable.class);
        String actualText = elementWithText.getText(getTextForComponent(componentName));
        if (positive) {
            shouldHaveText(elementWithText, actualText);
        } else {
            shouldNotHaveText(elementWithText, actualText);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_HAVE_NUMBER_METHOD, elementName, expectedNumberValue.getShortDescription())
                : assertInvocation(SHOULD_NOT_HAVE_NUMBER_METHOD, elementName, expectedNumberValue.getShortDescription());
    }

    @Override
    public WebShouldHaveTextNumberValueElementCondition validate(@NotNull WebChildElement element) {
        if (!isSubtypeOf(element, WebGetTextAvailable.class)) {
            throw ElementCast.exception(ELEMENT_CANNOT_BE_CASTED.getMessage(WebGetTextAvailable.class.getCanonicalName()))
                    .addFirstAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
        return this;
    }

    @Override
    public WebShouldHaveTextNumberValueElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldHaveTextNumberValueElementCondition inverse() {
        this.positive = !positive;
        return this;
    }

    protected void shouldHaveText(WebGetTextAvailable element, String actualText) {
        if (!expectedNumberValue.checkString(actualText)) {
            throw WebElementTextValue.assertionError(ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumberValue));
        }
    }

    protected void shouldNotHaveText(WebGetTextAvailable element, String actualText) {
        if (expectedNumberValue.checkString(actualText)) {
            throw WebElementTextValue.assertionError(ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumberValue));
        }
    }

}
