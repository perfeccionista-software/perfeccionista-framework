package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.ElementStateNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyRegistry;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateRegistry;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsDisplayedOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsPresentOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetColorOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetElementBoundsOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsComponentDisplayedOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsComponentPresentOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsDisplayedOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsInFocusOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsOnTheScreenOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsPresentOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetScreenshotOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetStringAttributeValueOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebHoverToOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebScrollToOperationType;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.color.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_STATE_NOT_FOUND;
import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HAS_STATE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.FOCUS;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.HOVER;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.PRESENTED;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

public class AbstractWebChildElement extends AbstractWebChildElementBase implements WebChildElement {

    protected WebElementStateRegistry stateRegistry;
    protected WebElementPropertyRegistry propertyRegistry;

    @Override
    public WebChildElement executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public WebChildElement should(@NotNull WebChildElementMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull WebGetColorAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull WebIsPresentAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull WebComponentAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }


    @Override
    public WebChildElement should(@NotNull WebElementStateAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Get Color

    @Override
    public @NotNull Color getColor(@NotNull String cssProperty) {
        WebGetColorOperationType operationType = WebGetColorOperationType.of(this, ROOT, cssProperty);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType).executeGetter());
    }

    @Override
    public @NotNull Color getColor(@NotNull String componentName, @NotNull String cssProperty) {
        WebGetColorOperationType operationType = WebGetColorOperationType.of(this, componentName, cssProperty);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // Get ElementBounds

    @Override
    public @NotNull ElementBounds getElementBounds() {
        WebGetElementBoundsOperationType operationType = WebGetElementBoundsOperationType.of(this, ROOT);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType).executeGetter());
    }

    @Override
    public @NotNull ElementBounds getElementBounds(@NotNull String componentName) {
        WebGetElementBoundsOperationType operationType = WebGetElementBoundsOperationType.of(this, componentName);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // Get Screenshot

    @Override
    public @NotNull Screenshot getScreenshot() {
        WebGetScreenshotOperationType operationType = WebGetScreenshotOperationType.of(this, ROOT);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType).executeGetter());
    }

    @Override
    public @NotNull Screenshot getScreenshot(@NotNull String componentName) {
        WebGetScreenshotOperationType operationType = WebGetScreenshotOperationType.of(this, componentName);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // HoverTo

    @Override
    public WebChildElement hoverTo(boolean withOutOfBounds) {
        WebHoverToOperationType operationType = WebHoverToOperationType.of(this, withOutOfBounds);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, HOVER).executeAction());
        return this;
    }

    // IsDisplayed

    @Override
    public boolean isDisplayed() {
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(this);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementIsDisplayedOperationHandler.of(this, operationType, DISPLAYED).executeGetter());
    }

    // IsInFocus

    @Override
    public boolean isInFocus() {
        WebGetIsInFocusOperationType operationType = WebGetIsInFocusOperationType.of(this);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, FOCUS).executeGetter());
    }

    // IsOnTheScreen

    @Override
    public boolean isOnTheScreen() {
        WebGetIsOnTheScreenOperationType operationType = WebGetIsOnTheScreenOperationType.of(this);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType).executeGetter());
    }

    // IsPresent

    @Override
    public boolean isPresent() {
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(this);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementIsPresentOperationHandler.of(this, operationType, PRESENTED).executeGetter());
    }

    // ScrollTo

    @Override
    public WebChildElement scrollTo() {
        WebScrollToOperationType operationType = WebScrollToOperationType.of(this);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType).executeAction());
        return this;
    }

    // WebComponent

    @Override
    public boolean isComponentDisplayed(@NotNull String componentName) {
        WebGetIsComponentDisplayedOperationType operationType = WebGetIsComponentDisplayedOperationType.of(this, componentName);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    @Override
    public boolean isComponentPresent(@NotNull String componentName) {
        WebGetIsComponentPresentOperationType operationType = WebGetIsComponentPresentOperationType.of(this, componentName);
        return runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // WebProperty

    @Override
    public Optional<WebElementPropertyHolder> getProperty(String propertyName) {
        return propertyRegistry.getProperty(propertyName);
    }

    @Override
    public @Nullable String getPropertyValue(@NotNull String propertyName) {
        Optional<WebElementPropertyHolder> optionalPropertyHolder = getProperty(propertyName);
        if (optionalPropertyHolder.isPresent()) {
            WebElementPropertyHolder propertyHolder = optionalPropertyHolder.get();
            return runCheck(getterInvocation(GET_PROPERTY_VALUE_METHOD, this, propertyHolder), () -> {
                WebElementOperation<String> operation = propertyHolder.getOperation(this);
                return getWebBrowserDispatcher()
                        .executor()
                        .executeWebElementOperation(operation)
                        .ifException((exceptionMapper, originalException) -> {
                            throw exceptionMapper.mapElementException(this, originalException);
                        })
                        .getResult();
            });
        } else {
            // TODO: Если нет атрибута - выбрасывать эксепшн о том, что атрибут не найден
            WebGetStringAttributeValueOperationType operationType = WebGetStringAttributeValueOperationType.of(this, propertyName);
            return runCheck(operationType.getInvocationName(),
                    () -> WebElementOperationHandler.of(this, operationType, propertyName).executeGetter());
        }
    }

    // WebState

    @Override
    public Optional<WebElementStateHolder> getState(String stateName) {
        return stateRegistry.getState(stateName);
    }

    @Override
    public boolean hasState(@NotNull String stateName) {
        WebElementStateHolder stateHolder = getState(stateName)
                .orElseThrow(() -> ElementStateNotFound.exception(ELEMENT_STATE_NOT_FOUND.getMessage(stateName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(this)));
        return runCheck(getterInvocation(HAS_STATE_METHOD, this, stateHolder), () -> {
            WebElementOperation<Boolean> operation = stateHolder.getOperation(this);
            WebElementOperationResult<Boolean> operationResult = getWebBrowserDispatcher()
                    .executor()
                    .executeWebElementOperation(operation)
                    .ifException((exceptionMapper, originalException) -> {
                        throw exceptionMapper.mapElementException(this, originalException);
                    });
                    if (operationResult.hasResult()) {
                        return operationResult.getNotNullResult();
                    }
                    return false;
        });
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("properties", this.propertyRegistry.toJson());
        rootNode.set("states", this.stateRegistry.toJson());
        return rootNode;
    }

}
