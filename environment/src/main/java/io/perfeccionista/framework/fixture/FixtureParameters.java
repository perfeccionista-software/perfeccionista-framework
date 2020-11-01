package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.exceptions.ClassCast;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_CAST_OBJECT;
import static io.perfeccionista.framework.utils.ReflectionUtils.isSubtypeOf;

public class FixtureParameters {

    private final Map<String, Object> parameters;

    private FixtureParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public static FixtureParameters builder() {
        return new FixtureParameters(new HashMap<>());
    }

    public static FixtureParameters of(@NotNull Map<String, Object> parameters) {
        return new FixtureParameters(parameters);
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
        throw ClassCast.exception(CANT_CAST_OBJECT.getMessage(parameter.getClass().getCanonicalName(), parameterType.getCanonicalName()));
    }

}