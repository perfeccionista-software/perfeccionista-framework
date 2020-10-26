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

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull WebList webListFrame,
                                                                                        @NotNull WebListFilterBuilder filterBuilder) {
        return WebListBlockContextLimiter.of(webListFrame, filterBuilder, intEquals(1));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull String webListPath,
                                                                                        @NotNull WebListFilterBuilder filterBuilder) {
        return WebListBlockContextLimiter.of(webListPath, filterBuilder, intEquals(1));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList webListFrame,
                                                                                         @NotNull WebListFilterBuilder filterBuilder,
                                                                                         int expectedSize) {
        return WebListBlockContextLimiter.of(webListFrame, filterBuilder, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList webListFrame,
                                                                                         @NotNull WebListFilterBuilder filterBuilder,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebListBlockContextLimiter.of(webListFrame, filterBuilder, expectedSize);
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull String webListPath,
                                                                                         @NotNull WebListFilterBuilder filterBuilder,
                                                                                         int expectedSize) {
        return WebListBlockContextLimiter.of(webListPath, filterBuilder, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull String webListPath,
                                                                                         @NotNull WebListFilterBuilder filterBuilder,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebListBlockContextLimiter.of(webListPath, filterBuilder, expectedSize);
    }

    // WebTable row limiters

    public static WebTableRowContextLimiter selectWebTableRow(@NotNull String webTablePath,
                                                              @NotNull WebTableFilterBuilder filterBuilder) {
        return WebTableRowContextLimiter.of(webTablePath, filterBuilder, intEquals(1));
    }

    public static WebTableRowContextLimiter selectWebTableRows(@NotNull String webTablePath,
                                                               @NotNull WebTableFilterBuilder filterBuilder,
                                                               int expectedSize) {
        return WebTableRowContextLimiter.of(webTablePath, filterBuilder, intEquals(expectedSize));
    }

    public static WebTableRowContextLimiter selectWebTableRows(@NotNull String webTablePath,
                                                               @NotNull WebTableFilterBuilder filterBuilder,
                                                               @NotNull NumberValue<Integer> expectedSize) {
        return WebTableRowContextLimiter.of(webTablePath, filterBuilder, expectedSize);
    }

    // WebTable cell limiters

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCell(@NotNull WebTable webTableFrame,
                                                                                        @NotNull String columnName,
                                                                                        @NotNull WebTableFilterBuilder filterBuilder) {
        return WebTableCellContextLimiter.of(webTableFrame, columnName, filterBuilder, intEquals(1));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCell(@NotNull String webTablePath,
                                                                                        @NotNull String columnName,
                                                                                        @NotNull WebTableFilterBuilder filterBuilder) {
        return WebTableCellContextLimiter.of(webTablePath, columnName, filterBuilder, intEquals(1));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull WebTable webTableFrame,
                                                                                         @NotNull String columnName,
                                                                                         @NotNull WebTableFilterBuilder filterBuilder,
                                                                                         int expectedSize) {
        return WebTableCellContextLimiter.of(webTableFrame, columnName, filterBuilder, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull WebTable webTableFrame,
                                                                                         @NotNull String columnName,
                                                                                         @NotNull WebTableFilterBuilder filterBuilder,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebTableCellContextLimiter.of(webTableFrame, columnName, filterBuilder, expectedSize);
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull String webTablePath,
                                                                                         @NotNull String columnName,
                                                                                         @NotNull WebTableFilterBuilder filterBuilder,
                                                                                         int expectedSize) {
        return WebTableCellContextLimiter.of(webTablePath, columnName, filterBuilder, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull String webTablePath,
                                                                                         @NotNull String columnName,
                                                                                         @NotNull WebTableFilterBuilder filterBuilder,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebTableCellContextLimiter.of(webTablePath, columnName, filterBuilder, expectedSize);
    }

}
