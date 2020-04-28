package io.perfeccionista.framework.bdd.comparators;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class BddTimeComparator implements BddValueComparator {

    protected final DateTimeFormatter defaultFormatter;

    public BddTimeComparator() {
        this.defaultFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
    }

    public BddTimeComparator(DateTimeFormatter defaultFormatter) {
        this.defaultFormatter = defaultFormatter;
    }

    @Override
    public int compare(String o1, String o2) {
        return convert(o1).compareTo(convert(o2));
    }

    @Override
    public Comparator<String> reversed() {
        return (o1, o2) -> convert(o2).compareTo(convert(o1));
    }

    protected LocalTime convert(String value) {
        return LocalTime.parse(value, defaultFormatter);
    }

}
