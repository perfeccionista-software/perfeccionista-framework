package io.perfeccionista.framework.pagefactory.filter.textlist.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition;
import io.perfeccionista.framework.value.string.StringValue;
import io.perfeccionista.framework.value.string.StringValueResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsTextBlock;
import static java.util.Objects.isNull;

@CucumberResolverExpression("text is {stringValue}")
@CucumberResolverExpression("содержится {stringValue}")
public class WebTextListBlockTextStringValueConditionCucumberResolver extends AbstractWebTextListBlockConditionCucumberResolver {

    @Override
    public Optional<WebTextListBlockCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String stringValue = matcher.group("stringValue");
                if (isNull(stringValue)) {
                    return Optional.empty();
                }
                StringValue resolvedStringValue = new StringValueResolver(environment, stringValue).getStringValue();
                return Optional.of(containsTextBlock(resolvedStringValue));
            }
        }
        return Optional.empty();
    }

}