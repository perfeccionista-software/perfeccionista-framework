package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.filter.Condition;

public interface WebTextListBlockCondition extends Condition {

    WebTextListBlockCondition and(WebTextListBlockCondition condition);

    WebTextListBlockCondition or(WebTextListBlockCondition condition);

}
