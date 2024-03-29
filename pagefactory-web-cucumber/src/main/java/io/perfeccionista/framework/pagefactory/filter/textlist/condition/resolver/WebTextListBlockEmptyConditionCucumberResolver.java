package io.perfeccionista.framework.pagefactory.filter.textlist.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@CucumberResolverExpression({"without filter", "no filter", "all"})
@CucumberResolverExpression({"без фильтра", "нет фильтра", "все"})
public class WebTextListBlockEmptyConditionCucumberResolver extends AbstractWebTextListBlockConditionCucumberResolver {

//    @Override
//    public Optional<WebTextBlockCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
//        for (Pattern pattern : patterns) {
//            Matcher matcher = pattern.matcher(expression);
//            if (matcher.find()) {
//                return Optional.of(allTextBlocks());
//            }
//        }
//        return Optional.empty();
//    }

}
