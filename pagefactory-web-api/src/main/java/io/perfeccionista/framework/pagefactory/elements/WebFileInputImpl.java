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
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebFileInputMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
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
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLEAR;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLICK;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.INPUT;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;

public class WebFileInputImpl extends AbstractWebChildElement implements WebFileInput {

    // Actions

    @Override
    public WebFileInput executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public WebFileInput should(@NotNull WebFileInputMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebGetLabelAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebGetTextAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebFileInput should(@NotNull WebIsEnabledAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Click

    @Override
    public WebFileInput click() {
        WebClickOperationType operationType = WebClickOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, CLICK).executeAction());
        return this;
    }

    // InputText

    @Override
    public WebFileInput clear() {
        WebClearOperationType operationType = WebClearOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebFileInput typeText(@NotNull String text) {
        WebTypeTextOperationType operationType = WebTypeTextOperationType.of(this, text);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebFileInput replaceText(@NotNull String text) {
        WebReplaceTextOperationType operationType = WebReplaceTextOperationType.of(this, text);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebFileInput sendKeyEvents(@NotNull KeyEventsChain keyEvents) {
        WebSendKeyEventsOperationType operationType = WebSendKeyEventsOperationType.of(this, keyEvents);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
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
    public WebFileInput hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
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
    public WebFileInput scrollTo() {
        super.scrollTo();
        return this;
    }

}
