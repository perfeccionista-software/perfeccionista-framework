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
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.blockIndex;

public class WebListImpl extends AbstractWebChildElement implements WebList {

    protected WebListFrame<WebBlock> webListFrame;

    @Override
    public @NotNull WebListFrame<WebBlock> getWebListFrame() {
        return webListFrame;
    }

    // Extractor

    @Override
    public @NotNull <V> WebMultipleIndexedResult<V, WebList> extractAll(@NotNull WebListBlockValueExtractor<V> extractor) {
        return WebListMultipleIndexedResult.of(this, extractor);
    }

    // Filter

    @Override
    public @NotNull WebListFilter filter(@NotNull WebListFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    // Actions

    @Override
    public WebList executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    @Override
    public WebList executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(name, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebList should(@NotNull WebListMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebList should(@NotNull WebIndexesMatcher matcher) {
        WebListMultipleIndexedResult<Integer> indexesResult = WebListMultipleIndexedResult.of(this, blockIndex());
        matcher.check(indexesResult);
        return this;
    }

    @Override
    public WebList should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull GetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull GetDimensionsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull GetLocationAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull GetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull IsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull IsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull IsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // HoverTo

    @Override
    public WebList hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // ScrollTo

    @Override
    public WebList scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.put("mappedBlock", webListFrame.getMappedBlockFrame().getClass().getCanonicalName());
        return rootNode;
    }

}
