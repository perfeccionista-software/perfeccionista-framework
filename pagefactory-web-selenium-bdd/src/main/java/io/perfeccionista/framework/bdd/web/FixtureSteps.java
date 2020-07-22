package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.FixtureParameter;
import io.perfeccionista.framework.fixture.FixtureService;

public class FixtureSteps implements EnvironmentAvailable {

    @Given("user execute precondition {fixtureName}")
    @Given("пользователь выполняет предусловие {fixtureName}")
    public void userExecutePrecondition(FixtureParameter<?> fixtureName) {
        getEnvironment().getService(FixtureService.class)
                .executeFixture(fixtureName.getFixtureInstance(this.getEnvironment()));
    }

}
