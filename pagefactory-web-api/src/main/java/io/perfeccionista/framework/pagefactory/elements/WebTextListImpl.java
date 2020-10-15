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
import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListMultipleIndexedResult;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.textBlockIndex;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.textBlockValue;

public class WebTextListImpl extends AbstractWebChildElement implements WebTextList {

    protected WebListFrame<DefaultWebTextBlock> webTextListFrame;

    @Override
    public @NotNull WebListFrame<DefaultWebTextBlock> getWebTextListFrame() {
        return webTextListFrame;
    }

    // Extractor

    @Override
    public @NotNull WebMultipleIndexedResult<String, WebTextList> extractAll() {
        return WebTextListMultipleIndexedResult.of(this, textBlockValue());
    }

    @Override
    public @NotNull <V> WebMultipleIndexedResult<V, WebTextList> extractAll(@NotNull WebTextListBlockValueExtractor<V> extractor) {
        return WebTextListMultipleIndexedResult.of(this, extractor);
    }

    // Filter

    @Override
    public @NotNull WebTextListFilter filter(@NotNull WebTextListFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    // Actions

    @Override
    public WebTextList executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebTextList executeInteraction(@NotNull String interactionName, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextList should(@NotNull WebTextListMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebIndexesMatcher matcher) {
        WebTextListMultipleIndexedResult<Integer> indexesResult = WebTextListMultipleIndexedResult.of(this, textBlockIndex());
        matcher.check(indexesResult);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull GetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull GetDimensionsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull GetLocationAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull GetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull IsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull IsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull IsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // HoverTo

    @Override
    public WebTextList hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // ScrollTo

    @Override
    public WebTextList scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.put("mappedBlock", webTextListFrame.getMappedBlockFrame().getClass().getCanonicalName());
        return rootNode;
    }

}
