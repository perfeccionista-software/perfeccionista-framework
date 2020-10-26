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
import io.perfeccionista.framework.matcher.element.WebTextTableMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableMultipleIndexedResult;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.textRowIndex;

public class WebTextTableImpl extends AbstractWebChildElement implements WebTextTable {

    protected WebTableFrame<DefaultWebTextBlock> webTextTableFrame;

    @Override
    public @NotNull WebTableFrame<DefaultWebTextBlock> getWebTextTableFrame() {
        return webTextTableFrame;
    }

    // Extractor

    @Override
    public @NotNull <V> WebSingleIndexedResult<V, WebTextTable> extractHeader(@NotNull WebTextTableCellValueExtractor<V> extractor) {
        return WebTextTableMultipleIndexedResult.of(this, extractor.fromHeader())
                .singleResult();
    }

    @Override
    public @NotNull <V> WebMultipleIndexedResult<V, WebTextTable> extractRows(@NotNull WebTextTableCellValueExtractor<V> extractor) {
        return WebTextTableMultipleIndexedResult.of(this, extractor);
    }

    @Override
    public @NotNull <V> WebSingleIndexedResult<V, WebTextTable> extractFooter(@NotNull WebTextTableCellValueExtractor<V> extractor) {
        return WebTextTableMultipleIndexedResult.of(this, extractor.fromFooter())
                .singleResult();
    }

    // Filter

    @Override
    public @NotNull WebTextTableFilter filter(@NotNull WebTextTableFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    // Actions

    @Override
    public WebTextTable executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebTextTable executeInteraction(@NotNull String interactionName, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextTable should(@NotNull WebTextTableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebIndexesMatcher matcher) {
        WebTextTableMultipleIndexedResult<Integer> indexesResult = WebTextTableMultipleIndexedResult.of(this, textRowIndex());
        matcher.check(indexesResult);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull GetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull GetDimensionsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull GetLocationAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull GetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull IsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull IsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull IsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // HoverTo

    @Override
    public WebTextTable hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // ScrollTo

    @Override
    public WebTextTable scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("columns", webTextTableFrame.toJson());
        return rootNode;
    }

}