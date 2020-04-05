package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.pagefactory.elements.WebStringList;
import io.perfeccionista.framework.pagefactory.filter.stringlist.WebStringListBlockCondition;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebStringListFilter implements Filter<WebStringList, String> {

    private final Deque<JsStringBlockConditionHolder> conditions = new ArrayDeque<>();

    public WebStringListFilter() {
    }

    public WebStringListFilter add(WebStringListBlockCondition condition) {
        this.conditions.addLast(new JsStringBlockConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebStringListFilter subtract(WebStringListBlockCondition condition) {
        this.conditions.addLast(new JsStringBlockConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public SingleResult<String> singleResult(WebStringList inputData) throws PerfeccionistaException {
        return null;
    }

    @Override
    public MultipleResult<String> multipleResult(WebStringList inputData) throws PerfeccionistaException {
        return null;
    }

    private static class JsStringBlockConditionHolder {

        private final ConditionUsage usage;
        private final WebStringListBlockCondition condition;

        public JsStringBlockConditionHolder(ConditionUsage usage, WebStringListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public ConditionUsage getUsage() {
            return usage;
        }

        public WebStringListBlockCondition getCondition() {
            return condition;
        }

    }


    //        LocatorChain locatorChainForFilter = element.getLocatorChain();
//        LocatorHolder ulLocatorHolderForFilter = locatorChainForFilter.getLastLocator().calculateHash(true);
//        String ulLocatorId = ulLocatorHolderForFilter.getId();
//        LocatorHolder liLocatorHolderForFilter = unorderedList.getLocator(LI);
//        locatorChainForFilter.addLocator(liLocatorHolderForFilter);


    //                operationResultForFilter.getNodeHash(ulLocatorId)
//                .orElseThrow(() -> new NodeHashNotCalculatedException(REQUESTED_NODE_HASH_NOT_CALCULATED.getMessage()));

}
