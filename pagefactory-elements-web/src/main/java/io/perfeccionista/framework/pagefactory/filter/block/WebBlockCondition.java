package io.perfeccionista.framework.pagefactory.filter.block;

public interface WebBlockCondition {

    WebBlockCondition and(WebBlockCondition condition);

    WebBlockCondition or(WebBlockCondition condition);

}
