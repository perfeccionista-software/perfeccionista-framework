package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebTextListFilterBuilder extends WebFilterBuilder<WebTextList, WebTextListFilter> {

    @Override
    @NotNull WebTextListFilter build(@NotNull WebTextList element);

    WebTextListFilterBuilder add(@NotNull WebTextListBlockCondition condition);

    WebTextListFilterBuilder subtract(@NotNull WebTextListBlockCondition condition);

    Deque<WebTextBlockConditionHolder> getConditions();

}
