package io.perfeccionista.framework.comparators.string;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class StringTimeComparator implements StringValueComparator {

    protected final DateTimeFormatter defaultFormatter;

    public StringTimeComparator() {
        this.defaultFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
    }

    public StringTimeComparator(DateTimeFormatter defaultFormatter) {
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
