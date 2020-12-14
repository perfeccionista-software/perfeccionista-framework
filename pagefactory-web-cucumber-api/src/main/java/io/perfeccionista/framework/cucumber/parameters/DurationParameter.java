package io.perfeccionista.framework.cucumber.parameters;

import java.time.Duration;

public interface DurationParameter extends CucumberStepParameter {

    Duration getDuration();

}
