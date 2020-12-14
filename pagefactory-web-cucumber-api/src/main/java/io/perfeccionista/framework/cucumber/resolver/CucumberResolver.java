package io.perfeccionista.framework.cucumber.resolver;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public interface CucumberResolver<T> {

    void init(@NotNull Environment environment, @NotNull List<String> patterns);

    Optional<T> tryResolve(@NotNull String expression, @Nullable Object... args);

    void setPriority(int priority);

    int getPriority();

    default Pattern convertToRegexp(@NotNull String expression) {
        String regularExpression = "^" + expression
                .replace("{", "\"(?<")
                .replace("}", ">.*?)\"") + "$";
        return Pattern.compile(regularExpression);
    }

}
