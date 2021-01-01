package io.perfeccionista.framework.exceptions.messages;

public enum UtilsMessages implements Messages {

    CANT_CAST_OBJECT("Class %s can't be casted to type %s"),
    CANT_LOAD_CLASS("Class %s is not found"),
    CONSTRUCTOR_NOT_FOUND("Constructor for class %s with parameters %s not found"),
    CLIPBOARD_VALUE_NOT_AVAILABLE("Clipboard value not available"),
    EMPTY_PATH("Path is empty"),
    FIELD_NOT_FOUND("Class %s does not contain field with name %s"),
    FIELD_READING_ERROR("Can't read field with name %s"),
    FIELD_WRITING_ERROR("Can't write field with name %s"),
    FILE_EXISTS("File '%s' exists"),
    FILE_NOT_EXIST("File '%s' is not exist"),
    METHOD_NOT_FOUND("Method with name %s is not declared for element"),
    REQUIRED_ARGUMENT_NOT_FOUND("Required argument is not found"),
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
