package io.perfeccionista.framework.exceptions.messages;

public enum UtilsMessages implements Messages {

    CONSTRUCTOR_NOT_FOUND("Constructor for class %s with parameters %s not found"),
    CLIPBOARD_VALUE_NOT_AVAILABLE("Clipboard value not available"),
    FILE_EXISTS("File '%s' exists"),
    FILE_NOT_EXISTS("File '%s' not exists"),
    JSON_OBJECT_PARSE_ERROR("Json object parse error"),
    JSON_STRING_PARSE_ERROR("Json string parse error"),
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
