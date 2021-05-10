package io.perfeccionista.framework.exceptions.messages;

public enum PageFactoryApiMessages implements Messages {

    // Factory
    ACTIVE_PAGE_NOT_INITIALIZED("Active page is not initialized"),
    ELEMENT_PATH_NOT_RESOLVED("Element path '%s' is not resolved. Incorrect parent element type. Element '%s' cannot have child elements"),
    ELEMENT_METHOD_NOT_IMPLEMENTED("Element method %s not implemented"),
    LOCATOR_STRATEGY_VALIDATION_FAILED("Locator must contain only one filled search strategy"),

    OPERATION_RESULT_HAS_NO_VALUE("Operation result has no returned value"),
    OPERATION_RESULT_HAS_MORE_THAN_ONE_VALUE("Operation result has more than one returned value"),
    LOCATOR_PROCESSING_RESULT_NOT_FOUND("Processing result for locator is not found"),
    LOCATOR_HASH_NOT_CALCULATED("Locator hash is not calculated"),
    LOCATOR_STRATEGY_UNSUPPORTED("Locator strategy '%s' is not supported for driver '%s'"),


//    ELEMENT_IMPLEMENTATION_HAS_UNIMPLEMENTED_METHODS("Element implementation '%s' has unimplemented methods"),
//    ELEMENT_IMPLEMENTATION_CANT_IMPLEMENTS_MORE_THAN_ONE_OTHER_ELEMENTS("Element implementation '%s' can implements only one other Element"),
    ELEMENT_IMPLEMENTATION_CANT_BE_ABSTRACT("Element implementation '%s' can't be abstract"),
    ELEMENT_IMPLEMENTATION_NOT_FOUND("Element implementation for '%s' is not found"),

    STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER("Wrong number of arguments. Expected number '%s' but actual '%s'"),

    // Mapping
    MAPPED_BLOCK_IMPLEMENTATION_INCORRECT_TYPE("Mapped block implementation must inherit class '%s'"),
    MAPPED_BLOCK_IMPLEMENTATION_NOT_FOUND("Mapped block implementation for '%s' is not found"),

    PAGE_NAME_DUPLICATE("Page name '%s' used for classes '%s' and '%s'"),
    PAGE_NOT_FOUND_BY_NAME("Page with name '%s' is not found"),
    PAGE_NOT_FOUND_BY_CLASS("Page with class '%s' is not found"),

    TABLE_ELEMENT_LOCATOR_FOR_COLUMN_HEADER_NOT_FOUND("Table element locator for column header %s is not found"),
    TABLE_ELEMENT_LOCATOR_FOR_COLUMN_BODY_NOT_FOUND("Table element locator for column body %s is not found"),
    TABLE_ELEMENT_LOCATOR_FOR_COLUMN_FOOTER_NOT_FOUND("Table element locator for column footer %s is not found"),
    TABLE_MAPPED_BLOCK_FOR_COLUMN_HEADER_NOT_FOUND("Table mapped block for column header '%s' is not found"),
    TABLE_MAPPED_BLOCK_FOR_COLUMN_BODY_NOT_FOUND("Table mapped block for column body '%s' is not found"),
    TABLE_MAPPED_BLOCK_FOR_COLUMN_FOOTER_NOT_FOUND("Table mapped block for column footer '%s' is not found"),


    // Elements
    COMPONENT_NOT_PRESENT("Component '%s' of element '%s' is not present in DOM"),
    COMPONENT_IS_PRESENT("Component '%s' of element '%s' is present in DOM but should not"),
    COMPONENT_NOT_DISPLAYED("Component '%s' of element '%s' is not displayed"),
    COMPONENT_IS_DISPLAYED("Component '%s' of element '%s' is displayed but should not"),
    ELEMENT_NOT_INTRACTABLE("Element '%s' is not intractable"),
    ELEMENT_IS_PRESENT("Element '%s' is present in DOM but should not"),
    ELEMENT_NOT_PRESENT("Element '%s' is not present in DOM"),
    ELEMENT_IS_DISPLAYED("Element '%s' is displayed but should not"),
    ELEMENT_NOT_DISPLAYED("Element '%s' is not displayed"),
    ELEMENT_IS_SELECTED("Element '%s' is selected but should not"),
    ELEMENT_NOT_SELECTED("Element '%s' is not selected"),
    ELEMENT_NOT_FOUND("Element '%s' is not found"),
    ELEMENT_LOCATOR_NOT_FOUND("Element locator '%s' is not found"),
    ELEMENT_PROPERTY_NOT_FOUND("Element property '%s' is not found"),
    ELEMENT_STATE_NOT_FOUND("Element state '%s' is not found"),
    ENDPOINT_HANDLER_NOT_FOUND("Element action '%s' is not found"),
    ELEMENT_INTERACTION_NOT_FOUND("Element interaction '%s' is not found"),
    ELEMENT_NAME_NOT_FOUND("Element does not contain name '%s'"),
    ELEMENT_CANNOT_BE_CASTED("Element '%s' cannot be casted to %s"),
    ELEMENT_BOUNDS_CANT_BE_PARSED("Element bounds '%s' cannot be parsed"),
    ELEMENT_DOES_NOT_HAVE_EXPECTED_STATE("Element '%s' does not have expected state '%s'"),
    ELEMENT_HAS_UNEXPECTED_STATE("Element '%s' has unexpected state '%s'"),
    ELEMENT_NOT_ON_THE_SCREEN("Element '%s' is not on the screen"),
    ELEMENT_IS_STALE("Element '%s' is no longer exists in the hierarchy"),

    SCREENSHOT_MIME_TYPE_NOT_SUPPORTED("Screenshot mime type %s is not supported"),



    // Context
    SEARCH_CONTEXT_EXPECTED_SIZE_DOES_NOT_MATCH("SearchContext actual size does not match with expected size"),


    // Result
    SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE("SingleResult has more than one value after filter applying"),
    SINGLE_RESULT_HAS_NO_VALUE("SingleResult has no value after filter applying"),
    FILTERED_ELEMENT_CONTAINS_NULL_RESULT("Filtered element contains null result for index '%s' but should not"),
    FILTERED_ELEMENT_CONTAINS_NON_NULL_RESULT("Filtered element contains non null result for index '%s' but should not"),


    ;

    private String key;

    PageFactoryApiMessages(String key) {
        this.key = key;
    }

    @Override
    public String getErrorMessage() {
        return key;
    }

}
