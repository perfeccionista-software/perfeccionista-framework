package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.FixtureParameter;
import io.perfeccionista.framework.fixture.FixtureParameters;
import io.perfeccionista.framework.fixture.FixtureService;

public class FixtureStepDefinitions implements CucumberStepDefinitions {

    @Дано("пользователь выполняет предусловие {fixture}")
    @Given("user executes fixture {fixture}")
    public void userExecutesFixture(FixtureParameter<?, ?> fixtureName) {
        getEnvironment().getService(FixtureService.class)
                .executeFixture(fixtureName.getFixtureInstance());
    }

    @Дано("пользователь выполняет предусловие {fixture} с параметрами")
    @Given("user executes fixture {fixture} with parameters")
    public void userExecutesFixture(FixtureParameter<?, ?> fixtureName, FixtureParameters fixtureParameters) {
        getEnvironment().getService(FixtureService.class)
                .executeFixture(fixtureName.getFixtureInstance(fixtureParameters));
    }

    // TODO: Добавить степы на сохранение значений из фикстур в стеш и выполнение фикстур несколько раз

}
