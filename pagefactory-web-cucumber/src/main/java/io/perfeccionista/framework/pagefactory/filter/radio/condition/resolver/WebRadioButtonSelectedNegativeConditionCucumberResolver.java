package io.perfeccionista.framework.pagefactory.filter.radio.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.Web.notSelected;

@CucumberResolverExpression("button is not selected")
@CucumberResolverExpression("кнопка не выделена")
public class WebRadioButtonSelectedNegativeConditionCucumberResolver extends AbstractWebRadioButtonConditionCucumberResolver {

    @Override
    public Optional<WebRadioButtonCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                return Optional.of(notSelected());
            }
        }
        return Optional.empty();
    }

}
