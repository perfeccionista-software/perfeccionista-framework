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
import io.perfeccionista.framework.matcher.element.MobileTextTableMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileTableFrame;
import io.perfeccionista.framework.pagefactory.extractor.MobileExtractors;
import io.perfeccionista.framework.pagefactory.extractor.texttable.MobileTextTableMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.extractor.texttable.MobileTextTableValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.texttable.MobileTextTableFilter;
import io.perfeccionista.framework.pagefactory.filter.texttable.MobileTextTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.MobileTextTableRowCondition;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.filter.MobileFilterConditions.allTextRows;
import static io.perfeccionista.framework.pagefactory.filter.MobileFilters.with;

public class MobileTextTableImpl extends AbstractMobileChildElement implements MobileTextTable {

    protected MobileTableFrame<DefaultMobileTextBlock> mobileTextTableFrame;

    @Override
    public @NotNull MobileTableFrame<DefaultMobileTextBlock> getMobileTextTableFrame() {
        return mobileTextTableFrame;
    }

    // Extractor

    @Override
    public @NotNull <V> MobileSingleIndexedResult<V, MobileTextTable> extractHeader(@NotNull MobileTextTableValueExtractor<V> extractor) {
        return MobileTextTableMultipleIndexedResult.of(this, extractor.fromHeader())
                .singleResult();
    }

    @Override
    public @NotNull <V> MobileMultipleIndexedResult<V, MobileTextTable> extractRows(@NotNull MobileTextTableValueExtractor<V> extractor) {
        return MobileTextTableMultipleIndexedResult.of(this, extractor);
    }

    @Override
    public @NotNull <V> MobileSingleIndexedResult<V, MobileTextTable> extractFooter(@NotNull MobileTextTableValueExtractor<V> extractor) {
        return MobileTextTableMultipleIndexedResult.of(this, extractor.fromFooter())
                .singleResult();
    }

    // Filter

    @Override
    public @NotNull MobileTextTableFilter filter(@NotNull MobileTextTableFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    @Override
    public @NotNull MobileTextTableFilter filter(@NotNull MobileTextTableRowCondition filterCondition) {
        return with(filterCondition).build(this);
    }

    // Actions

    @Override
    public MobileTextTable executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Asserts

    @Override
    public MobileTextTable should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher) {
        filter(allTextRows()).should(matcher);
        return null;
    }

    @Override
    public MobileTextTable should(@NotNull MobileTextTableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileIndexesMatcher matcher) {
        matcher.check(MobileTextTableMultipleIndexedResult.of(this, MobileExtractors.textRowIndex()));
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextTable should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // SwipeTo

    @Override
    public MobileTextTable scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("columns", mobileTextTableFrame.toJson());
        return rootNode;
    }

}
