package io.perfeccionista.framework.measurements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.exceptions.LocationFormatNotResolved;
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

import static io.perfeccionista.framework.exceptions.messages.EnvironmentCucumberApiMessages.LOCATION_FORMAT_NOT_RESOLVED;

@CucumberResolverExpression("^\\((?<xCoordinate>-?\\d+?\\.?\\d*?),\\s*?(?<yCoordinate>-?\\d+?\\.?\\d*?)\\)\\s*?(\\((?<inaccuracy>\\d+?\\.?\\d*?)\\))*?$")
public class LocationCucumberResolverImpl implements LocationCucumberResolver {

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
    public Optional<Point2D> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                double xCoordinate = extractCoordinate(matcher.group("xCoordinate"), expression);
                double yCoordinate = extractCoordinate(matcher.group("yCoordinate"), expression);
                double inaccuracy = extractInaccuracy(matcher.group("inaccuracy"), expression)
                        .orElse(0.5d);
                return Optional.of(Point2D.of(xCoordinate, yCoordinate).setInaccuracy(inaccuracy));
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

    protected double extractCoordinate(@Nullable String coordinateValue, @NotNull String expression) {
        if (Objects.isNull(coordinateValue)) {
            throw LocationFormatNotResolved.exception(LOCATION_FORMAT_NOT_RESOLVED.getMessage(expression));
        }
        try {
            return Double.parseDouble(coordinateValue);
        } catch (NumberFormatException exception) {
            throw LocationFormatNotResolved.exception(LOCATION_FORMAT_NOT_RESOLVED.getMessage(expression), exception);
        }
    }

    protected Optional<Double> extractInaccuracy(@Nullable String inaccuracyValue, @NotNull String expression) {
        if (Objects.isNull(inaccuracyValue)) {
            return Optional.empty();
        }
        try {
            double inaccuracy = Double.parseDouble(inaccuracyValue);
            if (inaccuracy < 0) {
                throw LocationFormatNotResolved.exception(LOCATION_FORMAT_NOT_RESOLVED.getMessage(expression));
            }
            return Optional.of(inaccuracy);
        } catch (NumberFormatException exception) {
            throw LocationFormatNotResolved.exception(LOCATION_FORMAT_NOT_RESOLVED.getMessage(expression), exception);
        }
    }

}
