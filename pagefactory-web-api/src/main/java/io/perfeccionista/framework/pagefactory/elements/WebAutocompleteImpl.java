package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebDropDownAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebAutocompleteMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.WebClearOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebReplaceTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebSendKeyEventsOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebTypeTextOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.INPUT;

public class WebAutocompleteImpl<T extends WebBlock> extends WebDropDownListImpl<T> implements WebAutocomplete<T> {

    // Checks

    @Override
    public WebAutocomplete<T> forEachBlock(@NotNull Consumer<T> listBlockConsumer) {
        super.forEachBlock(listBlockConsumer);
        return this;
    }

    @Override
    public WebAutocomplete<T> forFirstBlock(@NotNull Consumer<T> listBlockConsumer) {
        super.forFirstBlock(listBlockConsumer);
        return this;
    }

    @Override
    public WebAutocomplete<T> forLastBlock(@NotNull Consumer<T> listBlockConsumer) {
        super.forLastBlock(listBlockConsumer);
        return this;
    }

    // Actions

    @Override
    public WebAutocomplete<T> executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public WebAutocomplete<T> should(@NotNull WebAutocompleteMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebDropDownListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebIndexesMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebGetLabelAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebGetTextAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete<T> should(@NotNull WebDropDownAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // Click

    @Override
    public WebAutocomplete<T> click() {
        super.click();
        return this;
    }

    // DropDown

    @Override
    public WebAutocomplete<T> open() {
        super.open();
        return this;
    }

    @Override
    public WebAutocomplete<T> close() {
        super.close();
        return this;
    }

    // HoverTo

    @Override
    public WebAutocomplete<T> hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // InputText

    @Override
    public WebAutocomplete<T> clear() {
        WebClearOperationType operationType = WebClearOperationType.of(this);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebAutocomplete<T> typeText(@NotNull String text) {
        WebTypeTextOperationType operationType = WebTypeTextOperationType.of(this, text);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebAutocomplete<T> replaceText(@NotNull String text) {
        WebReplaceTextOperationType operationType = WebReplaceTextOperationType.of(this, text);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebAutocomplete<T> sendKeyEvents(@NotNull KeyEventsChain keyEvents) {
        WebSendKeyEventsOperationType operationType = WebSendKeyEventsOperationType.of(this, keyEvents);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    // ScrollTo

    @Override
    public WebAutocomplete<T> scrollTo() {
        super.scrollTo();
        return this;
    }

}
