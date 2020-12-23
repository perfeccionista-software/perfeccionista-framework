package io.perfeccionista.framework.pagefactory.filter.table.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import io.perfeccionista.framework.value.string.StringValue;
import io.perfeccionista.framework.value.string.StringValueResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notContainProperty;
import static java.util.Objects.isNull;

@CucumberResolverExpression("in column {columnName} property {propertyName} of element {webElement} does not contain {stringValue}")
@CucumberResolverExpression("в столбце {columnName} свойство {propertyName} элемента {webElement} не содержит {stringValue}")
public class WebTableCellElementPropertyStringValueNegativeConditionCucumberResolver extends AbstractWebTableRowConditionCucumberResolver {

    @Override
    public Optional<WebTableRowCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String columnName = matcher.group("columnName");
                String propertyName = matcher.group("propertyName");
                String elementPath = matcher.group("webElement");
                String stringValue = matcher.group("stringValue");
                if (isNull(columnName) || isNull(propertyName) || isNull(elementPath) || isNull(stringValue)) {
                    return Optional.empty();
                }
                StringValue resolvedStringValue = new StringValueResolver(environment, stringValue).getStringValue();
                return Optional.of(notContainProperty(columnName, elementPath, propertyName, resolvedStringValue));
            }
        }
        return Optional.empty();
    }

}