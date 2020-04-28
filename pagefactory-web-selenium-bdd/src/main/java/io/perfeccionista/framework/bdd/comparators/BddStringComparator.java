package io.perfeccionista.framework.bdd.comparators;

import java.util.Comparator;

public class BddStringComparator implements BddValueComparator {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

    @Override
    public Comparator<String> reversed() {
        return Comparator.reverseOrder();
    }

}
