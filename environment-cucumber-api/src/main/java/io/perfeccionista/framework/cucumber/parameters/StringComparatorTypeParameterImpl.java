package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.comparators.string.StringValueComparator;
import io.perfeccionista.framework.comparators.SortDirection;
import io.perfeccionista.framework.comparators.string.StringValueComparatorCucumberResolver;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.StringValueComparatorFormatNotResolved;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentCucumberApiMessages.STRING_VALUE_COMPARATOR_FORMAT_NOT_RESOLVED;

public class StringComparatorTypeParameterImpl implements StringComparatorTypeParameter {

    private final Environment environment;
    private final String rawInput;

    public StringComparatorTypeParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull StringValueComparator findComparatorForDirection(SortDirection sortDirection) {
        return environment.getService(CucumberService.class)
                .resolveFirst(StringValueComparatorCucumberResolver.class, rawInput)
                .orElseThrow(() -> StringValueComparatorFormatNotResolved.exception(STRING_VALUE_COMPARATOR_FORMAT_NOT_RESOLVED.getMessage(rawInput)));
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
