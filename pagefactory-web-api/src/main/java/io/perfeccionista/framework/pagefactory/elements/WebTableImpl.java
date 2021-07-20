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
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTableMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.Web.*;

public class WebTableImpl extends AbstractWebChildElement implements WebTable {

    protected WebTableFrame<WebBlock> webTableFrame;

    @Override
    public @NotNull WebTableFrame<WebBlock> getWebTableFrame() {
        return webTableFrame;
    }

    // Extractor

    @Override
    public @NotNull <V> WebSingleIndexedResult<V, WebTable> extractHeader(@NotNull WebTableValueExtractor<V> extractor) {
        return WebTableMultipleIndexedResult.of(this, extractor)
                .singleResult();
    }

    @Override
    public @NotNull <V> WebMultipleIndexedResult<V, WebTable> extractRows(@NotNull WebTableValueExtractor<V> extractor) {
        return WebTableMultipleIndexedResult.of(this, extractor);
    }

    @Override
    public @NotNull <V> WebSingleIndexedResult<V, WebTable> extractFooter(@NotNull WebTableValueExtractor<V> extractor) {
        return WebTableMultipleIndexedResult.of(this, extractor)
                .singleResult();
    }

    // Filter

    @Override
    public @NotNull WebTableFilter filterBuilder(@NotNull WebTableFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    @Override
    public @NotNull WebTableFilter filter(@NotNull WebTableRowCondition filterCondition) {
        return with(filterCondition).build(this);
    }

    // Actions

    @Override
    public WebTable executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Asserts

    @Override
    public WebTable should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        filter(allRows()).should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebTableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebIndexesMatcher matcher) {
        matcher.check(WebTableMultipleIndexedResult.of(this, rowIndex()));
        return this;
    }

    @Override
    public WebTable should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTable should(@NotNull WebIsPresentAvailableMatcher matcher) {
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

    @Override
    public WebTable should(@NotNull WebElementStateAvailableMatcher matcher) {
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
