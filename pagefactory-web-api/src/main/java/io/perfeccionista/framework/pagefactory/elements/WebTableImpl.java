package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.matcher.element.WebListMatcher;
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
import io.perfeccionista.framework.matcher.element.WebTableMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebBlockFrame;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static io.perfeccionista.framework.Web.*;
import static java.util.Objects.nonNull;

public class WebTableImpl<H extends WebBlock, T extends WebBlock> extends WebListImpl<T> implements WebTable<H, T> {

    protected WebTableFrame<H, T> webTableFrame;

    @Override
    public @NotNull WebBlockFrame<T> getBlockFrame() {
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

    // Asserts

    @Override
    public WebTable<H, T> should(@NotNull WebTableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        filter(allItems()).should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebIndexesMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable<H, T> should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // HoverTo

    @Override
    public WebTable<H, T> hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // ScrollTo

    @Override
    public WebTable<H, T> scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        if (nonNull(webTableFrame)) {
            rootNode.put("mappedHeader", webTableFrame.getMappedHeaderClass().getCanonicalName());
            rootNode.put("mappedRow", webTableFrame.getMappedBlockFrame().getClass().getCanonicalName());
        }
        return rootNode;
    }

}
