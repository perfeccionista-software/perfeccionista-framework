package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.MobileFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.MobileTextListBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface MobileTextListFilterBuilder extends MobileFilterBuilder<MobileTextList, MobileTextListFilter> {

    @Override
    @NotNull MobileTextListFilter build(@NotNull MobileTextList element);

    MobileTextListFilterBuilder add(@NotNull MobileTextListBlockCondition condition);

    MobileTextListFilterBuilder subtract(@NotNull MobileTextListBlockCondition condition);

    Deque<MobileTextListBlockFilterResultGroupingHolder> getConditions();

    class MobileTextListBlockFilterResultGroupingHolder {

        private final FilterResultGrouping usage;
        private final MobileTextListBlockCondition condition;

        private MobileTextListBlockFilterResultGroupingHolder(FilterResultGrouping usage, MobileTextListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static MobileTextListBlockFilterResultGroupingHolder of(FilterResultGrouping usage, MobileTextListBlockCondition condition) {
            return new MobileTextListBlockFilterResultGroupingHolder(usage, condition);
        }

        public FilterResultGrouping getUsage() {
            return usage;
        }

        public MobileTextListBlockCondition getCondition() {
            return condition;
        }

    }

}
