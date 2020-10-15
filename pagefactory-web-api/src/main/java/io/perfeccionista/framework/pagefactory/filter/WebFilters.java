package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.allBlocks;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.allRadioButtons;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.allRows;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.allTextBlocks;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.allTextRows;
import static io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilderImpl.webListFilterBuilder;
import static io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilderImpl.webRadioGroupFilterBuilder;
import static io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilderImpl.webTableFilterBuilder;
import static io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilderImpl.webTextListFilterBuilder;
import static io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilderImpl.webTextTableFilterBuilder;

// TODO: Это тоже можно вынести в конфигурируемый интерфейс.
//  Тогда при замене модуля элементов нужно будет в тестах только подменить конфигурацию фильтров
public class WebFilters {

    protected WebFilters() {
    }

    // RadioButton

    public static WebRadioGroupFilterBuilder emptyWebRadioButtonFilter() {
        return webRadioGroupFilterBuilder()
                .add(allRadioButtons());
    }

    public static WebRadioGroupFilterBuilder with(@NotNull WebRadioButtonCondition condition) {
        return webRadioGroupFilterBuilder()
                .add(condition);
    }

    public static WebRadioGroupFilterBuilder without(@NotNull WebRadioButtonCondition condition) {
        return webRadioGroupFilterBuilder()
                .add(allRadioButtons())
                .subtract(condition);
    }

    // List

    public static WebListFilterBuilder emptyWebListFilter() {
        return webListFilterBuilder()
                .add(allBlocks());
    }

    public static WebListFilterBuilder with(@NotNull WebListBlockCondition condition) {
        return webListFilterBuilder()
                .add(condition);
    }

    public static WebListFilterBuilder without(@NotNull WebListBlockCondition condition) {
        return webListFilterBuilder()
                .add(allBlocks())
                .subtract(condition);
    }

    // TextList

    public static WebTextListFilterBuilder emptyWebTextListFilter() {
        return webTextListFilterBuilder()
                .add(allTextBlocks());
    }

    public static WebTextListFilterBuilder with(@NotNull WebTextListBlockCondition stringCondition) {
        return webTextListFilterBuilder()
                .add(stringCondition);
    }

    public static WebTextListFilterBuilder without(@NotNull WebTextListBlockCondition stringCondition) {
        return webTextListFilterBuilder()
                .add(allTextBlocks())
                .subtract(stringCondition);
    }

    // Table

    public static WebTableFilterBuilder emptyWebTableFilter() {
        return webTableFilterBuilder()
                .add(allRows());
    }

    public static WebTableFilterBuilder with(@NotNull WebTableRowCondition condition) {
        return webTableFilterBuilder()
                .add(condition);
    }

    public static WebTableFilterBuilder without(@NotNull WebTableRowCondition condition) {
        return webTableFilterBuilder()
                .add(allRows())
                .subtract(condition);
    }

    // TextTable

    public static WebTextTableFilterBuilder emptyWebTextTableFilter() {
        return webTextTableFilterBuilder()
                .add(allTextRows());
    }

    public static WebTextTableFilterBuilder with(@NotNull WebTextTableRowCondition stringCondition) {
        return webTextTableFilterBuilder()
                .add(stringCondition);
    }

    public static WebTextTableFilterBuilder without(@NotNull WebTextTableRowCondition stringCondition) {
        return webTextTableFilterBuilder()
                .add(allTextRows())
                .subtract(stringCondition);
    }

}
