package io.perfeccionista.framework.pagefactory.filter.texttable.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;
import io.perfeccionista.framework.value.string.StringValue;
import io.perfeccionista.framework.value.string.StringValueResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.Web.*;
import static java.util.Objects.isNull;

@CucumberResolverExpression("in column {columnName} text is {stringValue}")
@CucumberResolverExpression("в столбце {columnName} содержится {stringValue}")
public class WebTextTableRowTextStringValueConditionCucumberResolver extends AbstractWebTextTableRowConditionCucumberResolver {

    @Override
    public Optional<WebTextTableRowCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String columnName = matcher.group("columnName");
                String stringValue = matcher.group("stringValue");
                if (isNull(columnName) || isNull(stringValue)) {
                    return Optional.empty();
                }
                StringValue resolvedStringValue = new StringValueResolver(environment, stringValue).getStringValue();
                return Optional.of(containsTextCell(columnName, resolvedStringValue));
            }
        }
        return Optional.empty();
    }

}
