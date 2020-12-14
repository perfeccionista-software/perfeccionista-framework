package io.perfeccionista.framework.cucumber.comparators.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.comparators.string.StringDateComparator;
import io.perfeccionista.framework.comparators.string.StringDateTimeComparator;
import io.perfeccionista.framework.comparators.string.StringNumberComparator;
import io.perfeccionista.framework.comparators.string.StringStringComparator;
import io.perfeccionista.framework.comparators.string.StringTimeComparator;
import io.perfeccionista.framework.comparators.string.StringValueComparator;
import io.perfeccionista.framework.cucumber.parameters.DateTimeFormatParameter;

public class WebStringValueComparators {

    @Given("text")
    @Given("текст")
    public StringValueComparator stringFormat() {
        return new StringStringComparator();
    }

    @Given("number")
    @Given("число")
    public StringValueComparator numberFormat() {
        return new StringNumberComparator();
    }

    @Given("date")
    @Given("дата")
    public StringValueComparator dateFormat() {
        return new StringDateComparator();
    }

    @Given("date{dateTimeFormat}")
    @Given("дата{dateTimeFormat}")
    public StringValueComparator customDateFormat(DateTimeFormatParameter dateTimeFormat) {
        return new StringDateComparator(dateTimeFormat.getFormatter());
    }

    @Given("time")
    @Given("время")
    public StringValueComparator timeFormat() {
        return new StringTimeComparator();
    }

    @Given("time{dateTimeFormat}")
    @Given("время{dateTimeFormat}")
    public StringValueComparator customTimeFormat(DateTimeFormatParameter dateTimeFormat) {
        return new StringTimeComparator(dateTimeFormat.getFormatter());
    }

    @Given("date and time")
    @Given("дата и время")
    public StringValueComparator dateTimeFormat() {
        return new StringDateTimeComparator();
    }

    @Given("date and time{dateTimeFormat}")
    @Given("дата и время{dateTimeFormat}")
    public StringValueComparator customDateTimeFormat(DateTimeFormatParameter dateTimeFormat) {
        return new StringDateTimeComparator(dateTimeFormat.getFormatter());
    }

}
