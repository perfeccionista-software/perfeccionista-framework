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
import io.perfeccionista.framework.matcher.element.MobileTableMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileTableFrame;
import io.perfeccionista.framework.pagefactory.extractor.MobileExtractors;
import io.perfeccionista.framework.pagefactory.extractor.table.MobileTableMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.extractor.table.MobileTableValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilter;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.condition.MobileTableRowCondition;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.filter.MobileFilterConditions.allRows;
import static io.perfeccionista.framework.pagefactory.filter.MobileFilters.with;

public class MobileTableImpl extends AbstractMobileChildElement implements MobileTable {

    protected MobileTableFrame<MobileBlock> mobileTableFrame;

    @Override
    public @NotNull MobileTableFrame<MobileBlock> getMobileTableFrame() {
        return mobileTableFrame;
    }

    // Extractor

    @Override
    public @NotNull <V> MobileSingleIndexedResult<V, MobileTable> extractHeader(@NotNull MobileTableValueExtractor<V> extractor) {
        return MobileTableMultipleIndexedResult.of(this, extractor)
                .singleResult();
    }

    @Override
    public @NotNull <V> MobileMultipleIndexedResult<V, MobileTable> extractRows(@NotNull MobileTableValueExtractor<V> extractor) {
        return MobileTableMultipleIndexedResult.of(this, extractor);
    }

    @Override
    public @NotNull <V> MobileSingleIndexedResult<V, MobileTable> extractFooter(@NotNull MobileTableValueExtractor<V> extractor) {
        return MobileTableMultipleIndexedResult.of(this, extractor)
                .singleResult();
    }

    // Filter

    @Override
    public @NotNull MobileTableFilter filter(@NotNull MobileTableFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    @Override
    public @NotNull MobileTableFilter filter(@NotNull MobileTableRowCondition filterCondition) {
        return with(filterCondition).build(this);
    }

    // Actions

    @Override
    public MobileTable executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Asserts

    @Override
    public MobileTable should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher) {
        filter(allRows()).should(matcher);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileTableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileIndexesMatcher matcher) {
        matcher.check(MobileTableMultipleIndexedResult.of(this, MobileExtractors.rowIndex()));
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTable should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // ScrollTo

    @Override
    public MobileTable scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("columns", mobileTableFrame.toJson());
        return rootNode;
    }

}
