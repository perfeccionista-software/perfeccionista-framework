package io.perfeccionista.framework.bdd.parameters.datatable.entries;

import io.perfeccionista.framework.bdd.parameters.BddStepParameter;
import org.jetbrains.annotations.NotNull;

public interface FixtureEntry extends BddStepParameter, VerifiableDataTableEntry {

    @NotNull String getParameterName();

    @NotNull String getParameterValue();

}
