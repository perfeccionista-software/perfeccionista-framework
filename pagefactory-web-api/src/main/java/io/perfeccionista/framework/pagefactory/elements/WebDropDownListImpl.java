package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOpenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.invocation.runner.InvocationName.actionInvocation;
import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLICK;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLOSE;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.OPEN;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.OPEN_METHOD;

public class WebDropDownListImpl extends WebListImpl implements WebDropDownList {

    // Actions

    @Override
    public WebDropDownList executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    @Override
    public WebDropDownList executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(name, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebDropDownList should(@NotNull WebDropDownListMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull WebListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull WebIndexesMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull GetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull GetDimensionsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull GetLocationAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull GetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull IsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull IsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull IsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull GetLabelAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull GetTextAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebDropDownList should(@NotNull IsOpenAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Click

    @Override
    public WebDropDownList click() {
        runCheck(getEnvironment(), actionInvocation(CLICK_METHOD, this),
                () -> getActionImplementation(CLICK_METHOD, Void.class).execute(this, CLICK));
        return this;
    }

    // Close

    @Override
    public WebDropDownList close() {
        runCheck(getEnvironment(), actionInvocation(CLOSE_METHOD, this),
                () -> {
                    boolean isOpen = getActionImplementation(IS_OPEN_METHOD, Boolean.class)
                            .execute(this);
                    if (isOpen) {
                        getActionImplementation(CLOSE_METHOD, Void.class)
                                .execute(this, CLOSE);
                    }
                });
        return this;
    }

    // Get Label

    @Override
    public @Nullable String getLabel() {
        return runCheck(getEnvironment(), getterInvocation(GET_LABEL_METHOD, this),
                () -> getActionImplementation(GET_LABEL_METHOD, String.class).execute(this));
    }

    // Get Text

    @Override
    public @Nullable String getText() {
        return runCheck(getEnvironment(), getterInvocation(GET_TEXT_METHOD, this),
                () -> getActionImplementation(GET_TEXT_METHOD, String.class).execute(this));
    }

    // HoverTo

    @Override
    public WebDropDownList hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsOpen

    @Override
    public boolean isOpen() {
        return runCheck(getEnvironment(), getterInvocation(IS_OPEN_METHOD, this),
                () -> getActionImplementation(IS_OPEN_METHOD, Boolean.class).execute(this));
    }

    // Open

    @Override
    public WebDropDownList open() {
        runCheck(getEnvironment(), actionInvocation(OPEN_METHOD, this),
                () -> {
                    boolean isOpen = getActionImplementation(IS_OPEN_METHOD, Boolean.class)
                            .execute(this);
                    if (!isOpen) {
                        getActionImplementation(OPEN_METHOD, Void.class)
                                .execute(this, OPEN);
                    }
                });
        return this;
    }

    // ScrollTo

    @Override
    public WebDropDownList scrollTo() {
        super.scrollTo();
        return this;
    }

}
