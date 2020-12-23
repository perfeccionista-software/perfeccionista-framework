package io.perfeccionista.framework.comparators;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.perfeccionista.framework.comparators.SortDirection.ASC;
import static io.perfeccionista.framework.comparators.SortDirection.DESC;
import static io.perfeccionista.framework.comparators.SortDirection.NOT_ASC;
import static io.perfeccionista.framework.comparators.SortDirection.NOT_DESC;
import static java.util.stream.Collectors.toSet;

public class SortDirectionCucumberResolverImpl implements SortDirectionCucumberResolver {

    protected static final Set<String> ascKeyWords = Stream
            .of("acs", "ascending", "ascending order", "по возрастанию").collect(toSet());
    protected static final Set<String> descKeyWords = Stream
            .of("desc", "descending", "descending order", "по убыванию").collect(toSet());
    protected static final Set<String> notAscKeyWords = Stream
            .of("not asc", "not ascending", "ascending order", "не по возрастанию").collect(toSet());
    protected static final Set<String> notDescKeyWords = Stream
            .of("not desc", "not descending", "not descending order", "не по убыванию").collect(toSet());

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
    public Optional<SortDirection> tryResolve(@NotNull String expression, @Nullable Object... args) {
        if (ascKeyWords.stream().anyMatch(expression.toLowerCase()::equals)) {
            return Optional.of(ASC);
        }
        if (descKeyWords.stream().anyMatch(expression.toLowerCase()::equals)) {
            return Optional.of(DESC);
        }
        if (notAscKeyWords.stream().anyMatch(expression.toLowerCase()::equals)) {
            return Optional.of(NOT_ASC);
        }
        if (notDescKeyWords.stream().anyMatch(expression.toLowerCase()::equals)) {
            return Optional.of(NOT_DESC);
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
