package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.impl.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.filter.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemCondition;
import io.perfeccionista.framework.value.string.StringValue;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface WebTextList extends WebList<WebTextBlock> {

    @API(status = Status.MAINTAINED)
    @NotNull WebListFrame<WebTextBlock> getItemFrame();

    // Select
    WebTextList select(@NotNull String text);
    WebTextList select(@NotNull StringValue text);
    WebTextList select(@NotNull WebListFilterBuilder<WebTextBlock> filterBuilder);
    WebTextList select(@NotNull WebItemCondition<WebTextBlock> filterCondition);

    // Asserts
    WebTextList should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    WebTextList should(@NotNull WebTextListMatcher matcher);
    WebTextList should(@NotNull WebIndexesMatcher matcher);
//    WebTextList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebTextListFilterBuilder filterBuilder);
//    WebTextList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebTextListFilterBuilder filterBuilder);

    // Size
    int size();

}
