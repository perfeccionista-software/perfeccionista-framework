package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationHandler;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;

public class MobileBlockImpl extends AbstractMobileChildElement implements MobileBlock {

    protected MobileElementRegistry elementRegistry;

    @Override
    public @NotNull MobileElementRegistry getElementRegistry() {
        return elementRegistry;
    }

    // Search

    @Override
    public @NotNull MobileChildElement getElement(@NotNull String elementPath) {
        return getElementRegistry().getRequiredElementByPath(elementPath);
    }

    @Override
    public <T extends MobileChildElement> @NotNull T getElement(@NotNull String elementPath, @NotNull Class<T> elementClass) {
        return getElementRegistry().getRequiredElementByPath(elementPath, elementClass);
    }

    // Actions

    @Override
    public MobileBlock executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public MobileBlock should(@NotNull MobileBlockMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileBlock should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileBlock should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileBlock should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileBlock should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileBlock should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileBlock should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileBlock should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileBlock should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileBlock should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileBlock should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileBlock should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // ScrollTo

    @Override
    public MobileBlock scrollTo() {
        super.scrollTo();
        return this;
    }

//    @Override
//    public MobileBlock scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileChildElement childElement) {
//        MobileParentScrollToHorizontallyOperationType operationType = MobileParentScrollToHorizontallyOperationType.of(this, scrollDirection, childElement);
//        runCheck(getEnvironment(), operationType.getInvocationName(),
//                () -> MobileElementOperationHandler.of(this, operationType).executeAction());
//        return this;
//    }
//
//    @Override
//    public MobileBlock scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileChildElement childElement) {
//        MobileParentScrollToVerticallyOperationType operationType = MobileParentScrollToVerticallyOperationType.of(this, scrollDirection, childElement);
//        runCheck(getEnvironment(), operationType.getInvocationName(),
//                () -> MobileElementOperationHandler.of(this, operationType).executeAction());
//        return this;
//    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("elements", this.elementRegistry.toJson());
        return rootNode;
    }

}
