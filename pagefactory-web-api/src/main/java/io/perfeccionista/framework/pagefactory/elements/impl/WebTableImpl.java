package io.perfeccionista.framework.pagefactory.elements.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.conditions.WebListElementCondition;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.elements.options.HoverOptions;
import io.perfeccionista.framework.pagefactory.elements.options.ScrollOptions;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static java.util.Objects.nonNull;

public class WebTableImpl<H extends WebBlock<?>, T extends WebBlock<?>> extends WebListImpl<T> implements WebTable<H, T> {

    protected WebTableFrame<H, T> webTableFrame;

    @Override
    public @NotNull WebListFrame<T> getItemFrame() {
        return webTableFrame;
    }

    @Override
    public @NotNull H header() {
        return webTableFrame.getMappedHeader();
    }

    // Checks

    @Override
    public WebTable<H, T> forEach(@NotNull Consumer<T> tableRowConsumer) {
        super.forEach(tableRowConsumer);
        return this;
    }

    @Override
    public WebTable<H, T> forFirst(@NotNull Consumer<T> tableRowConsumer) {
        super.forFirst(tableRowConsumer);
        return this;
    }

    @Override
    public WebTable<H, T> forLast(@NotNull Consumer<T> tableRowConsumer) {
        super.forLast(tableRowConsumer);
        return this;
    }

    // Actions

    @Override
    public WebTable<H, T> executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Add

    @Override
    public WebTable<H, T> addName(@NotNull String elementName) {
        super.addName(elementName);
        return this;
    }

    @Override
    public WebTable<H, T> addComponent(@NotNull String componentName, @NotNull WebSelectorHolder selector) {
        super.addComponent(componentName, selector);
        return this;
    }

    // Asserts

    @Override
    public WebTable<H, T> should(@NotNull WebElementCondition... conditions) {
        super.should(conditions);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebListElementCondition... conditions) {
        super.should(conditions);
        return this;
    }

    @Override
    public WebTable<H, T> shouldNot(@NotNull WebElementCondition... conditions) {
        super.shouldNot(conditions);
        return this;
    }

    @Override
    public WebTable<H, T> shouldNot(@NotNull WebListElementCondition... conditions) {
        super.shouldNot(conditions);
        return this;
    }

    // HoverTo

    @Override
    public WebTable<H, T> hoverTo() {
        super.hoverTo();
        return this;
    }

    @Override
    public WebTable<H, T> hoverTo(@NotNull HoverOptions options) {
        super.hoverTo(options);
        return this;
    }

    // PressKey

    @Override
    public WebTable<H, T> press(@NotNull Key key) {
        super.press(key);
        return this;
    }

    // ScrollTo

    @Override
    public WebTable<H, T> scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public WebTable<H, T> scrollTo(@NotNull ScrollOptions options) {
        super.scrollTo(options);
        return this;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        if (nonNull(webTableFrame)) {
            rootNode.put("mappedHeader", webTableFrame.getMappedHeaderClass().getCanonicalName());
            rootNode.put("mappedRow", webTableFrame.getMappedItemFrame().getClass().getCanonicalName());
        }
        return rootNode;
    }

}
