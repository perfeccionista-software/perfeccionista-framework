package io.perfeccionista.framework.pagefactory.filter.table.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notSelected;
import static java.util.Objects.isNull;

@CucumberResolverExpression("in column {columnName} {webElement} is not selected")
@CucumberResolverExpression("в столбце {columnName} {webElement} не выделен")
public class WebTableCellElementSelectedNegativeConditionCucumberResolver extends AbstractWebTableRowConditionCucumberResolver {

    @Override
    public Optional<WebTableRowCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String columnName = matcher.group("columnName");
                String elementPath = matcher.group("webElement");
                if (isNull(columnName) || isNull(elementPath)) {
                    return Optional.empty();
                }
                return Optional.of(notSelected(columnName, elementPath));
            }
        }
        return Optional.empty();
    }

}