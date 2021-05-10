package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.MobileTextListBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class MobileTextListFilterBuilderImpl implements MobileTextListFilterBuilder {

    private final Deque<MobileTextListBlockFilterResultGroupingHolder> conditions = new ArrayDeque<>();

    private MobileTextListFilterBuilderImpl() {
    }

    public static MobileTextListFilterBuilderImpl mobileTextListFilterBuilder() {
        return new MobileTextListFilterBuilderImpl();
    }

    public MobileTextListFilterBuilder add(@NotNull MobileTextListBlockCondition condition) {
        this.conditions.addLast(MobileTextListBlockFilterResultGroupingHolder.of(FilterResultGrouping.ADD, condition));
        return this;
    }

    public MobileTextListFilterBuilder subtract(@NotNull MobileTextListBlockCondition condition) {
        this.conditions.addLast(MobileTextListBlockFilterResultGroupingHolder.of(FilterResultGrouping.SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull MobileTextListFilter build(@NotNull MobileTextList element) {
        return MobileTextListFilterImpl.of(element, this);
    }

    public Deque<MobileTextListBlockFilterResultGroupingHolder> getConditions() {
        return conditions;
    }

}
