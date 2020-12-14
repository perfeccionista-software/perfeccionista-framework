package io.perfeccionista.framework.comparators.string;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class StringDateTimeComparator implements StringValueComparator {

    protected final DateTimeFormatter defaultFormatter;

    public StringDateTimeComparator() {
        this.defaultFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    public StringDateTimeComparator(DateTimeFormatter defaultFormatter) {
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
