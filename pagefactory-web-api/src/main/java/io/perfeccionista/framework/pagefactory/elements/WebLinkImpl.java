package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;


public class WebLinkImpl extends WebTextImpl implements WebLink {

    // Actions

    @Override
    public WebLink executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public WebLink should(@NotNull WebTextMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebLink should(@NotNull WebGetTextAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Click

    @Override
    public WebLink click() {
        super.click();
        return this;
    }

    // HoverTo

    @Override
    public WebLink hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // ScrollTo

    @Override
    public WebLink scrollTo() {
        super.scrollTo();
        return this;
    }

}
