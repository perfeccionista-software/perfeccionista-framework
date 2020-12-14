package io.perfeccionista.framework.cucumber.stepdefs.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.cucumber.parameters.ScenarioNameParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.cucumber.parameters.datatable.ScenarioParameters;
import io.perfeccionista.framework.pagefactory.elements.methods.HoverToAvailable;

// TODO:
public class ScenarioStructureSteps implements WebStepDefinitions {

    /**
     *
     * @param stepNumber -
     * @param stepName -
     */
    @Given("Step {integerValue} {stringValue}")
    @Given("шаг {integerValue} {stringValue}")
    public void step(ValueIntegerParameter stepNumber, ValueStringParameter stepName) {

    }

    /**
     *
     * @param stepName -
     */
    @Given("Step {stringValue}")
    @Given("шаг {stringValue}")
    public void step(ValueStringParameter stepName) {

    }









    @Given("call scenario {scenarioName}")
    @Given("выполнить сценарий {scenarioName}")
    public void call(ScenarioNameParameter scenarioName) {

    }

    @Given("call scenario {scenarioName} with {stringValue} parameter equal to {stringValue}")
    @Given("выполнить сценарий {scenarioName} с параметром {stringValue} равным {stringValue}")
    public void callWithParameter(ScenarioNameParameter scenarioName, ValueStringParameter paramName, ValueStringParameter paramValue) {

    }

    @Given("call scenario {scenarioName} with parameters")
    @Given("выполнить сценарий {scenarioName} с параметрами")
    public void callWithParameters(ScenarioNameParameter scenarioName, ScenarioParameters scenarioParameters) {

    }

    /**
     *
     */
    @Given("user executes a sequence of {integerValue} steps")
    @Given("пользователь выполняет последовательность из {integerValue} шагов")
    public void userExecuteSequenceOfSteps(ValueIntegerParameter sequenceLength) {

    }

    /**
     * TODO: Было бы хорошо в своем DSL сделать последнюю 'И' семантической конструкцией, которая привязывает последующий степ
     * @param elementFinder -
     */
    @Given("user hovers the cursor over the {webElement} and")
    @Given("пользователь наводит курсор на {webElement} и")
    public void userHoversTheCursorOverTheElement(WebElementParameter<HoverToAvailable> elementFinder) {

    }





}
