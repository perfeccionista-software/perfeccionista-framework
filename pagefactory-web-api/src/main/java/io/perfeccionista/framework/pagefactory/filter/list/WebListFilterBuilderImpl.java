package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

import static io.perfeccionista.framework.Web.allBlocks;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.ADD;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.SUBTRACT;

public class WebListFilterBuilderImpl<T extends WebBlock> implements WebListFilterBuilder<T> {

    private final Deque<WebListBlockFilterResultGroupingHolder<T>> conditions = new ArrayDeque<>();

    private WebListFilterBuilderImpl() {
    }

    public static <T extends WebBlock> WebListFilterBuilder<T> webListFilterBuilderWith(@NotNull WebListBlockCondition<T> condition) {
        return new WebListFilterBuilderImpl<T>()
                .add(condition);
    }

    public static <T extends WebBlock> WebListFilterBuilder<T> webListFilterBuilderWithout(@NotNull WebListBlockCondition<T> condition) {
        return new WebListFilterBuilderImpl<T>()
                .add(allBlocks())
                .subtract(condition);
    }

    public WebListFilterBuilder<T> add(@NotNull WebListBlockCondition<T> condition) {
        this.conditions.addLast(WebListBlockFilterResultGroupingHolder.of(ADD, condition));
        return this;
    }

    public WebListFilterBuilder<T> subtract(@NotNull WebListBlockCondition<T> condition) {
        this.conditions.addLast(WebListBlockFilterResultGroupingHolder.of(SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull WebListFilter<T> build(@NotNull WebList<T> element) {
        return WebListFilterImpl.of(element, this);
    }

    public Deque<WebListBlockFilterResultGroupingHolder<T>> getConditions() {
        return conditions;
    }


}
