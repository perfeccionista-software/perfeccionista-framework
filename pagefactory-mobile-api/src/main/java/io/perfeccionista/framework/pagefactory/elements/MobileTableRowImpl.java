package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.MobileBlockMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;

public class MobileTableRowImpl extends MappedMobileBlockImpl implements MobileTableRow {

    @Override
    public @NotNull MobileBlock getCell(@NotNull String columnName) {
        return getElementRegistry().getRequiredElementByPath(columnName, MobileBlock.class);
    }

    @Override
    public <T extends MobileBlock> @NotNull T getCell(@NotNull String columnName, @NotNull Class<T> cellClass) {
        return getElementRegistry().getRequiredElementByPath(columnName, cellClass);
    }

    // Actions

    @Override
    public MobileTableRow executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public MobileTableRow should(@NotNull MobileBlockMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileTableRow should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTableRow should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTableRow should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTableRow should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTableRow should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTableRow should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTableRow should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTableRow should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTableRow should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTableRow should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTableRow should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // ScrollTo

    @Override
    public MobileTableRow scrollTo() {
        super.scrollTo();
        return this;
    }

//    @Override
//    public MobileTableRow scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileChildElement childElement) {
//        super.scrollToHorizontally(scrollDirection, childElement);
//        return this;
//    }
//
//    @Override
//    public MobileTableRow scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileChildElement childElement) {
//        super.scrollToVertically(scrollDirection, childElement);
//        return this;
//    }

}
