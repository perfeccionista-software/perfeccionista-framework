package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.comparators.string.StringValueComparator;
import io.perfeccionista.framework.comparators.SortDirection;

public class StringComparatorTypeParameterImpl implements StringComparatorTypeParameter {

    private final Environment environment;
    private final String rawInput;

    public StringComparatorTypeParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public StringValueComparator findComparatorForDirection(SortDirection sortDirection) {
        return null;
    }

    @Override
    public String getRaw() {
        return null;
    }
}
