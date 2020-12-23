package io.perfeccionista.framework.comparators.string;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CucumberResolverExpression("number")
@CucumberResolverExpression("число")
public class StringNumberComparatorCucumberResolver implements StringValueComparatorCucumberResolver {

    protected Environment environment;
    protected List<String> patterns = new ArrayList<>();
    protected int priority = 0;

    @Override
    public void init(@NotNull Environment environment, @NotNull List<String> patterns) {
        this.environment = environment;
        this.patterns.addAll(patterns);
    }

    @Override
    public Optional<StringValueComparator> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (String pattern : patterns) {
            if (pattern.equalsIgnoreCase(expression)) {
                return Optional.of(StringNumberComparator.defaultFormat());
            }
        }
        return Optional.empty();
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
