package io.perfeccionista.framework.comparators.string;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@CucumberResolverExpression("^time \\[(?<format>.*?)\\]$")
@CucumberResolverExpression("^время \\[(?<format>.*?)\\]$")
public class StringTimeComparatorCucumberResolver implements StringValueComparatorCucumberResolver {

    protected Environment environment;
    protected List<Pattern> patterns = new ArrayList<>();
    protected int priority = 0;

    @Override
    public void init(@NotNull Environment environment, @NotNull List<String> patterns) {
        this.environment = environment;
        Set<Pattern> compiledPatterns = patterns.stream()
                .map(Pattern::compile)
                .collect(Collectors.toSet());
        this.patterns.addAll(compiledPatterns);
    }

    @Override
    public Optional<StringValueComparator> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression.toLowerCase());
            if (matcher.find()) {
                String format = matcher.group("format");
                if (Objects.isNull(format)) {
                    return Optional.of(StringTimeComparator.defaultFormat());
                }
                return Optional.of(StringTimeComparator.format(DateTimeFormatter.ofPattern(format)));
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
