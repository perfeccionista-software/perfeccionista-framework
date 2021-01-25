package io.perfeccionista.framework.pagefactory.filter.radio.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import io.perfeccionista.framework.value.number.BigDecimalValueResolver;
import io.perfeccionista.framework.value.number.IntegerValueResolver;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.Web.containsLabel;
import static java.util.Objects.isNull;

@CucumberResolverExpression("label contains number {numberValue}")
@CucumberResolverExpression("лейбл содержит число {numberValue}")
public class WebRadioButtonLabelNumberValueConditionCucumberResolver extends AbstractWebRadioButtonConditionCucumberResolver {

    @Override
    public Optional<WebRadioButtonCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String numberValue = matcher.group("numberValue");
                if (isNull(numberValue)) {
                    return Optional.empty();
                }
                if (numberValue.contains(".")) {
                    NumberValue<BigDecimal> resolvedNumberValue = new BigDecimalValueResolver(environment, numberValue).getBigDecimalValue();
                    return Optional.of(containsLabel(resolvedNumberValue));
                }
                NumberValue<Integer> resolvedNumberValue = new IntegerValueResolver(environment, numberValue).getIntegerValue();
                return Optional.of(containsLabel(resolvedNumberValue));
            }
        }
        return Optional.empty();
    }

}
