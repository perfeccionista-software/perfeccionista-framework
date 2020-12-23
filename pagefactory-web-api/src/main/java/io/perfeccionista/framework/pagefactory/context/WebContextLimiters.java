package io.perfeccionista.framework.pagefactory.context;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.value.Values.intEquals;

public class WebContextLimiters {

    private WebContextLimiters() {
    }

    // WebList block limiters

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull WebList fromList,
                                                                                        @NotNull WebListFilterBuilder blockFilter) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(1));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull String fromList,
                                                                                        @NotNull WebListFilterBuilder blockFilter) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(1));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList fromList,
                                                                                         @NotNull WebListFilterBuilder blockFilter,
                                                                                         int expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList fromList,
                                                                                         @NotNull WebListFilterBuilder blockFilter,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, expectedSize);
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull String fromList,
                                                                                         @NotNull WebListFilterBuilder blockFilter,
                                                                                         int expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull String fromList,
                                                                                         @NotNull WebListFilterBuilder blockFilter,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, expectedSize);
    }

    // WebTable row limiters

    public static WebTableRowContextLimiter selectWebTableRow(@NotNull String fromTable,
                                                              @NotNull WebTableFilterBuilder rowFilter) {
        return WebTableRowContextLimiter.of(fromTable, rowFilter, intEquals(1));
    }

    public static WebTableRowContextLimiter selectWebTableRows(@NotNull String fromTable,
                                                               @NotNull WebTableFilterBuilder rowFilter,
                                                               int expectedSize) {
        return WebTableRowContextLimiter.of(fromTable, rowFilter, intEquals(expectedSize));
    }

    public static WebTableRowContextLimiter selectWebTableRows(@NotNull String fromTable,
                                                               @NotNull WebTableFilterBuilder rowFilter,
                                                               @NotNull NumberValue<Integer> expectedSize) {
        return WebTableRowContextLimiter.of(fromTable, rowFilter, expectedSize);
    }

    // WebTable cell limiters

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCell(@NotNull WebTable fromTable,
                                                                                        @NotNull String fromColumn,
                                                                                        @NotNull WebTableFilterBuilder rowFilter) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(1));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCell(@NotNull String fromTable,
                                                                                        @NotNull String fromColumn,
                                                                                        @NotNull WebTableFilterBuilder rowFilter) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(1));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull WebTable fromTable,
                                                                                         @NotNull String fromColumn,
                                                                                         @NotNull WebTableFilterBuilder rowFilter,
                                                                                         int expectedSize) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull WebTable fromTable,
                                                                                         @NotNull String fromColumn,
                                                                                         @NotNull WebTableFilterBuilder rowFilter,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, expectedSize);
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull String fromTable,
                                                                                         @NotNull String fromColumn,
                                                                                         @NotNull WebTableFilterBuilder rowFilter,
                                                                                         int expectedSize) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull String fromTable,
                                                                                         @NotNull String fromColumn,
                                                                                         @NotNull WebTableFilterBuilder rowFilter,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, expectedSize);
    }

}
