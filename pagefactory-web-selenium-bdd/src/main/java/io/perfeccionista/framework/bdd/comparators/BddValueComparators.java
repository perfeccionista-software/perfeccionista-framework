package io.perfeccionista.framework.bdd.comparators;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.DateTimeFormatParameter;

public class BddValueComparators {

    @Given("text")
    @Given("текст")
    public BddValueComparator stringFormat() {
        return new BddStringComparator();
    }

    @Given("number")
    @Given("число")
    public BddValueComparator numberFormat() {
        return new BddNumberComparator();
    }

    @Given("date")
    @Given("дата")
    public BddValueComparator dateFormat() {
        return new BddDateComparator();
    }

    @Given("date{dateTimeFormat}")
    @Given("дата{dateTimeFormat}")
    public BddValueComparator customDateFormat(DateTimeFormatParameter dateTimeFormat) {
        return new BddDateComparator(dateTimeFormat.getFormatter());
    }

    @Given("time")
    @Given("время")
    public BddValueComparator timeFormat() {
        return new BddTimeComparator();
    }

    @Given("time{dateTimeFormat}")
    @Given("время{dateTimeFormat}")
    public BddValueComparator customTimeFormat(DateTimeFormatParameter dateTimeFormat) {
        return new BddTimeComparator(dateTimeFormat.getFormatter());
    }

    @Given("date and time")
    @Given("дата и время")
    public BddValueComparator dateTimeFormat() {
        return new BddDateTimeComparator();
    }

    @Given("date and time{dateTimeFormat}")
    @Given("дата и время{dateTimeFormat}")
    public BddValueComparator customDateTimeFormat(DateTimeFormatParameter dateTimeFormat) {
        return new BddDateTimeComparator(dateTimeFormat.getFormatter());
    }

}
