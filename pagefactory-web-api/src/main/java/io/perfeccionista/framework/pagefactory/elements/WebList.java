package io.perfeccionista.framework.pagefactory.elements;

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
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface WebList extends WebChildElement, WebElementContainer<WebListFilter, WebListFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull WebListFrame<WebBlock> getWebListFrame();

    // Extractor
    @NotNull <V> WebMultipleIndexedResult<V, WebList> extractAll(@NotNull WebListBlockValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull WebListFilter filter(@NotNull WebListFilterBuilder filterBuilder);
    @NotNull WebListFilter filter(@NotNull WebListBlockCondition filterCondition);

    // Actions
    @Override
    WebList executeAction(@NotNull String name, Object... args);

    // Asserts
    WebList should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    WebList should(@NotNull WebListMatcher matcher);
    WebList should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebList should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebList should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebList should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebList should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebList should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebList should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebList should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebList should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebList should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebList should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebList should(@NotNull WebElementStateAvailableMatcher matcher);

    // HoverTo
    @Override
    WebList hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebList scrollTo();
//    WebList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebListFilterBuilder filterBuilder);
//    WebList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebListFilterBuilder filterBuilder);

}
