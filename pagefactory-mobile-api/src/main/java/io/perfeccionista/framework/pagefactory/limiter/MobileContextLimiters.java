package io.perfeccionista.framework.pagefactory.limiter;

import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilterBuilder;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.value.Values.intEquals;

public class MobileContextLimiters {

    private MobileContextLimiters() {
    }

    // MobileList block limiters

    public static <T extends MobileBlock> MobileListBlockContextLimiter<T> selectMobileListBlock(@NotNull MobileList fromList,
                                                                                                 @NotNull MobileListFilterBuilder blockFilter) {
        return MobileListBlockContextLimiter.of(fromList, blockFilter, intEquals(1));
    }

    public static <T extends MobileBlock> MobileListBlockContextLimiter<T> selectMobileListBlock(@NotNull String fromList,
                                                                                                 @NotNull MobileListFilterBuilder blockFilter) {
        return MobileListBlockContextLimiter.of(fromList, blockFilter, intEquals(1));
    }

    public static <T extends MobileBlock> MobileListBlockContextLimiter<T> selectMobileListBlocks(@NotNull MobileList fromList,
                                                                                                  @NotNull MobileListFilterBuilder blockFilter,
                                                                                                  int expectedSize) {
        return MobileListBlockContextLimiter.of(fromList, blockFilter, intEquals(expectedSize));
    }

    public static <T extends MobileBlock> MobileListBlockContextLimiter<T> selectMobileListBlocks(@NotNull MobileList fromList,
                                                                                                  @NotNull MobileListFilterBuilder blockFilter,
                                                                                                  @NotNull NumberValue<Integer> expectedSize) {
        return MobileListBlockContextLimiter.of(fromList, blockFilter, expectedSize);
    }

    public static <T extends MobileBlock> MobileListBlockContextLimiter<T> selectMobileListBlocks(@NotNull String fromList,
                                                                                                  @NotNull MobileListFilterBuilder blockFilter,
                                                                                                  int expectedSize) {
        return MobileListBlockContextLimiter.of(fromList, blockFilter, intEquals(expectedSize));
    }

    public static <T extends MobileBlock> MobileListBlockContextLimiter<T> selectMobileListBlocks(@NotNull String fromList,
                                                                                                  @NotNull MobileListFilterBuilder blockFilter,
                                                                                                  @NotNull NumberValue<Integer> expectedSize) {
        return MobileListBlockContextLimiter.of(fromList, blockFilter, expectedSize);
    }

    // MobileTable row limiters

    public static MobileTableRowContextLimiter selectMobileTableRow(@NotNull String fromTable,
                                                                    @NotNull MobileTableFilterBuilder rowFilter) {
        return MobileTableRowContextLimiter.of(fromTable, rowFilter, intEquals(1));
    }

    public static MobileTableRowContextLimiter selectMobileTableRows(@NotNull String fromTable,
                                                                     @NotNull MobileTableFilterBuilder rowFilter,
                                                                     int expectedSize) {
        return MobileTableRowContextLimiter.of(fromTable, rowFilter, intEquals(expectedSize));
    }

    public static MobileTableRowContextLimiter selectMobileTableRows(@NotNull String fromTable,
                                                                     @NotNull MobileTableFilterBuilder rowFilter,
                                                                     @NotNull NumberValue<Integer> expectedSize) {
        return MobileTableRowContextLimiter.of(fromTable, rowFilter, expectedSize);
    }

    // MobileTable cell limiters

    public static <T extends MobileBlock> MobileTableCellContextLimiter<T> selectMobileTableCell(@NotNull MobileTable fromTable,
                                                                                                 @NotNull String fromColumn,
                                                                                                 @NotNull MobileTableFilterBuilder rowFilter) {
        return MobileTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(1));
    }

    public static <T extends MobileBlock> MobileTableCellContextLimiter<T> selectMobileTableCell(@NotNull String fromTable,
                                                                                                 @NotNull String fromColumn,
                                                                                                 @NotNull MobileTableFilterBuilder rowFilter) {
        return MobileTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(1));
    }

    public static <T extends MobileBlock> MobileTableCellContextLimiter<T> selectMobileTableCells(@NotNull MobileTable fromTable,
                                                                                                  @NotNull String fromColumn,
                                                                                                  @NotNull MobileTableFilterBuilder rowFilter,
                                                                                                  int expectedSize) {
        return MobileTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(expectedSize));
    }

    public static <T extends MobileBlock> MobileTableCellContextLimiter<T> selectMobileTableCells(@NotNull MobileTable fromTable,
                                                                                                  @NotNull String fromColumn,
                                                                                                  @NotNull MobileTableFilterBuilder rowFilter,
                                                                                                  @NotNull NumberValue<Integer> expectedSize) {
        return MobileTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, expectedSize);
    }

    public static <T extends MobileBlock> MobileTableCellContextLimiter<T> selectMobileTableCells(@NotNull String fromTable,
                                                                                                  @NotNull String fromColumn,
                                                                                                  @NotNull MobileTableFilterBuilder rowFilter,
                                                                                                  int expectedSize) {
        return MobileTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(expectedSize));
    }

    public static <T extends MobileBlock> MobileTableCellContextLimiter<T> selectMobileTableCells(@NotNull String fromTable,
                                                                                                  @NotNull String fromColumn,
                                                                                                  @NotNull MobileTableFilterBuilder rowFilter,
                                                                                                  @NotNull NumberValue<Integer> expectedSize) {
        return MobileTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, expectedSize);
    }

}
