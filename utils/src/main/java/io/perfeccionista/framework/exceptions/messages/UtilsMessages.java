package io.perfeccionista.framework.exceptions.messages;

public enum UtilsMessages implements Messages {

    CONSTRUCTOR_NOT_FOUND("Constructor for class %s with parameters %s not found"),
    ;

    private String key;

    UtilsMessages(String key) {
        this.key = key;
    }

    @Override
    public String getErrorMessage() {
        return key;
    }

}
