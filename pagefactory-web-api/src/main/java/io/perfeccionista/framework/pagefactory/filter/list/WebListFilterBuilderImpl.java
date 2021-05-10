package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.ADD;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.SUBTRACT;

public class WebListFilterBuilderImpl implements WebListFilterBuilder {

    private final Deque<WebListBlockFilterResultGroupingHolder> conditions = new ArrayDeque<>();

    private WebListFilterBuilderImpl() {
    }

    public static WebListFilterBuilderImpl webListFilterBuilder() {
        return new WebListFilterBuilderImpl();
    }

    public WebListFilterBuilder add(@NotNull WebListBlockCondition condition) {
        this.conditions.addLast(WebListBlockFilterResultGroupingHolder.of(ADD, condition));
        return this;
    }

    public WebListFilterBuilder subtract(@NotNull WebListBlockCondition condition) {
        this.conditions.addLast(WebListBlockFilterResultGroupingHolder.of(SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull WebListFilter build(@NotNull WebList element) {
        return WebListFilterImpl.of(element, this);
    }

    public Deque<WebListBlockFilterResultGroupingHolder> getConditions() {
        return conditions;
    }


}
