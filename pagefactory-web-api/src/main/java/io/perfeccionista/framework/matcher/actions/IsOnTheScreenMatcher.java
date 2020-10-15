package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebElementNotOnTheScreen;
import io.perfeccionista.framework.exceptions.WebElementOnTheScreen;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOnTheScreenAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_NOT_ON_THE_SCREEN;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ON_THE_SCREEN;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_BE_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_NOT_BE_ON_THE_SCREEN_METHOD;

// TODO: Добавить
//  centerBeOnTheScreen
//  centerNotBeOnTheScreen
//  partlyBeOnTheScreen
//  partlyNotBeOnTheScreen
//  completelyBeOnTheScreen
//  completelyNotBeOnTheScreen
public class IsOnTheScreenMatcher implements IsOnTheScreenAvailableMatcher {

    private final boolean positive;

    public IsOnTheScreenMatcher(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void check(@NotNull IsOnTheScreenAvailable element) {
        InvocationName invocationName = positive
                ? InvocationName.of(SHOULD_BE_ON_THE_SCREEN_METHOD, element)
                : InvocationName.of(SHOULD_NOT_BE_ON_THE_SCREEN_METHOD, element);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    boolean onTheScreen = element.getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class)
                            .execute(element);
                    if (positive) {
                        shouldBeOnTheScreen(element, onTheScreen);
                    } else {
                        shouldNotBeOnTheScreen(element, onTheScreen);
                    }
                });
    }

    protected void shouldBeOnTheScreen(IsOnTheScreenAvailable element, boolean onTheScreen) {
        if (!onTheScreen) {
            throw WebElementNotOnTheScreen.assertionError(ELEMENT_NOT_ON_THE_SCREEN.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        }
    }

    protected void shouldNotBeOnTheScreen(IsOnTheScreenAvailable element, boolean onTheScreen) {
        if (onTheScreen) {
            throw WebElementOnTheScreen.assertionError(ELEMENT_ON_THE_SCREEN.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        }
    }

}
