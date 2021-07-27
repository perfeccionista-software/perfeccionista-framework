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
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.WebClickOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebCloseOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsOpenOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetLabelOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebOpenOperationType;
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

public class WebDropDownListImpl<T extends WebBlock> extends WebListImpl<T> implements WebDropDownList<T> {


    // Checks

    @Override
    public WebDropDownList<T> forEach(@NotNull Consumer<T> blockConsumer) {
        super.forEach(blockConsumer);
        return this;
    }

    @Override
    public WebDropDownList<T> forFirst(@NotNull Consumer<T> blockConsumer) {
        super.forFirst(blockConsumer);
        return this;
    }

    @Override
    public WebDropDownList<T> forLast(@NotNull Consumer<T> blockConsumer) {
        super.forLast(blockConsumer);
        return this;
    }

    // Actions

    @Override
    public WebDropDownList<T> executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public WebDropDownList<T> should(@NotNull WebDropDownListMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull WebListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebIndexesMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebGetLabelAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebGetTextAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebDropDownList<T> should(@NotNull WebDropDownAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Click

    @Override
    public WebDropDownList<T> click() {
        WebClickOperationType operationType = WebClickOperationType.of(this);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, CLICK).executeAction());
        return this;
    }

    // Close

    @Override
    public WebDropDownList<T> close() {
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
    public WebDropDownList<T> hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsOpen

    @Override
    public boolean isOpen() {
        WebGetIsOpenOperationType operationType = WebGetIsOpenOperationType.of(this);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, UL).executeGetter());
    }

    // Open

    @Override
    public WebDropDownList<T> open() {
        WebGetIsOpenOperationType isOpenOperationType = WebGetIsOpenOperationType.of(this);
        WebOpenOperationType openOperationType = WebOpenOperationType.of(this);
        runCheck(openOperationType.getInvocationName(),
                () -> {
                    boolean isOpen = WebElementOperationHandler.of(this, isOpenOperationType, UL).executeGetter();
                    if (!isOpen) {
                        WebElementOperationHandler.of(this, openOperationType, OPEN).executeAction();
                    }
                });
        return this;
    }

    // ScrollTo

    @Override
    public WebDropDownList<T> scrollTo() {
        super.scrollTo();
        return this;
    }

}
