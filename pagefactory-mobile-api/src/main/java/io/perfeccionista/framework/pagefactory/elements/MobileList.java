package io.perfeccionista.framework.pagefactory.elements;

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
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileListFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.list.MobileListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.condition.MobileListBlockCondition;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface MobileList extends MobileChildElement, MobileElementContainer<MobileListFilter, MobileListFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull MobileListFrame<MobileBlock> getMobileListFrame();

    // Extractor
    @NotNull <V> MobileMultipleIndexedResult<V, MobileList> extractAll(@NotNull MobileListBlockValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull MobileListFilter filter(@NotNull MobileListFilterBuilder filterBuilder);
    @NotNull MobileListFilter filter(@NotNull MobileListBlockCondition filterCondition);

    // Actions
    @Override
    MobileList executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileList should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);
    MobileList should(@NotNull MobileListMatcher matcher);
    MobileList should(@NotNull MobileIndexesMatcher matcher);
    @Override
    MobileList should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileList should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileList should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileList should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileList should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileList should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileList should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileList should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileList should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileList should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileList should(@NotNull MobileElementStateAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileList scrollTo();
//    MobileList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder);
    MobileList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder);

}
