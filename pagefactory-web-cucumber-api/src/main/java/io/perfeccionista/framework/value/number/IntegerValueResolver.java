package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.IntegerValueConditionNotResolved;
import io.perfeccionista.framework.value.checker.number.resolver.IntegerValueCheckerCucumberResolver;
import io.perfeccionista.framework.value.transformer.number.resolver.IntegerValueCucumberTransformerResolver;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.INTEGER_VALUE_CONDITION_NOT_RESOLVED;
import static io.perfeccionista.framework.value.Values.intEquals;

public class IntegerValueResolver {
    public static final Pattern STRING_VALUE_RESOLVER_PATTERN = Pattern.compile("^\\[(?<condition>.*?)]");

    private final Environment environment;
    private final String rawStringValue;

    protected String integerValueCondition = null;
    protected Integer integerValue = null;

    public IntegerValueResolver(Environment environment, String rawStringValue) {
        this.environment = environment;
        this.rawStringValue = rawStringValue;
        process(rawStringValue);
    }

    public @NotNull NumberValue<Integer> getIntegerValue() {
        return resolveIntegerValueTransformers(resolveIntegerValueCondition());
    }

    protected void process(String stringValueToResolve) {
        Matcher matcher = STRING_VALUE_RESOLVER_PATTERN.matcher(stringValueToResolve);
        if (matcher.find()) {
            this.integerValueCondition = matcher.group("condition");
            this.integerValue = Integer.parseInt(stringValueToResolve.replaceAll(STRING_VALUE_RESOLVER_PATTERN.pattern(), ""));
            return;
        }
        this.integerValue = Integer.parseInt(stringValueToResolve);
    }

    protected NumberValue<Integer> resolveIntegerValueCondition() {
        if (Objects.isNull(integerValueCondition)) {
            return intEquals(integerValue);
        }
        return environment.getService(CucumberService.class)
                .resolveFirst(IntegerValueCheckerCucumberResolver.class, integerValueCondition, integerValue)
                .orElseThrow(() -> IntegerValueConditionNotResolved.exception(INTEGER_VALUE_CONDITION_NOT_RESOLVED.getMessage(rawStringValue)));
    }

    protected @NotNull NumberValue<Integer> resolveIntegerValueTransformers(NumberValue<Integer> integerValue) {
        if (Objects.isNull(integerValueCondition)) {
            return integerValue;
        }
        environment.getService(CucumberService.class)
                .resolveAll(IntegerValueCucumberTransformerResolver.class, integerValueCondition)
                .forEach(integerValue::addTransformer);
        return integerValue;
    }

}
