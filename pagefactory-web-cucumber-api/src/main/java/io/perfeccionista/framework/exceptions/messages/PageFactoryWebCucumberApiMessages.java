package io.perfeccionista.framework.exceptions.messages;

// TODO: Добавить в ошибки имя элемента, переименовать в WEB_ELEMENT..., проверить отладочную инфу
//  Поправить все типы аттачментов (перевести максимально в json)
//  TODO: Переделать все аттачменты с элементами через ElementAttachment

public enum PageFactoryWebCucumberApiMessages implements Messages {

    // Execution operation exceptions
    INCORRECT_WEB_LIST_FILTER_BUILDER_DATA_TABLE_FORMAT("Incorrect DataTable format for WebListFilterBuilder"),
    INCORRECT_WEB_RADIO_GROUP_FILTER_BUILDER_DATA_TABLE_FORMAT("Incorrect DataTable format for WebRadioGroupFilterBuilder"),
    INCORRECT_WEB_TABLE_FILTER_BUILDER_DATA_TABLE_FORMAT("Incorrect DataTable format for WebTableFilterBuilder"),
    INCORRECT_WEB_TEXT_LIST_FILTER_BUILDER_DATA_TABLE_FORMAT("Incorrect DataTable format for WebTextListFilterBuilder"),
    INCORRECT_WEB_TEXT_TABLE_FILTER_BUILDER_DATA_TABLE_FORMAT("Incorrect DataTable format for WebTextTableFilterBuilder"),
    LOCATION_FORMAT_NOT_RESOLVED("Incorrect Location format %s"),
    INTEGER_VALUE_RESULT_IS_NULL("IntegerValue result is null, but should not"),
    STRING_VALUE_RESULT_IS_NULL("StringValue result is null, but should not"),

    WEB_COLOR_FORMAT_NOT_RESOLVED("WebColor format '%s' is not resolved"),
    DIMENSIONS_FORMAT_NOT_RESOLVED("Dimensions format '%s' is not resolved"),
    BIG_DECIMAL_VALUE_CONDITION_NOT_RESOLVED("BigDecimalValue condition '%s' is not resolved"),
    INTEGER_VALUE_CONDITION_NOT_RESOLVED("IntegerValue condition '%s' is not resolved"),
    STRING_VALUE_CONDITION_NOT_RESOLVED("StringValue condition '%s' is not resolved"),
    WEB_FILTER_CONDITION_NOT_RESOLVED("WebFilter condition '%s' for '%s' is not resolved"),
    WEB_FILTER_OPERATOR_NOT_RESOLVED("WebFilter operator '%s' is not resolved"),
    ;

    private String key;

    PageFactoryWebCucumberApiMessages(String key) {
        this.key = key;
    }

    @Override
    public String getErrorMessage() {
        return key;
    }

}
