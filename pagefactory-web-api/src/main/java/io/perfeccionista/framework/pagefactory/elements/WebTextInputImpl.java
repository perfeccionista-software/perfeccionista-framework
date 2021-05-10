package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsEnabledAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextInputMatcher;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.WebClearOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebClickOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsEnabledOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetLabelOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebReplaceTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebSendKeyEventsOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebTypeTextOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLICK;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.INPUT;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;

public class WebTextInputImpl extends AbstractWebChildElement implements WebTextInput {

    // Actions

    @Override
    public WebTextInput executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextInput should(@NotNull WebTextInputMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebGetLabelAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebGetTextAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextInput should(@NotNull WebIsEnabledAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Click

    @Override
    public WebTextInput click() {
        WebClickOperationType operationType = WebClickOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, CLICK).executeAction());
        return this;
    }

    // Get Label

    @Override
    public @Nullable String getLabel() {
        WebGetLabelOperationType operationType = WebGetLabelOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, LABEL).executeGetter());
    }

    // Get Text

    @Override
    public @Nullable String getText() {
        WebGetTextOperationType operationType = WebGetTextOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, TEXT).executeGetter());
    }

    // HoverTo

    @Override
    public WebTextInput hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // InputText

    @Override
    public WebTextInput clear() {
        WebClearOperationType operationType = WebClearOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebTextInput typeText(@NotNull String text) {
        WebTypeTextOperationType operationType = WebTypeTextOperationType.of(this, text);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebTextInput replaceText(@NotNull String text) {
        WebReplaceTextOperationType operationType = WebReplaceTextOperationType.of(this, text);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebTextInput sendKeyEvents(@NotNull KeyEventsChain keyEvents) {
        WebSendKeyEventsOperationType operationType = WebSendKeyEventsOperationType.of(this, keyEvents);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    // IsEnabled

    @Override
    public boolean isEnabled() {
        WebGetIsEnabledOperationType operationType = WebGetIsEnabledOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ENABLED).executeGetter());
    }

    // ScrollTo

    @Override
    public WebTextInput scrollTo() {
        super.scrollTo();
        return this;
    }

}
