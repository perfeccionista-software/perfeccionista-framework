package io.perfeccionista.framework.bdd.parameters.definition;

import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.pagefactory.filter.WebConditions;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonCondition;

public class RuConditionDefinition {

    @ConditionDefinition("с индексом {integerValue}")
    public WebRadioButtonCondition radioButtonPresent(ValueIntegerParameter indexParameter) {
        return WebConditions.radioButtonIndex(indexParameter.getValue());
    }




}
