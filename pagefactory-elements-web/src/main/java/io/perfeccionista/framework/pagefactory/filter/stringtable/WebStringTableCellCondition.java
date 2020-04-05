package io.perfeccionista.framework.pagefactory.filter.stringtable;

import io.perfeccionista.framework.pagefactory.filter.Condition;

public interface WebStringTableCellCondition extends Condition {

    WebStringTableCellCondition and(WebStringTableCellCondition condition);

    WebStringTableCellCondition or(WebStringTableCellCondition condition);

}
