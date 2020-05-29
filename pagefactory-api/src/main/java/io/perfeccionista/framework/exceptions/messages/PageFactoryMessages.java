package io.perfeccionista.framework.exceptions.messages;

public enum PageFactoryMessages implements Messages {

    CANT_CAST_ELEMENT("Element can't be cast to %s"),
    ELEMENT_COMPONENT_NOT_DECLARED("Element component %s is not declared for element"),
    ELEMENT_PROPERTY_NOT_DECLARED("Element property %s is not declared for element"),
    EMPTY_ELEMENT_PATH("Element path is empty"),
    INCORRECT_PARENT_ELEMENT("Incorrect parent element type. %s can't have child elements"),
    LOCATOR_NOT_DECLARED("Locator %s is not declared for element"),
    METHOD_NOT_DECLARED("Method with name %s is not declared for element"),
    PAGE_NAME_DUPLICATE("Page name %s used for classes %s and %s"),
    REQUESTED_NODE_HASH_NOT_CALCULATED("Requested node hash not calculated"),
    SCREENSHOT_MIME_TYPE_NOT_SUPPORTED("Screenshot mime type %s is not supported"),
    MULTIPLE_RESULT_HAS_MORE_THAN_ONE_VALUE("MultipleResult has more than one value and can't be converted to SingleResult")
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
