package io.perfeccionista.framework.comparators.string;

import java.util.Comparator;

public class StringTextComparator implements StringValueComparator {

    private StringTextComparator() {
    }

    public static StringTextComparator defaultFormat() {
        return new StringTextComparator();
    }

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

    @Override
    public Comparator<String> reversed() {
        return Comparator.reverseOrder();
    }

}
