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
import io.perfeccionista.framework.matcher.element.WebTextDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebDropDownAvailable;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockCondition;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface WebTextDropDownList extends WebTextList,
        WebClickAvailable, WebGetTextAvailable, WebGetLabelAvailable, WebDropDownAvailable, WebChildElement {

    // Select
    @Override
    WebTextDropDownList select(@NotNull String text);
    @Override
    WebTextDropDownList select(@NotNull StringValue text);
    @Override
    WebTextDropDownList select(@NotNull WebTextBlockFilterBuilder filterBuilder);
    @Override
    WebTextDropDownList select(@NotNull WebTextBlockCondition filterCondition);

    // Checks
    @Override
    WebTextDropDownList forEach(@NotNull Consumer<WebLink> textBlockConsumer);
    @Override
    WebTextDropDownList forFirst(@NotNull Consumer<WebLink> textBlockConsumer);
    @Override
    WebTextDropDownList forLast(@NotNull Consumer<WebLink> textBlockConsumer);

    // Actions
    @Override
    WebTextDropDownList executeAction(@NotNull String name, Object... args);

    // Asserts
    WebTextDropDownList should(@NotNull WebTextDropDownListMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    @Override
    WebTextDropDownList should(@NotNull WebTextListMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebGetLabelAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebGetTextAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebDropDownAvailableMatcher matcher);

    // Click
    @Override
    WebTextDropDownList click();

    // DropDown
    @Override
    WebTextDropDownList open();
    @Override
    WebTextDropDownList close();

    // HoverTo
    @Override
    WebTextDropDownList hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTextDropDownList scrollTo();

}
