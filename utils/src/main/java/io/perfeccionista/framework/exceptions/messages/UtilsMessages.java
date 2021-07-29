package io.perfeccionista.framework.exceptions.messages;

public enum UtilsMessages implements Messages {

    CANT_CAST_OBJECT("Class %s can't be casted to type %s"),
    CANT_CREATE_OBJECT("Class %s can't be instantiated with args %s"),
    CANT_CREATE_OBJECT_WITH_CONSTRUCTOR("Constructor %s can't be instantiated with args %s"),
    CANT_LOAD_CLASS("Class %s is not found"),
    CANT_READ_FILE("File reading failed by path %s"),
    CANT_WRITE_FILE("File writing failed by path %s"),
    CANT_CREATE_DIRECTORY("Directory '%s' creating failed"),
    CANT_READ_URL("Url reading failed by path %s"),
    TARGET_IS_NOT_A_FILE("Target %s is not a file"),
    COMMAND_EXECUTION_FAILED_WITH_CODE("Command execution failed with code '%s'"),
    COMMAND_EXECUTION_FAILED("Command execution failed"),
    CONSTRUCTOR_NOT_FOUND("Constructor for class %s with parameters %s not found"),
    CLIPBOARD_VALUE_NOT_AVAILABLE("Clipboard value not available"),
    EMPTY_PATH("Path is empty"),
    EMPTY_ATTACHMENT_ENTRY("Content of the attachment entry is empty"),
    EXCEPTION_MAPPER_FOR_CLASS_NOT_FOUND("Exception mapper for class %s is not found"),
    FIELD_NOT_FOUND("Class %s does not contain field with name %s"),
    FIELD_READING_ERROR("Can't read field with name %s"),
    FIELD_WRITING_ERROR("Can't write field with name %s"),
    FILE_EXISTS("File '%s' exists"),
    FILE_NOT_EXIST("File '%s' is not exist"),
    RESOURCE_NOT_EXIST("File '%s' is not exist in classpath"),
    INCORRECT_URL("Incorrect URL '%s'"),
    INCORRECT_FILE_NAME("File name '%s' contains null byte symbol(s)"),
    METHOD_NOT_FOUND("Method with name %s is not declared for element"),
    METHOD_INVOCATION_FAILED("Method %s invocation failed"),
    REQUIRED_ARGUMENT_NOT_FOUND("Required argument is not found"),
    RESPONSE_FORMAT_NOT_VALID("Response format is not valid"),
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
