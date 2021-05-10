package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.ElementStateNotFound;
import io.perfeccionista.framework.invocation.runner.InvocationName;
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
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateRegistry;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetElementBoundsOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsInFocusOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetStringAttributeValueOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetColorOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsComponentDisplayedOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsComponentPresentOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsDisplayedOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsOnTheScreenOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsPresentOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetScreenshotOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileScrollToOperationType;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.properties.base.MobileElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.base.MobileElementPropertyRegistry;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_STATE_NOT_FOUND;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HAS_STATE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.FOCUS;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.PRESENTED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

public class AbstractMobileChildElement extends AbstractMobileChildElementBase implements MobileChildElement {

    protected MobileElementStateRegistry stateRegistry;
    protected MobileElementPropertyRegistry propertyRegistry;

    @Override
    public MobileChildElement executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public MobileChildElement should(@NotNull MobileChildElementMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileChildElement should(@NotNull MobileGetColorAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileChildElement should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }


    @Override
    public MobileChildElement should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileChildElement should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileChildElement should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileChildElement should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileChildElement should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileChildElement should(@NotNull MobileComponentAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileChildElement should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileChildElement should(@NotNull MobileElementStateAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Get Color

    @Override
    public @NotNull Color getColor(@NotNull String property) {
        MobileGetColorOperationType operationType = MobileGetColorOperationType.of(this, ROOT, property);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType).executeGetter());
    }

    @Override
    public @NotNull Color getColor(@NotNull String componentName, @NotNull String property) {
        MobileGetColorOperationType operationType = MobileGetColorOperationType.of(this, componentName, property);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // Get ElementBounds

    @Override
    public @NotNull ElementBounds getElementBounds() {
        MobileGetElementBoundsOperationType operationType = MobileGetElementBoundsOperationType.of(this, ROOT);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType).executeGetter());
    }

    @Override
    public @NotNull ElementBounds getElementBounds(@NotNull String componentName) {
        MobileGetElementBoundsOperationType operationType = MobileGetElementBoundsOperationType.of(this, componentName);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // Get Screenshot

    @Override
    public @NotNull Screenshot getScreenshot() {
        MobileGetScreenshotOperationType operationType = MobileGetScreenshotOperationType.of(this, ROOT);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType).executeGetter());
    }

    @Override
    public @NotNull Screenshot getScreenshot(@NotNull String componentName) {
        MobileGetScreenshotOperationType operationType = MobileGetScreenshotOperationType.of(this, componentName);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // IsDisplayed

    @Override
    public boolean isDisplayed() {
        MobileGetIsDisplayedOperationType operationType = MobileGetIsDisplayedOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, DISPLAYED).executeGetter());
    }

    // IsInFocus

    @Override
    public boolean isInFocus() {
        MobileGetIsInFocusOperationType operationType = MobileGetIsInFocusOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, FOCUS).executeGetter());
    }

    // IsOnTheScreen

    @Override
    public boolean isOnTheScreen() {
        MobileGetIsOnTheScreenOperationType operationType = MobileGetIsOnTheScreenOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, DISPLAYED).executeGetter());
    }

    // IsPresent

    @Override
    public boolean isPresent() {
        MobileGetIsPresentOperationType operationType = MobileGetIsPresentOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, PRESENTED).executeGetter());
    }

    // MobileComponent

    @Override
    public boolean isComponentDisplayed(@NotNull String componentName) {
        MobileGetIsComponentDisplayedOperationType operationType = MobileGetIsComponentDisplayedOperationType.of(this, componentName);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    @Override
    public boolean isComponentPresent(@NotNull String componentName) {
        MobileGetIsComponentPresentOperationType operationType = MobileGetIsComponentPresentOperationType.of(this, componentName);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // MobileProperty

    @Override
    public Optional<MobileElementPropertyHolder> getProperty(String propertyName) {
        return propertyRegistry.getProperty(propertyName);
    }

    @Override
    public @Nullable String getPropertyValue(@NotNull String propertyName) {
        Optional<MobileElementPropertyHolder> optionalPropertyHolder = getProperty(propertyName);
        if (optionalPropertyHolder.isPresent()) {
            MobileElementPropertyHolder propertyHolder = optionalPropertyHolder.get();
            return runCheck(getEnvironment(), InvocationName.getterInvocation(GET_PROPERTY_VALUE_METHOD, this, propertyHolder), () -> {
                MobileElementOperation<String> operation = propertyHolder.getOperation(this);
                return getMobileDeviceDispatcher()
                        .executor()
                        .executeMobileElementOperation(operation)
                        .ifException((exceptionMapper, originalException) -> {
                            throw exceptionMapper.mapElementException(this, originalException);
                        })
                        .getResult();
            });
        } else {
            // TODO: Если нет атрибута - выбрасывать эксепшн о том, что атрибут не найден
            MobileGetStringAttributeValueOperationType operationType = MobileGetStringAttributeValueOperationType.of(this, propertyName);
            return runCheck(getEnvironment(), operationType.getInvocationName(),
                    () -> MobileElementOperationHandler.of(this, operationType, propertyName).executeGetter());
        }
    }

    // MobileState

    @Override
    public Optional<MobileElementStateHolder> getState(String stateName) {
        return stateRegistry.getState(stateName);
    }

    @Override
    public boolean hasState(@NotNull String stateName) {
        MobileElementStateHolder stateHolder = getState(stateName)
                .orElseThrow(() -> ElementStateNotFound.exception(ELEMENT_STATE_NOT_FOUND.getMessage(stateName)));
        return runCheck(getEnvironment(), InvocationName.getterInvocation(HAS_STATE_METHOD, this, stateHolder), () -> {
            MobileElementOperation<Boolean> operation = stateHolder.getOperation(this);
            return getMobileDeviceDispatcher()
                    .executor()
                    .executeMobileElementOperation(operation)
                    .ifException((exceptionMapper, originalException) -> {
                        throw exceptionMapper.mapElementException(this, originalException);
                    })
                    .getResult();
        });
    }

    // ScrollTo

    @Override
    public MobileChildElement scrollTo() {
        MobileScrollToOperationType operationType = MobileScrollToOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType).executeAction());
        return this;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("properties", this.propertyRegistry.toJson());
        rootNode.set("states", this.stateRegistry.toJson());
        return rootNode;
    }

}
