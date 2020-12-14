package io.perfeccionista.framework.value.checker.number.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@CucumberResolverExpression({"greater", "greater than"})
@CucumberResolverExpression({"больше", "больше чем"})
public class IntegerGreaterValueCheckerCucumberResolver extends AbstractIntegerCheckerCucumberResolver {

    @Override
    public Optional<NumberValue<Integer>> tryResolve(@NotNull String numberValueCondition, @Nullable Object... args) {
        for (String pattern : patterns) {
            if (numberValueCondition.contains(pattern)) {
                return Optional.of(environment.getService(ValueService.class)
                        .intGreaterThan(extractFirstNotNullIntegerArgument(args)));
            }
        }
        return Optional.empty();
    }

}
