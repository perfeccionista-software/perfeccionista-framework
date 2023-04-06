package io.perfeccionista.framework.pagefactory.filter.list.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.Web.componentNotPresent;
import static java.util.Objects.isNull;

@CucumberResolverExpression("component {component} of element {webElement} is not present")
@CucumberResolverExpression("компонент {component} элемента {webElement} отсутствует")
public class WebListBlockElementComponentPresentNegativeConditionCucumberResolver extends AbstractWebListBlockConditionCucumberResolver {

    @Override
    public Optional<WebItemCondition> tryResolve(@NotNull String expression, @Nullable Object[] args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String component = matcher.group("component");
                String elementPath = matcher.group("webElement");
                if (isNull(component) || isNull(elementPath)) {
                    return Optional.empty();
                }
                return Optional.of(componentNotPresent(elementPath, component));
            }
        }
        return Optional.empty();
    }

}
