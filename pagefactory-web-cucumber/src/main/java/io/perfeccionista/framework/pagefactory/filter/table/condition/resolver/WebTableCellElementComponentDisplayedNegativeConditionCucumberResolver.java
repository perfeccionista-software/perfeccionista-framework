package io.perfeccionista.framework.pagefactory.filter.table.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.componentNotDisplayed;
import static java.util.Objects.isNull;

@CucumberResolverExpression("in column {columnName} component {component} of element {webElement} is not displayed")
@CucumberResolverExpression("в столбце {columnName} компонент {component} элемента {webElement} не отображается")
public class WebTableCellElementComponentDisplayedNegativeConditionCucumberResolver extends AbstractWebTableRowConditionCucumberResolver {

    @Override
    public Optional<WebTableRowCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String columnName = matcher.group("columnName");
                String component = matcher.group("component");
                String elementPath = matcher.group("webElement");
                if (isNull(columnName) || isNull(component) || isNull(elementPath)) {
                    return Optional.empty();
                }
                return Optional.of(componentNotDisplayed(columnName, elementPath, component));
            }
        }
        return Optional.empty();
    }

}
