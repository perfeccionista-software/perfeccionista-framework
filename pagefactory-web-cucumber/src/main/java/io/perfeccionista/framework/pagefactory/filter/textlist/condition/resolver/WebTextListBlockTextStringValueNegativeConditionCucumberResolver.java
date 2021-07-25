package io.perfeccionista.framework.pagefactory.filter.textlist.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockCondition;
import io.perfeccionista.framework.value.string.StringValue;
import io.perfeccionista.framework.value.string.StringValueResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.Web.notContainTextBlock;
import static java.util.Objects.isNull;

@CucumberResolverExpression("text is not {stringValue}")
@CucumberResolverExpression("не содержится {stringValue}")
public class WebTextListBlockTextStringValueNegativeConditionCucumberResolver extends AbstractWebTextListBlockConditionCucumberResolver {

    @Override
    public Optional<WebTextBlockCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String stringValue = matcher.group("stringValue");
                if (isNull(stringValue)) {
                    return Optional.empty();
                }
                StringValue resolvedStringValue = new StringValueResolver(environment, stringValue).getStringValue();
                return Optional.of(notContainTextBlock(resolvedStringValue));
            }
        }
        return Optional.empty();
    }

}
