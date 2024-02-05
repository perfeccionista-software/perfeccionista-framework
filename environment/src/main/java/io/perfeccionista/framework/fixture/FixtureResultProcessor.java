package io.perfeccionista.framework.fixture;

public interface FixtureResultProcessor {

    void processFixtureSetUpResult(FixtureSetUpResult<?> fixtureSetUpResult);

    void processFixtureTearDownResult(FixtureTearDownResult<?> fixtureTearDownResult);

}
