package io.perfeccionista.framework.pagefactory.filter.radio.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.value.string.StringValue;
import io.perfeccionista.framework.value.string.StringValueResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.Web.notContainLabel;
import static java.util.Objects.isNull;

@CucumberResolverExpression("label does not contain {stringValue}")
@CucumberResolverExpression("лейбл не содержит {stringValue}")
public class WebRadioButtonLabelStringValueNegativeConditionCucumberResolver extends AbstractWebRadioButtonConditionCucumberResolver {

//    @Override
//    public Optional<WebRadioButtonCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
//        for (Pattern pattern : patterns) {
//            Matcher matcher = pattern.matcher(expression);
//            if (matcher.find()) {
//                String stringValue = matcher.group("stringValue");
//                if (isNull(stringValue)) {
//                    return Optional.empty();
//                }
//                StringValue resolvedStringValue = new StringValueResolver(environment, stringValue).getStringValue();
//                return Optional.of(notContainLabel(resolvedStringValue));
//            }
//        }
//        return Optional.empty();
//    }

}
