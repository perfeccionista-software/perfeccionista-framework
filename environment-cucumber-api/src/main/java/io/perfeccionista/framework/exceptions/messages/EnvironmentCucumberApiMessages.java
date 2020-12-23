package io.perfeccionista.framework.exceptions.messages;

public enum EnvironmentCucumberApiMessages implements Messages {

    COLOR_FORMAT_NOT_RESOLVED("Color format '%s' is not resolved"),
    DIMENSIONS_FORMAT_NOT_RESOLVED("Dimensions format '%s' is not resolved"),
    DURATION_FORMAT_NOT_RESOLVED("Duration format '%s' is not resolved"),
    LOCATION_FORMAT_NOT_RESOLVED("Incorrect Location format %s"),
    SCREENSHOT_FORMAT_NOT_RESOLVED("Incorrect Screenshot format %s"),
    SORT_DIRECTION_FORMAT_NOT_RESOLVED("Incorrect SortDirection format %s"),
    STRING_VALUE_COMPARATOR_FORMAT_NOT_RESOLVED("Incorrect StringValueComparator format %s"),
    BIG_DECIMAL_VALUE_CONDITION_NOT_RESOLVED("BigDecimalValue condition '%s' is not resolved"),
    INTEGER_VALUE_CONDITION_NOT_RESOLVED("IntegerValue condition '%s' is not resolved"),
    STRING_VALUE_CONDITION_NOT_RESOLVED("StringValue condition '%s' is not resolved"),

    INTEGER_VALUE_RESULT_IS_NULL("IntegerValue result is null, but should not"),
    STRING_VALUE_RESULT_IS_NULL("StringValue result is null, but should not"),

    INCORRECT_FIXTURE_PARAMETERS_DATA_TABLE_FORMAT("Incorrect DataTable format for FixtureParameters"),

    ;

    private String key;

    EnvironmentCucumberApiMessages(String key) {
        this.key = key;
    }

    @Override
    public String getErrorMessage() {
        return key;
    }

}

