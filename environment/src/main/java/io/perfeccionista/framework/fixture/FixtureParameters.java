package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.exceptions.ClassCanNotBeCast;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_CAST_OBJECT;
import static io.perfeccionista.framework.utils.CastUtils.isSubtypeOf;

public class FixtureParameters {

    protected final Map<String, Object> parameters;

    protected FixtureParameters() {
        this.parameters = new HashMap<>();
    }

    protected FixtureParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public static FixtureParameters builder() {
        return new FixtureParameters();
    }

    public FixtureParameters addParameter(@NotNull String parameterName, @Nullable Object parameter) {
        parameters.put(parameterName, parameter);
        return this;
    }

    public <T> @Nullable T get(@NotNull String parameterName, @NotNull Class<T> parameterType) {
        Object parameter = parameters.get(parameterName);
        if (Objects.isNull(parameter)) {
            return null;
        }
        if (isSubtypeOf(parameter, parameterType)) {
            return (T) parameter;
        }
        throw ClassCanNotBeCast.exception(CANT_CAST_OBJECT.getMessage(parameter.getClass().getCanonicalName(), parameterType.getCanonicalName()));
    }

    @NotNull
    public final Set<String> getNamesSet() {
        return parameters.keySet();
    }

    public final boolean contains(@NotNull String name) {
        return parameters.containsKey(name);
    }

    @Override
    public String toString() {
        return "Fixture parameters = {\n"
                + "Type: " + this.getClass().getCanonicalName() + "\n"
                + "Parameters: \n"
                + parameters.entrySet().stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue())
                .collect(Collectors.joining("\n")) + "\n}";
    }

}
