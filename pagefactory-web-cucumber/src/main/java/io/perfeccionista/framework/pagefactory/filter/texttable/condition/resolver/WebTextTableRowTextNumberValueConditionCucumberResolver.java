package io.perfeccionista.framework.pagefactory.filter.texttable.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;
import io.perfeccionista.framework.value.number.BigDecimalValueResolver;
import io.perfeccionista.framework.value.number.IntegerValueResolver;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsTextCell;
import static java.util.Objects.isNull;

@CucumberResolverExpression("in column {columnName} number is {numberValue}")
@CucumberResolverExpression("в столбце {columnName} содержится число {numberValue}")
public class WebTextTableRowTextNumberValueConditionCucumberResolver extends AbstractWebTextTableRowConditionCucumberResolver {

    @Override
    public Optional<WebTextTableRowCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String columnName = matcher.group("columnName");
                String numberValue = matcher.group("numberValue");
                if (isNull(columnName) || isNull(numberValue)) {
                    return Optional.empty();
                }
                if (numberValue.contains(".")) {
                    NumberValue<BigDecimal> resolvedNumberValue = new BigDecimalValueResolver(environment, numberValue).getBigDecimalValue();
                    return Optional.of(containsTextCell(columnName, resolvedNumberValue));
                }
                NumberValue<Integer> resolvedNumberValue = new IntegerValueResolver(environment, numberValue).getIntegerValue();
                return Optional.of(containsTextCell(columnName, resolvedNumberValue));
            }
        }
        return Optional.empty();
    }

}