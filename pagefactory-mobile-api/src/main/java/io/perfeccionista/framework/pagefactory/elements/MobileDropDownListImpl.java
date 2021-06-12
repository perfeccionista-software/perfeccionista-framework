package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOpenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.matcher.element.MobileDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.MobileListMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.MobileCloseOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileDoubleTapOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsOpenOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetLabelOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileLongTapOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileOpenOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileTapOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLOSE;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.OPEN;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TAP;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.UL;

public class MobileDropDownListImpl extends MobileListImpl implements MobileDropDownList {

    // Actions

    @Override
    public MobileDropDownList executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public MobileDropDownList should(@NotNull MobileDropDownListMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileIndexesMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileGetLabelAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileGetTextAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileDropDownList should(@NotNull MobileIsOpenAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Close


    @Override
    public boolean isOpen() {
        MobileGetIsOpenOperationType operationType = MobileGetIsOpenOperationType.of(this);
        return runCheck(operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, UL).executeGetter());
    }

    @Override
    public MobileDropDownList open() {
        MobileGetIsOpenOperationType isOpenOperationType = MobileGetIsOpenOperationType.of(this);
        MobileOpenOperationType openOperationType = MobileOpenOperationType.of(this);
        runCheck(openOperationType.getInvocationName(),
                () -> {
                    boolean isOpen = MobileElementOperationHandler.of(this, isOpenOperationType, UL).executeGetter();
                    if (!isOpen) {
                        MobileElementOperationHandler.of(this, openOperationType, OPEN).executeAction();
                    }
                });
        return this;
    }

    @Override
    public MobileDropDownList close() {
        MobileGetIsOpenOperationType isOpenOperationType = MobileGetIsOpenOperationType.of(this);
        MobileCloseOperationType closeOperationType = MobileCloseOperationType.of(this);
        runCheck(closeOperationType.getInvocationName(),
                () -> {
                    boolean isOpen = MobileElementOperationHandler.of(this, isOpenOperationType, UL).executeGetter();
                    if (isOpen) {
                        MobileElementOperationHandler.of(this, closeOperationType, CLOSE).executeAction();
                    }
                });
        return this;
    }

    // Get Label

    @Override
    public @Nullable String getLabel() {
        MobileGetLabelOperationType operationType = MobileGetLabelOperationType.of(this);
        return runCheck(operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, LABEL).executeGetter());
    }

    // Get Text

    @Override
    public @Nullable String getText() {
        MobileGetTextOperationType operationType = MobileGetTextOperationType.of(this);
        return runCheck(operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TEXT).executeGetter());
    }

    // IsOpen


    // ScrollTo

    @Override
    public MobileDropDownList scrollTo() {
        super.scrollTo();
        return this;
    }
//
//    @Override
//    public MobileDropDownList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder) {
//        super.scrollToHorizontally(scrollDirection, filterBuilder);
//        return this;
//    }
//
//    @Override
//    public MobileDropDownList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder) {
//        super.scrollToVertically(scrollDirection, filterBuilder);
//        return this;
//    }

    // Tap

    @Override
    public MobileDropDownList tap() {
        MobileTapOperationType operationType = MobileTapOperationType.of(this);
        runCheck(operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }


    @Override
    public MobileDropDownList longTap() {
        MobileLongTapOperationType operationType = MobileLongTapOperationType.of(this);
        runCheck(operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }

    @Override
    public MobileDropDownList doubleTap() {
        MobileDoubleTapOperationType operationType = MobileDoubleTapOperationType.of(this);
        runCheck(operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }

}
