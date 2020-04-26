package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.filter.Condition;

public interface WebTextTableCellCondition extends Condition {

    WebTextTableCellCondition and(WebTextTableCellCondition condition);

    WebTextTableCellCondition or(WebTextTableCellCondition condition);

}
