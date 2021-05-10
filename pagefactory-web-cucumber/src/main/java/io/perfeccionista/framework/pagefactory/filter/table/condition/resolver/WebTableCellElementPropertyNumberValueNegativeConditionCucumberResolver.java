package io.perfeccionista.framework.pagefactory.filter.table.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import io.perfeccionista.framework.value.number.BigDecimalValueResolver;
import io.perfeccionista.framework.value.number.IntegerValueResolver;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.Web.*;
import static java.util.Objects.isNull;

@CucumberResolverExpression("in column {columnName} property {propertyName} of element {webElement} does not contain number {numberValue}")
@CucumberResolverExpression("в столбце {columnName} свойство {propertyName} элемента {webElement} не содержит число {numberValue}")
public class WebTableCellElementPropertyNumberValueNegativeConditionCucumberResolver extends AbstractWebTableRowConditionCucumberResolver {

    @Override
    public Optional<WebTableRowCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String columnName = matcher.group("columnName");
                String propertyName = matcher.group("propertyName");
                String elementPath = matcher.group("webElement");
                String numberValue = matcher.group("numberValue");
                if (isNull(columnName) || isNull(propertyName) || isNull(elementPath) || isNull(numberValue)) {
                    return Optional.empty();
                }
                if (numberValue.contains(".")) {
                    NumberValue<BigDecimal> resolvedNumberValue = new BigDecimalValueResolver(environment, numberValue).getBigDecimalValue();
                    return Optional.of(notContainProperty(columnName, elementPath, propertyName, resolvedNumberValue));
                }
                NumberValue<Integer> resolvedNumberValue = new IntegerValueResolver(environment, numberValue).getIntegerValue();
                return Optional.of(notContainProperty(columnName, elementPath, propertyName, resolvedNumberValue));
            }
        }
        return Optional.empty();
    }

}
