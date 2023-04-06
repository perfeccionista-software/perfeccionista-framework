package io.perfeccionista.framework.pagefactory.elements.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.conditions.WebElementConditionExceptionHandler;
import io.perfeccionista.framework.exceptions.ElementStateNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.options.GetScreenshotOptions;
import io.perfeccionista.framework.pagefactory.elements.options.HoverOptions;
import io.perfeccionista.framework.pagefactory.elements.options.OnTheScreenOptions;
import io.perfeccionista.framework.pagefactory.elements.options.ScrollOptions;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateRegistry;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsDisplayedOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsPresentOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebCheckAttributeOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetColorOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetElementBoundsOperationType;
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

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_STATE_NOT_FOUND;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.invocation.wrapper.SingleAttemptInvocationWrapper.runInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HAS_STATE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.options.HoverOptions.hoverOptions;
import static io.perfeccionista.framework.pagefactory.elements.options.OnTheScreenOptions.onTheScreenOptions;
import static io.perfeccionista.framework.pagefactory.elements.options.ScrollOptions.scrollOptions;
import static io.perfeccionista.framework.utils.WebElementUtils.checkAndCollect;

public class AbstractWebChildElement extends AbstractWebChildElementBase implements WebChildElement {

    protected WebElementStateRegistry stateRegistry;

    // Actions

    @Override
    public WebChildElement executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Add

    @Override
    public WebChildElement addName(@NotNull String elementName) {
        super.addName(elementName);
        return this;
    }

    @Override
    public WebChildElement addComponent(@NotNull String componentName, @NotNull WebSelectorHolder selector) {
        super.addComponent(componentName, selector);
        return this;
    }

    // Asserts

    @Override
    public WebChildElement should(@NotNull WebElementCondition... conditions) {
        checkAndCollect(conditions).forEach(condition -> {
            condition.validate(this);
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            repeatInvocation(invocationInfo, () -> condition.check(this, invocationInfo));
        });
        return this;
    }

    @Override
    public WebChildElement shouldNot(@NotNull WebElementCondition... conditions) {
        checkAndCollect(conditions).forEach(condition -> {
            condition.validate(this)
                    .inverse();
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            repeatInvocation(invocationInfo, () -> condition.check(this, invocationInfo));
        });
        return this;
    }

    // Checks

    @Override
    public boolean check(@NotNull WebElementCondition... conditions) {
        return checkAndCollect(conditions).stream().allMatch(condition -> {
            condition.validate(this);
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            return runInvocation(invocationInfo, () -> WebElementConditionExceptionHandler.of(() -> condition.check(this, invocationInfo))
                    .isSuccess());
        });
    }

    @Override
    public boolean checkNot(@NotNull WebElementCondition... conditions) {
        return checkAndCollect(conditions).stream().allMatch(condition -> {
            condition.validate(this)
                    .inverse();
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            return runInvocation(invocationInfo, () -> WebElementConditionExceptionHandler.of(() -> condition.check(this, invocationInfo))
                    .isSuccess());
        });
    }

    // Get Color

