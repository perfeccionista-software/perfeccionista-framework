package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebImageMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.runner.InvocationName.actionInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLICK;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SAVE_TO_FILE_METHOD;

public class WebImageImpl extends AbstractWebChildElement implements WebImage {

    @Override
    public WebImage saveToFile(@NotNull String filePath) {
        runCheck(getEnvironment(), actionInvocation(SAVE_TO_FILE_METHOD, this, filePath),
                () -> getActionImplementation(SAVE_TO_FILE_METHOD, Void.class).execute(this, filePath));
        return this;
    }

    // Actions

    @Override
    public WebImage executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    @Override
    public WebImage executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(name, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebImage should(@NotNull WebImageMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull WebChildElementMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull GetColorAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull GetDimensionsAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull GetLocationAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull GetScreenshotAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull IsDisplayedAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull IsInFocusAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull IsPresentAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull WebComponentAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Click

    @Override
    public WebImage click() {
        runCheck(getEnvironment(), actionInvocation(CLICK_METHOD, this),
                () -> getActionImplementation(CLICK_METHOD, Void.class).execute(this, CLICK));
        return this;
    }

    // HoverTo

    @Override
    public WebImage hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // ScrollTo

    @Override
    public WebImage scrollTo() {
        super.scrollTo();
        return this;
    }

}
