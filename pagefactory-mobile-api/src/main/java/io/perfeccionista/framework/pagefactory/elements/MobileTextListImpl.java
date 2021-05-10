package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.matcher.element.MobileTextListMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileListFrame;
import io.perfeccionista.framework.pagefactory.extractor.MobileExtractors;
import io.perfeccionista.framework.pagefactory.extractor.textlist.MobileTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.MobileTextListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.MobileTextListFilter;
import io.perfeccionista.framework.pagefactory.filter.textlist.MobileTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.MobileTextListBlockCondition;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.filter.MobileFilterConditions.allTextBlocks;
import static io.perfeccionista.framework.pagefactory.filter.MobileFilters.with;

public class MobileTextListImpl extends AbstractMobileChildElement implements MobileTextList {

    protected MobileListFrame<DefaultMobileTextBlock> mobileTextListFrame;

    @Override
    public @NotNull MobileListFrame<DefaultMobileTextBlock> getMobileTextListFrame() {
        return mobileTextListFrame;
    }

    // Extractor

    @Override
    public @NotNull MobileMultipleIndexedResult<String, MobileTextList> extractAll() {
        return MobileTextListMultipleIndexedResult.of(this, MobileExtractors.textBlockValue());
    }

    @Override
    public @NotNull <V> MobileMultipleIndexedResult<V, MobileTextList> extractAll(@NotNull MobileTextListBlockValueExtractor<V> extractor) {
        return MobileTextListMultipleIndexedResult.of(this, extractor);
    }

    // Filter

    @Override
    public @NotNull MobileTextListFilter filter(@NotNull MobileTextListFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    @Override
    public @NotNull MobileTextListFilter filter(@NotNull MobileTextListBlockCondition filterCondition) {
        return with(filterCondition).build(this);
    }

    // Actions

    @Override
    public MobileTextList executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Asserts

    @Override
    public MobileTextList should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher) {
        filter(allTextBlocks()).should(matcher);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileTextListMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileIndexesMatcher matcher) {
        matcher.check(MobileTextListMultipleIndexedResult.of(this, MobileExtractors.textBlockIndex()));
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextList should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // ScrollTo

    @Override
    public MobileTextList scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.put("mappedBlock", mobileTextListFrame.getMappedBlockFrame().getClass().getCanonicalName());
        return rootNode;
    }

}
