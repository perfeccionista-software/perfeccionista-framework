package io.perfeccionista.framework.bdd.comparators;

import java.math.BigDecimal;
import java.util.Comparator;

public class BddNumberComparator implements BddValueComparator {

    @Override
    public int compare(String o1, String o2) {
        return convert(o1).compareTo(convert(o2));
    }

    @Override
    public Comparator<String> reversed() {
        return (o1, o2) -> convert(o2).compareTo(convert(o1));
    }

    protected BigDecimal convert(String value) {
        return new BigDecimal(value);
    }

}
