package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonCondition;
import io.perfeccionista.framework.pagefactory.filter.stringlist.WebStringListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.stringtable.WebStringTableCellCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellCondition;

public class WebFilters {

    private WebFilters() {
    }

    // RadioButton

    public static WebRadioButtonFilter emptyRadioButtonFilter() {
        return new WebRadioButtonFilter();
    }

    public static WebRadioButtonFilter with(WebRadioButtonCondition condition) {
        return new WebRadioButtonFilter().add(condition);
    }

    public static WebRadioButtonFilter without(WebRadioButtonCondition condition) {
        return new WebRadioButtonFilter().subtract(condition);
    }

    // List

    public static WebListFilter emptyListFilter() {
        return new WebListFilter();
    }

    public static WebListFilter with(WebListBlockCondition condition) {
        return new WebListFilter().add(condition);
    }

    public static WebListFilter without(WebListBlockCondition condition) {
        return new WebListFilter().subtract(condition);
    }

    // StringList

    public static WebStringListFilter emptyStringListFilter() {
        return new WebStringListFilter();
    }

    public static WebStringListFilter with(WebStringListBlockCondition stringCondition) {
        return new WebStringListFilter().add(stringCondition);
    }

    public static WebStringListFilter without(WebStringListBlockCondition stringCondition) {
        return new WebStringListFilter().subtract(stringCondition);
    }

    // Table

    public static WebTableFilter emptyTableFilter() {
        return new WebTableFilter();
    }

    public static WebTableFilter with(WebTableCellCondition condition) {
        return new WebTableFilter().add(condition);
    }

    public static WebTableFilter without(WebTableCellCondition condition) {
        return new WebTableFilter().subtract(condition);
    }

    // StringTable

    public static WebStringTableFilter emptyStringTableFilter() {
        return new WebStringTableFilter();
    }

    public static WebStringTableFilter with(WebStringTableCellCondition stringCondition) {
        return new WebStringTableFilter().add(stringCondition);
    }

    public static WebStringTableFilter without(WebStringTableCellCondition stringCondition) {
        return new WebStringTableFilter().subtract(stringCondition);
    }

}
