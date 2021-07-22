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
import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static io.perfeccionista.framework.Web.*;


public class WebTextListImpl extends AbstractWebChildElement implements WebTextList {

    protected WebListFrame<DefaultWebTextBlock> webTextListFrame;

    @Override
    public @NotNull WebListFrame<DefaultWebTextBlock> getWebTextListFrame() {
        return webTextListFrame;
    }

    // Select

    @Override
    public WebTextList select(@NotNull String text) {
        with(containsTextBlock(text)).build(this)
                .forSingleBlock(WebLink::click);
        return this;
    }

    @Override
    public WebTextList select(@NotNull StringValue text) {
        with(containsTextBlock(text)).build(this)
                .forSingleBlock(WebLink::click);
        return this;
    }

    @Override
    public WebTextList select(@NotNull WebTextListFilterBuilder filterBuilder) {
        filterBuilder.build(this)
                .forSingleBlock(WebLink::click);
        return this;
    }

    @Override
    public WebTextList select(@NotNull WebTextListBlockCondition filterCondition) {
        with(filterCondition).build(this)
                .forSingleBlock(WebLink::click);
        return this;
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
    public @NotNull WebTextListFilter filterBuilder(@NotNull WebTextListFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    @Override
    public @NotNull WebTextListFilter filter(@NotNull WebTextListBlockCondition filterCondition) {
        return with(filterCondition).build(this);
    }

    // Checks

    public WebTextList forEachBlock(@NotNull Consumer<WebLink> textListBlockConsumer) {
        filterBuilder(emptyWebTextListFilter())
                .forEachBlock(textListBlockConsumer);
        return this;
    }

    public WebTextList forFirstBlock(@NotNull Consumer<WebLink> textListBlockConsumer) {
        filterBuilder(emptyWebTextListFilter())
                .forFirstBlock(textListBlockConsumer);
        return this;
    }

    public WebTextList forLastBlock(@NotNull Consumer<WebLink> textListBlockConsumer) {
        filterBuilder(emptyWebTextListFilter())
                .forLastBlock(textListBlockConsumer);
        return this;
    }

    // Actions

    @Override
    public WebTextList executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextList should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        filter(allTextBlocks()).should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebTextListMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebIndexesMatcher matcher) {
        matcher.check(WebTextListMultipleIndexedResult.of(this, textBlockIndex()));
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextList should(@NotNull WebIsPresentAvailableMatcher matcher) {
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

    @Override
    public WebTextList should(@NotNull WebElementStateAvailableMatcher matcher) {
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

    // Size

    @Override
    public int size() {
        return extractAll(textBlockIndex()).getSize();
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.put("mappedBlock", webTextListFrame.getMappedBlockFrame().getClass().getCanonicalName());
        return rootNode;
    }

}
