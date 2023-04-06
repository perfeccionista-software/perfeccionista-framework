package io.perfeccionista.framework.exceptions.messages;

// TODO: Добавить в ошибки имя элемента, переименовать в WEB_ELEMENT..., проверить отладочную инфу
//  Поправить все типы аттачментов (перевести максимально в json)
//  TODO: Переделать все аттачменты с элементами через ElementAttachment

public enum PageFactoryWebApiMessages implements Messages {

    // Execution operation exceptions
    NO_FILES_TO_UPLOAD("No files have been declared for upload"),

    // Element construction exceptions
    ELEMENT_COMPONENT_NOT_FOUND("Element component %s is not found"),
    ELEMENT_JS_OPERATION_ACTION_NOT_FOUND("Element action with js operation %s is not found"),





    TABLE_COLUMN_LOCATOR_NOT_FOUND("Table column locator for column with name %s is not found for element %s"),


    ELEMENT_TABLE_COLUMN_LOCATOR_NOT_DECLARED("Element column locator for column %s is not declared"),

    // WEB BROWSER

    WEB_BROWSER_DIMENSIONS_FORMAT_UNSUPPORTED("Web browser's dimensions format is unsupported '%s'"),

    WEB_BROWSER_HAS_NO_TAB_WITH_TITLE("Web browser has no tab with title '%s'"),
    WEB_BROWSER_HAS_NO_TAB_WITH_URL("Web browser has no tab with url '%s'"),
    WEB_BROWSER_HAS_NO_EXPECTED_TABS_COUNT("Web browser has a different number of tabs than expected"),
    WEB_BROWSER_CONFIGURATION_NOT_FOUND("Web browser configuration with name %s is not found"),
    WEB_BROWSER_SERVICE_CONFIGURATION_NOT_DECLARED("Web browser service configuration not declared"),
    NO_ACTIVE_WEB_BROWSER_DISPATCHER_FOUND("No active web browser dispatcher found"),
    NO_ACTIVE_WEB_BROWSER_DISPATCHER_WITH_NAME_FOUND("No active web browser dispatcher with name %s found"),

    WEB_BROWSER_ACTIVE_TAB_TITLE_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE("Web browser's active tab title does not contain expected value"),
    WEB_BROWSER_ACTIVE_TAB_TITLE_TEXT_CONTAINS_EXPECTED_VALUE("Web browser's active tab title contains expected value but should not"),
    WEB_BROWSER_ACTIVE_TAB_URL_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE("Web browser's active tab url does not contain expected value"),
    WEB_BROWSER_ACTIVE_TAB_URL_TEXT_CONTAINS_EXPECTED_VALUE("Web browser's active tab url contains expected value but should not"),

    WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_TITLE("Web browser does not contain tab with expected title"),
    WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_TITLE("Web browser contains tab with expected title but should not"),
    WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_URL("Web browser does not contain tab with expected url"),
    WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_URL("Web browser contains tab with expected url but should not"),


    WEB_BROWSER_TAB_COUNT_DOES_NOT_CONTAIN_EXPECTED_VALUE("Web browser does not contain expected count of tabs"),
    WEB_BROWSER_TAB_COUNT_CONTAINS_EXPECTED_VALUE("Web browser contains expected count of tabs but should not"),





    WEB_PAGE_IMPLEMENTATION_NOT_DECLARED("WebPage implementation is not declared"),
    WEB_RADIO_BUTTON_IMPLEMENTATION_NOT_DECLARED("WebRadioButton implementation is not declared"),
    WEB_BLOCK_IMPLEMENTATION_NOT_DECLARED("WebBlock implementation is not declared"),

    // Asserts



    ELEMENT_IS_DISABLED("Element %s is disabled"),
    ELEMENT_IS_CLOSED("Element %s is closed"),
    ELEMENT_IS_OPEN("Element %s is open"),
    ELEMENT_IS_ENABLED("Element %s is enabled"),
    ELEMENT_IS_SELECTED("Element %s is selected"),
    ELEMENT_NOT_SELECTED("Element %s is not selected"),

    ELEMENT_ATTRIBUTE_VALUE_IS_MISSING("Element attribute '%s' for element '%s' for component '%s' is missing"),
    ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_VALUE("Element attribute '%s' for component '%s' doesn't contain expected value"),
    ELEMENT_ATTRIBUTE_DOES_NOT_CONTAIN_EXPECTED_TEXT("Element attribute '%s' for component '%s' doesn't contain expected text"),
    ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_VALUE("Element attribute '%s' for component '%s' contains expected value but should not"),
    ELEMENT_ATTRIBUTE_CONTAINS_EXPECTED_TEXT("Element attribute '%s' for component '%s' contains expected text but should not"),

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
    ELEMENT_ON_THE_SCREEN("Element %s is on the screen but should not"),

    FILTERED_ELEMENT_SIZE_MATCH("Filtered element size match the expected size but should not"),
    FILTERED_ELEMENT_SIZE_NOT_MATCH("Filtered element size doesn't match the expected size"),
    FILTERED_ELEMENT_DOES_NOT_CONTAIN_EXPECTED_RESULT("Filtered element does not contain expected result"),
    FILTERED_ELEMENT_CONTAINS_INDEX("Filtered element contains expected index but should not"),
    FILTERED_ELEMENT_DOES_NOT_CONTAIN_INDEX("Filtered element does not contain expected index"),
    FILTERED_ELEMENT_IS_NOT_SORTED("Filtered element is not sorted"),



    REQUESTED_NODE_HASH_NOT_CALCULATED("Requested node hash not calculated"),

    COOKIES_DOMAIN_CONTAINS_PORT("Cookie's domain contains a port but should not"),
    COOKIES_NAME_EMPTY("Cookie's name is empty but should not"),
    COOKIES_NAME_INCORRECT("Cookie's name contains ';' but should not"),


    WEB_LIST_MAPPED_CLASS_NOT_DECLARED("WebList's mapped class for %s is not declared"),
    WEB_RADIO_GROUP_MAPPED_CLASS_NOT_DECLARED("WebRadioGroup's mapped class for %s is not declared"),
    WEB_TEXT_LIST_MAPPED_CLASS_NOT_DECLARED("WebTextList's mapped class for %s is not declared"),



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
