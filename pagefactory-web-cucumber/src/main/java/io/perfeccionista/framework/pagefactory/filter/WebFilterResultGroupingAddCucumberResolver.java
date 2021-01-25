package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@CucumberResolverExpression("\\+")
public class WebFilterResultGroupingAddCucumberResolver implements WebFilterResultGroupingCucumberResolver {

    protected Environment environment;
    protected List<Pattern> patterns = new ArrayList<>();
    protected int priority = 0;

    @Override
    public void init(@NotNull Environment environment, @NotNull List<String> patterns) {
        this.environment = environment;
        Set<Pattern> compiledPatterns = patterns.stream()
                .map(this::convertToRegexp)
                .collect(Collectors.toSet());
        this.patterns.addAll(compiledPatterns);
    }

    @Override
    public Optional<FilterResultGrouping> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                return Optional.of(FilterResultGrouping.ADD);
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
