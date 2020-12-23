package io.perfeccionista.framework.value.checker.number.resolver;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractIntegerCheckerCucumberResolver implements IntegerValueCheckerCucumberResolver {

    protected Environment environment;
    protected List<String> patterns;
    protected int priority = 0;

    @Override
    public void init(@NotNull Environment environment, @NotNull List<String> patterns) {
        this.environment = environment;
        this.patterns = patterns;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }

}

