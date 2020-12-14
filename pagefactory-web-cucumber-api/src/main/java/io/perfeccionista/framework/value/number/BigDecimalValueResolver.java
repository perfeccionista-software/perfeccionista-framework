package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.BigDecimalValueConditionNotResolved;
import io.perfeccionista.framework.value.checker.number.resolver.BigDecimalValueCheckerCucumberResolver;
import io.perfeccionista.framework.value.transformer.number.resolver.BigDecimalValueCucumberTransformerResolver;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.BIG_DECIMAL_VALUE_CONDITION_NOT_RESOLVED;
import static io.perfeccionista.framework.value.Values.bigDecimalEquals;

public class BigDecimalValueResolver {
    public static final Pattern STRING_VALUE_RESOLVER_PATTERN = Pattern.compile("^\\[(?<condition>.*?)]");

    private final Environment environment;
    private final String rawStringValue;

    protected String bigDecimalValueCondition = null;
    protected BigDecimal bigDecimalValue = null;

    public BigDecimalValueResolver(Environment environment, String rawStringValue) {
        this.environment = environment;
        this.rawStringValue = rawStringValue;
        process(rawStringValue);
    }

    public @NotNull NumberValue<BigDecimal> getBigDecimalValue() {
        return resolveBigDecimalValueTransformers(resolveBigDecimalValueCondition());
    }

    protected void process(String stringValueToResolve) {
        Matcher matcher = STRING_VALUE_RESOLVER_PATTERN.matcher(stringValueToResolve);
        if (matcher.find()) {
            this.bigDecimalValueCondition = matcher.group("condition");
            this.bigDecimalValue = new BigDecimal(stringValueToResolve.replaceAll(STRING_VALUE_RESOLVER_PATTERN.pattern(), ""));
            return;
        }
        this.bigDecimalValue = new BigDecimal(stringValueToResolve);
    }

    protected NumberValue<BigDecimal> resolveBigDecimalValueCondition() {
        if (Objects.isNull(bigDecimalValueCondition)) {
            return bigDecimalEquals(bigDecimalValue);
        }
        return environment.getService(CucumberService.class)
                .resolveFirst(BigDecimalValueCheckerCucumberResolver.class, bigDecimalValueCondition, bigDecimalValue)
                .orElseThrow(() -> BigDecimalValueConditionNotResolved.exception(BIG_DECIMAL_VALUE_CONDITION_NOT_RESOLVED.getMessage(rawStringValue)));
    }

    protected @NotNull NumberValue<BigDecimal> resolveBigDecimalValueTransformers(NumberValue<BigDecimal> bigDecimalValue) {
        if (Objects.isNull(bigDecimalValueCondition)) {
            return bigDecimalValue;
        }
        environment.getService(CucumberService.class)
                .resolveAll(BigDecimalValueCucumberTransformerResolver.class, bigDecimalValueCondition)
                .forEach(bigDecimalValue::addTransformer);
        return bigDecimalValue;
    }

}
