package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.condition.MobileListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.condition.MobileTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.MobileTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.MobileTextListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.MobileTextTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.MobileTextTableRowCondition;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.filter.MobileFilterConditions.allBlocks;
import static io.perfeccionista.framework.pagefactory.filter.MobileFilterConditions.allRows;
import static io.perfeccionista.framework.pagefactory.filter.MobileFilterConditions.allTextBlocks;
import static io.perfeccionista.framework.pagefactory.filter.MobileFilterConditions.allTextRows;
import static io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilderImpl.mobileListFilterBuilder;
import static io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilterBuilderImpl.mobileTableFilterBuilder;
import static io.perfeccionista.framework.pagefactory.filter.textlist.MobileTextListFilterBuilderImpl.mobileTextListFilterBuilder;
import static io.perfeccionista.framework.pagefactory.filter.texttable.MobileTextTableFilterBuilderImpl.mobileTextTableFilterBuilder;

public class MobileFilters {

    protected MobileFilters() {
    }

    // List

    public static MobileListFilterBuilder emptyMobileListFilter() {
        return mobileListFilterBuilder()
                .add(allBlocks());
    }

    public static MobileListFilterBuilder with(@NotNull MobileListBlockCondition condition) {
        return mobileListFilterBuilder()
                .add(condition);
    }

//    public static MobileListFilterBuilder without(@NotNull MobileListBlockCondition condition) {
//        return mobileListFilterBuilder()
//                .add(allBlocks())
//                .subtract(condition);
//    }

    // TextList

    public static MobileTextListFilterBuilder emptyMobileTextListFilter() {
        return mobileTextListFilterBuilder()
                .add(allTextBlocks());
    }

    public static MobileTextListFilterBuilder with(@NotNull MobileTextListBlockCondition stringCondition) {
        return mobileTextListFilterBuilder()
                .add(stringCondition);
    }

//    public static MobileTextListFilterBuilder without(@NotNull MobileTextListBlockCondition stringCondition) {
//        return mobileTextListFilterBuilder()
//                .add(allTextBlocks())
//                .subtract(stringCondition);
//    }

    // Table

    public static MobileTableFilterBuilder emptyMobileTableFilter() {
        return mobileTableFilterBuilder()
                .add(allRows());
    }

    public static MobileTableFilterBuilder with(@NotNull MobileTableRowCondition condition) {
        return mobileTableFilterBuilder()
                .add(condition);
    }
//
//    public static MobileTableFilterBuilder without(@NotNull MobileTableRowCondition condition) {
//        return mobileTableFilterBuilder()
//                .add(allRows())
//                .subtract(condition);
//    }

    // TextTable

    public static MobileTextTableFilterBuilder emptyMobileTextTableFilter() {
        return mobileTextTableFilterBuilder()
                .add(allTextRows());
    }

    public static MobileTextTableFilterBuilder with(@NotNull MobileTextTableRowCondition stringCondition) {
        return mobileTextTableFilterBuilder()
                .add(stringCondition);
    }

//    public static MobileTextTableFilterBuilder without(@NotNull MobileTextTableRowCondition stringCondition) {
//        return mobileTextTableFilterBuilder()
//                .add(allTextRows())
//                .subtract(stringCondition);
//    }

}
