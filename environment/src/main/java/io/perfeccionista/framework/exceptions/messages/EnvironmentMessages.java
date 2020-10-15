package io.perfeccionista.framework.exceptions.messages;

/**
 * TODO: JavaDoc
 */
public enum EnvironmentMessages implements Messages {

    SERVICE_REGISTER_BY_CLASS_DUPLICATE("AdditionProvider %s already registered by class"),
    SERVICE_REGISTER_CLASS_CAST("AdditionProvider %s must be instance of %s"),

    DATA_CONVERTER_REGISTER_BY_CLASS_DUPLICATE("DataConverter with class %s already registered"),
    DATA_CONVERTER_REGISTER_BY_NAME_DUPLICATE("DataConverter with name %s already registered"),
    DATA_SOURCE_REGISTER_BY_CLASS_DUPLICATE("DataSource with class %s already registered"),
    DATA_SOURCE_REGISTER_BY_NAME_DUPLICATE("DataSource with name %s already registered"),

    CREATE_REPEAT_POLICY_INSTANCE_EXCEPTION("Can't create instance of RepeatPolicy class %s"),

    CHECK_ARGUMENT_MUST_NOT_BE_NULL("%s must not be null"),
    CHECK_ARRAY_MUST_NOT_BE_EMPTY("Array '%s' must not be empty"),
    CHECK_CONFIGURATION_NOT_VALID("Configuration '%s' is not valid for '%s'"),


    DEFAULT_DATA_SOURCE_NOT_DECLARED("Default DataSource is not configured for DataSourceService"),
    ENVIRONMENT_NOT_DECLARED("Environment is not configured for test class and test method"),
    ENVIRONMENT_NOT_INITIALIZED("Environment instance is not initialized for current thread"),

    SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE("WebSingleResult has more than one value"),
    SINGLE_RESULT_HAS_NO_VALUE("WebSingleResult has no value"),
    NUMBER_VALUE_TO_BIG_DECIMAL_PARSING_FAILED("String '%s' parsing to BigDecimal failed"),
    NUMBER_VALUE_TO_DOUBLE_PARSING_FAILED("String '%s' parsing to Double failed"),
    NUMBER_VALUE_TO_INTEGER_PARSING_FAILED("String '%s' parsing to Integer failed"),
    STRING_VALUE_DATA_CONVERTER_NAME_NOT_FOUND("StringValue '%s' processing failed. DataConverter name not found"),
    STRING_VALUE_DATA_CONVERTER_INCORRECT_KEY_TYPE("StringValue '%s' processing failed. Incorrect key type for DataConverter"),
    STRING_VALUE_DATA_SOURCE_INCORRECT_KEY_TYPE("StringValue '%s' processing failed. Incorrect key type for DataSource"),
    STRING_VALUE_PARSING_FAILED("StringValue '%s' parsing failed. Context of token is not closed"),
    STRING_VALUE_PROCESSING_FAILED("StringValue '%s' processing failed. Incorrect token"),
    STRING_VALUE_COMPOUND_DATA_SOURCE_KEY("StringValue '%s' processing failed. DataSource key must contain either a string or an object"),
    STRING_VALUE_COMPOUND_DATA_CONVERTER_KEY("StringValue '%s' processing failed. DataConverter key must contain either a string or an object"),


    // Not found exceptions
    ACTION_RUNNER_IMPLEMENTATION_NOT_FOUND("Action runner implementation for wrapper %s is not declared in configuration %s"),
    DATA_CONVERTER_NOT_FOUND_BY_CLASS("DataConverter not found by class %s"),
    DATA_CONVERTER_NOT_FOUND_BY_NAME("DataConverter not found by name %s"),
    DATA_CONVERTER_VALUE_NOT_FOUND("DataConverter %s can't convert value by key %s and format %s"),
    DATA_SOURCE_NOT_FOUND_BY_CLASS("DataSource not found by class %s"),
    DATA_SOURCE_NOT_FOUND_BY_NAME("DataSource not found by name %s"),
    DATA_SOURCE_VALUE_NOT_FOUND("DataSource %s hasn't value by key %s"),
    SERVICE_NOT_FOUND("Service %s not registered in environment"),
    TIMEOUTS_IMPLEMENTATION_NOT_FOUND("Timeouts implementation for class %s is not declared in configuration %s"),
    UNEXPECTED_TEST_CLASS_NOT_FOUND("Unexpected exception when processing test class in PerfeccionistaExtension"),
    UNEXPECTED_TEST_METHOD_NOT_FOUND("Unexpected exception when processing test method in PerfeccionistaExtension")
    ;


    private String key;

    EnvironmentMessages(String key) {
        this.key = key;
    }

    @Override
    public String getErrorMessage() {
        return key;
    }

}
