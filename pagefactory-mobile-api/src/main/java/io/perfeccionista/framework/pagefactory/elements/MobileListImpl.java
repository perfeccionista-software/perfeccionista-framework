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
import io.perfeccionista.framework.matcher.element.MobileListMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileListFrame;
import io.perfeccionista.framework.pagefactory.extractor.MobileExtractors;
import io.perfeccionista.framework.pagefactory.extractor.list.MobileListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.MobileListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.condition.MobileListBlockCondition;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.MobileListScrollToVerticallyOperationType;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.filter.MobileFilterConditions.allBlocks;
import static io.perfeccionista.framework.pagefactory.filter.MobileFilters.with;

public class MobileListImpl extends AbstractMobileChildElement implements MobileList {

    protected MobileListFrame<MobileBlock> mobileListFrame;

    @Override
    public @NotNull MobileListFrame<MobileBlock> getMobileListFrame() {
        return mobileListFrame;
    }

    // Extractor

    @Override
    public @NotNull <V> MobileMultipleIndexedResult<V, MobileList> extractAll(@NotNull MobileListBlockValueExtractor<V> extractor) {
        return MobileListMultipleIndexedResult.of(this, extractor);
    }

    // Filter

    @Override
    public @NotNull MobileListFilter filter(@NotNull MobileListFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    @Override
    public @NotNull MobileListFilter filter(@NotNull MobileListBlockCondition filterCondition) {
        return with(filterCondition).build(this);
    }

    // Actions

    @Override
    public MobileList executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public MobileList should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher) {
        filter(allBlocks()).should(matcher);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileListMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileIndexesMatcher matcher) {
        matcher.check(MobileListMultipleIndexedResult.of(this, MobileExtractors.blockIndex()));
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileList should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // ScrollTo

    @Override
    public MobileList scrollTo() {
        super.scrollTo();
        return this;
    }

//    @Override
//    public MobileList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder) {
//        MobileListScrollToHorizontallyOperationType operationType = MobileListScrollToHorizontallyOperationType.of(this, scrollDirection, filterBuilder);
//        runCheck(getEnvironment(), operationType.getInvocationName(),
//                () -> MobileElementOperationHandler.of(this, operationType).executeAction());
//        return this;
//    }

    @Override
    public MobileList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder) {
        MobileListScrollToVerticallyOperationType operationType = MobileListScrollToVerticallyOperationType.of(this, scrollDirection, filterBuilder);
        runCheck(operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType).executeAction());
        return this;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.put("mappedBlock", mobileListFrame.getMappedBlockFrame().getClass().getCanonicalName());
        return rootNode;
    }

}
