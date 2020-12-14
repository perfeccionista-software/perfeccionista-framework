package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ValueStringParameter extends CucumberStepParameter {

    @NotNull String getNotNullProcessedValue();

    @Nullable String getProcessedValue();

    @NotNull StringValue getValue();

}
