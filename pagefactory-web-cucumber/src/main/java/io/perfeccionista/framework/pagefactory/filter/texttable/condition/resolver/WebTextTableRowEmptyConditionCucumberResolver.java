package io.perfeccionista.framework.pagefactory.filter.texttable.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.Web.allTextRows;

@CucumberResolverExpression({"without filter", "no filter", "all"})
@CucumberResolverExpression({"без фильтра", "нет фильтра", "все"})
public class WebTextTableRowEmptyConditionCucumberResolver extends AbstractWebTextTableRowConditionCucumberResolver {

    @Override
    public Optional<WebTextTableRowCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                return Optional.of(allTextRows());
            }
        }
        return Optional.empty();
    }

}
