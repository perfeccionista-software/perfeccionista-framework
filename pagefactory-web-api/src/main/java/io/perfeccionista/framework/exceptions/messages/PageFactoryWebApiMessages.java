package io.perfeccionista.framework.exceptions.messages;

public enum PageFactoryWebApiMessages implements Messages {

    CONTEXT_LIMITER_RETURN_MORE_THAN_ONE_SEARCH_CONTEXT("Context limiter return more than one search context"),
    CONTEXT_LIMITER_RETURN_NO_ONE_SEARCH_CONTEXT("Context limiter return no one search context"),
    WEB_BROWSER_CONFIGURATION_NOT_DECLARED("Web browser configuration with name %s is not declared"),
    CALLED_METHOD_IS_NOT_IMPLEMENTED("Called method %s is not implemented"),
    NO_ACTIVE_WEB_BROWSER_DISPATCHER_FOUND("No active web browser dispatcher found"),
    NO_WEB_BROWSER_DISPATCHER_WITH_NAME_CREATED("No web browser dispatcher with name %s created"),
    NO_EXCEPTION_MAPPER_BY_CLASS_DECLARED("No exception mapper by class %s declared"),
    WEB_BROWSER_SERVICE_CONFIGURATION_NOT_DECLARED("Web browser service configuration not declared"),
    WEB_LOCATOR_PROCESSING_RESULT_NOT_FOUND("Processing result for locator is not found"),
    WEB_LOCATOR_STRATEGY_VALIDATION_FAILED("Locator must contain only one filled search strategy"),
    WEB_PAGE_IMPLEMENTATION_NOT_DECLARED("WebPage implementation is not declared"),
    WEB_BLOCK_IMPLEMENTATION_NOT_DECLARED("WebBlock implementation is not declared"),
    WEB_MAPPED_BLOCK_IMPLEMENTATION_NOT_DECLARED("WebMappedBlock implementation is not declared"),
    WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_DECLARED("WebChildElement implementation for %s is not declared"),
    WEB_CHILD_ELEMENT_IMPLEMENTATION_HAS_UNIMPLEMENTED_METHODS("WebChildElement implementation %s has unimplemented methods"),
    WEB_CHILD_ELEMENT_IMPLEMENTATION_IMPLEMENTS_MORE_THAN_ONE_WEB_CHILD_ELEMENTS("WebChildElement implementation %s implements more than one WebChildElement"),
    ABSTRACT_WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_ALLOWED("Abstract WebChildElement implementation %s is not allowed"),

    WEB_LIST_MAPPED_CLASS_NOT_DECLARED("WebList's mapped class for %s is not declared"),
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
