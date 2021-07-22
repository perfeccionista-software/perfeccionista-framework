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
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextAutocompleteMatcher;
import io.perfeccionista.framework.matcher.element.WebTextDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.WebClearOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebReplaceTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebSendKeyEventsOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebTypeTextOperationType;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.INPUT;

public class WebTextAutocompleteImpl extends WebTextDropDownListImpl implements WebTextAutocomplete {

    // Select

    @Override
    public WebTextAutocomplete select(@NotNull String text) {
        super.select(text);
        return this;
    }

    @Override
    public WebTextAutocomplete select(@NotNull StringValue text) {
        super.select(text);
        return this;
    }

    @Override
    public WebTextAutocomplete select(@NotNull WebTextListFilterBuilder filterBuilder) {
        super.select(filterBuilder);
        return this;
    }

    @Override
    public WebTextAutocomplete select(@NotNull WebTextListBlockCondition filterCondition) {
        super.select(filterCondition);
        return this;
    }

    // Checks

    public WebTextAutocomplete forEachBlock(@NotNull Consumer<WebLink> textListBlockConsumer) {
        super.forEachBlock(textListBlockConsumer);
        return this;
    }

    public WebTextAutocomplete forFirstBlock(@NotNull Consumer<WebLink> textListBlockConsumer) {
        super.forFirstBlock(textListBlockConsumer);
        return this;
    }

    public WebTextAutocomplete forLastBlock(@NotNull Consumer<WebLink> textListBlockConsumer) {
        super.forLastBlock(textListBlockConsumer);
        return this;
    }

    // Actions

    @Override
    public WebTextAutocomplete executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextAutocomplete should(@NotNull WebTextAutocompleteMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebTextDropDownListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebTextListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebIndexesMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebGetLabelAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebGetTextAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebDropDownAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // Click

    @Override
    public WebTextAutocomplete click() {
        super.click();
        return this;
    }

    // DropDown

    @Override
    public WebTextAutocomplete open() {
        super.open();
        return this;
    }

    @Override
    public WebTextAutocomplete close() {
        super.close();
        return this;
    }

    // HoverTo

    @Override
    public WebTextAutocomplete hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // InputText

    @Override
    public WebTextAutocomplete clear() {
        WebClearOperationType operationType = WebClearOperationType.of(this);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebTextAutocomplete typeText(@NotNull String text) {
        WebTypeTextOperationType operationType = WebTypeTextOperationType.of(this, text);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebTextAutocomplete replaceText(@NotNull String text) {
        WebReplaceTextOperationType operationType = WebReplaceTextOperationType.of(this, text);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebTextAutocomplete sendKeyEvents(@NotNull KeyEventsChain keyEvents) {
        WebSendKeyEventsOperationType operationType = WebSendKeyEventsOperationType.of(this, keyEvents);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    // ScrollTo

    @Override
    public WebTextAutocomplete scrollTo() {
        super.scrollTo();
        return this;
    }

}
