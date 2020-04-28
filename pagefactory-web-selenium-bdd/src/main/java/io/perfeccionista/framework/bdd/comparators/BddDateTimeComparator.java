package io.perfeccionista.framework.bdd.comparators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class BddDateTimeComparator implements BddValueComparator {

    protected final DateTimeFormatter defaultFormatter;

    public BddDateTimeComparator() {
        this.defaultFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    public BddDateTimeComparator(DateTimeFormatter defaultFormatter) {
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

    protected LocalDateTime convert(String value) {
        return LocalDateTime.parse(value, defaultFormatter);
    }

}
