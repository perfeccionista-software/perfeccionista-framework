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
import io.perfeccionista.framework.matcher.element.WebTextTableMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.Web.*;


public class WebTextTableImpl extends AbstractWebChildElement implements WebTextTable {

    protected WebTableFrame<DefaultWebTextBlock> webTextTableFrame;

    @Override
    public @NotNull WebTableFrame<DefaultWebTextBlock> getWebTextTableFrame() {
        return webTextTableFrame;
    }

    // Extractor

    @Override
    public @NotNull <V> WebSingleIndexedResult<V, WebTextTable> extractHeader(@NotNull WebTextTableValueExtractor<V> extractor) {
        return WebTextTableMultipleIndexedResult.of(this, extractor.fromHeader())
                .singleResult();
    }

    @Override
    public @NotNull <V> WebMultipleIndexedResult<V, WebTextTable> extractRows(@NotNull WebTextTableValueExtractor<V> extractor) {
        return WebTextTableMultipleIndexedResult.of(this, extractor);
    }

    @Override
    public @NotNull <V> WebSingleIndexedResult<V, WebTextTable> extractFooter(@NotNull WebTextTableValueExtractor<V> extractor) {
        return WebTextTableMultipleIndexedResult.of(this, extractor.fromFooter())
                .singleResult();
    }

    // Filter

    @Override
    public @NotNull WebTextTableFilter filter(@NotNull WebTextTableFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    @Override
    public @NotNull WebTextTableFilter filter(@NotNull WebTextTableRowCondition filterCondition) {
        return with(filterCondition).build(this);
    }

    // Actions

    @Override
    public WebTextTable executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextTable should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        filter(allTextRows()).should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebTextTableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebIndexesMatcher matcher) {
        matcher.check(WebTextTableMultipleIndexedResult.of(this, textRowIndex()));
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextTable should(@NotNull WebIsPresentAvailableMatcher matcher) {
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

    @Override
    public WebTextTable should(@NotNull WebElementStateAvailableMatcher matcher) {
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
