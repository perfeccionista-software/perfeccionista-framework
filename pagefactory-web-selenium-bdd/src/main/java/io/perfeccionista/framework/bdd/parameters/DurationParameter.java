package io.perfeccionista.framework.bdd.parameters;

import java.time.Duration;

public interface DurationParameter extends BddStepParameter {

    Duration getDuration();

}
