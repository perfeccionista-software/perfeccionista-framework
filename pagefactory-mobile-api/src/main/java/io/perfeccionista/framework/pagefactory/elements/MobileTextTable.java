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
import io.perfeccionista.framework.matcher.element.MobileTextTableMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileTableFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.texttable.MobileTextTableValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.texttable.MobileTextTableFilter;
import io.perfeccionista.framework.pagefactory.filter.texttable.MobileTextTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.MobileTextTableRowCondition;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface MobileTextTable extends MobileChildElement, MobileElementContainer<MobileTextTableFilter, MobileTextTableFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull MobileTableFrame<DefaultMobileTextBlock> getMobileTextTableFrame();

    // Extractor
    @NotNull <V> MobileSingleIndexedResult<V, MobileTextTable> extractHeader(@NotNull MobileTextTableValueExtractor<V> extractor);
    @NotNull <V> MobileMultipleIndexedResult<V, MobileTextTable> extractRows(@NotNull MobileTextTableValueExtractor<V> extractor);
    @NotNull <V> MobileSingleIndexedResult<V, MobileTextTable> extractFooter(@NotNull MobileTextTableValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull MobileTextTableFilter filter(@NotNull MobileTextTableFilterBuilder filterBuilder);
    @NotNull MobileTextTableFilter filter(@NotNull MobileTextTableRowCondition filterCondition);

    // Actions
    @Override
    MobileTextTable executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileTextTable should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);
    MobileTextTable should(@NotNull MobileTextTableMatcher matcher);
    MobileTextTable should(@NotNull MobileIndexesMatcher matcher);
    @Override
    MobileTextTable should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileTextTable should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileTextTable should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileTextTable should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileTextTable should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileTextTable should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileTextTable should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileTextTable should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileTextTable should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileTextTable should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileTextTable should(@NotNull MobileElementStateAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileTextTable scrollTo();
//    MobileTextTable scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileTextTableFilterBuilder filterBuilder);
//    MobileTextTable scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileTextTableFilterBuilder filterBuilder);

}

