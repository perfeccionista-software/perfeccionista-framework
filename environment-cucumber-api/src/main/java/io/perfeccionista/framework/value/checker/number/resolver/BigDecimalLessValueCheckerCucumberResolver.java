package io.perfeccionista.framework.value.checker.number.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Optional;

@CucumberResolverExpression({"less", "less than"})
@CucumberResolverExpression({"меньше", "меньше чем"})
public class BigDecimalLessValueCheckerCucumberResolver extends AbstractBigDecimalCheckerCucumberResolver {

    @Override
    public Optional<NumberValue<BigDecimal>> tryResolve(@NotNull String numberValueCondition, @Nullable Object... args) {
        for (String pattern : patterns) {
            if (numberValueCondition.contains(pattern)) {
                return Optional.of(environment.getService(ValueService.class)
                        .bigDecimalLessThan(extractFirstNotNullBigDecimalArgument(args)));
            }
        }
        return Optional.empty();
    }

}