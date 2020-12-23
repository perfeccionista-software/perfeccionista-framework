package io.perfeccionista.framework.comparators.string;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class StringDateComparator implements StringValueComparator {

    protected final DateTimeFormatter defaultFormatter;

    private StringDateComparator() {
        this.defaultFormatter  = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    private StringDateComparator(DateTimeFormatter defaultFormatter) {
        this.defaultFormatter = defaultFormatter;
    }

    public static StringDateComparator defaultFormat() {
        return new StringDateComparator();
    }

    public static StringDateComparator format(@NotNull DateTimeFormatter defaultFormatter) {
        return new StringDateComparator(defaultFormatter);
    }

    @Override
    public int compare(String o1, String o2) {
        return convert(o1).compareTo(convert(o2));
    }

    @Override
    public Comparator<String> reversed() {
        return (o1, o2) -> convert(o2).compareTo(convert(o1));
    }

    protected LocalDate convert(String value) {
        return LocalDate.parse(value, defaultFormatter);
    }

}
