package io.perfeccionista.framework.conditions.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.exceptions.ElementNotOnTheScreen;
import io.perfeccionista.framework.exceptions.WebElementOnTheScreen;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsOnTheScreenAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_ON_THE_SCREEN;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ON_THE_SCREEN;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.options.OnTheScreenOptions.onTheScreenForComponent;

// TODO: Добавить
//  centerBeOnTheScreen
//  centerNotBeOnTheScreen
//  partlyBeOnTheScreen
//  partlyNotBeOnTheScreen
//  completelyBeOnTheScreen
//  completelyNotBeOnTheScreen
public class WebShouldBeOnTheScreenElementCondition implements WebElementCondition {

    private String componentName;
    private boolean positive;

    public WebShouldBeOnTheScreenElementCondition() {
        this.componentName = ROOT;
        this.positive = true;
    }

    @Override
    public void check(@NotNull WebChildElement element, @NotNull InvocationInfo invocationInfo) {
        boolean onTheScreen = element.isOnTheScreen(onTheScreenForComponent(this.componentName));
        if (positive) {
            shouldBeOnTheScreen(element, onTheScreen);
        } else {
            shouldNotBeOnTheScreen(element, onTheScreen);
        }
    }

    @Override
    public InvocationInfo createInvocationInfoForElement(@NotNull WebChildElement element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        return positive
                ? assertInvocation(SHOULD_BE_ON_THE_SCREEN_METHOD, elementName)
                : assertInvocation(SHOULD_NOT_BE_ON_THE_SCREEN_METHOD, elementName);
    }

    @Override
    public WebShouldBeOnTheScreenElementCondition validate(@NotNull WebChildElement element) {
        // WebChildElement extends WebIsOnTheScreenAvailable interface
        return this;
    }

    @Override
    public WebShouldBeOnTheScreenElementCondition forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public WebShouldBeOnTheScreenElementCondition inverse() {
        this.positive = !positive;
        return this;
    }

    protected void shouldBeOnTheScreen(WebIsOnTheScreenAvailable element, boolean onTheScreen) {
        if (!onTheScreen) {
            throw ElementNotOnTheScreen.assertionError(ELEMENT_NOT_ON_THE_SCREEN.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        }
    }

    protected void shouldNotBeOnTheScreen(WebIsOnTheScreenAvailable element, boolean onTheScreen) {
        if (onTheScreen) {
            throw WebElementOnTheScreen.assertionError(ELEMENT_ON_THE_SCREEN.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        }
    }

}
