package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.filter.Condition;

public interface WebListBlockCondition extends Condition {

    WebListBlockCondition and(WebListBlockCondition condition);

    WebListBlockCondition or(WebListBlockCondition condition);

}
