package io.perfeccionista.framework.pagefactory.filter.texttable.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;
import io.perfeccionista.framework.value.number.IntegerValueResolver;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.Web.*;
import static java.util.Objects.isNull;

@CucumberResolverExpression("index {integerValue}")
@CucumberResolverExpression("индекс {integerValue}")
public class WebTextTableRowIndexConditionCucumberResolver extends AbstractWebTextTableRowConditionCucumberResolver {

    @Override
    public Optional<WebTextTableRowCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String integerValue = matcher.group("integerValue");
                if (isNull(integerValue)) {
                    return Optional.empty();
                }
                NumberValue<Integer> resolvedIntegerValue = new IntegerValueResolver(environment, integerValue).getIntegerValue();
                return Optional.of(textRowIndex(resolvedIntegerValue));
            }
        }
        return Optional.empty();
    }

}