    @Override
    public @NotNull Color getColor(@NotNull String cssProperty) {
        WebGetColorOperationType operationType = WebGetColorOperationType.of(this, ROOT, cssProperty);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ROOT).executeGetter());
    }

    @Override
    public @NotNull Color getColor(@NotNull String componentName, @NotNull String cssProperty) {
        WebGetColorOperationType operationType = WebGetColorOperationType.of(this, componentName, cssProperty);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // Get ElementBounds

    @Override
    public @NotNull ElementBounds getElementBounds() {
        WebGetElementBoundsOperationType operationType = WebGetElementBoundsOperationType.of(this, ROOT);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ROOT).executeGetter());
    }

    @Override
    public @NotNull ElementBounds getElementBounds(@NotNull String componentName) {
        WebGetElementBoundsOperationType operationType = WebGetElementBoundsOperationType.of(this, componentName);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // Get Screenshot

    @Override
    public @NotNull Screenshot getScreenshot() {
        GetScreenshotOptions options = GetScreenshotOptions.screenshotOptions();
        WebGetScreenshotOperationType operationType = WebGetScreenshotOperationType.of(this, options);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.getComponentName()).executeGetter());
    }

    @Override
    public @NotNull Screenshot getScreenshot(@NotNull GetScreenshotOptions options) {
        WebGetScreenshotOperationType operationType = WebGetScreenshotOperationType.of(this, options);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.getComponentName()).executeGetter());
    }

    // HoverTo

    @Override
    public WebChildElement hoverTo() {
        HoverOptions options = hoverOptions();
        WebHoverToOperationType operationType = WebHoverToOperationType.of(this, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.getComponentName()).executeAction());
        return this;
    }

    @Override
    public WebChildElement hoverTo(@NotNull HoverOptions options) {
        WebHoverToOperationType operationType = WebHoverToOperationType.of(this, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.getComponentName()).executeAction());
        return this;
    }

    // IsDisplayed

    @Override
    public boolean isDisplayed() {
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(this, ROOT);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementIsDisplayedOperationHandler.of(this, operationType, ROOT).executeGetter());
    }

    @Override
    public boolean isDisplayed(@NotNull String componentName) {
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(this, componentName);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementIsDisplayedOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // IsInFocus

    @Override
    public boolean isInFocus() {
        WebGetIsInFocusOperationType operationType = WebGetIsInFocusOperationType.of(this, ROOT);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ROOT).executeGetter());
    }

    @Override
    public boolean isInFocus(@NotNull String componentName) {
        WebGetIsInFocusOperationType operationType = WebGetIsInFocusOperationType.of(this, componentName);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // IsOnTheScreen

    @Override
    public boolean isOnTheScreen() {
        OnTheScreenOptions options = onTheScreenOptions();
        WebGetIsOnTheScreenOperationType operationType = WebGetIsOnTheScreenOperationType.of(this, options);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.getComponentName()).executeGetter());
    }

    @Override
    public boolean isOnTheScreen(@NotNull OnTheScreenOptions options) {
        WebGetIsOnTheScreenOperationType operationType = WebGetIsOnTheScreenOperationType.of(this, options);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.getComponentName()).executeGetter());
    }

    // IsPresent

    @Override
    public boolean isPresent() {
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(this, ROOT);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementIsPresentOperationHandler.of(this, operationType, ROOT).executeGetter());
    }

    @Override
    public boolean isPresent(@NotNull String componentName) {
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(this, componentName);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementIsPresentOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // Press key

    @Override
    public WebChildElement press(@NotNull Key key) {
        super.press(key);
        return this;
    }

    // ScrollTo

    @Override
    public WebChildElement scrollTo() {
        ScrollOptions options = scrollOptions();
        WebScrollToOperationType operationType = WebScrollToOperationType.of(this, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.getComponentName()).executeAction());
        return this;
    }

    @Override
    public WebChildElement scrollTo(@NotNull ScrollOptions options) {
        WebScrollToOperationType operationType = WebScrollToOperationType.of(this, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.getComponentName()).executeAction());
        return this;
    }

    // WebAttribute

    @Override
    public boolean hasAttribute(@NotNull String attributeName) {
        WebCheckAttributeOperationType operationType = WebCheckAttributeOperationType.of(this, ROOT, attributeName);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ROOT).executeGetter());
    }

    @Override
    public boolean hasAttribute(@NotNull String componentName, @NotNull String attributeName) {
        WebCheckAttributeOperationType operationType = WebCheckAttributeOperationType.of(this, componentName, attributeName);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    @Override
    public Optional<String> getAttribute(@NotNull String attributeName) {
        WebGetStringAttributeValueOperationType operationType = WebGetStringAttributeValueOperationType.of(this, ROOT, attributeName);
        return Optional.of(repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ROOT).executeGetter()));
    }

    @Override
    public Optional<String> getAttribute(@NotNull String componentName, @NotNull String attributeName) {
        WebGetStringAttributeValueOperationType operationType = WebGetStringAttributeValueOperationType.of(this, componentName, attributeName);
        return Optional.of(repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter()));
    }

    // WebState

    @Override
    public Optional<WebElementStateHolder> getStateHolder(String stateName) {
        return stateRegistry.getState(stateName);
    }

    @Override
    public boolean hasState(@NotNull String stateName) {
        WebElementStateHolder stateHolder = getStateHolder(stateName)
                .orElseThrow(() -> ElementStateNotFound.exception(ELEMENT_STATE_NOT_FOUND.getMessage(stateName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(this)));
        String elementName = this.elementIdentifier.getLastUsedName();
        return repeatInvocation(getterInvocation(HAS_STATE_METHOD, elementName, stateHolder.getName()), () -> {
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

//    @Override
//    public WebNode asNode() {
//        return WebPageService.getInstance().createWebNode(this);
//    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("states", this.stateRegistry.toJson());
        return rootNode;
    }

}
