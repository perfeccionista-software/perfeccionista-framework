package io.perfeccionista.framework.exceptions.messages;

// TODO: Добавить в ошибки имя элемента, переименовать в WEB_ELEMENT..., проверить отладочную инфу
//  Поправить все типы аттачментов (перевести максимально в json)
//  TODO: Переделать все аттачменты с элементами через ElementAttachment

public enum PageFactoryWebApiMessages implements Messages {

    // Execution operation exceptions
    JS_OPERATION_RESULT_HAS_NO_VALUE("Operation result has no returned value"),
    JS_OPERATION_RESULT_HAS_MORE_THAN_ONE_VALUE("Operation result has more than one returned value"),

    // Element construction exceptions
    ELEMENT_NOT_FOUND("Element %s is not found"),
    ELEMENT_COMPONENT_NOT_FOUND("Element component %s is not found"),
    ELEMENT_ACTION_NOT_FOUND("Element action %s is not found"),
    ELEMENT_JS_OPERATION_ACTION_NOT_FOUND("Element action with js operation %s is not found"),
    ELEMENT_INTERACTION_NOT_FOUND("Element interaction %s is not found"),
    ELEMENT_LOCATOR_NOT_FOUND("Element locator %s is not found"),

    ELEMENT_LOCATOR_FOR_COLUMN_HEADER_NOT_FOUND("Element locator for column header %s is not found"),
    ELEMENT_LOCATOR_FOR_COLUMN_BODY_NOT_FOUND("Element locator for column body %s is not found"),
    ELEMENT_LOCATOR_FOR_COLUMN_FOOTER_NOT_FOUND("Element locator for column footer %s is not found"),

    TABLE_MAPPED_BLOCK_FOR_COLUMN_HEADER_NOT_FOUND("Table mapped block for column header %s is not found"),
    TABLE_MAPPED_BLOCK_FOR_COLUMN_BODY_NOT_FOUND("Table mapped block for column body %s is not found"),
    TABLE_MAPPED_BLOCK_FOR_COLUMN_FOOTER_NOT_FOUND("Table mapped block for column footer %s is not found"),

    CANT_CAST_ELEMENT("Element can't be cast to %s"),
    INCORRECT_PARENT_ELEMENT("Incorrect parent element type. %s can't have child elements"),
    TABLE_COLUMN_LOCATOR_NOT_FOUND("Table column locator for column with name %s is not found for element %s"),


    ELEMENT_TABLE_COLUMN_LOCATOR_NOT_DECLARED("Element column locator for column %s is not declared"),
    ELEMENT_PROPERTY_NOT_FOUND("Element property %s is not found"),


    WEB_BROWSER_CONFIGURATION_NOT_FOUND("Web browser configuration with name %s is not found"),
    WEB_BROWSER_SERVICE_CONFIGURATION_NOT_DECLARED("Web browser service configuration not declared"),
    WEB_BROWSER_EXCEPTION_MAPPER_BY_CLASS_NOT_FOUND("Exception mapper for class %s is not found"),
    NO_ACTIVE_WEB_BROWSER_DISPATCHER_FOUND("No active web browser dispatcher found"),
    NO_ACTIVE_WEB_BROWSER_DISPATCHER_WITH_NAME_FOUND("No active web browser dispatcher with name %s found"),

    WEB_LOCATOR_PROCESSING_RESULT_NOT_FOUND("Processing result for locator is not found"),
    WEB_LOCATOR_HASH_NOT_CALCULATED("Hash for locator is not calculated"),
    WEB_PAGE_IMPLEMENTATION_NOT_DECLARED("WebPage implementation is not declared"),
    WEB_RADIO_BUTTON_IMPLEMENTATION_NOT_DECLARED("WebRadioButton implementation is not declared"),
    WEB_BLOCK_IMPLEMENTATION_NOT_DECLARED("WebBlock implementation is not declared"),
    WEB_MAPPED_BLOCK_NOT_FOUND("WebMappedBlock implementation for %s is not found"),
    WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_FOUND("WebChildElement implementation for %s is not found"),

    // Asserts
    COMPONENT_NOT_PRESENT("Component %s of element %s is not present in DOM"),
    COMPONENT_IS_PRESENT("Component %s of element %s is present in DOM but should not"),
    COMPONENT_NOT_DISPLAYED("Component %s of element %s is not displayed"),
    COMPONENT_IS_DISPLAYED("Component %s of element %s is displayed but should not"),
    ELEMENT_IS_PRESENT("Element %s is present in DOM but should not"),
    ELEMENT_NOT_PRESENT("Element %s is not present in DOM"),
    ELEMENT_IS_DISABLED("Element %s is disabled"),
    ELEMENT_IS_CLOSED("Element %s is closed"),
    ELEMENT_IS_OPEN("Element %s is open"),
    ELEMENT_IS_ENABLED("Element %s is enabled"),
    ELEMENT_IS_DISPLAYED("Element %s is displayed but should not"),
    ELEMENT_IS_SELECTED("Element %s is selected"),
    ELEMENT_NOT_DISPLAYED("Element %s is not displayed"),
    ELEMENT_NOT_SELECTED("Element %s is not selected"),

