package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentAvailable;

public interface CucumberStepDefinitions extends EnvironmentAvailable {

    @Override
    default Environment getEnvironment() {
        return Environment.getCurrent();
    }

}
