package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebImageMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.WebClickOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebIsImageOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebSaveImageToFileOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLICK;

public class WebImageImpl extends AbstractWebChildElement implements WebImage {

    @Override
    public boolean isImage() {
        WebIsImageOperationType operationType = WebIsImageOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType).executeGetter());
    }

    @Override
    public WebImage saveToFile(@NotNull String filePath) {
        WebSaveImageToFileOperationType operationType = WebSaveImageToFileOperationType.of(this, filePath);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType).executeAction());
        return this;
    }

    // Actions

    @Override
    public WebImage executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
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
    public WebImage should(@NotNull WebGetColorAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebImage should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebImage should(@NotNull WebIsPresentAvailableMatcher matcher) {
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

    @Override
    public WebImage should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // Click

    @Override
    public WebImage click() {
        WebClickOperationType operationType = WebClickOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, CLICK).executeAction());
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
