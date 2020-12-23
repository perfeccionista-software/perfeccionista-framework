package io.perfeccionista.framework.comparators.string;

import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class StringTimeComparator implements StringValueComparator {

    protected final DateTimeFormatter defaultFormatter;

    private StringTimeComparator() {
        this.defaultFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
    }

    private StringTimeComparator(DateTimeFormatter defaultFormatter) {
        this.defaultFormatter = defaultFormatter;
    }

    public static StringTimeComparator defaultFormat() {
        return new StringTimeComparator();
    }

    public static StringTimeComparator format(@NotNull DateTimeFormatter defaultFormatter) {
        return new StringTimeComparator(defaultFormatter);
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
