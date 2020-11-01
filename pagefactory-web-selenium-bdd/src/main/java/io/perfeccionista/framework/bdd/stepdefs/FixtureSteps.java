package io.perfeccionista.framework.bdd.stepdefs;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.FixtureParameter;
import io.perfeccionista.framework.bdd.parameters.datatable.entries.FixtureEntry;
import io.perfeccionista.framework.fixture.FixtureService;

import java.util.List;

public class FixtureSteps implements WebStepDefinitions {

    @Given("user executes fixture {fixtureName}")
    @Given("пользователь выполняет предусловие {fixtureName}")
    public void userExecutesFixture(FixtureParameter<?, ?> fixtureName) {
        getEnvironment().getService(FixtureService.class)
                .executeFixture(fixtureName.getFixtureInstance());
    }

    @Given("user executes fixture {fixtureName} with parameters")
    @Given("пользователь выполняет предусловие {fixtureName} с параметрами")
    public void userExecutesFixture(FixtureParameter<?, ?> fixtureName,
                                    List<FixtureEntry> fixtureParameters) {
        getEnvironment().getService(FixtureService.class)
                .executeFixture(fixtureName.getFixtureInstance(fixtureParameters));
    }

    // TODO: Добавить степы на сохранение значений из фикстур в стеш и выполнение фикстур несколько раз



}
