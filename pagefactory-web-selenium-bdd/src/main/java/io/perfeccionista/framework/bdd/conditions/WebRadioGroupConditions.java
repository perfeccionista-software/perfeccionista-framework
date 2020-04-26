package io.perfeccionista.framework.bdd.conditions;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonLabelCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonSelectedCondition;

public class WebRadioGroupConditions {

    /**
     *
     * @param buttonIndex -
     */
    @Given("with index {integerValue}")
    @Given("с индексом {integerValue}")
    public WebRadioButtonCondition withIndex(ValueIntegerParameter buttonIndex) {
        return new WebRadioButtonIndexCondition(buttonIndex.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("with label {stringValue}")
    @Given("с лейблом {stringValue}")
    public WebRadioButtonCondition withLabel(ValueStringParameter stringValue) {
        return new WebRadioButtonLabelCondition(stringValue.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("without label {stringValue}")
    @Given("без лейбла {stringValue}")
    public WebRadioButtonCondition withoutLabel(ValueStringParameter stringValue) {
        return new WebRadioButtonLabelCondition(stringValue.getValue()).inverse();
    }

    /**
     *
     */
    @Given("which enabled")
    @Given("которая доступна")
    public WebRadioButtonCondition withEnabled() {
        return new WebRadioButtonEnabledCondition();
    }

    /**
     *
     */
    @Given("which disabled")
    @Given("которая недоступна")
    public WebRadioButtonCondition withDisabled() {
        return new WebRadioButtonEnabledCondition().inverse();
    }

    /**
     *
     */
    @Given("which selected")
    @Given("которая выделена")
    public WebRadioButtonCondition withSelected() {
        return new WebRadioButtonSelectedCondition();
    }

    /**
     *
     */
    @Given("which not selected")
    @Given("которая не выделена")
    public WebRadioButtonCondition withNotSelected() {
        return new WebRadioButtonSelectedCondition().inverse();
    }

}
