package io.perfeccionista.framework.exceptions.messages;

public enum PageFactoryMessages implements Messages {

    // Declared checks
    ELEMENT_NOT_DECLARED("Element %s is not declared"),
    ELEMENT_ACTION_NOT_DECLARED("Element action %s is not declared"),
    ELEMENT_COMPONENT_NOT_DECLARED("Element component %s is not declared"),
    ELEMENT_INTERACTION_NOT_DECLARED("Element interaction %s is not declared"),
    ELEMENT_LOCATOR_NOT_DECLARED("Element locator %s is not declared"),
    ELEMENT_PROPERTY_NOT_DECLARED("Element property %s is not declared"),
    // Runtime checks
    ELEMENT_IS_PRESENT("Element %s is present in DOM"),
    ELEMENT_NOT_PRESENT("Element %s is not present in DOM"),
    ELEMENT_IS_DISABLED("Element %s is disabled"),
    ELEMENT_IS_ENABLED("Element %s is enabled"),
    ELEMENT_IS_DISPLAYED("Element %s is displayed"),
    ELEMENT_NOT_DISPLAYED("Element %s is not displayed"),
    // TODO: Добавить в ошибки имя элемента
    ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_VALUE("Element property %s doesn't contain expected value"),
    ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE("Element property %s contains expected value but should not"),

    ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE("Element %s doesn't contains expected value"),
    ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE("Element %s contains expected value but should not"),

    ELEMENT_LABEL_DOES_NOT_CONTAIN_EXPECTED_VALUE("Element's label of %s doesn't contains expected value"),
    ELEMENT_LABEL_CONTAINS_EXPECTED_VALUE("Element's label of %s contains expected value but should not"),

    ELEMENT_COLOR_IS_NOT_EQUAL_EXPECTED_COLOR("Element color for component %s is not equal to expected color"),
    ELEMENT_COLOR_IS_EQUAL_EXPECTED_COLOR("Element color for component %s is equal to expected color but should not"),
    ELEMENT_DIMENSIONS_ARE_NOT_EQUAL_EXPECTED_DIMENSIONS("Element dimensions for component %s are not equal to expected dimensions"),
    ELEMENT_DIMENSIONS_ARE_EQUAL_EXPECTED_DIMENSIONS("Element dimensions for component %s are equal to expected dimensions but should not"),
    ELEMENT_LOCATION_IS_NOT_EQUAL_EXPECTED_LOCATION("Element location for component %s is not equal to expected location"),
    ELEMENT_LOCATION_IS_EQUAL_EXPECTED_LOCATION("Element location for component %s is equal to expected location but should not"),
    ELEMENT_SCREENSHOT_IS_NOT_EQUAL_EXPECTED_SCREENSHOT("Element screenshot for component %s is not equal to expected screenshot"),
    ELEMENT_SCREENSHOT_IS_EQUAL_EXPECTED_SCREENSHOT("Element screenshot for component %s is equal to expected screenshot but should not"),
    ELEMENT_SIZE_NOT_MATCH("Element size doesn't match the expected size"),
    ELEMENT_NOT_IN_FOCUS("Element %s is not in focus"),
    ELEMENT_IN_FOCUS("Element %s is in focus but should not"),


    CANT_CAST_ELEMENT("Element can't be cast to %s"),
    EMPTY_ELEMENT_PATH("Element path is empty"),
    INCORRECT_PARENT_ELEMENT("Incorrect parent element type. %s can't have child elements"),
    METHOD_NOT_DECLARED("Method with name %s is not declared for element"),
    MULTIPLE_RESULT_HAS_MORE_THAN_ONE_VALUE("MultipleResult has more than one value and can't be converted to SingleResult"),
    PAGE_NAME_DUPLICATE("Page name %s used for classes %s and %s"),
    PAGE_NOT_FOUND_BY_NAME("Page with name %s is not found"),
    PAGE_NOT_FOUND_BY_CLASS("Page with class %s is not found"),
    REQUESTED_NODE_HASH_NOT_CALCULATED("Requested node hash not calculated"),
    SCREENSHOT_MIME_TYPE_NOT_SUPPORTED("Screenshot mime type %s is not supported"),
    ;

    private String key;

    PageFactoryMessages(String key) {
        this.key = key;
    }

    @Override
    public String getErrorMessage() {
        return key;
    }

}
