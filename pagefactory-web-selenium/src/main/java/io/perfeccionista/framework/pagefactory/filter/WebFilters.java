package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilderSeleniumImpl;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilterBuilderSeleniumImpl;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilderSeleniumImpl;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableRowElementEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilderSeleniumImpl;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilderSeleniumImpl;

// TODO: Это тоже можно вынести в конфигурируемый интерфейс.
//  Тогда при замене модуля элементов нужно будет в тестах только подменить конфигурацию фильтров
public class WebFilters {

    protected WebFilters() {
    }

    // RadioButton

    public static WebRadioButtonFilterBuilder emptyRadioButtonFilter() {
        return new WebRadioButtonFilterBuilderSeleniumImpl();
    }

    public static WebRadioButtonFilterBuilder with(WebRadioButtonCondition condition) {
        return new WebRadioButtonFilterBuilderSeleniumImpl().add(condition);
    }

    public static WebRadioButtonFilterBuilder without(WebRadioButtonCondition condition) {
        return new WebRadioButtonFilterBuilderSeleniumImpl().subtract(condition);
    }

    // List

    public static WebListFilterBuilder emptyListFilter() {
        return new WebListFilterBuilderSeleniumImpl();
    }

    public static WebListFilterBuilder with(WebListBlockCondition condition) {
        return new WebListFilterBuilderSeleniumImpl().add(condition);
    }

    public static WebListFilterBuilder without(WebListBlockCondition condition) {
        return new WebListFilterBuilderSeleniumImpl().subtract(condition);
    }

    // StringList

    public static WebTextListFilterBuilder emptyTextListFilter() {
        return new WebTextListFilterBuilderSeleniumImpl();
    }

    public static WebTextListFilterBuilder with(WebTextListBlockCondition stringCondition) {
        return new WebTextListFilterBuilderSeleniumImpl().add(stringCondition);
    }

    public static WebTextListFilterBuilder without(WebTextListBlockCondition stringCondition) {
        return new WebTextListFilterBuilderSeleniumImpl().subtract(stringCondition);
    }

    // Table

    public static WebTableFilterBuilder emptyTableFilter() {
        return new WebTableFilterBuilderSeleniumImpl()
                .add(new WebTableRowElementEmptyCondition());
    }

    public static WebTableFilterBuilder with(WebTableRowCondition condition) {
        return new WebTableFilterBuilderSeleniumImpl()
                .add(condition);
    }

    public static WebTableFilterBuilder without(WebTableRowCondition condition) {
        return emptyTableFilter()
                .subtract(condition);
    }

    // StringTable

    public static WebTextTableFilterBuilder emptyTextTableFilter() {
        return new WebTextTableFilterBuilderSeleniumImpl();
    }

    public static WebTextTableFilterBuilder with(WebTextTableRowCondition stringCondition) {
        return new WebTextTableFilterBuilderSeleniumImpl().add(stringCondition);
    }

    public static WebTextTableFilterBuilder without(WebTextTableRowCondition stringCondition) {
        return new WebTextTableFilterBuilderSeleniumImpl().subtract(stringCondition);
    }

}
