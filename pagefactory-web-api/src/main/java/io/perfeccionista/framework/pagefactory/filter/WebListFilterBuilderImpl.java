package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

import static io.perfeccionista.framework.Web.allItems;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.ADD;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.SUBTRACT;

public class WebListFilterBuilderImpl<T extends WebBlock<?>> implements WebListFilterBuilder<T> {

    private final Deque<WebListFilterResultGroupingHolder<T>> conditions = new ArrayDeque<>();

    private WebListFilterBuilderImpl() {
    }

    public static <T extends WebBlock<?>> WebListFilterBuilder<T> webBlockFilterBuilderWith(@NotNull WebItemCondition<T> condition) {
        return new WebListFilterBuilderImpl<T>()
                .add(condition);
    }

    public static <T extends WebBlock<?>> WebListFilterBuilder<T> webBlockFilterBuilderWithout(@NotNull WebItemCondition<T> condition) {
        return new WebListFilterBuilderImpl<T>()
                .add(allItems())
                .subtract(condition);
    }

    public WebListFilterBuilder<T> add(@NotNull WebItemCondition<T> condition) {
        this.conditions.addLast(WebListFilterResultGroupingHolder.of(ADD, condition));
        return this;
    }

    public WebListFilterBuilder<T> subtract(@NotNull WebItemCondition<T> condition) {
        this.conditions.addLast(WebListFilterResultGroupingHolder.of(SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull WebListFilter<T> build(@NotNull WebList<T> element) {
        return WebListFilterImpl.of(element, this);
    }

    public Deque<WebListFilterResultGroupingHolder<T>> getConditions() {
        return conditions;
    }

}
