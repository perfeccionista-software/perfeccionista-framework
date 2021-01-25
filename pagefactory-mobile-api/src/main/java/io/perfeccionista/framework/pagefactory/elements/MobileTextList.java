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
import io.perfeccionista.framework.matcher.element.MobileTextListMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileListFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.textlist.MobileTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.textlist.MobileTextListFilter;
import io.perfeccionista.framework.pagefactory.filter.textlist.MobileTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.MobileTextListBlockCondition;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface MobileTextList extends MobileChildElement, MobileElementContainer<MobileTextListFilter, MobileTextListFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull MobileListFrame<DefaultMobileTextBlock> getMobileTextListFrame();

    // Extractor
    @NotNull MobileMultipleIndexedResult<String, MobileTextList> extractAll();
    @NotNull <V> MobileMultipleIndexedResult<V, MobileTextList> extractAll(@NotNull MobileTextListBlockValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull MobileTextListFilter filter(@NotNull MobileTextListFilterBuilder filterBuilder);
    @NotNull MobileTextListFilter filter(@NotNull MobileTextListBlockCondition filterCondition);

    // Actions
    @Override
    MobileTextList executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileTextList should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);
    MobileTextList should(@NotNull MobileTextListMatcher matcher);
    MobileTextList should(@NotNull MobileIndexesMatcher matcher);
    @Override
    MobileTextList should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileTextList should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileTextList should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileTextList should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileTextList should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileTextList should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileTextList should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileTextList should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileTextList should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileTextList should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileTextList should(@NotNull MobileElementStateAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileTextList scrollTo();
//    MobileTextList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileTextListFilterBuilder filterBuilder);
//    MobileTextList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileTextListFilterBuilder filterBuilder);

}
