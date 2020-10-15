package io.perfeccionista.framework.bdd.parameters.definition;

import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.pagefactory.filter.WebFilterConditions;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;

public class RuConditionDefinition {

    @ConditionDefinition("с индексом {integerValue}")
    public WebRadioButtonCondition radioButtonPresent(ValueIntegerParameter indexParameter) {
        return WebFilterConditions.radioButtonIndex(indexParameter.getValue());
    }




}
