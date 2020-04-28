package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.bdd.comparators.BddValueComparator;
import io.perfeccionista.framework.bdd.comparators.SortDirection;

public class StringComparatorTypeParameterImpl implements StringComparatorTypeParameter {

    private final Environment environment;
    private final String rawInput;

    public StringComparatorTypeParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public BddValueComparator findComparatorForDirection(SortDirection sortDirection) {
        return null;
    }

    @Override
    public String getRaw() {
        return null;
    }
}
