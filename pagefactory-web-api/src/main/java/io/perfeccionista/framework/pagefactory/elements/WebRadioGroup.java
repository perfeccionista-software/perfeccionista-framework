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
import io.perfeccionista.framework.matcher.element.WebRadioGroupMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebRadioGroupFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilter;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface WebRadioGroup extends WebChildElement, WebElementContainer<WebRadioGroupFilter, WebRadioGroupFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull WebRadioGroupFrame<DefaultWebRadioButtonBlock> getBlockFrame();

    // Extractor
    @NotNull <V> WebMultipleIndexedResult<V, WebRadioGroup> extractAll(@NotNull WebRadioButtonValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull WebRadioGroupFilter filterBuilder(@NotNull WebRadioGroupFilterBuilder filterBuilder);
    @NotNull WebRadioGroupFilter filter(@NotNull WebRadioButtonCondition filterCondition);

    // Button Extractors
    @NotNull WebRadioButton getSelected();
    @NotNull WebRadioButton getByIndex(int expectedIndex);
    @NotNull WebRadioButton getByLabel(@NotNull String expectedText);

    // Actions
    @Override
    WebRadioGroup executeAction(@NotNull String name, Object... args);

    // Asserts
    WebRadioGroup should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    WebRadioGroup should(@NotNull WebRadioGroupMatcher matcher);
    WebRadioGroup should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebElementStateAvailableMatcher matcher);

    // HoverTo
    @Override
    WebRadioGroup hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebRadioGroup scrollTo();

    // Size
    int size();

}
