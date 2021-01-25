package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.MobileFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.condition.MobileListBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface MobileListFilterBuilder extends MobileFilterBuilder<MobileList, MobileListFilter> {

    @Override
    @NotNull MobileListFilter build(@NotNull MobileList element);

    MobileListFilterBuilder add(@NotNull MobileListBlockCondition condition);

    MobileListFilterBuilder subtract(@NotNull MobileListBlockCondition condition);

    Deque<MobileListBlockFilterResultGroupingHolder> getConditions();

    class MobileListBlockFilterResultGroupingHolder {

        private final FilterResultGrouping usage;
        private final MobileListBlockCondition condition;

        private MobileListBlockFilterResultGroupingHolder(FilterResultGrouping usage, MobileListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static MobileListBlockFilterResultGroupingHolder of(FilterResultGrouping usage, MobileListBlockCondition condition) {
            return new MobileListBlockFilterResultGroupingHolder(usage, condition);
        }

        public FilterResultGrouping getUsage() {
            return usage;
        }

        public MobileListBlockCondition getCondition() {
            return condition;
        }

    }

}
