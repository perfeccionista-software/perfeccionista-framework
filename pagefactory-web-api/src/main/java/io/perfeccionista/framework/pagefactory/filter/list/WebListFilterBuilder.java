package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebListFilterBuilder extends WebFilterBuilder<WebList, WebListFilter> {

    @Override
    @NotNull WebListFilter build(@NotNull WebList element);

    WebListFilterBuilder add(@NotNull WebListBlockCondition condition);

    WebListFilterBuilder subtract(@NotNull WebListBlockCondition condition);

    Deque<WebListBlockConditionHolder> getConditions();

}
