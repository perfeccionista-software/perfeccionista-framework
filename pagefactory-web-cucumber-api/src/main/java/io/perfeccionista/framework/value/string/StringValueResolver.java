package io.perfeccionista.framework.value.string;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.StringValueConditionNotResolved;
import io.perfeccionista.framework.value.checker.string.resolver.StringValueCheckerCucumberResolver;
import io.perfeccionista.framework.value.transformer.string.resolver.StringValueCucumberTransformerResolver;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.STRING_VALUE_CONDITION_NOT_RESOLVED;
import static io.perfeccionista.framework.value.Values.stringEquals;

public class StringValueResolver {
    public static final Pattern STRING_VALUE_RESOLVER_PATTERN = Pattern.compile("^\\[(?<condition>.*?)]");

    private final Environment environment;
    private final String rawStringValue;

    protected String stringValueCondition = null;
    protected String stringValue = null;

    public StringValueResolver(Environment environment, String rawStringValue) {
        this.environment = environment;
        this.rawStringValue = rawStringValue;
        process(rawStringValue);
    }

    public @NotNull StringValue getStringValue() {
        return resolveStringValueTransformers(resolveStringValueCondition());
    }

    protected void process(String stringValueToResolve) {
        Matcher matcher = STRING_VALUE_RESOLVER_PATTERN.matcher(stringValueToResolve);
        if (matcher.find()) {
            this.stringValueCondition = matcher.group("condition");
            this.stringValue = stringValueToResolve.replaceAll(STRING_VALUE_RESOLVER_PATTERN.pattern(), "");
            return;
        }
        this.stringValue = stringValueToResolve;
    }

    protected StringValue resolveStringValueCondition() {
        if (Objects.isNull(stringValueCondition)) {
            return stringEquals(stringValue);
        }
        return environment.getService(CucumberService.class)
                .resolveFirst(StringValueCheckerCucumberResolver.class, stringValueCondition, stringValue)
                .orElseThrow(() -> StringValueConditionNotResolved.exception(STRING_VALUE_CONDITION_NOT_RESOLVED.getMessage(rawStringValue)));
    }

    protected @NotNull StringValue resolveStringValueTransformers(StringValue stringValue) {
        if (Objects.isNull(stringValueCondition)) {
            return stringValue;
        }
        environment.getService(CucumberService.class)
                .resolveAll(StringValueCucumberTransformerResolver.class, stringValueCondition)
                .forEach(stringValue::addTransformer);
        return stringValue;
    }

}
