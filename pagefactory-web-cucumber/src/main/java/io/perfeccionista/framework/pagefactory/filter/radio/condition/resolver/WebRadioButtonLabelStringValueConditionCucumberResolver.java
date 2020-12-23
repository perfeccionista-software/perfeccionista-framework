package io.perfeccionista.framework.pagefactory.filter.radio.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import io.perfeccionista.framework.value.number.IntegerValueResolver;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import io.perfeccionista.framework.value.string.StringValueResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsLabel;
import static java.util.Objects.isNull;

@CucumberResolverExpression("label contains {stringValue}")
@CucumberResolverExpression("лейбл содержит {stringValue}")
public class WebRadioButtonLabelStringValueConditionCucumberResolver extends AbstractWebRadioButtonConditionCucumberResolver {

    @Override
    public Optional<WebRadioButtonCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String stringValue = matcher.group("stringValue");
                if (isNull(stringValue)) {
                    return Optional.empty();
                }
                StringValue resolvedStringValue = new StringValueResolver(environment, stringValue).getStringValue();
                return Optional.of(containsLabel(resolvedStringValue));
            }
        }
        return Optional.empty();
    }

}