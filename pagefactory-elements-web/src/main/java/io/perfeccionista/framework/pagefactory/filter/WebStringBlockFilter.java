package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.pagefactory.elements.WebSimpleUnorderedList;
import io.perfeccionista.framework.pagefactory.filter.stringblock.WebStringBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.stringblock.WebStringBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.stringblock.WebStringBlockValueCondition;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebStringBlockFilter implements Filter<WebSimpleUnorderedList, String> {

    private final Deque<JsStringBlockConditionHolder> conditions = new ArrayDeque<>();

    public WebStringBlockFilter() {
        // TODO: пустое условие, которое возвращает все элементы
    }

    public WebStringBlockFilter(StringValue value) {
        this(new WebStringBlockValueCondition(value));
    }

    public WebStringBlockFilter(NumberValue<Integer> value) {
        this(new WebStringBlockIndexCondition(value));
    }

    public WebStringBlockFilter(WebStringBlockCondition condition) {
        this.conditions.addLast(new JsStringBlockConditionHolder(ConditionUsage.ADD, condition));
    }

    public WebStringBlockFilter add(WebStringBlockCondition condition) {
        this.conditions.addLast(new JsStringBlockConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebStringBlockFilter substract(WebStringBlockCondition condition) {
        this.conditions.addLast(new JsStringBlockConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public SingleResult<String> singleResult(WebSimpleUnorderedList inputData) throws PerfeccionistaException {
        return null;
    }

    @Override
    public MultipleResult<String> multipleResult(WebSimpleUnorderedList inputData) throws PerfeccionistaException {
        return null;
    }

    private static class JsStringBlockConditionHolder {

        private final ConditionUsage usage;
        private final WebStringBlockCondition condition;

        public JsStringBlockConditionHolder(ConditionUsage usage, WebStringBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public ConditionUsage getUsage() {
            return usage;
        }

        public WebStringBlockCondition getCondition() {
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
