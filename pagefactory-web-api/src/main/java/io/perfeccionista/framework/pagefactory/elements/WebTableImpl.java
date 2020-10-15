package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTableMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableMultipleIndexedResult;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.rowIndex;

public class WebTableImpl extends AbstractWebChildElement implements WebTable {

    protected WebTableFrame<WebBlock> webTableFrame;

    @Override
    public @NotNull WebTableFrame<WebBlock> getWebTableFrame() {
        return webTableFrame;
    }

    // Extractor

    @Override
    public @NotNull <V> WebSingleIndexedResult<V, WebTable> extractHeader(@NotNull WebTableCellValueExtractor<V> extractor) {
        return WebTableMultipleIndexedResult.of(this, extractor)
                .singleResult();
    }

    @Override
    public @NotNull <V> WebMultipleIndexedResult<V, WebTable> extractAllRows(@NotNull WebTableCellValueExtractor<V> extractor) {
        return WebTableMultipleIndexedResult.of(this, extractor);
    }

    @Override
    public @NotNull <V> WebSingleIndexedResult<V, WebTable> extractFooter(@NotNull WebTableCellValueExtractor<V> extractor) {
        return WebTableMultipleIndexedResult.of(this, extractor)
                .singleResult();
    }

    // Filter

    @Override
    public @NotNull WebTableFilter filter(@NotNull WebTableFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    // Actions

    @Override
    public WebTable executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebTable executeInteraction(@NotNull String interactionName, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebTable should(@NotNull WebTableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebIndexesMatcher matcher) {
        WebTableMultipleIndexedResult<Integer> indexesResult = WebTableMultipleIndexedResult.of(this, rowIndex());
        matcher.check(indexesResult);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull GetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull GetDimensionsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull GetLocationAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull GetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull IsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull IsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull IsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // HoverTo

    @Override
    public WebTable hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // ScrollTo

    @Override
    public WebTable scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("columns", webTableFrame.toJson());
        return rootNode;
    }

}
