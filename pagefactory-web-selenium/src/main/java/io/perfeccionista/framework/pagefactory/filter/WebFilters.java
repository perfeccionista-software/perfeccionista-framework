package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterSeleniumImpl;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilterSeleniumImpl;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterSeleniumImpl;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterSeleniumImpl;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableCellCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterSeleniumImpl;

// TODO: Это тоже можно вынести в конфигурируемый интерфейс.
//  Тогда при замене модуля элементов нужно будет в тестах только подменить конфигурацию фильтров
public class WebFilters {

    protected WebFilters() {
    }

    // RadioButton

    public static WebRadioButtonFilter emptyRadioButtonFilter() {
        return new WebRadioButtonFilterSeleniumImpl();
    }

    public static WebRadioButtonFilter with(WebRadioButtonCondition condition) {
        return new WebRadioButtonFilterSeleniumImpl().add(condition);
    }

    public static WebRadioButtonFilter without(WebRadioButtonCondition condition) {
        return new WebRadioButtonFilterSeleniumImpl().subtract(condition);
    }

    // List

    public static WebListFilter emptyListFilter() {
        return new WebListFilterSeleniumImpl();
    }

    public static WebListFilter with(WebListBlockCondition condition) {
        return new WebListFilterSeleniumImpl().add(condition);
    }

    public static WebListFilter without(WebListBlockCondition condition) {
        return new WebListFilterSeleniumImpl().subtract(condition);
    }

    // StringList

    public static WebTextListFilter emptyTextListFilter() {
        return new WebTextListFilterSeleniumImpl();
    }

    public static WebTextListFilter with(WebTextListBlockCondition stringCondition) {
        return new WebTextListFilterSeleniumImpl().add(stringCondition);
    }

    public static WebTextListFilter without(WebTextListBlockCondition stringCondition) {
        return new WebTextListFilterSeleniumImpl().subtract(stringCondition);
    }

    // Table

    public static WebTableFilter emptyTableFilter() {
        return new WebTableFilterSeleniumImpl();
    }

    public static WebTableFilter with(WebTableCellCondition condition) {
        return new WebTableFilterSeleniumImpl().add(condition);
    }

    public static WebTableFilter without(WebTableCellCondition condition) {
        return new WebTableFilterSeleniumImpl().subtract(condition);
    }

    // StringTable

    public static WebTextTableFilter emptyTextTableFilter() {
        return new WebTextTableFilterSeleniumImpl();
    }

    public static WebTextTableFilter with(WebTextTableCellCondition stringCondition) {
        return new WebTextTableFilterSeleniumImpl().add(stringCondition);
    }

    public static WebTextTableFilter without(WebTextTableCellCondition stringCondition) {
        return new WebTextTableFilterSeleniumImpl().subtract(stringCondition);
    }

}
