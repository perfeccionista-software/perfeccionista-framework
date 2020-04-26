package io.perfeccionista.framework.bdd.conditions;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListBlockTextCondition;

public class WebTextListConditions {

    /**
     *
     * @param blockIndex -
     */
    @Given("with index {integerValue}")
    @Given("с индексом {integerValue}")
    public WebTextListBlockCondition withIndex(ValueIntegerParameter blockIndex) {
        return new WebTextListBlockIndexCondition(blockIndex.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("with text {stringValue}")
    @Given("с текстом {stringValue}")
    public WebTextListBlockCondition withText(ValueStringParameter stringValue) {
        return new WebTextListBlockTextCondition(stringValue.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("without text {stringValue}")
    @Given("без текста {stringValue}")
    public WebTextListBlockCondition withoutText(ValueStringParameter stringValue) {
        return new WebTextListBlockTextCondition(stringValue.getValue()).inverse();
    }

}
