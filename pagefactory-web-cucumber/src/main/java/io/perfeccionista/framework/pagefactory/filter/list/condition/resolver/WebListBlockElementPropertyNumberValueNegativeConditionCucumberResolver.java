package io.perfeccionista.framework.pagefactory.filter.list.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.WebFilterConditions;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import io.perfeccionista.framework.value.number.BigDecimalValueResolver;
import io.perfeccionista.framework.value.number.IntegerValueResolver;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notContainProperty;
import static java.util.Objects.isNull;

@CucumberResolverExpression("property {property} of element {webElement} does not contain number {numberValue}")
@CucumberResolverExpression("свойство {property} элемента {webElement} не содержит число {numberValue}")
public class WebListBlockElementPropertyNumberValueNegativeConditionCucumberResolver extends AbstractWebListBlockConditionCucumberResolver {

    @Override
    public Optional<WebListBlockCondition> tryResolve(@NotNull String expression, @Nullable Object[] args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String property = matcher.group("property");
                String elementPath = matcher.group("webElement");
                String numberValue = matcher.group("numberValue");
                if (isNull(property) || isNull(elementPath) || isNull(numberValue)) {
                    return Optional.empty();
                }
                if (numberValue.contains(".")) {
                    NumberValue<BigDecimal> resolvedNumberValue = new BigDecimalValueResolver(environment, numberValue).getBigDecimalValue();
                    return Optional.of(WebFilterConditions.notContainProperty(elementPath, property, resolvedNumberValue));
                }
                NumberValue<Integer> resolvedNumberValue = new IntegerValueResolver(environment, numberValue).getIntegerValue();
                return Optional.of(WebFilterConditions.notContainProperty(elementPath, property, resolvedNumberValue));
            }
        }
        return Optional.empty();
    }

}
