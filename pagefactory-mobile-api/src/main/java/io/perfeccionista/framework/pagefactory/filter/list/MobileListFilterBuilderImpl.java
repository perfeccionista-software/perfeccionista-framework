package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.filter.list.condition.MobileListBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.ADD;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.SUBTRACT;

public class MobileListFilterBuilderImpl implements MobileListFilterBuilder {

    private final Deque<MobileListBlockFilterResultGroupingHolder> conditions = new ArrayDeque<>();

    private MobileListFilterBuilderImpl() {
    }

    public static MobileListFilterBuilderImpl mobileListFilterBuilder() {
        return new MobileListFilterBuilderImpl();
    }

    public MobileListFilterBuilder add(@NotNull MobileListBlockCondition condition) {
        this.conditions.addLast(MobileListBlockFilterResultGroupingHolder.of(ADD, condition));
        return this;
    }

    public MobileListFilterBuilder subtract(@NotNull MobileListBlockCondition condition) {
        this.conditions.addLast(MobileListBlockFilterResultGroupingHolder.of(SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull MobileListFilter build(@NotNull MobileList element) {
        return MobileListFilterImpl.of(element, this);
    }

    public Deque<MobileListBlockFilterResultGroupingHolder> getConditions() {
        return conditions;
    }

}
