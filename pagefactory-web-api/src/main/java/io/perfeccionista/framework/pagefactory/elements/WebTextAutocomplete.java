package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebDropDownAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextAutocompleteMatcher;
import io.perfeccionista.framework.matcher.element.WebTextDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebInputTextAvailable;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface WebTextAutocomplete extends WebTextDropDownList,
        WebInputTextAvailable, WebChildElement {

    // Select
    @Override
    WebTextAutocomplete select(@NotNull String text);
    @Override
    WebTextAutocomplete select(@NotNull StringValue text);
    @Override
    WebTextAutocomplete select(@NotNull WebTextListFilterBuilder filterBuilder);
    @Override
    WebTextAutocomplete select(@NotNull WebTextListBlockCondition filterCondition);

    // Checks
    @Override
    WebTextAutocomplete forEachBlock(@NotNull Consumer<WebLink> textListBlockConsumer);
    @Override
    WebTextAutocomplete forFirstBlock(@NotNull Consumer<WebLink> textListBlockConsumer);
    @Override
    WebTextAutocomplete forLastBlock(@NotNull Consumer<WebLink> textListBlockConsumer);

    // Actions
    @Override
    WebTextAutocomplete executeAction(@NotNull String name, Object... args);

    // Asserts
    WebTextAutocomplete should(@NotNull WebTextAutocompleteMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebTextDropDownListMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebTextListMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebGetLabelAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebGetTextAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebDropDownAvailableMatcher matcher);

    // Click
    @Override
    WebTextAutocomplete click();

    // InputText
    @Override
    WebTextAutocomplete clear();
    @Override
    WebTextAutocomplete typeText(@NotNull String keys);
    @Override
    WebTextAutocomplete replaceText(@NotNull String keys);
    @Override
    WebTextAutocomplete sendKeyEvents(@NotNull KeyEventsChain keyEvents);

    // DropDown
    @Override
    WebTextAutocomplete open();
    @Override
    WebTextAutocomplete close();

    // HoverTo
    @Override
    WebTextAutocomplete hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTextAutocomplete scrollTo();

}
