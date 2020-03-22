package io.perfeccionista.framework.pagefactory.filter.stringblock;

import io.perfeccionista.framework.pagefactory.filter.Condition;

public interface WebStringBlockCondition extends Condition {

    WebStringBlockCondition and(WebStringBlockCondition condition);

    WebStringBlockCondition or(WebStringBlockCondition condition);

}
