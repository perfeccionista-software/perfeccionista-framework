package io.perfeccionista.framework.cucumber.parameters.datatable.entries;

import io.perfeccionista.framework.cucumber.parameters.CucumberStepParameter;
import org.jetbrains.annotations.NotNull;

public interface FixtureEntry extends CucumberStepParameter, VerifiableDataTableEntry {

    @NotNull String getParameterName();

    @NotNull String getParameterValue();

}
