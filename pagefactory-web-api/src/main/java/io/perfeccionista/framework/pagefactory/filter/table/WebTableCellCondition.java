package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.filter.Condition;

public interface WebTableCellCondition extends Condition {

    WebTableCellCondition and(WebTableCellCondition condition);

    WebTableCellCondition or(WebTableCellCondition condition);

}
