package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.bdd.comparators.SortDirection;

import java.util.Comparator;

public class StringComparatorTypeParameterImpl implements StringComparatorTypeParameter {

    private final Environment environment;
    private final String rawInput;

    public StringComparatorTypeParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public Comparator<String> findForDirection(SortDirection sortDirection) {
        return null;
    }

    @Override
    public String getRaw() {
        return null;
    }
}
