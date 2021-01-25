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
import io.perfeccionista.framework.matcher.element.MobileTableMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileTableFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.table.MobileTableValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilter;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.condition.MobileTableRowCondition;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface MobileTable extends MobileChildElement, MobileElementContainer<MobileTableFilter, MobileTableFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull MobileTableFrame<MobileBlock> getMobileTableFrame();

    // Extractor
    @NotNull <V> MobileSingleIndexedResult<V, MobileTable> extractHeader(@NotNull MobileTableValueExtractor<V> extractor);
    @NotNull <V> MobileMultipleIndexedResult<V, MobileTable> extractRows(@NotNull MobileTableValueExtractor<V> extractor);
    @NotNull <V> MobileSingleIndexedResult<V, MobileTable> extractFooter(@NotNull MobileTableValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull MobileTableFilter filter(@NotNull MobileTableFilterBuilder filterBuilder);
    @NotNull MobileTableFilter filter(@NotNull MobileTableRowCondition filterCondition);

    // Actions
    @Override
    MobileTable executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileTable should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);
    MobileTable should(@NotNull MobileTableMatcher matcher);
    MobileTable should(@NotNull MobileIndexesMatcher matcher);
    @Override
    MobileTable should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileTable should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileTable should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileTable should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileTable should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileTable should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileTable should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileTable should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileTable should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileTable should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileTable should(@NotNull MobileElementStateAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileTable scrollTo();
//    MobileTable scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileTableFilterBuilder filterBuilder);
//    MobileTable scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileTableFilterBuilder filterBuilder);

}
