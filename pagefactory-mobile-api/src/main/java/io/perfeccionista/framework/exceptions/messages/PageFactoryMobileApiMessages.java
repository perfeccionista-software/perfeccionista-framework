package io.perfeccionista.framework.exceptions.messages;

public enum PageFactoryMobileApiMessages implements Messages {

    INCORRECT_MOBILE_LOCATOR_VALUE("Mobile locator search strategy 'SelfNode' can't be used from root element"),

    MOBILE_DEVICE_LOCKED("Mobile device is locked"),
    MOBILE_DEVICE_UNLOCKED("Mobile device is unlocked"),
    MOBILE_DEVICE_ORIENTATION_NOT_MATCH("Mobile device orientation '%s' is not match with expected '%s'"),

    MOBILE_APPLICATION_NOT_INSTALLED("Mobile application '%s' is not installed"),
    MOBILE_APPLICATION_INSTALLED("Mobile application '%s' is installed but should not"),

    MOBILE_DEVICE_CONFIGURATION_NOT_FOUND("Mobile device configuration with name %s is not found"),
    NO_ACTIVE_MOBILE_DEVICE_DISPATCHER_FOUND("No active mobile device dispatcher found"),
    NO_ACTIVE_MOBILE_DEVICE_DISPATCHER_WITH_NAME_FOUND("No active mobile device dispatcher with name %s found"),

    ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE("Element doesn't contain expected value"),
    ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE("Element contains expected value but should not"),

    FILTERED_ELEMENT_SIZE_MATCH("Filtered element size match the expected size but should not"),
    FILTERED_ELEMENT_SIZE_NOT_MATCH("Filtered element size [%d] doesn't match the expected size [%d]"),
    FILTERED_ELEMENT_IS_NOT_SORTED("Filtered element is not sorted"),
    ;

    private String key;

    PageFactoryMobileApiMessages(String key) {
        this.key = key;
    }

    @Override
    public String getErrorMessage() {
        return key;
    }

}
