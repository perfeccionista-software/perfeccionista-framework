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
import io.perfeccionista.framework.pagefactory.elements.mapping.WebBlockFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilter;
import io.perfeccionista.framework.value.string.StringValue;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface WebTextList extends WebChildElement, WebElementContainer<WebTextBlockFilter, WebTextBlockFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull WebBlockFrame<DefaultWebTextBlock> getBlockFrame();

    // Select
    WebTextList select(@NotNull String text);
    WebTextList select(@NotNull StringValue text);
    WebTextList select(@NotNull WebTextBlockFilterBuilder filterBuilder);
    WebTextList select(@NotNull WebTextBlockCondition filterCondition);

    // Extractor
    @NotNull WebMultipleIndexedResult<String, WebTextList> extractAll();
    @NotNull <T> WebMultipleIndexedResult<T, WebTextList> extractAll(@NotNull WebTextListBlockValueExtractor<T> extractor);

    // Filter
    @Override
    @NotNull WebTextBlockFilter filterBuilder(@NotNull WebTextBlockFilterBuilder filterBuilder);
    @NotNull WebTextBlockFilter filter(@NotNull WebTextBlockCondition filterCondition);

    // Checks
    WebTextList forEach(@NotNull Consumer<WebLink> textBlockConsumer);
    WebTextList forFirst(@NotNull Consumer<WebLink> textBlockConsumer);
    WebTextList forLast(@NotNull Consumer<WebLink> textBlockConsumer);

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

    // Size
    int size();

}
