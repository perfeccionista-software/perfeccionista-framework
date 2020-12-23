package io.perfeccionista.framework.color;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.exceptions.ColorFormatNotResolved;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentCucumberApiMessages.COLOR_FORMAT_NOT_RESOLVED;

@CucumberResolverExpression("^rgba?\\((?<r>\\d+?),\\s*?(?<g>\\d+?),\\s*?(?<b>\\d+?)(,\\s*?(?<alpha>\\d?\\.?\\d*?))?\\)$")
public class ColorCucumberResolverImpl implements ColorCucumberResolver {

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
    public Optional<Color> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                int red = extractColor(matcher.group("r"), expression);
                int green = extractColor(matcher.group("g"), expression);
                int blue = extractColor(matcher.group("b"), expression);
                double alpha = extractAlpha(matcher.group("alpha"), expression)
                        .orElse(1.0d);
                return Optional.of(Color.of(red, green, blue, alpha));
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

    protected int extractColor(@Nullable String colorValue, @NotNull String expression) {
        if (Objects.isNull(colorValue)) {
            throw ColorFormatNotResolved.exception(COLOR_FORMAT_NOT_RESOLVED.getMessage(expression));
        }
        try {
            int color = Integer.parseInt(colorValue);
            if (color < 0 || color > 256) {
                throw ColorFormatNotResolved.exception(COLOR_FORMAT_NOT_RESOLVED.getMessage(expression));
            }
            return color;
        } catch (NumberFormatException exception) {
            throw ColorFormatNotResolved.exception(COLOR_FORMAT_NOT_RESOLVED.getMessage(expression), exception);
        }
    }

    protected Optional<Double> extractAlpha(@Nullable String alphaValue, @NotNull String expression) {
        if (Objects.isNull(alphaValue)) {
            return Optional.empty();
        }
        try {
            double alpha = Double.parseDouble(alphaValue);
            if (alpha < 0 || alpha > 1.0d) {
                throw ColorFormatNotResolved.exception(COLOR_FORMAT_NOT_RESOLVED.getMessage(expression));
            }
            return Optional.of(alpha);
        } catch (NumberFormatException exception) {
            throw ColorFormatNotResolved.exception(COLOR_FORMAT_NOT_RESOLVED.getMessage(expression), exception);
        }
    }

}
