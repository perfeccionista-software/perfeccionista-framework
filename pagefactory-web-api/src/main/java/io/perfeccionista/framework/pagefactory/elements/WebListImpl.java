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
import io.perfeccionista.framework.pagefactory.elements.mapping.WebBlockFrame;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import static io.perfeccionista.framework.Web.*;

public class WebListImpl<T extends WebBlock> extends AbstractWebChildElement implements WebList<T> {

    protected WebBlockFrame<T> webListFrame;

    @Override
    public @NotNull WebBlockFrame<T> getBlockFrame() {
        return webListFrame;
    }

    // Extractor

    @Override
    public @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull WebBlockValueExtractor<R, T> extractor) {
        return WebListMultipleIndexedResult.of(this, extractor);
    }

    @Override
    public @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull Function<T, ? extends WebBlockValueExtractor<R, T>> extractorFunction) {
        return WebListMultipleIndexedResult.of(this, extractorFunction.apply(getBlockFrame().getMappedBlockFrame()));
    }

    // Filter
    @Override
    public @NotNull WebBlockFilter<T> filterBuilder(@NotNull WebBlockFilterBuilder<T> filterBuilder) {
        return filterBuilder.build(this);
    }

    @Override
    public @NotNull WebBlockFilter<T> filterBuilder(@NotNull Function<T, ? extends WebBlockFilterBuilder<T>> filterBuilderFunction) {
        return filterBuilderFunction.apply(getBlockFrame().getMappedBlockFrame())
                .build(this);
    }

    @Override
    public @NotNull WebBlockFilter<T> filter(@NotNull WebBlockCondition<T> filterCondition) {
        return with(filterCondition).build(this);
    }

    @Override
    public @NotNull WebBlockFilter<T> filter(@NotNull Function<T, ? extends WebBlockCondition<T>> filterConditionFunction) {
        return with(filterConditionFunction.apply(getBlockFrame().getMappedBlockFrame()))
                .build(this);
    }

    @Override
    public WebList<T> forEach(@NotNull Consumer<T> blockConsumer) {
        filterBuilder(block -> emptyWebBlockFilter())
                .forEach(blockConsumer);
        return this;
    }

    @Override
    public WebList<T> forFirst(@NotNull Consumer<T> blockConsumer) {
        filterBuilder(block -> emptyWebBlockFilter())
                .forFirst(blockConsumer);
        return this;
    }

    @Override
    public WebList<T> forLast(@NotNull Consumer<T> blockConsumer) {
        filterBuilder(block -> emptyWebBlockFilter())
                .forLast(blockConsumer);
        return this;
    }

    // Actions

    @Override
    public WebList<T> executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts


    @Override
    public WebList<T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        filterBuilder(block -> emptyWebBlockFilter()).should(matcher);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebListMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebIndexesMatcher matcher) {
        matcher.check(WebListMultipleIndexedResult.of(this, blockIndex()));
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // HoverTo

    @Override
    public WebList<T> hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // ScrollTo

    @Override
    public WebList<T> scrollTo() {
        super.scrollTo();
        return this;
    }

    // Size

    @Override
    public int size() {
        return extractAll(blockIndex()).getSize();
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        if (Objects.nonNull(webListFrame)) {
            rootNode.put("mappedBlock", webListFrame.getMappedBlockFrame().getClass().getCanonicalName());
        }
        return rootNode;
    }

}
