package io.perfeccionista.framework.exceptions.messages;

public enum PageFactoryMessages implements Messages {

    CANT_CAST_ELEMENT("Element can't be cast to %s"),
    ELEMENT_STATE_NOT_DECLARED("Element state %s is not declared for element"),
    ELEMENT_PROPERTY_NOT_DECLARED("Element property %s is not declared for element"),
    EMPTY_ELEMENT_PATH("Element path is empty"),
    INCORRECT_PARENT_ELEMENT("Incorrect parent element type. %s can't have child elements"),
    LOCATOR_NOT_DECLARED("Locator %s is not declared for element"),
    METHOD_NOT_DECLARED("Method with name %s is not declared for element"),
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
