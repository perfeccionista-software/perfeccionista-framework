package io.perfeccionista.framework.value.checker.string.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolverPriority;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@CucumberResolverPriority(100)
@CucumberResolverExpression("starts with")
@CucumberResolverExpression("начинается с")
public class StringStartsWithValueCheckerCucumberResolver extends AbstractStringCheckerCucumberResolver {

    @Override
    public Optional<StringValue> tryResolve(@NotNull String stringValueCondition, @Nullable Object... args) {
        for (String pattern : patterns) {
            if (stringValueCondition.contains(pattern)) {
                return Optional.of(environment.getService(ValueService.class)
                        .stringStartsWith(extractFirstNotNullStringArgument(args)));
            }
        }
        return Optional.empty();
    }

}