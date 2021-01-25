package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.matcher.element.MobileTextMatcher;
import org.jetbrains.annotations.NotNull;

public class MobileTileImpl extends MobileButtonImpl implements MobileTile {

    // Actions

    @Override
    public MobileTile executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public MobileTile should(@NotNull MobileTextMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTile should(@NotNull MobileGetTextAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // SwipeTo

    @Override
    public MobileTile scrollTo() {
        super.scrollTo();
        return this;
    }

    // Tap

    @Override
    public MobileTile tap() {
        super.tap();
        return this;
    }

    @Override
    public MobileTile longTap() {
        super.longTap();
        return this;
    }

    @Override
    public MobileTile doubleTap() {
        super.doubleTap();
        return this;
    }

}
