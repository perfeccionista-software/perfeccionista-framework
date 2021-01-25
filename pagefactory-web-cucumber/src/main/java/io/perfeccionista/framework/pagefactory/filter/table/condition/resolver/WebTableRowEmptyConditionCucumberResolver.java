package io.perfeccionista.framework.pagefactory.filter.table.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.Web.allRows;

@CucumberResolverExpression({"without filter", "no filter", "all"})
@CucumberResolverExpression({"без фильтра", "нет фильтра", "все"})
public class WebTableRowEmptyConditionCucumberResolver extends AbstractWebTableRowConditionCucumberResolver {

    @Override
    public Optional<WebTableRowCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                return Optional.of(allRows());
            }
        }
        return Optional.empty();
    }

}
