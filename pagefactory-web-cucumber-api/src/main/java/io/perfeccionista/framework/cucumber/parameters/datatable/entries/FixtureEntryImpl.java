package io.perfeccionista.framework.cucumber.parameters.datatable.entries;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class FixtureEntryImpl implements FixtureEntry {

    private final String parameterName;
    private final String parameterValue;

    public FixtureEntryImpl(@NotNull String parameterName, @NotNull String parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }

    @Override
    public @NotNull String getParameterName() {
        return parameterName;
    }

    @Override
    public @NotNull String getParameterValue() {
        return parameterValue;
    }

    @Override
    public String getRaw() {
        return null;
    }

    @Override
    public Map<String, String> verify(Map<String, String> entry) {
        return null;
    }

}
