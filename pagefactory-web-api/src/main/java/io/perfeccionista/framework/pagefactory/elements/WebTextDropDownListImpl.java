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
import io.perfeccionista.framework.matcher.element.WebTextDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockCondition;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsDisplayedOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.WebClickOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebCloseOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsDisplayedOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsOpenOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetLabelOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebOpenOperationType;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLICK;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLOSE;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.OPEN;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.UL;

public class WebTextDropDownListImpl extends WebTextListImpl implements WebTextDropDownList {

    // Select

    @Override
    public WebTextDropDownList select(@NotNull String text) {
        super.select(text);
        return this;
    }

    @Override
    public WebTextDropDownList select(@NotNull StringValue text) {
        super.select(text);
        return this;
    }

    @Override
    public WebTextDropDownList select(@NotNull WebTextBlockFilterBuilder filterBuilder) {
        super.select(filterBuilder);
        return this;
    }

    @Override
    public WebTextDropDownList select(@NotNull WebTextBlockCondition filterCondition) {
        super.select(filterCondition);
        return this;
    }

    // Checks

    @Override
    public WebTextDropDownList forEach(@NotNull Consumer<WebLink> textBlockConsumer) {
        super.forEach(textBlockConsumer);
        return this;
    }

    @Override
    public WebTextDropDownList forFirst(@NotNull Consumer<WebLink> textBlockConsumer) {
        super.forFirst(textBlockConsumer);
        return this;
    }

    @Override
    public WebTextDropDownList forLast(@NotNull Consumer<WebLink> textBlockConsumer) {
        super.forLast(textBlockConsumer);
        return this;
    }

    // Actions

    @Override
    public WebTextDropDownList executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextDropDownList should(@NotNull WebTextDropDownListMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebTextListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebIndexesMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebGetLabelAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebGetTextAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextDropDownList should(@NotNull WebDropDownAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Click

    @Override
    public WebTextDropDownList click() {
        WebClickOperationType operationType = WebClickOperationType.of(this);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, CLICK).executeAction());
        return this;
    }

    // Close

    @Override
    public WebTextDropDownList close() {
        WebGetIsOpenOperationType isOpenOperationType = WebGetIsOpenOperationType.of(this);
        WebCloseOperationType closeOperationType = WebCloseOperationType.of(this);
        runCheck(closeOperationType.getInvocationName(),
                () -> {
                    boolean isOpen = WebElementOperationHandler.of(this, isOpenOperationType, UL).executeGetter();
                    if (isOpen) {
                        WebElementOperationHandler.of(this, closeOperationType, CLOSE).executeAction();
                    }
                });
        return this;
    }

    // Get Label

    @Override
    public @Nullable String getLabel() {
        WebGetLabelOperationType operationType = WebGetLabelOperationType.of(this);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, LABEL).executeGetter());
    }

    // Get Text

    @Override
    public @Nullable String getText() {
        WebGetTextOperationType operationType = WebGetTextOperationType.of(this);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, TEXT).executeGetter());
    }

    // HoverTo

    @Override
    public WebTextDropDownList hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsOpen

    @Override
    public boolean isOpen() {
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(this);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementIsDisplayedOperationHandler.of(this, operationType, UL).executeGetter());
    }

    // Open

    @Override
    public WebTextDropDownList open() {
        WebGetIsDisplayedOperationType isOpenOperationType = WebGetIsDisplayedOperationType.of(this);
        WebOpenOperationType openOperationType = WebOpenOperationType.of(this);
        runCheck(openOperationType.getInvocationName(),
                () -> {
                    boolean isOpen = WebElementIsDisplayedOperationHandler.of(this, isOpenOperationType, UL).executeGetter();
                    if (!isOpen) {
                        WebElementOperationHandler.of(this, openOperationType, OPEN).executeAction();
                    }
                });
        return this;
    }

    // ScrollTo

    @Override
    public WebTextDropDownList scrollTo() {
        super.scrollTo();
        return this;
    }

}
