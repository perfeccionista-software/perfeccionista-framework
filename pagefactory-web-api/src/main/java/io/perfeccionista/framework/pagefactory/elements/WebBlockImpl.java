package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import io.perfeccionista.framework.matcher.element.WebBlockMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import org.jetbrains.annotations.NotNull;

public class WebBlockImpl extends AbstractWebChildElement implements WebBlock {

    protected WebElementRegistry elementRegistry;

    @Override
    public @NotNull WebElementRegistry getElementRegistry() {
        return elementRegistry;
    }

    // Search

    @Override
    public @NotNull WebChildElement getElement(@NotNull String elementPath) {
        return getElementRegistry().getRequiredElementByPath(elementPath);
    }

    @Override
    public <T extends WebChildElement> @NotNull T getElement(@NotNull String elementPath, @NotNull Class<T> elementClass) {
        return getElementRegistry().getRequiredElementByPath(elementPath, elementClass);
    }

    // Actions

    @Override
    public WebBlock executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public WebBlock should(@NotNull WebBlockMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebBlock should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebBlock should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebBlock should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebBlock should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebBlock should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebBlock should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebBlock should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebBlock should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebBlock should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebBlock should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebBlock should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // HoverTo

    @Override
    public WebBlock hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // ScrollTo

    @Override
    public WebBlock scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("elements", this.elementRegistry.toJson());
        return rootNode;
    }

}
