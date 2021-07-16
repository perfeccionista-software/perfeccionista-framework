package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public class DefaultInvocationInfoNameFormatter implements InvocationInfoNameFormatter {

    private final Map<String, String> invocationNamesMap;

    public DefaultInvocationInfoNameFormatter(@NotNull Map<String, String> invocationNamesMap) {
        this.invocationNamesMap = invocationNamesMap;
    }

    @Override
    public String format(@NotNull String name, String... args) {
        String formattedName= name;
        String format = invocationNamesMap.get(name);
        if (Objects.nonNull(format)) {
            formattedName = String.format(format, args);
        }
        return formattedName;
    }

}
