package io.perfeccionista.framework.pagefactory.elements.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.options.HoverOptions;
import io.perfeccionista.framework.pagefactory.elements.options.ScrollOptions;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import org.jetbrains.annotations.NotNull;

public class WebBlockImpl extends AbstractWebChildElement implements WebBlock<WebBlock> {

    protected WebElementRegistry elementRegistry;

    @Override
    public @NotNull WebElementRegistry getElementRegistry() {
        return elementRegistry;
    }

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

    // Add

    @Override
    public WebBlock addComponent(@NotNull String componentName, @NotNull WebSelectorHolder selector) {
        super.addComponent(componentName, selector);
        return this;
    }

    @Override
    public WebBlock addName(@NotNull String elementName) {
        super.addName(elementName);
        return this;
    }

    // Asserts

    @Override
    public WebBlock should(@NotNull WebElementCondition... conditions) {
        super.should(conditions);
        return this;
    }

    @Override
    public WebBlock shouldNot(@NotNull WebElementCondition... conditions) {
        super.shouldNot(conditions);
        return this;
    }

    // HoverTo

    @Override
    public WebBlock hoverTo() {
        super.hoverTo();
        return this;
    }

    @Override
    public WebBlock hoverTo(@NotNull HoverOptions options) {
        super.hoverTo(options);
        return this;
    }

    // PressKey

    @Override
    public WebBlock press(@NotNull Key key) {
        super.press(key);
        return this;
    }

    // ScrollTo

    @Override
    public WebBlock scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public WebBlock scrollTo(@NotNull ScrollOptions options) {
        super.scrollTo(options);
        return this;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("elements", this.elementRegistry.toJson());
        return rootNode;
    }

}