    ELEMENT_PROPERTY_VALUE_IS_NULL("Element property '%s' for element '%s' is null"),

    ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_VALUE("Element property %s doesn't contain expected value"),
    ELEMENT_PROPERTY_DOES_NOT_CONTAIN_EXPECTED_TEXT("Element property %s doesn't contain expected text"),
    ELEMENT_PROPERTY_CONTAINS_EXPECTED_VALUE("Element property %s contains expected value but should not"),
    ELEMENT_PROPERTY_CONTAINS_EXPECTED_TEXT("Element property %s contains expected text but should not"),

    ELEMENT_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE("Element doesn't contain expected value"),
    ELEMENT_TEXT_CONTAINS_EXPECTED_VALUE("Element contains expected value but should not"),

    ELEMENT_LABEL_DOES_NOT_CONTAIN_EXPECTED_VALUE("Element's label doesn't contains expected value"),
    ELEMENT_LABEL_CONTAINS_EXPECTED_VALUE("Element's label contains expected value but should not"),

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
    ELEMENT_NOT_ON_THE_SCREEN("Element %s is not on the screen"),
    ELEMENT_ON_THE_SCREEN("Element %s is on the screen but should not"),

    FILTERED_ELEMENT_SIZE_MATCH("Filtered element size match the expected size but should not"),
    FILTERED_ELEMENT_SIZE_NOT_MATCH("Filtered element size doesn't match the expected size"),
    FILTERED_ELEMENT_DOES_NOT_CONTAIN_EXPECTED_RESULT("Filtered element does not contain expected result"),
    FILTERED_ELEMENT_CONTAINS_INDEX("Filtered element contains expected index but should not"),
    FILTERED_ELEMENT_DOES_NOT_CONTAIN_INDEX("Filtered element does not contain expected index"),
    FILTERED_ELEMENT_CONTAINS_NULL_RESULT("Filtered element contains null result for index %s but should not"),
    FILTERED_ELEMENT_CONTAINS_NON_NULL_RESULT("Filtered element contains non null result for index %s but should not"),
    FILTERED_ELEMENT_IS_NOT_SORTED("Filtered element is not sorted"),



    PAGE_NAME_DUPLICATE("Page name %s used for classes %s and %s"),
    PAGE_NOT_FOUND_BY_NAME("Page with name %s is not found"),
    PAGE_NOT_FOUND_BY_CLASS("Page with class %s is not found"),
    REQUESTED_NODE_HASH_NOT_CALCULATED("Requested node hash not calculated"),
    SCREENSHOT_MIME_TYPE_NOT_SUPPORTED("Screenshot mime type %s is not supported"),

    WEB_ELEMENT_NAME_NOT_FOUND("WebElement doesn't contain name %s"),
    WEB_ELEMENT_METHOD_NOT_IMPLEMENTED("WebElement method %s not implemented"),

    CONTEXT_LIMITER_RETURN_MORE_THAN_ONE_SEARCH_CONTEXT("Context limiter return more than one search context"),
    CONTEXT_LIMITER_RETURN_NO_ONE_SEARCH_CONTEXT("Context limiter return no one search context"),

    WEB_LOCATOR_STRATEGY_VALIDATION_FAILED("Locator must contain only one filled search strategy"),
    WEB_CHILD_ELEMENT_IMPLEMENTATION_HAS_UNIMPLEMENTED_METHODS("WebChildElement implementation %s has unimplemented methods"),
    WEB_CHILD_ELEMENT_IMPLEMENTATION_IMPLEMENTS_MORE_THAN_ONE_WEB_CHILD_ELEMENTS("WebChildElement implementation %s implements more than one WebChildElement"),
    ABSTRACT_WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_ALLOWED("Abstract WebChildElement implementation %s is not allowed"),

    WEB_LIST_MAPPED_CLASS_NOT_DECLARED("WebList's mapped class for %s is not declared"),
    WEB_RADIO_GROUP_MAPPED_CLASS_NOT_DECLARED("WebRadioGroup's mapped class for %s is not declared"),
    WEB_TEXT_LIST_MAPPED_CLASS_NOT_DECLARED("WebTextList's mapped class for %s is not declared"),

    WEB_MAPPED_BLOCK_INCORRECT_TYPE("Mapped block must inherit %s"),

    MAPPED_WEB_BLOCK_CALLED_BY_METHOD("Mapped WebBlock instance hasn't method. It's declared by mapped class"),
    ;

    private String key;

    PageFactoryWebApiMessages(String key) {
        this.key = key;
    }

    @Override
    public String getErrorMessage() {
        return key;
    }

}
