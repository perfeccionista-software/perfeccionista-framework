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
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.Web.*;

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

    public @NotNull WebListFilter filter(@NotNull WebListBlockCondition filterCondition) {
        return with(filterCondition).build(this);
    }

    // Actions

    @Override
    public WebList executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts


    @Override
    public WebList should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        filter(allBlocks()).should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull WebListMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebList should(@NotNull WebIndexesMatcher matcher) {
        matcher.check(WebListMultipleIndexedResult.of(this, blockIndex()));
        return this;
    }

    @Override
    public WebList should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList should(@NotNull WebIsPresentAvailableMatcher matcher) {
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

    @Override
    public WebList should(@NotNull WebElementStateAvailableMatcher matcher) {
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
