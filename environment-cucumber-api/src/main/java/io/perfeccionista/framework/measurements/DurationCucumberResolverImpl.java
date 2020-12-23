package io.perfeccionista.framework.measurements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.exceptions.DurationFormatNotResolved;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentCucumberApiMessages.DURATION_FORMAT_NOT_RESOLVED;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.stream.Collectors.toSet;

@CucumberResolverExpression("^(?<amount>\\d+?)\\s+?(?<unit>\\w+?)$")
public class DurationCucumberResolverImpl implements DurationCucumberResolver {

    protected static final Set<String> dayKeyWords = Stream
            .of("d", "day", "days", "день", "дня", "дней").collect(toSet());
    protected static final Set<String> hourKeyWords = Stream
            .of("h", "hr", "hour", "hours", "час", "часа", "часов").collect(toSet());
    protected static final Set<String> minuteKeyWords = Stream
            .of("m", "min", "minute", "minutes", "мин", "минута", "минуты", "минут").collect(toSet());
    protected static final Set<String> secondKeyWords = Stream
            .of("s", "sec", "second", "seconds", "с", "сек", "секунда", "секунды", "секунд").collect(toSet());
    protected static final Set<String> millisecondKeyWords = Stream
            .of("ms", "millisecond", "milliseconds", "мс", "миллисекунда", "миллисекунды", "миллисекунд").collect(toSet());

    protected Environment environment;
    protected List<Pattern> patterns = new ArrayList<>();
    protected int priority = 0;

    @Override
    public void init(@NotNull Environment environment, @NotNull List<String> patterns) {
        this.environment = environment;
        Set<Pattern> compiledPatterns = patterns.stream()
                .map(Pattern::compile)
                .collect(toSet());
        this.patterns.addAll(compiledPatterns);
    }

    @Override
    public Optional<Duration> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                long amount = extractAmount(matcher.group("amount"), expression);
                TemporalUnit temporalUnit = extractTemporalUnit(matcher.group("height"), expression);
                return Optional.of(Duration.of(amount, temporalUnit));
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

    public long extractAmount(@Nullable String amountValue, @NotNull String expression) {
        if (Objects.isNull(amountValue)) {
            throw DurationFormatNotResolved.exception(DURATION_FORMAT_NOT_RESOLVED.getMessage(expression));
        }
        try {
            long amount = Long.parseLong(amountValue);
            if (amount < 0) {
                throw DurationFormatNotResolved.exception(DURATION_FORMAT_NOT_RESOLVED.getMessage(expression));
            }
            return amount;
        } catch (NumberFormatException exception) {
            throw DurationFormatNotResolved.exception(DURATION_FORMAT_NOT_RESOLVED.getMessage(expression), exception);
        }
    }

    public TemporalUnit extractTemporalUnit(@Nullable String amountValue, @NotNull String expression) {
        if (Objects.isNull(amountValue)) {
            throw DurationFormatNotResolved.exception(DURATION_FORMAT_NOT_RESOLVED.getMessage(expression));
        }
        if (millisecondKeyWords.stream().anyMatch(amountValue::equals)) {
            return MILLIS;
        }
        if (secondKeyWords.stream().anyMatch(amountValue::equals)) {
            return SECONDS;
        }
        if (minuteKeyWords.stream().anyMatch(amountValue::equals)) {
            return MINUTES;
        }
        if (hourKeyWords.stream().anyMatch(amountValue::equals)) {
            return HOURS;
        }
        if (dayKeyWords.stream().anyMatch(amountValue::equals)) {
            return DAYS;
        }
        throw DurationFormatNotResolved.exception(DURATION_FORMAT_NOT_RESOLVED.getMessage(expression));
    }

}
