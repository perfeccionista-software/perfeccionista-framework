package io.perfeccionista.framework.matcher.methods.implementations;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementNotOnTheScreen;
import io.perfeccionista.framework.exceptions.WebElementOnTheScreen;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsOnTheScreenAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_ON_THE_SCREEN;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ON_THE_SCREEN;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_BE_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_NOT_BE_ON_THE_SCREEN_METHOD;

// TODO: Добавить
//  centerBeOnTheScreen
//  centerNotBeOnTheScreen
//  partlyBeOnTheScreen
//  partlyNotBeOnTheScreen
//  completelyBeOnTheScreen
//  completelyNotBeOnTheScreen
@Deprecated
public class WebShouldBeOnTheScreenMatcher implements WebIsOnTheScreenAvailableMatcher {

    private final boolean positive;

    public WebShouldBeOnTheScreenMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebIsOnTheScreenAvailable element) {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationName = positive
                ? assertInvocation(SHOULD_BE_ON_THE_SCREEN_METHOD, elementName)
                : assertInvocation(SHOULD_NOT_BE_ON_THE_SCREEN_METHOD, elementName);

        MultipleAttemptInvocationWrapper.repeatInvocation(invocationName,
                () -> {
                    boolean onTheScreen = element.isOnTheScreen();
                    if (positive) {
                        shouldBeOnTheScreen(element, onTheScreen);
                    } else {
                        shouldNotBeOnTheScreen(element, onTheScreen);
                    }
                });
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
