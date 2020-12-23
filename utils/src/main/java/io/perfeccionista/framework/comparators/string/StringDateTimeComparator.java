package io.perfeccionista.framework.comparators.string;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class StringDateTimeComparator implements StringValueComparator {

    protected final DateTimeFormatter defaultFormatter;

    private StringDateTimeComparator() {
        this.defaultFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    private StringDateTimeComparator(DateTimeFormatter defaultFormatter) {
        this.defaultFormatter = defaultFormatter;
    }

    public static StringDateTimeComparator defaultFormat() {
        return new StringDateTimeComparator();
    }

    public static StringDateTimeComparator format(@NotNull DateTimeFormatter defaultFormatter) {
        return new StringDateTimeComparator(defaultFormatter);
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
