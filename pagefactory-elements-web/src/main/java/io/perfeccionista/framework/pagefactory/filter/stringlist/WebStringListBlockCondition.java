package io.perfeccionista.framework.pagefactory.filter.stringlist;

import io.perfeccionista.framework.pagefactory.filter.Condition;

public interface WebStringListBlockCondition extends Condition {

    WebStringListBlockCondition and(WebStringListBlockCondition condition);

    WebStringListBlockCondition or(WebStringListBlockCondition condition);

}
