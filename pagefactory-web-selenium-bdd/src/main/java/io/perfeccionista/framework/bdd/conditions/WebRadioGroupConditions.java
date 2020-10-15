package io.perfeccionista.framework.bdd.conditions;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.pagefactory.filter.WebFilterConditions;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;

// TODO: Реализации этих методов по хорошему тоже нужно куда-то вынести.
//  Или просто для каждого модуля элементов иметь отдельную реализацию BDD
//  Или вынести в конфигурацию, где можно для конкретного метода подложить другой кондишен.
@BddFilterCondition(WebRadioGroupFilterBuilder.class)
public class WebRadioGroupConditions {

    /**
     *
     */
    @Given("without filter")
    @Given("без фильтра")
    public WebRadioButtonCondition withEmptyCondition() {
        return WebFilterConditions.allRadioButtons();
    }

    /**
     *
     * @param buttonIndex -
     */
    @Given("with index {integerValue}")
    @Given("с индексом {integerValue}")
    public WebRadioButtonCondition withIndex(ValueIntegerParameter buttonIndex) {
        return WebFilterConditions.radioButtonIndex(buttonIndex.getValue());
    }

    /**
     *
     * @param buttonIndex -
     */
    @Given("without index {integerValue}")
    @Given("с индексом не {integerValue}")
    public WebRadioButtonCondition withoutIndex(ValueIntegerParameter buttonIndex) {
        return WebFilterConditions.radioButtonIndexNot(buttonIndex.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("with label {stringValue}")
    @Given("с лейблом {stringValue}")
    public WebRadioButtonCondition withLabel(ValueStringParameter stringValue) {
        return WebFilterConditions.containsLabel(stringValue.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("without label {stringValue}")
    @Given("без лейбла {stringValue}")
    public WebRadioButtonCondition withoutLabel(ValueStringParameter stringValue) {
        return WebFilterConditions.notContainsLabel(stringValue.getValue());
    }

    /**
     *
     */
    @Given("which enabled")
    @Given("которая доступна")
    public WebRadioButtonCondition withEnabled() {
        return WebFilterConditions.enabled();
    }

    /**
     *
     */
    @Given("which disabled")
    @Given("которая недоступна")
    public WebRadioButtonCondition withDisabled() {
        return WebFilterConditions.disabled();
    }

    /**
     *
     */
    @Given("which selected")
    @Given("которая выделена")
    public WebRadioButtonCondition withSelected() {
        return WebFilterConditions.selected();
    }

    /**
     *
     */
    @Given("which not selected")
    @Given("которая не выделена")
    public WebRadioButtonCondition withNotSelected() {
        return WebFilterConditions.notSelected();
    }

}
