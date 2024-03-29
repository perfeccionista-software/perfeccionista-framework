package io.perfeccionista.framework.pagefactory.filter.radio.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CucumberResolverExpression({"without filter", "no filter", "all"})
@CucumberResolverExpression({"без фильтра", "нет фильтра", "все"})
public class WebRadioButtonEmptyConditionCucumberResolver extends AbstractWebRadioButtonConditionCucumberResolver {

//    @Override
//    public Optional<WebRadioButtonCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
//        for (Pattern pattern : patterns) {
//            Matcher matcher = pattern.matcher(expression);
//            if (matcher.find()) {
//                return Optional.of(allRadioButtons());
//            }
//        }
//        return Optional.empty();
//    }

}
