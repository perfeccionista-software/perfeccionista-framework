package io.perfeccionista.framework.pagefactory.filter.list.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.componentDisplayed;
import static java.util.Objects.isNull;

@CucumberResolverExpression("component {component} of element {webElement} is displayed")
@CucumberResolverExpression("компонент {component} элемента {webElement} отображается")
public class WebListBlockElementComponentDisplayedConditionCucumberResolver extends AbstractWebListBlockConditionCucumberResolver {

    @Override
    public Optional<WebListBlockCondition> tryResolve(@NotNull String expression, @Nullable Object[] args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String component = matcher.group("component");
                String elementPath = matcher.group("webElement");
                if (isNull(component) || isNull(elementPath)) {
                    return Optional.empty();
                }
                return Optional.of(componentDisplayed(elementPath, component));
            }
        }
        return Optional.empty();
    }

}

