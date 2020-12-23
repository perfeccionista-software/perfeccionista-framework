package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.ScenarioNameParameter;
import io.perfeccionista.framework.cucumber.parameters.StepNameParameter;

// TODO: Implement
public class ScenarioStructureStepDefinitions implements CucumberStepDefinitions {

    /**
     *
     * @param stepName -
     */
    @Дано("шаг {stepName}")
    @Given("step {stepName}")
    public void step(StepNameParameter stepName) {

    }

    /**
     *
     * @param scenarioName -
     */
    @Дано("сценарий {scenarioName}")
    @Given("scenario {scenarioName}")
    public void scenario(ScenarioNameParameter scenarioName) {

    }

    /**
     *
     * @param scenarioName -
     * @param scenarioParameters -
     */
    @Дано("сценарий {scenarioName} с параметрами")
    @Given("scenario {scenarioName} with parameters")
    public void scenarioWithParameters(ScenarioNameParameter scenarioName, DataTable scenarioParameters) {

    }

}
