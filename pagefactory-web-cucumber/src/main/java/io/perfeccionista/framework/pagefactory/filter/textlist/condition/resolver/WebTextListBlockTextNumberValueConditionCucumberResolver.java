package io.perfeccionista.framework.pagefactory.filter.textlist.condition.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.value.number.BigDecimalValueResolver;
import io.perfeccionista.framework.value.number.IntegerValueResolver;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.Web.*;
import static java.util.Objects.isNull;

@CucumberResolverExpression("number is {numberValue}")
@CucumberResolverExpression("содержится число {numberValue}")
public class WebTextListBlockTextNumberValueConditionCucumberResolver extends AbstractWebTextListBlockConditionCucumberResolver {

//    @Override
//    public Optional<WebTextBlockCondition> tryResolve(@NotNull String expression, @Nullable Object... args) {
//        for (Pattern pattern : patterns) {
//            Matcher matcher = pattern.matcher(expression);
//            if (matcher.find()) {
//                String numberValue = matcher.group("numberValue");
//                if (isNull(numberValue)) {
//                    return Optional.empty();
//                }
//                if (numberValue.contains(".")) {
//                    NumberValue<BigDecimal> resolvedNumberValue = new BigDecimalValueResolver(environment, numberValue).getBigDecimalValue();
//                    return Optional.of(containsTextBlock(resolvedNumberValue));
//                }
//                NumberValue<Integer> resolvedNumberValue = new IntegerValueResolver(environment, numberValue).getIntegerValue();
//                return Optional.of(containsTextBlock(resolvedNumberValue));
//            }
//        }
//        return Optional.empty();
//    }

}
