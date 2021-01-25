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
import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface WebTextList extends WebChildElement, WebElementContainer<WebTextListFilter, WebTextListFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull WebListFrame<DefaultWebTextBlock> getWebTextListFrame();

    // Extractor
    @NotNull WebMultipleIndexedResult<String, WebTextList> extractAll();
    @NotNull <V> WebMultipleIndexedResult<V, WebTextList> extractAll(@NotNull WebTextListBlockValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull WebTextListFilter filter(@NotNull WebTextListFilterBuilder filterBuilder);
    @NotNull WebTextListFilter filter(@NotNull WebTextListBlockCondition filterCondition);

    // Actions
    @Override
    WebTextList executeAction(@NotNull String name, Object... args);

    // Asserts
    WebTextList should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    WebTextList should(@NotNull WebTextListMatcher matcher);
    WebTextList should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebTextList should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTextList should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull WebElementStateAvailableMatcher matcher);

    // HoverTo
    @Override
    WebTextList hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTextList scrollTo();
//    WebTextList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebTextListFilterBuilder filterBuilder);
//    WebTextList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebTextListFilterBuilder filterBuilder);

}
