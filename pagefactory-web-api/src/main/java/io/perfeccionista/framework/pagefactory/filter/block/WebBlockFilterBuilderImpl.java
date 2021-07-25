package io.perfeccionista.framework.pagefactory.filter.block;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

import static io.perfeccionista.framework.Web.allItems;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.ADD;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.SUBTRACT;

public class WebBlockFilterBuilderImpl<T extends WebBlock> implements WebBlockFilterBuilder<T> {

    private final Deque<WebListBlockFilterResultGroupingHolder<T>> conditions = new ArrayDeque<>();

    private WebBlockFilterBuilderImpl() {
    }

    public static <T extends WebBlock> WebBlockFilterBuilder<T> webBlockFilterBuilderWith(@NotNull WebBlockCondition<T> condition) {
        return new WebBlockFilterBuilderImpl<T>()
                .add(condition);
    }

    public static <T extends WebBlock> WebBlockFilterBuilder<T> webBlockFilterBuilderWithout(@NotNull WebBlockCondition<T> condition) {
        return new WebBlockFilterBuilderImpl<T>()
                .add(allItems())
                .subtract(condition);
    }

    public WebBlockFilterBuilder<T> add(@NotNull WebBlockCondition<T> condition) {
        this.conditions.addLast(WebListBlockFilterResultGroupingHolder.of(ADD, condition));
        return this;
    }

    public WebBlockFilterBuilder<T> subtract(@NotNull WebBlockCondition<T> condition) {
        this.conditions.addLast(WebListBlockFilterResultGroupingHolder.of(SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull WebBlockFilter<T> build(@NotNull WebList<T> element) {
        return WebBlockFilterImpl.of(element, this);
    }

    public Deque<WebListBlockFilterResultGroupingHolder<T>> getConditions() {
        return conditions;
    }


}
